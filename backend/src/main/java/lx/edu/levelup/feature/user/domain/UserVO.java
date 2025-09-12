package lx.edu.levelup.feature.user.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data// @Getter @Setter @ToString@EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
public class UserVO {

	String userName;
	String userBirthDate;
	String userMobile;
	String userAddress;
	int userNo;
	String userId;
	String userPassword;
	int ticketNo;


}
