package lx.edu.levelup.feature.ride;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RideRequestDTO {
	
	private int userNo;
	private int rideBookNo;
	private int rideBookPersonAmount;
	private int rideBookConfirmNo; // 예약 확정 고유번호
	private int facilityNo;

}
