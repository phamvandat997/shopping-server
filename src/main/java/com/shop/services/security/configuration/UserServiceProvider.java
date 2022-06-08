package com.shop.services.security.configuration;

import com.shop.domain.User;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserServiceProvider implements UserDetailsService {

    @Autowired
    protected UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User details not found for the user : " + userName);
        }
        return new CustomUserDetails(user);
    }
}
