package lx.edu.levelup.feature.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 요청/응답 처리, API 엔드포인트
 */

@RestController
public class TicketController {

	@Autowired
    TicketRepository repo;
	
	@RequestMapping("/")
	public TicketDTO form(TicketDTO dto) {
		//VO를 바로 내보내면 예민함
		//DTO에 필요한 것만 빼고 보냄
		return dto; //객체 json
	}
	
}
