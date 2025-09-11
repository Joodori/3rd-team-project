package lx.edu.levelup.feature.ticket;


public class TicketResponseDTO {
	
	private Long ticket_no;
	private String ticket_name;
	private String ticket_reserve_date;
	private int adult;
	private int children;
	
	public static TicketResponseDTO fromEntity(lx.edu.levelup.feature.ticket.domain.TicketVO ticket) {
		TicketResponseDTO dto = new TicketResponseDTO();
		dto.ticket_no = ticket.getTicket_no();
		dto.ticket_name = ticket.getTicket_name();
		dto.ticket_reserve_date = ticket.getTicket_reserve_date();
		dto.adult = ticket.getAdult();
		dto.children = ticket.getChildren();
		return dto;
	}
	
	
}
