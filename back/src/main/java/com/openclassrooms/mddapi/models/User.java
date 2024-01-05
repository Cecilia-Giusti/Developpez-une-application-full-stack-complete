package com.openclassrooms.mddapi.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Represents a user entity in the application.
 * This class is linked to the 'users' table in the database and includes user-related information such as username, email, and password.
 * It also contains timestamps for tracking the creation and update of user data.
 * Implements the UserDetails interface, integrating with Spring Security for authentication and authorization purposes.
 */
@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email address of the user. It is unique within the system.
     */
    @Column(unique = true)
    private String email;

    /**
     * The password for the user account.
     */
    private String password;

    /**
     * The timestamp when the user account was created.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * The timestamp when the user account was last updated.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /**
     * Lifecycle hook for actions to be performed just before the entity is persisted.
     * Sets the createdAt timestamp.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    /**
     * Lifecycle hook for actions to be performed just before the entity is updated.
     * Sets the updatedAt timestamp.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
