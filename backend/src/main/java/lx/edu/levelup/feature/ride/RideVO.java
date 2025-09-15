package lx.edu.levelup.feature.ride;

import lombok.Data;

@Data
public class RideVO {

	private int userNo;
	private String facilityName;
	private String rideBookInfoTime; //탑승시각
	private int rideBookPersonAmount; // 예약인원
	private int rideBookNo; // 예약 가능 리스트 고유번호
	
}
