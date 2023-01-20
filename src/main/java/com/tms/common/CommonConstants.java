package com.tms.common;

public interface CommonConstants {
	
	public interface ResponseStatus {
		String SUCCESS = "Success";
		String FAIL = "Fail";
	}

	public interface Token {

		String INVALIDTOKEN = "Token is Invalid";
	}

	public interface Authentication {

		String AUTHORIZEDUSER = "Authorized User";
		String UNAUTHORIZEDUSER = "Incorrect Username or Password";
		String INVALIDATESESSION = "Session Invalidated Successfully";
		String INVALIDATESESSIONFAIL = "Session Invalidation Failed";
		String FORGOTPASSWORD = "Successfully Requested to Reset Password";
		String INVALIDUSER = "Invalid User";
		String ValidRESETToken = "Valid Reset Password Token";
		String INVALIDRESETTOKEN = "Invalid User Or Reset Password Token";
		String UPDATEPASSWORD = "Password Updated Successfully";
		String NOTUPDATEPASSWORD = "Password Updation Failed";
		String CHANGEPASSWORD = "Password Changed Successfully";
		String NOTCHANGEDPASSWORD = "You used this password recently. Please choose different one";
		String INVALIDOLDPASSWORD = "Your old password is invalid";
		String EMAILNOTFOUND = "User Email Not Found";
		String EMAILEXIST = "Email Already Exists";
		String EMPTYEMAIL = "EmailId should not be empty";
		String SETPASSWORD = "Successfully Requested to Set Password";
		String NOTMEMBER = "Members only Login through Mobile..Login through Web";
	}

	public interface User {

		String SAVE = "User Saved Successfully";
		String UPDATE = "User Updated Successfully";
		String RECORDFOUND = "User Found";
		String RECORDNOTFOUND = "User Not Found";
		String LISTFOUND = "User List Found";
		String LISTNOTFOUND = "User List Not Found";
		String EMAILEXIST = "Email Already Exists";
		String PHONEEXIST = "Phone Number Already Exists";
		String ACTIVATEUSER = "User Activated successfully";
		String MEMBER = "Member";
		String ERROR = "Error while saving the User";
		String DELETED = "Deleted user Details sucessfully";
		String UNABLETODELETE = "Oops, Unable to Delete Data";
	}
	public interface Hotel {

		String SAVE = "Hotel Saved Successfully";
		String UPDATE = "Hotel Updated Successfully";
		String RECORDFOUND = "Hotel Found";
		String RECORDNOTFOUND = "Hotel Not Found";
		String LISTFOUND = "Hotel List Found";
		String LISTNOTFOUND = "Hotel List Not Found";
		String EMAILEXIST = "Email Already Exists";
		String PHONEEXIST = "Phone Number Already Exists";
		String ACTIVATEUSER = "Hotel Activated successfully";
		String MEMBER = "Member";
		String ERROR = "Error while saving the hotel details";
		String DELETED = "Deleted user Details sucessfully";
		String UNABLETODELETE = "Oops, Unable to Delete Data";
		String HOTELEXIST ="Hotel name existing already";
	}
	

}
