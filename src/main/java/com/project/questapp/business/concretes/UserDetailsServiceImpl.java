package com.project.questapp.business.concretes;

import com.project.questapp.dataAccess.UserRepository;
import com.project.questapp.entities.User;
import com.project.questapp.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

   private UserRepository userRepository;

   public UserDetailsServiceImpl(UserRepository userRepository){
       this.userRepository=userRepository;
   }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUserName(username);
       return JwtUserDetails.create(user);
    }
}
