package lx.edu.levelup.feature.ride;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface RideRepository {
	
	void insertRideBookConfirm(RideVO vo);
	
	List<RideResponseDTO> getRideBookList(@RequestParam("user_no") int user_no);
	
	List<RideResponseDTO> getRideReserveList();

	List<RideResponseDTO> getRideReserveByNo(@RequestParam("facility_no") int facility_no);

	int updateRideBookInfoAmount(RideVO vo);
	
	int deleteRideBookConfirm(@RequestParam("ride_book_confirm_no") int ride_book_confirm_no);
	
}
