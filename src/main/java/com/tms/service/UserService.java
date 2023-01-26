package com.tms.service;

import com.tms.model.TMSResponse;
import com.tms.model.UserDetails;

public interface UserService {

	TMSResponse saveUserDetails(UserDetails userDetails);

	TMSResponse getuserdetails(Boolean isActive, String search);

	TMSResponse getUserDetailsById(Integer userId);

	TMSResponse deleteUserDetailsById(Integer userId, Boolean status);
}
