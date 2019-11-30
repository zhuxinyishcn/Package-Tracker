package edu.unl.cse.csce361.package_tracker;

import edu.unl.cse.csce361.package_tracker.backend.Package;
import edu.unl.cse.csce361.package_tracker.backend.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


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

    @Test
    public void TestInsertPackage () {

        Session session = HibernateUtil.createSession().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Address address = new Address("1400 R St, Lincoln, NE 68588", "Lincoln", "68508");
            Sender sender = new Sender(address, "test", "sxc258");
            Receiver receiver = new Receiver(address, "dddsx258");
            Package packageInfo = new Package(sender, receiver, 2, "rwef");
            receiver.setPackageid(packageInfo);
            Set<Package> packages = new HashSet<>();
            packages.add(packageInfo);
            sender.setPackageSet(packages);
            session.saveOrUpdate(packageInfo);
            transaction.commit();
            HibernateUtil.closeSession(session);
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            HibernateUtil.closeSession(session);
            throw e;
        }

    }
}
