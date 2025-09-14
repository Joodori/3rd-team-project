package lx.edu.levelup.feature.ride;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideService {
	
	private final RideRepository rideMapper;
	
	public boolean reserveRide(RideRequestDTO rideRequestDTO) {
		
		if(rideRequestDTO.getRideBookPersonAmount() == 0) {
			return false;
		}
		
		// DTO -> VO 변환
		RideVO vo = new RideVO();
		vo.setUserNo(rideRequestDTO.getUserNo());
		vo.setRideBookNo(rideRequestDTO.getRideBookNo());
		vo.setRideBookPersonAmount(rideRequestDTO.getRideBookPersonAmount());
		
		rideMapper.insertRideBookConfirm(vo);
		return true;
	}
	
	public List<RideResponseDTO> getRideBookList(int user_no){
		return rideMapper.getRideBookList(user_no);
	}
	
	
}
