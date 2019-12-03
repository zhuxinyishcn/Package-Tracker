package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.backend.*;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;

import java.util.List;


public class BackendTestSuites {
    private static final BackendFacade backendFacade = BackendFacade.getBackendFacade();

    @Test
    public void TestInsertAddress () {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Address address = new Address("1400 R St, Lincoln, NE 68588", "Lincoln", "68508");
            session.save(address);
            transaction.commit();
            HibernateUtil.closeSession(session);
        } catch (Throwable e) {
            session.getTransaction().rollback();
            HibernateUtil.closeSession(session);
            throw e;
        }
    }

    @Test
    public void TestInsertPackage () {

        Address address = new Address("1400 R St, Lincoln, NE 68588", "Lincoln", "68508");
        Sender sender = new Sender(address, "test", "sxc258");
        Address address2 = new Address("1400 R St2, Lincoln, NE 68588", "Lincoln", "68508");
        Receiver receiver = new Receiver(address2, "dddsx258");
        backendFacade.addPackageRecord(sender, receiver, 1);
    }

    @Test
    public void TestDeletePackage () {
        backendFacade.deletePakcageRecord(3);
    }

    @Test
    public void TestDeleteUser () {
        backendFacade.deleteUser(1);
    }

    @Test
    public void TestSearchSenderName () throws InterruptedException {
        final Session session = HibernateUtil.createSession().openSession();
        FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
        Transaction tx = fullTextSession.beginTransaction();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Sender.class)
                .get();
        try {
            org.apache.lucene.search.Query query = queryBuilder.phrase().onField("name")
                    .sentence("millard airport-east").createQuery();
            org.hibernate.query.Query hibQuery =
                    fullTextSession.createFullTextQuery(query, Sender.class);
            List<Sender> warehouseList = hibQuery.list();
            warehouseList.forEach(key -> {
                System.out.println("Iterator Value::" + key.getName());
            });
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestSearchUpdateStatus () {
        backendFacade.setPackageArrived(48);
    }

    //
//    @Test
//    public void TestSearchWarehouse_Name() {
//        final Session session = HibernateUtil.createSession().openSession();
//        FullTextSession session1 = Search.getFullTextSession(session);
//        FullTextEntityManager fullTextEntityManager;
//        Transaction tx = null;
//        String houseName = "Lincoln Hub (O and 27ST)";
//        try {
//            fullTextEntityManager = Search.getFullTextSession(session1);
//            session1.createIndexer(Warehouse.class).startAndWait();
//            String[] fields = new String[]{"warehouseID","Address","Latitude","Longitude","Name","Address"};
//            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
//            QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
//                    .forEntity(Warehouse.class).get();
//            org.apache.lucene.search.Query query = queryBuilder.keyword().fuzzy().onField("Name")
//                    .matching(houseName).createQuery();
//            org.hibernate.search.jpa.FullTextQuery jpaQuery =
//                    fullTextEntityManager.createFullTextQuery(query,Warehouse.class);
//            List<Warehouse> warehouseList = jpaQuery.getResultList();
//            System.out.println("NMSL warehouseList size: "+warehouseList.size());
//            for (Warehouse w:warehouseList) {
//                System.out.printf("warehouseID:%s\nAddress:%s\nLongtitude:%s\nLatitude:%s\nName:%s\n\n",w.getWarehouseID()
//                        ,w.getAddress(),w.getLongitude(),w.getLatitude(),w.getName());
//            }
//            assertEquals(1,warehouseList.size());
//            assertEquals(1,warehouseList.get(0).getWarehouseID());
//            assertEquals("-96.682142",warehouseList.get(0).getLatitude());
//            assertEquals("50.813314",warehouseList.get(0).getLongitude());
//            assertEquals(houseName,warehouseList.get(0).getName());
//            assertEquals(2701,warehouseList.get(0));
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        session1.close();
//    }
//
    @Test
    public void searchReceiver(){
        final Session session = HibernateUtil.createSession().openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        FullTextEntityManager fullTextEntityManager;
        Transaction tx = null;
        String trackingNumber = "326d2bfc-f78e-4b59-88ee-e63cc005c19a";
        try{
            fullTextEntityManager = Search.getFullTextSession(fullTextSession);
            fullTextSession.createIndexer(Package.class).startAndWait();
            String[] fields = new String[]{"trackingNumber"};
//            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,new StandardAnalyzer());
            QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Package.class).get();
            org.apache.lucene.search.Query query = queryBuilder.keyword().fuzzy().onField("trackingNumber").matching(trackingNumber).createQuery();
            org.hibernate.search.jpa.FullTextQuery searchQuery = fullTextEntityManager.createFullTextQuery(query,Package.class);
            List<Package> packageList = searchQuery.getResultList();
            for (Package p:packageList) {
                System.out.printf("package Receiver: %s",p.getStatus());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession(session);
        }
    }
//    @Test
//    public void searchPackage(){
//        setUp();
//        FullTextSession fullTextSession = Search.getFullTextSession(session);
//        FullTextEntityManager fullTextEntityManager;
//        Transaction tx = null;
//        String trackingNumber = "0x81F016D587AE451C94488E06FC3C75A8000000000000000000000000000000000000000000000000";
//        try{
//            fullTextEntityManager = Search.getFullTextSession(fullTextSession);
//            fullTextSession.createIndexer(Package.class).startAndWait();
//            String[] fields = new String[]{"PackageID","currentLocation","priprityID","shippingTime","status","trackingNumber","receiver","sender","PackageSet"};
//            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields,new StandardAnalyzer());
//            QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Package.class).get();
//            org.apache.lucene.search.Query query = queryBuilder.keyword().fuzzy().onField("trackingNumber").matching(trackingNumber).createQuery();
//            org.hibernate.search.jpa.FullTextQuery searchQuery = fullTextEntityManager.createFullTextQuery(query,Package.class);
//            List<Package> packageList = searchQuery.getResultList();
//            for (Package p:packageList) {
//                System.out.printf("package id: %s",p.getId());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//            tearDown();
//        }
//    }
    @Test
    public void TestReceiveAllData () {
        final Session session = HibernateUtil.createSession().openSession();
        List<Package> result = (List<Package>) session.createQuery("from Package").list();
        for (Package packages: result){
            System.out.println(packages.getTrackingNumber());
        }
    }

}
/*
*  1.login check if exist
*  2. add new users
*  3. use username find user id
*  4. cancel package (search uuid call edit packager)
*  5. give uuid find address status
*  6. tracking number edit all info
*  7. give user name find all package belongs to him
* )
* */