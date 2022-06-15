package com.example.firstapp.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
@Table(name = "user")
@ToString @EqualsAndHashCode
public class User {
    //LOMBOK ADD @GETTER/@SETTER
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter  @Column(name = "id", nullable = false)
    private Long id;
    @Getter @Setter @Column(name = "name")
    private String name;
    @Getter @Setter @Column(name = "lastname")
    private String lastName;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "phone")
    private String phoneNumber;
    @Getter @Setter @Column(name = "password")
    private String password;

}
