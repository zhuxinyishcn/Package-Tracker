package edu.unl.cse.csce361.package_tracker.backend;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;


public class test {
    @Test
    public void demo1 () {

// 1.Hiberante框架加载核心配置文件(有数据库连接信息)

        Configuration configuration = new Configuration().configure();

// 2.创建一个SessionFactory.(获得Session--相当连接对象)

        SessionFactory sessionFactory = configuration.buildSessionFactory();

// 3.获得Session对象.


        Package p = new Package("String senderID","String receiverID", 7, "String status");
// 4.默认的情况下,事务是不自动提交.

        Transaction transaction = null;
        try ( Session session = sessionFactory.getCurrentSession();) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(p);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
