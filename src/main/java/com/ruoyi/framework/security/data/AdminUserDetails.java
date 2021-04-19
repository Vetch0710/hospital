package com.ruoyi.framework.security.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import com.ruoyi.project.system.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 管理员用户
 * */
public class AdminUserDetails  implements UserDetails {

	//管理员信息
	private Account user;
	//管理员权限
	private Set<String> permissionSet;
	

    public AdminUserDetails(Account user, Set<String> permissionList) {
        this.user = user;
        this.permissionSet = permissionList;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simp=new SimpleGrantedAuthority(this.getPermissionSet().toString());
		return Arrays.asList(simp);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
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
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public Set<String> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<String> permissionSet) {
		this.permissionSet = permissionSet;
	}
	
}
