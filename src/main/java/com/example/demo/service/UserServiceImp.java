package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.demo.dao.CustomUserDetails;
import com.example.demo.dao.UserAuthoritiesDAO;
import com.example.demo.dao.UserDAO;
import com.example.demo.domain.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserDAO udao;

    @Autowired
    private UserAuthoritiesDAO userAuthdao;

    @Override
    public void join(UserVO uvo) {
        log.info(">>>>>>>>>>>>>>userservice join 진입");
        udao.join(uvo);
        log.info(">>>>>>>>>>>>>>userservice join 성공");
    }

    @Override
    public void modify(UserVO uvo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(String email) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(">>>>>>>>>>>email: " + email);
        UserVO user = udao.loginCheck(email);
        log.info(">>>>>>>>>" + user);
        if (user == null) {
            return null;
        } else {
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setUsername(user.getEmail());
            customUserDetails.setPassword(user.getPassword());
            customUserDetails.setAuthorities(getAuthorities(user.getEmail()));
            customUserDetails.setEnabled(true);
            customUserDetails.setAccountNonExpired(true);
            customUserDetails.setAccountNonLocked(true);
            customUserDetails.setCredentialsNonExpired(true);

            return customUserDetails;
        }

    }

    @Override
    public Collection<GrantedAuthority> getAuthorities(String email) {
        List<String> roles = userAuthdao.getUserRoles(email);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String authority : roles) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }

        return authorities;
    }

}
