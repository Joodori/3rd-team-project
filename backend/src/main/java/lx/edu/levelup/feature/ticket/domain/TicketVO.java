package lx.edu.levelup.feature.ticket.domain;

import lombok.*;

@Data// @Getter @Setter @ToString@EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
public class TicketVO {

	private Long ticketNo;
    private int userNo;
    private String ticketName;
    private String ticketReserveDate;
    private int ticketAmount;
    private String ticketMoneyStatus;;


}
