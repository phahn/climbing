package de.phahn.climbing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.phahn.climbing.model.User;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private RegistrationService registrationService;
  @Autowired private Assembler assembler;

  @Override
@Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {	 
	       
    User user = registrationService.findUserByUsername(username);
    if (user == null) {
		throw new UsernameNotFoundException("user not found");
	}

    return assembler.buildUserFromUserEntity(user);
  }
}