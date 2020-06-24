package com.kazam.shopingcart.Security;

import com.kazam.shopingcart.model.User;
import com.kazam.shopingcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user =userRepository.findByUserName(userName);
        MyUserDetails userDetails=null;
        if(user!=null){
            userDetails=new MyUserDetails();
            userDetails.setUser(user);

        }else {
            throw new UsernameNotFoundException("User not exist with name : " + userName);
        }
        return userDetails;
    }
}
