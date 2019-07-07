package com.rosu.flights.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rosu.flights.model.UserRole;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

	public List<UserRole> findByUserId(long userId);
}
