package com.tms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tms.client.common.CommonConstants.User;
import com.tms.client.model.TMSResponse;
import com.tms.client.model.TMSResponse.Status;
import com.tms.client.utils.DateUtils;
import com.tms.client.utils.TMSUtils;
import com.tms.dao.UserDao;
import com.tms.model.UserDetails;
import com.tms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userdao;

	@Override
	public TMSResponse saveUserDetails(UserDetails userDetails) {
		TMSResponse response = new TMSResponse();
		try {
			UserDetails userEmailObj = userdao.findByUserMailId(userDetails.getUserMailId());
			UserDetails userMobileObj = userdao.findByUserMobileNo(userDetails.getUserMobileNo());

			if (userEmailObj == null && userMobileObj == null) {
				userDetails.setCreatedOn(DateUtils.getTodayDate());
				userDetails.setUpdatedOn(DateUtils.getTodayDate());
				userDetails.setActive(true);
				response.setData(userdao.save(userDetails));
				response.setDetails(User.SAVE);
			} else if (userEmailObj != null) {
				response.setDetails(User.EMAILEXIST);
			} else if (userMobileObj != null) {
				response.setDetails(User.PHONEEXIST);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, unable to save data");
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}

		return response;
	}

	@Override
	public TMSResponse getuserdetails(Boolean isActive, String search, Integer start, Integer end) {

		TMSResponse response = new TMSResponse();
		List<UserDetails> userDetailsList = new ArrayList<>();
		try {
			Pageable pageable = PageRequest.of(start, end);
			// if (StringUtils.isNotEmpty(search)) {
			if (search != null || !search.isEmpty()) {
				userDetailsList = userdao.findAllByIsActiveAndUserMailIdContains(isActive, search, pageable);
			} else {
				userDetailsList = userdao.findAllByIsActive(isActive, pageable);
			}

			// if (CollectionUtils.isNotEmpty(userDetailsList)) {
			if (userDetailsList != null || userDetailsList.size() > 0) {
				response.setData(userDetailsList);
				response.setCount(userDetailsList.size());
			} else {
				// if (userDetailsList == null || userDetailsList.size() == 0) {

				response.setDetails(User.LISTNOTFOUND);
			}

			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, unable to fetch data");
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse getUserDetailsById(Integer userId) {
		TMSResponse response = new TMSResponse();
		try {
			Optional<UserDetails> userDetailsByID = userdao.findById(userId);
			if (userDetailsByID.isPresent()) {
				response.setData(userDetailsByID.get());
			} else {
				response.setData("OOps, No Data found for " + userId);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails("Oops, Unable to fetch data, please try after sometime");
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}

	@Override
	public TMSResponse deleteUserDetailsById(Integer userId, Boolean status) {
		TMSResponse response = new TMSResponse();

		try {
			UserDetails userDetailsObj = userdao.findByUserId(userId);
			if (userDetailsObj != null) {
				if (status) {
					userdao.deleteByUserId(userId);
				} else {
					userDetailsObj.setActive(status);
					response.setData(userdao.save(userDetailsObj));
				}
				response.setDetails(User.DELETED);
			} else {
				response.setDetails(User.RECORDNOTFOUND);
			}
			response.setStatus(Status.OK);
		} catch (Exception e) {
			response.setDetails(User.UNABLETODELETE);
			response.setErrorMessage(TMSUtils.getExceptionDetails(e));
			response.setStatus(Status.FAILED);
		}
		return response;
	}

}
