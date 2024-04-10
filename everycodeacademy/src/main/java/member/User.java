package member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long seq;
    private Long id;
    private String password;
    private String email;
    private LocalDateTime date;
    private String username;

    public User (Long id, String password, String email, LocalDateTime date) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.date = date;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return Collections.emptyList ();
    }

    @Override
    public String getUsername () {
        return "";
    }

    @Override
    public boolean isAccountNonExpired () {
        return true;
    }

    @Override
    public boolean isAccountNonLocked () {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }

    @Override
    public boolean isEnabled () {
        return true;
    }

    // Standard getters and setters
    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public void setUsername (String username) {
        this.username = username;
    }
}
