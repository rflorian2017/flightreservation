package com.rosu.flights.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rosu.flights.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>
{

}
