package helloJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            // insert
            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("hello0");
            entityManager.persist(member);
            entityTransaction.commit();
            */

            // findOne, delete
            /*
            Member findMember = entityManager.find(Member.class, 1L);
            entityManager.remove(findMember);
            entityTransaction.commit();
            */

            // findOne, update
            /*
            Member findMember = entityManager.find(Member.class, 1L);
            findMember.setName("helloJPA");
            entityTransaction.commit();
            */

            // 이때 Member는 테이블이 대상이 아니라 객체임(VO)
            /* 전체 id의 이름 가져옴
            */
            /*
            List<Member> result = entityManager.createQuery("select m from Member as m", Member.class)
                    //.setFirstResult(5)  // 페이징
                    //.setMaxResults(8)   // 페이징
                    .getResultList();
            for (Member member : result){
                System.out.println("member.name: "+member.getName());
            }
            */

            /*
            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("JPA");

            // 영속
            entityManager.persist(member);

            // db 저장
            entityTransaction.commit();
            */

            /*
            Member member = new Member();
            member.setUsername("JPA1");
            entityManager.persist(member);
            */

            // 저장
           /* Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            entityManager.persist(member);

            entityManager.flush();
            entityManager.clear();*/

            /*
            Member findMember = entityManager.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();
            for (Member m : members){
                System.out.println("m: "+m.getName());
            }
            */
            //Team findTeam = findMember.getTeam();

            //System.out.println("===="+findTeam.getName());

            /*
            Member member = new Member();
            member.setName("member1");
            entityManager.persist(member);

            Team team = new Team();
            team.setName("teamA");
            // 권장하지 않음. update 쿼리도 나감.
            team.getMembers().add(member); // => member 테이블에 외래키가 존재함
            entityManager.persist(team);
            */


/*
            // 고급매핑
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과 사라지다.");
            movie.setPrice(10000);
            entityManager.persist(movie);
            entityManager.flush();
            entityManager.clear();

//            Movie findMovie = entityManager.find(Movie.class, movie.getId());
            Item item = entityManager.find(Item.class, movie.getId());
            System.out.println("item: " + item);
*/



            Member member = entityManager.find(Member.class, 1L);
            printMember(member);


            //printMemberAndTeam(member);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
// 어느 경우에는 이름만 다른 경우엔 team까지 가져오고 싶으면
    private static void printMember(Member member) {
        System.out.println("member = " + member.getName());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getName();
        System.out.println("username = "+ username);
        Team team = member.getTeam();
        System.out.println("team = "+team);
    }
}
