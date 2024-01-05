package com.users.demo_users;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "User")
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name") // Mapping to the actual column name in the database
    private String firstName;

    @Column(name = "last_name") // Mapping to the actual column name in the database
    private String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
