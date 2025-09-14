package lx.edu.levelup.feature.ticket;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lx.edu.levelup.feature.ticket.TicketMapper;
import lx.edu.levelup.feature.ticket.domain.TicketVO;

@Service
@RequiredArgsConstructor
public class TicketService {
	
	private final TicketMapper ticketMapper;
	
	public boolean buyTicket(TicketRequestDTO ticketRequestDTO) {
		
		// 티켓 수량 = 0이면 구매 불가능 구현
		if(ticketRequestDTO.getTicketAmount() == 0) {
			return false;
		}
		
		// DTO -> VO 변환
		TicketVO vo = new TicketVO();
		vo.setTicketName(ticketRequestDTO.getTicketName());
		vo.setUserNo(ticketRequestDTO.getUserNo());
		vo.setTicketAmount(ticketRequestDTO.getTicketAmount());
		vo.setTicketMoneyStatus(ticketRequestDTO.getTicketMoneyStatus());
		
		ticketMapper.insertDB(vo);
		return true;
		
	}
	
	public List<TicketResponseDTO> getTicket(int user_no) {
		List<TicketResponseDTO> ticketList = ticketMapper.getTicketList(user_no);
		return ticketList;
	}

}
