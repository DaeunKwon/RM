package com.example.demo.dao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.domain.UserVO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails() {
    }

    public CustomUserDetails(String username, String password, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // public static CustomUserDetails build(UserVO uvo) {
    // List<GrantedAuthority> authorities = uvo.getRoles().stream()
    // .map(role -> new
    // SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

    // return new CustomUserDetails(uvo.getEmail(), uvo.getPassword(), authorities);
    // }

}
