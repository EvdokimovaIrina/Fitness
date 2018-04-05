package ru.jogging.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_user")
public class User implements Serializable {
    private Long id;
    private String login;
    private String password;
    private UserRoles userRole;

    private List<MorningJogging> morningJoggingList;

    public User() {
    }

    @JsonIgnore
    @Id
    @GeneratedValue
    @Column(nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MorningJogging> getMorningJoggingList() {
        return morningJoggingList;
    }

    public void setMorningJoggingList(List<MorningJogging> morningJoggingList) {
        this.morningJoggingList = morningJoggingList;
    }

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }
}
