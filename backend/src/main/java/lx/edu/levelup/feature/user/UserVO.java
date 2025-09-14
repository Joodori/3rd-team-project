package lx.edu.levelup.feature.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data// @Getter @Setter @ToString @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor // 매개변수 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
public class UserVO {

	int userNo;
	String userName;
	String userBirthDate;
	String userMobile;
	String userAddress;
	String userId;
	String userPassword;
	
	String userPasswordCipher;
	String userPasswordIv;
	
}
