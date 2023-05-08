package helloJPA;

import javax.persistence.*;

//@Entity
public class Locker {

    // member와 일대일 관계 단방향
    //@Id @GeneratedValue
    //@Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    // meber와 일대일 양방향하기 위해서 아래 추가
    //@OneToOne(mappedBy = "locker")
    private Member member;

}
