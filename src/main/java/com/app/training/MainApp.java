package com.app.training;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");

        try {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();

                Address address1 = new Address();
                address1.setCity("City1");
                address1.setStreet("Street1");
                address1.setZipCode("ZipCode1");

                em.persist(address1);

                Address address2 = new Address();
                address2.setCity("City2");
                address2.setStreet("Street2");
                address2.setZipCode("ZipCode2");

                em.persist(address2);

                Student student1 = new Student();
                student1.setFirstName("Urvil");
                student1.setLastName("Mehta");
                student1.setDateOfBirth(java.sql.Date.valueOf("1990-05-15"));

                student1.setAddress(address1);

                Laptop laptop1 = new Laptop();
                laptop1.setBrand("Brand1");
                laptop1.setModel("Model1");

                Laptop laptop2 = new Laptop();
                laptop2.setBrand("Brand2");
                laptop2.setModel("Model2");

                Feature feature1 = new Feature();
                feature1.setFeatureName("Touch Screen");

                Feature feature2 = new Feature();
                feature2.setFeatureName("Backlit Keyboard");

                laptop1.addFeature(feature1);
                laptop1.addFeature(feature2);

                student1.addLaptopItem(laptop1);
                student1.addLaptopItem(laptop2);

                em.persist(student1);

                Student student2 = new Student();
                student2.setFirstName("Another");
                student2.setLastName("Student");
                student2.setDateOfBirth(java.sql.Date.valueOf("1995-10-20"));

                student2.setAddress(address2);

                Laptop laptop3 = new Laptop();
                laptop3.setBrand("Brand3");
                laptop3.setModel("Model3");

                Laptop laptop4 = new Laptop();
                laptop4.setBrand("Brand4");
                laptop4.setModel("Model4");

                Feature feature3 = new Feature();
                feature3.setFeatureName("SSD Drive");

                Feature feature4 = new Feature();
                feature4.setFeatureName("High-Resolution Display");

                laptop3.addFeature(feature3);
                laptop3.addFeature(feature4);

                student2.addLaptopItem(laptop3);
                student2.addLaptopItem(laptop4);

                em.persist(student2);

                transaction.commit();
                System.out.println("Successfully saved");
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                em.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            emf.close();
        }
    }
}
