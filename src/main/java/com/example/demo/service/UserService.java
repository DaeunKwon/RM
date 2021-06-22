package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import com.example.demo.domain.UserVO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public void join(UserVO uvo);

    Collection<GrantedAuthority> getAuthorities(String email);

    public boolean emailCheck(String email);

    public List<UserVO> getlist();

    public UserVO findUserByEmail(UserVO user);

}
