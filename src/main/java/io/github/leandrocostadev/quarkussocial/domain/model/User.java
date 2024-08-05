package io.github.leandrocostadev.quarkussocial.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@NamedNativeQueries({
        @NamedNativeQuery(name = "LIST_ALL_USERS", query = "SELECT * FROM users", resultClass = User.class),
        @NamedNativeQuery(name = "FIND_BY_NAME", query = "SELECT * FROM users WHERE name LIKE CONCAT('%', :name, '%')", resultClass = User.class),
        @NamedNativeQuery(name = "FIND_USER_BY_ID", query = "SELECT * FROM users WHERE user_id = :id", resultClass = User.class),
        @NamedNativeQuery(name = "ADD_USER", query = "INSERT INTO users(name, age) VALUES(:name, :age)"),
        @NamedNativeQuery(name = "UPDATE_USER", query = "UPDATE users SET name = :name, age = :age WHERE user_id = :id"),
        @NamedNativeQuery(name = "DELETE_USER_BY_ID", query = "DELETE FROM users WHERE user_id = :id")
})
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;


}
