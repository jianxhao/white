package com.white.exchange.pojo;


import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xh
 * @create 2023-08-20  14:29
 */
@Data
@NoArgsConstructor
public class UserDetail implements UserDetails {

    private User user;

    private List<String> permissionList;

    @JsonIgnore
    private List<GrantedAuthority> authority;

    public UserDetail(User user, List<String> permissionList){
        this.user = user;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(ObjectUtil.isNotNull(authority)) return authority;
        authority = permissionList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus()==0?true:false;
    }
}
