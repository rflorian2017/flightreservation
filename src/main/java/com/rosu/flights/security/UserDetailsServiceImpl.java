package com.rosu.flights.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rosu.flights.model.Role;
import com.rosu.flights.model.User;
import com.rosu.flights.model.UserRole;
import com.rosu.flights.model.repository.RoleRepository;
import com.rosu.flights.model.repository.UserRepository;
import com.rosu.flights.model.repository.UserRoleRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private UserRoleRepository userRoleRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			UserRoleRepository userRoleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);

		if (null == user) {
			System.out.println("User not found:" + username);
			throw new UsernameNotFoundException("User not found:" + username);
		}

		System.out.println(username + " is found in database!");

		// want to read roles
		List<Long> idsList = new ArrayList<>();
		Iterable<UserRole> rolesUsers = userRoleRepository.findByUserId(user.getId());
		rolesUsers.forEach(rolesUser -> {
			idsList.add(rolesUser.getRoleId());
			System.out.println("ROLE in db" + rolesUser.getRoleId());
		});
		
		
		List<GrantedAuthority> grantedList = new ArrayList<>();
		if (idsList.size() > 0) {
			for (Long id : idsList) {
				System.out.println("FOUND id: " + id);
				Optional<Role> roleOptional = roleRepository.findById(id);
				System.out.println(roleOptional.get().getName());
				GrantedAuthority authority = new SimpleGrantedAuthority(roleOptional.get().getName());
				grantedList.add(authority);
			}

		}

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), grantedList);
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());

		return userDetails;
	}

}
