package com.smartinventory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartinventory.entity.User;
import com.smartinventory.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByUserName(String userName);
	List<User> findByRole(Role role);
	Optional<User> findByEmailId(String email);
}
