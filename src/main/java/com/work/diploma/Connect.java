package com.work.diploma;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connect {
    private static Connect instance;
    private final EntityManagerFactory sessionFactory;

    public static Connect getInstance(){
      if (instance == null) {
          //init instance
        instance = new Connect();
      }
      return instance;
    }

    private Connect (){
        sessionFactory = Persistence.createEntityManagerFactory( "com.work.diploma.jpa");
    }

    public EntityManager createEntityManager(){
        return sessionFactory.createEntityManager();
    }
}
