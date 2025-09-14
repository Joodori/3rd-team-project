package lx.edu.levelup.feature.ticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
