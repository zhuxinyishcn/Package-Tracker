package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.backend.Address;
import edu.unl.cse.csce361.package_tracker.backend.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


public class BackendTestSuites {
    @Test
    public void TestInsertAddress () {

        final Session session = HibernateUtil.createSession().openSession();
        Transaction transaction = session.beginTransaction();
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

//    @Test
//    public void TestInsertSeneder () {
//
//        Session session = HibernateUtil.createSession().openSession();
//        Transaction transaction = session.beginTransaction();
//
//        try {
//            Package p = new Package("23423String senderID", "String receiverID432", 7, "String status");
//            session.save(p);
//            transaction.commit();
//            HibernateUtil.closeSession(session);
//        } catch (RuntimeException e) {
//            session.getTransaction().rollback();
//            HibernateUtil.closeSession(session);
//            throw e;
//        }
//
//    }
}
