package com.tms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.tms.model.UserDetails;

@Transactional
public interface UserDao extends JpaRepository<UserDetails, Integer> {

	Optional<UserDetails> findByUserId(Integer id);

	UserDetails findByUserMailId(String userMailId);

	UserDetails findByUserMobileNo(long userMobileNo);

	List<UserDetails> findAllByIsActive(boolean b);

	void deleteByUserId(Integer userId);

}
