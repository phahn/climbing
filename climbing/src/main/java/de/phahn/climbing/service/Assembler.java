package de.phahn.climbing.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.phahn.climbing.model.Role;
import de.phahn.climbing.model.User;

@Service("assembler")
public class Assembler {

  @Transactional(readOnly = true)
  UserDetails buildUserFromUserEntity(User userEntity) {

    String username = userEntity.getUsername();
    String password = userEntity.getPassword();
    
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
     authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.name().toString()));
       
      org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username, password, enabled,
      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    return user;
  }
}