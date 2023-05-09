package helloJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class JpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {

/*
            List<Member_2> result = entityManager.createQuery("select m From Member_2 m where m.username like '%kim%'"
                            , Member_2.class).getResultList();
            for (Member_2 member : result) {
                System.out.println("member = " + member);
            }
*/

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

        entityManagerFactory.close();

    }

}
