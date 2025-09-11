package lx.edu.levelup.feature.user.domain;

public class UserVO {

	String userName;
	String userBirthDate;
	String userMobile;
	String userAddress;
	int userNo;
	String userId;
	String userPassword;
	int ticketNo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserBirthDate() {
		return userBirthDate;
	}

	public void setUserBirthDate(String userBirthDate) {
		this.userBirthDate = userBirthDate;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	@Override
	public String toString() {
		return "UserVO [userName=" + userName + ", userBirthDate=" + userBirthDate + ", userMobile=" + userMobile
				+ ", userAddress=" + userAddress + ", userNo=" + userNo + ", userId=" + userId + ", userPassword="
				+ userPassword + ", ticketNo=" + ticketNo + "]";
	}

}
