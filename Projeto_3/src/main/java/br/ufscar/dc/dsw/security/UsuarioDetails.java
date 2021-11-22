package br.ufscar.dc.dsw.security;
import br.ufscar.dc.dsw.domain.Usuario;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 
@SuppressWarnings("serial")
public class UsuarioDetails implements UserDetails {
    BCryptPasswordEncoder encoder;
    
    
    private Usuario usuario;
     
    public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usuario.getPapel());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return usuario.getSenha();
    }
 
    @Override
    public String getUsername() {
        return usuario.getEmail();
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

	public Usuario getUsuario() {
		return usuario;
	}
    
}