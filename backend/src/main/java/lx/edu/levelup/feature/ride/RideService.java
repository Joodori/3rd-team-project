package lx.edu.levelup.feature.ride;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideService {
	
	private final RideRepository rideMapper;
	
	@Transactional // update, insert 모두 성공해야 실제 DB에 반영
	public boolean reserveRide(RideRequestDTO rideRequestDTO) {
		
		// 예약 인원 수량이 0이면 예약 불가능 
		if(rideRequestDTO.getRideBookPersonAmount() == 0) {
			return false;
		}
		
		// DTO -> VO 변환
		RideVO vo = new RideVO();
		vo.setUserNo(rideRequestDTO.getUserNo());
		vo.setRideBookNo(rideRequestDTO.getRideBookNo());
		vo.setRideBookPersonAmount(rideRequestDTO.getRideBookPersonAmount());	
		
		// update 구문 실행
		int updateRow = rideMapper.updateRideBookInfoAmount(vo);
		if(updateRow == 0) {
			throw new RuntimeException("예약 가능 수량 부족으료 예약 실패"); // transactional을 이용하여 안됐을 때 rollback이 되게 하려면 반드시 RuntimeException을 던져야함
		}
		
		rideMapper.insertRideBookConfirm(vo);
		return true;
	}
	
	public List<RideResponseDTO> getRideBookList(int user_no){
		return rideMapper.getRideBookList(user_no);
	}

	public List<RideResponseDTO> getRideReserveList(){
		return rideMapper.getRideReserveList();
	}
	
	public boolean cancelResevation(int ride_book_confirm_no) {
		int deleteRow = rideMapper.deleteRideBookConfirm(ride_book_confirm_no);
		if(deleteRow == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
