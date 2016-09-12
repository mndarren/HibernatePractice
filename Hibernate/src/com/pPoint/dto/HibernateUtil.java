package com.pPoint.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public enum HibernateUtil {
	INSTANCE;
	public final SessionFactory sessionFactory = buildSessionFactory();    
	public final Session session = sessionFactory.openSession();
    private SessionFactory buildSessionFactory() {
        try {           
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

            /**
             * From Hibernate 5, use metadata to create SessionFactory.
             */
            Metadata metadata = new MetadataSources( standardRegistry )
            .getMetadataBuilder()
            .build();

            return metadata.getSessionFactoryBuilder().build();
        }catch(Throwable ex) {                
          throw new ExceptionInInitializerError(ex);
        }

       }

//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }  
//    public Session getHibernateSession() {
//        return session;
//    }
}