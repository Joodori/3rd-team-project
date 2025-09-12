package lx.edu.levelup.feature.ticket.domain;


import lombok.*;

@Data// @Getter @Setter @ToString@EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
public class TicketVO {

	private Long ticket_no;
	private String ticket_name;
	private String ticket_reserve_date;
	private int adult;  	// 성인티켓 구매수량
	private int children;  	// 아동티켓 구매수량


}
