package com.yr.springbootsecuritymybatiscrud.config;

import com.yr.springbootsecuritymybatiscrud.entity.UPermission;
import com.yr.springbootsecuritymybatiscrud.entity.UUser;
import com.yr.springbootsecuritymybatiscrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于根据用户名查询用户信息、权限，封装返回
 */
@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserService userService;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("username: " + username);
		UUser user = userService.getUserName(username);
		System.out.println("User : "+user);
		if(user==null){//没有这个用户
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		List<UPermission> permission = userService.getUserPermission(username);//查出该用户所有权限
		for (UPermission per : permission){
			if(per.getMark() != null && !"".equals(per.getMark())){
				System.out.println("拥有权限： " + per.getMark());
				authorities.add(new SimpleGrantedAuthority("ROLE_"+per.getMark()));//添加所有权限
			}

		}

		//把用户名、密码、权限信息封装起来返回，然后和页面输入的信息对比
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getStatus() == 1, true, true,true, authorities);
	}


}
