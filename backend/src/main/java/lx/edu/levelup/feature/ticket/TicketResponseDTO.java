package lx.edu.levelup.feature.ticket;

import lombok.Getter;

@Getter
public class TicketResponseDTO {
	
	private Long ticketNo;
	private String ticketName;
	private String ticketReserveDate;
	private int ticketAmount;
	private String ticketMoneyStatus;

	public static TicketResponseDTO fromEntity(lx.edu.levelup.feature.ticket.domain.TicketVO ticket) {
		TicketResponseDTO dto = new TicketResponseDTO();
		dto.ticketNo = ticket.getTicketNo();
		dto.ticketName = ticket.getTicketName();
		dto.ticketReserveDate = ticket.getTicketReserveDate();
		dto.ticketAmount = ticket.getTicketAmount();
		dto.ticketMoneyStatus = ticket.getTicketMoneyStatus();
		return dto;
	}
	
	
}
