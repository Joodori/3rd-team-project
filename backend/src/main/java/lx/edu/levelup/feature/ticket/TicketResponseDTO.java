//package lx.edu.levelup.feature.ticket;
//
//import lombok.Getter;
//
//@Getter
//public class TicketResponseDTO {
//	
//	private Long ticket_no;
//	private String ticket_name;
//	private String ticket_reserve_date;
//	private int ticket_amount;  	
//	private String ticket_money_status;  	
//	
//	public static TicketResponseDTO fromEntity(lx.edu.levelup.feature.ticket.domain.TicketVO ticket) {
//		TicketResponseDTO dto = new TicketResponseDTO();
//		dto.ticket_no = ticket.getTicket_no();
//		dto.ticket_name = ticket.getTicket_name();
//		dto.ticket_reserve_date = ticket.getTicket_reserve_date();
//		dto.ticket_amount = ticket.getTicket_amount();
//		dto.ticket_money_status = ticket.getTicket_money_status();
//		return dto;
//	}
//	
//	
//}
