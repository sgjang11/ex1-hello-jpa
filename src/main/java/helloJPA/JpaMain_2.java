package helloJPA;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_2 {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {

/*
//          Member member = entityManager.find(Member.class, 1L);
//          printMember(member);
//          printMemberAndTeam(member);
            Member member = new Member();
            member.setName("hello");
            entityManager.persist(member);
            // insert문이 나가고 영속성 컨텍스트를 깔끔하게 지워줌
            entityManager.flush();
            entityManager.clear();

            // 값을 출력 find를 하게 되면 사용하던 안하던 무조건 select 쿼리문이 자동으로 나감
//            Member findMember = entityManager.find(Member.class, member.getId());
//            System.out.println("findMember id = "+ findMember.getId());
//            System.out.println("findMember name = "+ findMember.getName());

            // getReference를 하면 사용안하면 select 쿼리문 안나감.
            // 지금 member의 id는 알고 있기 때문에 select 문이 안나가고 정보가 없을 때만 select 문이 나감
            Member findMember = entityManager.getReference(Member.class, member.getId());
            System.out.println("findMember id =" + findMember.getId());
            // name은 db에 저장되어 있기 때문에 select 문 나감.
            System.out.println("findMember name =" + findMember.getName());
            System.out.println("findMember =" + findMember.getClass());
*/
/*
            Member member1 = new Member();
            member1.setName("member1");
            entityManager.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            entityManager.persist(member2);

            entityManager.flush();
            entityManager.clear();

            //Member findMember1 = entityManager.find(Member.class, member1.getId());
            //Member findMeber2 = entityManager.find(Member.class, member2.getId());
            //Member findMember2 = entityManager.getReference(Member.class, member2.getId());

            //getPrintMember(findMember1, findMember2);

            Member refMember = entityManager.getReference(Member.class, member1.getId());
            System.out.println("refMember = " + refMember.getClass()); // proxy
            //refMember.getName(); // 초기화
            Hibernate.initialize(refMember); // 강제 초기화
            System.out.println("refMember 초기화 했는지? = " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(refMember));
*/
/*
            // 지연로딩사용, 즉시로딩사용
            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setName("member");
            member.setTeam(team);

            entityManager.persist(member);
            entityManager.flush();
            entityManager.clear();

            Member findMember = entityManager.find(Member.class, member.getId());

            System.out.println("findMember.getTeam = " + findMember.getTeam().getClass());
            System.out.println("===================");
            findMember.getTeam().getName();
            System.out.println("===================");

            // team이 지연로딩인 경우 아래 하나의 쿼리만 날라가는데
            // team이 즉시로딩인 경우 아래 쿼리를 날리고 team의 정보는 가지고 있지 않기 때문에 team에 관한 쿼리를 한번 더 날린다.
            List<Member> selectMember1 = entityManager.createQuery("select m from Member m", Member.class).getResultList();
            // 지연로딩이지만 team join을 원한다면
            List<Member> selectMember2 = entityManager.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();
*/
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            // 3개를 모두 persist해야 3번 나감.
            // 이걸 하기 귀찮으면
            //entityManager.persist(parent);
            //entityManager.persist(child1);
            //entityManager.persist(child2);

            entityManager.persist(parent);

            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }

    private static void getPrintMember(Member findMember1, Member findMember2) {
        //System.out.println("find1 == find2: "+ (findMember1.getClass() == findMember2.getClass()));
        System.out.println("find1 instanceof: "+ (findMember1 instanceof Member));
        System.out.println("find2 instanceof: "+ (findMember2 instanceof Member));
    }

    // 어느 경우에는 이름만 다른 경우엔 team까지 가져오고 싶으면 이런 함수를 써서 경우에 따라 사용해야함.
    // 근데 jpa는 프록시라는 가짜 객체 조회기능이 있다. 이것을 활용하면 됨.
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
