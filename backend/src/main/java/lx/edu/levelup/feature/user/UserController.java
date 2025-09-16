package lx.edu.levelup.feature.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository repo;

	/*
	 * 회원가입 / 행 개수 반환 (성공시 1반환), 근데 null 값 있어도 1 반환하니까 입력값 없으면 입력하라고 프론트에서 ㄱㄱ
	 */
	@PostMapping("/insert")
	public int insert(@RequestBody UserVO vo) {
		int result = repo.insertDB(vo);
		return result; // 행 개수 반환 (회원가입 성공시 1 반환) 
	}

	/*
	 * 아이디 중복확인 / id 조회 후 해당 id 가진 사람 정보 반환, 없으면 빈 배열 반환
	 */
	@PostMapping("/check-id")
	public List<UserVO> checkId(@RequestBody UserVO vo) {
		List<UserVO> result = repo.findUser(vo);
		return result;
	}

	/*
	 * 로그인 / id, pw 조회 후 해당 id 가진 사람 정보 반환, 없으면 빈 배열 반환
	 * 이 반환값을 프론트에서 받아서 마이페이지에 적용
	 * 세션 사용 안하니까 프론트에서 관리자 아이디 구분해서 관리해야됨. userNo도
	 */
	@PostMapping("/login")
	public List<UserVO> login(@RequestBody UserVO vo, HttpSession session) {
		List<UserVO> result = repo.findUser(vo);
		return result;
	}

	/*
	 * 아이디 찾기 (이름, 주소 입력로) / 있으면 아이디 반환, 없으면 1반환 (null 문자열)
	 * 스프링 json 변환시 null을 그대로 못주고 1같은 기본 값으로 변환됨.
	 */
	@PostMapping("/find-id")
	public String findId(@RequestBody UserVO vo) {
		String userId = repo.findUserId(vo);
		return userId;
	}

	/*
	 * 비밀번호 찾기 (이름, 이메일, 아이디로) / 있으면 비밀번호 반환, 없으면 1 반환
	 */
	@PostMapping("/find-pw")
	public String findPw(@RequestBody UserVO vo) {
		String userPw = repo.findUserPw(vo);
		return userPw;
	}
	
	/*
	 * 회원 탈퇴
	 */
	@PostMapping("/delete-account")
	public int deleteAccount(@RequestBody UserVO vo) {
		int result = repo.deleteAccount(vo);
		return result;
	}

}
