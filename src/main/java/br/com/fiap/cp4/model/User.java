package br.com.fiap.cp4.model;

import br.com.fiap.cp4.dto.autenticacao.RegistroDTO;
import br.com.fiap.cp4.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CP4_USER")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CP4_USER_SEQ")
    @SequenceGenerator(name = "CP4_USER_SEQ", sequenceName = "CP4_USER_SEQ", allocationSize = 50)
    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private String name;

    public User(String login, String password, UserRole role, String name) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public User(RegistroDTO registroDTO) {
        this.login = registroDTO.login();
        this.password = registroDTO.password();
        this.role = registroDTO.role();
        this.name = registroDTO.name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }

        throw new IllegalArgumentException("Role not found");
    }

    @Override
    public String getUsername() {
        return login;
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
