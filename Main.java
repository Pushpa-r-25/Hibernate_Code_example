package com.learn;

import com.learn.hibernate.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {

        // 1. Create service registry
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // Make sure this file is in src/main/resources
                .build();

        // 2. Create metadata
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Student.class)
                .getMetadataBuilder().build();

        // 3. Create session factory
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        // 4. Open session and begin transaction
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // 5. Create a Student object
        Student stu = new Student("Pushpa", 21, "Led");

        // 6. Persist the object
        session.persist(stu);

        // 7. Commit transaction
        transaction.commit();

        // 8. Close session and factory
        session.close();
        sessionFactory.close();

        System.out.println("Student has been created: " + stu);
    }
}
