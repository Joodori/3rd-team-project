package lx.edu.levelup.feature.ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDTO {
	
	private int ticketNo;
	private String ticketName;
	private int userNo;
	private String ticketReserveDate;
	private int ticketAmount;
	private String ticketMoneyStatus;

}
