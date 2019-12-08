package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.backend.*;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public void TestInsertSender () {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Address address = new Address("1400 R St, Lincoln, NE 68588", "Lincoln", "68508");
            Sender sender = new Sender(address, "admin", "Admin");
            session.persist(sender);
            transaction.commit();
            HibernateUtil.closeSession(session);
        } catch (Throwable e) {
            session.getTransaction().rollback();
            HibernateUtil.closeSession(session);
            throw e;
        }
    }

    @Test
    public void TestInsertReceiver () {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Address address = new Address("140024 R St, Lincoln", "Lincoln", "68508");
            Receiver receiver = new Receiver(address, "test123");
            session.persist(receiver);
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
        backendFacade.addPackageRecord(sender, receiver, 1, Math.random());
    }

    @Test
    public void TestDeletePackage () {
        backendFacade.deletePakcageRecord("3fc3022b-eceb-4469-a324-3c807ed3356a");
    }

    @Test
    public void TestDeleteUser () {
        backendFacade.deleteUser(36);
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
        backendFacade.editPackageArrived("40ac7974-1978-4e28-9423-6dab8e8f189c");
    }


    @Test
    public void searchReceiver () {
        backendFacade.editPackageStatus("79d1cc90-ca30-47ee-8f23-5a9d68658730", "just Arrived");
    }


    @Test
    public void TestReceiveAllData () {
        long start = System.nanoTime();
        final Session session = HibernateUtil.createSession().openSession();
        List<Package> result = session.createQuery("from Package").list();
        Printer.printLogicAllPackage(result);
        for (Package packages : (result)) {
            System.out.println(packages.getTrackingNumber());
        }
        Sender sender = result.get(0).getSender();
        for (Package packages : (sender.getPackageSet())) {
            System.out.println(sender.getUserName() + " " + packages.getTrackingNumber());
        }
        System.out.println(System.nanoTime() - start + " nanosecond");
    }

//    @Test
//    public void TestEditPackagesInfo () {
//        backendFacade.editPackageAllInfo("40ac7974-1978-4e28-9423-6dab8e8f189c", "1",
//                "4", "2233/12/03 12:13:57",
//                "just Test it! again", "system Admin!", "system Admin:)!");
//    }


    @Test
    public void TestReturnPackage () {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            int packageid = backendFacade.searchPackage(session, "e560889c-ca9b-4bc2-a9c1-d4f3f3d2406d");
            Package packageInfo = session.get(Package.class, packageid);
            packageInfo.getReceiver().setAddress(packageInfo.getSender().getAddress());
            session.update(packageInfo);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Test
    public void TestReturnWarehouse () {
        long start = System.nanoTime();
        List<Warehouse> result = backendFacade.retrieveWarehouse();
        for (Warehouse wasrehouse : result) {
            System.out.println(wasrehouse.getAddress().getStreet());
        }
        System.out.println((System.nanoTime() - start) / 10000000);
        System.out.println((System.nanoTime() - start));
    }

    @Test
    public void TestSetPriorityNumber () {
        backendFacade.editPiorityID("3fc3022b-eceb-4469-a324-3c807ed3356a");
    }

    @Test
    public void TestLoadAllTable () {
        long start = System.nanoTime();
        final Session session = HibernateUtil.createSession().openSession();
        Map<String, Package> packageMap = new HashMap<>();
        Map<String, Sender> senderMap = new HashMap<>();
        ScrollableResults packageid = session.createQuery("from Package").scroll();
        while (packageid.next()) {
            Package packageInfo = (Package) packageid.get(0);
            packageMap.put(packageInfo.getTrackingNumber(), packageInfo);
            session.evict(packageInfo);
        }
        session.close();
        System.out.println((System.nanoTime() - start));
    }

    @Test
    public void TestInsertPackageToSender () {
        final Session session = HibernateUtil.createSession().openSession();
        List<String> userNameList = session.createQuery("select u.userName from Sender u").list();
        userNameList.forEach(System.out::println);
    }

    @Test
    public void TestCheckUserStatus () {
        long start = System.nanoTime();
        System.out.println(backendFacade.searchUserStatus("uno"));
        System.out.println((System.nanoTime() - start));
    }
    @Test
    public void TestUpgradeVIP () {
        long start = System.nanoTime();
        backendFacade.editSenderStatus("sxc258");
        System.out.println((System.nanoTime() - start));
    }

}
