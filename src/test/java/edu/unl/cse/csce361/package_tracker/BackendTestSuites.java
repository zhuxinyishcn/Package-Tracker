package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.backend.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;

import java.util.List;


public class BackendTestSuites {
    private final BackendFacade backendFacade = BackendFacade.getBackendFacade();

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
    public void TestSearchWarehouse_Name () throws InterruptedException {
        final Session session = HibernateUtil.createSession().openSession();
        FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
        Transaction tx = fullTextSession.beginTransaction();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Sender.class)
                .get();

        try {
            org.apache.lucene.search.Query query = queryBuilder.keyword().onFields("name")
                    .matching("test").createQuery();
            org.hibernate.query.Query hibQuery =
                    fullTextSession.createFullTextQuery(query, Sender.class);
            List warehouseList = hibQuery.list();
            System.out.println(((Sender) warehouseList.get(0)).getAddress().getStreet());
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
