package com.technoelevate.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technoelevate.springboot.entity.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findByUserName(String userName);
}
