package helloJPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    // member와 다대다 관계 단방향
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 다대다 양방향
 /*
    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();
*/
    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
