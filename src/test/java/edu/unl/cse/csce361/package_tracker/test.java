package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.backend.Package;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class test {
    @Test
    public void demo1 () {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Package p = new Package("23423String senderID", "String receiverID432", 7, "String status");
            session.save(p);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            session.close();
            throw e;
        }

    }
}
