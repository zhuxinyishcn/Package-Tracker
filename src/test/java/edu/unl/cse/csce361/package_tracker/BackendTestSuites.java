package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.backend.*;
import edu.unl.cse.csce361.package_tracker.frontend.Printer;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            Receiver receiver = new Receiver(address, "test123", (int) Math.abs(Math.random()% 8));
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
        Receiver receiver = new Receiver(address2, "dddsx258", (int) Math.abs(Math.random() % 8));
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
    public void TestSearchTranckingNumber () throws InterruptedException {
        long start = System.nanoTime();
        final Session session = HibernateUtil.createSession().openSession();
        FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
        //fullTextSession.createIndexer().startAndWait();
        Transaction tx = fullTextSession.beginTransaction();
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Package.class)
                .get();
        try {
            org.apache.lucene.search.Query query = queryBuilder.phrase().onField("trackingNumber")
                    .sentence("7918f73b-bc4-448d-9652-f00f67355ac8").createQuery();
            org.hibernate.query.Query hibQuery =
                    fullTextSession.createFullTextQuery(query, Package.class);
            Package warehouseList = (Package) hibQuery.getSingleResult();
            System.out.println(warehouseList.getEstimateTime());
            tx.commit();
            session.close();
            System.out.println((System.nanoTime() - start) + " nanosecond");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestSearchPackage () {
        long start = System.nanoTime();
        backendFacade.searchPackage("7918f73b-bc43-448d-9652-f00f67355ac8");
        System.out.println(System.nanoTime() - start + " nanosecond");
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


    @Test
    public void TestReturnPackage () {
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            Package packageInfo = backendFacade.searchPackage("f5f5837d-eb37-4db6-af22-3a2c13b7a364");
            System.out.println(packageInfo.getId());
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
        final DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();
        final Session session = HibernateUtil.createSession().openSession();
        final Transaction transaction = session.beginTransaction();
        ScrollableResults packageid = session.createQuery("from Package").scroll();
        while (packageid.next()) {
            Package packageInfo = (Package) packageid.get(0);
            LocalDateTime estimateTime = LocalDateTime.from(date.parse(packageInfo.getEstimateTime()));
            if (packageInfo.getStatus().equals("Despatching")) {
                if (now.isAfter(estimateTime)) {
                    packageInfo.setStatus("Arrived");
                } else {
                    LocalDateTime startTime = LocalDateTime.from(date.parse(packageInfo.getShippingTime()));
                    long second = (Duration.between(startTime, now).toMillis() / 1000);
                    int numberOfWarehouse = (int) ((second * 22.352) / 16093.44);
                    packageInfo.setCurrentLocation(packageInfo.getCurrentLocation() + numberOfWarehouse);
                }
            }
            session.update(packageInfo);
        }
        transaction.commit();
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

    @Test
    public void TestSearchSender () {
        long start = System.nanoTime();
        Address address = new Address("1213400 R St", "test", "200102");
        Receiver receiver = new Receiver(address, "dddsx258", (int) Math.abs(Math.random()% 8));
        Sender sender = backendFacade.searchSender("golf for ever");
        System.out.println(sender.getPackageSet().size());
        for (Package packageinfo : sender.getPackageSet()) {
            System.out.println(packageinfo.getTrackingNumber());
        }
        System.out.println((System.nanoTime() - start));
    }


    @Test
    public void TestEditLocation () {
        long start = System.nanoTime();
        backendFacade.editCurrentlocation("7918f73b-bc43-448d-9652-f00f67355ac8", 11);
        System.out.println((System.nanoTime() - start));
    }
}
