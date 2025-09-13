package lx.edu.levelup.feature.ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDTO {
	
	private String ticketName;
	private int userNo;
	private int ticketAmount;
	private String ticketMoneyStatus;

}
