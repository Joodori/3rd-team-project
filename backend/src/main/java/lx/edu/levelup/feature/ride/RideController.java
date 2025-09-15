package lx.edu.levelup.feature.ride;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RideController {
	
	private final RideService rideService;
	
	@PostMapping(value= "/reserveRide", produces = "text/plain; charset=UTF-8")
	public String reserveRide(@RequestBody RideRequestDTO dto) {
		boolean result = rideService.reserveRide(dto);
		return result ? "예약 완료" : "예약 실패";
	}
	
	@GetMapping("/getRideBookList")
	public List<RideResponseDTO> getRideBookList(@RequestParam("user_no") int user_no){
		return rideService.getRideBookList(user_no);
	}
	
}
