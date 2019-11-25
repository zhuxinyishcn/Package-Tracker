package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.backend.HibernateUtil;
import edu.unl.cse.csce361.package_tracker.backend.Package;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


public class test {
    @Test
    public void demo1 () {

        Session session = HibernateUtil.createSession().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Package p = new Package("23423String senderID", "String receiverID432", 7, "String status");
            session.save(p);
            transaction.commit();
            HibernateUtil.closeSession(session);
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            HibernateUtil.closeSession(session);
            throw e;
        }

    }
}
