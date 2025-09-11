package lx.edu.levelup.feature.ticket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class TicketVO {

	private Long ticket_no;
	private String ticket_name;
	private String ticket_reserve_date;
	private int adult;  	// 성인티켓 구매수량
	private int children;  	// 아동티켓 구매수량
	
	
	// 생성자
	public TicketVO(Long ticket_no, String ticket_name, String ticket_reserve_date, int adult, int children) {
		this.ticket_no = ticket_no;
		this.ticket_name = ticket_name;
		this.ticket_reserve_date = ticket_reserve_date;
		this.adult = adult;
		this.children = children;
	}
	
	// getter & setter
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
