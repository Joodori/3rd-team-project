package lx.edu.levelup.feature.ticket;

import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {
	
	private final TicketRepository ticketMapper;
	
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
		vo.setTicketReserveDate(ticketRequestDTO.getTicketReserveDate());
		vo.setTicketMoneyStatus(ticketRequestDTO.getTicketMoneyStatus());
		
		ticketMapper.insertDB(vo);
		return true;
		
	}
	
	public List<TicketResponseDTO> getTicketList(int user_no) {
		List<TicketResponseDTO> ticketList = ticketMapper.getTicketList(user_no);
		return ticketList;
	}

	public List<TicketResponseDTO> getTicketListAdmin() {
		List<TicketResponseDTO> ticketList = ticketMapper.getTicketListAdmin();
		return ticketList;
	}
	
	public boolean updateMoneystatusUser(int ticket_no) {
		System.out.println("넘어온 :" + ticket_no);

		int result = ticketMapper.updateMoneyStatusUser(ticket_no);
		return result > 0 ;
		
	}
	
	public boolean updateMoneystatusManager(int ticket_no) {
		System.out.println("넘어온 :" + ticket_no);
		int result = ticketMapper.updateMoneyStatusManager(ticket_no);
		return result > 0 ;
		
	}

}
