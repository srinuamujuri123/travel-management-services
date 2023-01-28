package com.tms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.common.CommonConstants.User;
import com.tms.dao.UserDao;
import com.tms.model.TMSResponse;
import com.tms.model.TMSResponse.Status;
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
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}

		return response;
	}

	@Override
	public TMSResponse getuserdetails(Boolean isActive, String search) {

		TMSResponse response = new TMSResponse();
		List<UserDetails> userDetailsList = new ArrayList<>();
		try {
			// if (StringUtils.isNotEmpty(search)) {
			if (search != null || !search.isEmpty()) {
				userDetailsList = userdao.findAllByUserMailIdContains(search);
			} else {
				userDetailsList = userdao.findAllByIsActive(isActive);
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
			response.setErrorMessage(e.getLocalizedMessage());
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
			response.setErrorMessage(e.getLocalizedMessage());
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
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}

}
