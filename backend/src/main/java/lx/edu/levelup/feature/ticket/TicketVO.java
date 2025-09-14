package lx.edu.levelup.feature.ticket;

import lombok.Data;

@Data
public class TicketVO {
	
	private Long ticketNo;
    private int userNo;
    private String ticketName;
    private String ticketReserveDate;
    private int ticketAmount;  	
    private String ticketMoneyStatus;;  	
	
	
	


}
