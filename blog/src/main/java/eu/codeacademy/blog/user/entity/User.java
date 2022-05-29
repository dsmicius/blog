package eu.codeacademy.blog.user.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    private String name;
    private String surname;
    private String email;
    private String password;
    private String zipCode;
    private String phoneNumber;
}
