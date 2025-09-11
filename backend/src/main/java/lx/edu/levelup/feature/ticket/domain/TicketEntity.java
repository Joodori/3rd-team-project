package lx.edu.levelup.feature.ticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TicketEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticket_no;
	
	private String ticket_name;
	private String ticket_reserve_date;
	// 성인티켓 구매수량
	private int adult;
	// 아동티켓 구매수량
	private int children;
	
	
	
	public Long getTicket_no() {
		return ticket_no;
	}
	public String getTicket_name() {
		return ticket_name;
	}
	public String getTicket_reserve_date() {
		return ticket_reserve_date;
	}
	public int getAdult() {
		return adult;
	}
	public int getChildren() {
		return children;
	}
	

	
	
}
