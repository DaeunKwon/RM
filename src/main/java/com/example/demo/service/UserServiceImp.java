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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserDAO udao;

    @Autowired
    private UserAuthoritiesDAO userAuthdao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDAO udao, PasswordEncoder passwordEncoder) {
        this.udao = udao;
        this.passwordEncoder = passwordEncoder;
    }

    public void join(UserVO uvo) {

        // **** 해싱하는 부분 ****
        String encodePassword = passwordEncoder.encode(uvo.getPassword());
        UserVO user = UserVO.builder().email(uvo.getEmail()).name(uvo.getName()).password(encodePassword).build();
        udao.join(user);
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(">>>>>>>>>>>email: " + email);
        UserVO user = udao.getUserfindByEmail(email);
        log.info(">>>>>>>>>" + user);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        } else {
            log.info(">>>>>>> customuserdetails 생성");
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

    @Override
    public boolean emailCheck(String email) {
        log.info(">>>>>>" + email);
        return (udao.getUserfindByEmail(email) == null) ? true : false;
    }

    @Override
    public List<UserVO> getlist() {
        log.info(">>>>>>> get user list (service)");
        return udao.getUserList();
    }

    @Override
    public UserVO findUserByEmail(UserVO user) {
        return udao.getUserfindByUser(user);
    }

}
