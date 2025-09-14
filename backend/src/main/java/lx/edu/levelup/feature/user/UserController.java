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

	//@Autowired
	// TicketRepository ticketRepo; // 티켓 관련 db 접근 (마이페이지-티켓내역 조회시 사용)

	/*
	 * 회원가입 -> postman 확인함
	 * 행 개수 반환 (성공시 1 반환), 근데 null 값 있어도 1 반환 하니까 프론트에서 입력값 없으면 입력하라고 해야됨.
	 */
	@PostMapping("/insert")
	public int insert(@RequestBody UserVO vo) {
		int result = repo.insertDB(vo);
		return result; // 행 개수 반환 (회원가입 성공시 1 반환) 
	}

	/*
	 * 아이디 중복확인
	 * id 조회 후 해당 id를 가진 사람의 정보 반환, 없으면 빈 배열 반환
	 */
	@PostMapping("/check-id")
	public List<UserVO> checkId(@RequestBody UserVO vo) {
		List<UserVO> result = repo.findUser(vo);
		return result;
	}

	/*
	 * 로그인
	 * id, pw 조회 후 해당 i 가진 사람의 정보 반환, 없으면 빈 배열 반환
	 * 이 반환값을 프론트에서 받아서 마이페이지에 적용
	 * 세션 사용 안하니까 프론트에서 관리자 아이디 구분해서 관리해야됨. userNo도
	 */
	@PostMapping("/login")
	public List<UserVO> login(@RequestBody UserVO vo, HttpSession session) {
		List<UserVO> result = repo.findUser(vo);
		if (result.isEmpty()) {
			return result;
		}
		return result;
	}

	/*
	 * 아이디 찾기 (이름, 주소 입력로)
	 * 있으면 아이디 반환
	 * 없으면 1 반환 (null 문자열) -> 스프링 json 변환시 null을 그대로 못주고 1같은 기본 값으로 변환됨.
	 */
	@PostMapping("/find-id")
	public String findId(@RequestBody UserVO vo) {
		String userId = repo.findUserId(vo);
		return userId;
	}

	/*
	 * 비밀번호 찾기 (이름, 이메일, 아이디로)
	 * 있으면 비밀번호 반환
	 * 없으면 1 반환 (null 문자열)
	 */
	@PostMapping("/find-pw")
	public String findPw(@RequestBody UserVO vo) {
		String userPw = repo.findUserPw(vo);
		return userPw;
	}
	
	/*
	 * 회원 - 마이페이지 입장권 내역
	 */
	
	/*
	 * 회원 - 마이페이지 놀이기구 예약 내역
	 */

	/*
	 * 관리자 - 마이페이지 입금내역
	 */
}
