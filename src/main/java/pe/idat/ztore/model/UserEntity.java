package pe.idat.ztore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.idat.ztore.model.enums.Role;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Builder
@Entity
@SQLDelete(sql = "UPDATE user_ SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @Fetch(FetchMode.JOIN)
    private Set<Role> role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;

    // --------------------- UserDetails props

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Role r : role) {
            authorities.add(new SimpleGrantedAuthority(r.name()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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
