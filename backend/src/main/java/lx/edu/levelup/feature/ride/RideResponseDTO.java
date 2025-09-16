package lx.edu.levelup.feature.ride;

import lombok.Getter;

@Getter
public class RideResponseDTO {
	
	private int facilityNo;
	private int rideBookInfoNo;
	private long rideBookConfirmNo;
	private String facilityName;
    private String rideBookTime;
	private int rideBookPersonAmount;
}
