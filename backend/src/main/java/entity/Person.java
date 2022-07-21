package entity;


import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstname;
    private String lastname;
    @OneToOne
    private Event event;
    private Role role;
    private String telephone;
    private String comment;

}
