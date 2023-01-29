package com.tms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tms.model.UserDetails;

@Transactional
public interface UserDao extends JpaRepository<UserDetails, Integer> {

	UserDetails findByUserId(Integer userId);

	UserDetails findByUserMailId(String userMailId);

	UserDetails findByUserMobileNo(long userMobileNo);

	List<UserDetails> findAllByIsActive(boolean isActive);

	void deleteByUserId(Integer userId);

	//List<UserDetails> findAllByUserMailIdContains(String search);

	List<UserDetails> findAllByIsActiveAndUserMailIdContains(Boolean isActive, String search);

}
