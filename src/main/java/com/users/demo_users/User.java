package com.users.demo_users;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Entity(name = "User")
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name is required.")
    @Column(name = "first_name") // Mapping to the actual column name in the database
    private String firstName;

    @NotNull(message = "Last name is required.")
    @Column(name = "last_name") // Mapping to the actual column name in the database
    private String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
