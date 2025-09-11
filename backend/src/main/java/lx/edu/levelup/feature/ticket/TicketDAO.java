package lx.edu.levelup.feature.ticket;

import org.apache.ibatis.annotations.Mapper;

import lx.edu.levelup.feature.ticket.domain.TicketVO;


public class TicketDAO {
	
	void insertTicket(TicketVO ticket) {
		Ticket selectTicket(Long ticket_no);
	}
}
