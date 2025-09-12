package lx.edu.levelup.feature.ticket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequestDTO {
	
	private String ticket_name;
	private int user_no;
	private int ticket_amount;
	private String ticket_money_status;

}
