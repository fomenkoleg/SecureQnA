package com.secureqna.secureqna.security.detailServices;

import com.secureqna.secureqna.objects.UserSqna;
import com.secureqna.secureqna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        UserSqna client = repository.findByUsername(username).orElse(null);

        if(client==null){
            throw new UsernameNotFoundException("User not found");
        }
        List<GrantedAuthority> roles = actRoles(client.getRoles());
        return new org.springframework.security.core.userdetails.User(client.getUsername(),
                client.getPass(), roles);
    }

    private List<GrantedAuthority> actRoles(List<String> roles){
        List<GrantedAuthority> allRoles = new ArrayList<>();
        for(String role: roles){
            allRoles.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return allRoles;
    }



}
