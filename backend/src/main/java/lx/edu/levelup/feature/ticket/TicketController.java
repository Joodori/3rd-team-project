package lx.edu.levelup.feature.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 요청/응답 처리, API 엔드포인트
 */

@RestController
public class TicketController {

	
	@RequestMapping("/ticket")
	public TicketRequestDTO form(TicketRequestDTO dto) {
		
		return dto; //객체 json
	}
	
}
