package com.tms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	public TMSResponse getuserdetails(Boolean isActive) {

		TMSResponse response = new TMSResponse();
		try {
			List<UserDetails> userDetailsList = userdao.findAllByIsActive(isActive);
			response.setData(userDetailsList);
			response.setCount(userDetailsList.size());
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
	public TMSResponse deleteUserDetailsById(Integer userId) {
		TMSResponse response = new TMSResponse();

		try {
			if (userdao.findByUserId(userId).isPresent()) {
				userdao.deleteByUserId(userId);
				response.setDetails(User.DELETED);
				response.setStatus(Status.OK);
			} else {
				response.setDetails(User.RECORDNOTFOUND);
				response.setStatus(Status.OK);

			}
		} catch (Exception e) {
			response.setDetails(User.UNABLETODELETE);
			response.setErrorMessage(e.getLocalizedMessage());
			response.setStatus(Status.FAILED);
		}
		return response;
	}

}
