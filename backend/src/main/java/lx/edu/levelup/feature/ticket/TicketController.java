package lx.edu.levelup.feature.ticket;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lx.edu.levelup.feature.ticket.domain.TicketVO;

/*
 * 요청/응답 처리, API 엔드포인트
 */

@RestController
@RequiredArgsConstructor
public class TicketController {

	private final TicketService ticketService;
	
	@PostMapping(value ="/buyticket", produces = "text/plain; charset=UTF-8")
	public String buyTicket(@RequestBody TicketRequestDTO dto) {
		boolean result = ticketService.buyTicket(dto);
		return result ? "구매 완료" : "구매 실패";
	}
	
	@GetMapping("/getticket")
	public List<TicketVO> getTicketList(@RequestParam("user_no") int user_no) {
		List<TicketVO> ticketList = ticketService.getTicket(user_no);
		return ticketList;
	}
}
