package lx.edu.levelup.feature.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lx.edu.levelup.feature.user.domain.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository repo;

	@Autowired
	// TicketRepository ticketRepo; // 티켓 관련 db 접근 (마이페이지-티켓내역 조회시 사용)

	/// ================= 회원가입 =================
	@PostMapping("/insert")
	public int insert(@RequestBody UserVO vo) {
		int result = repo.insertDB(vo);
		return result; // 행 개수 반환
	}

	// ================= 아이디 중복확인 =================
	@PostMapping("/check-id")
	public UserVO checkId(@RequestBody UserVO vo) {
		UserVO result = repo.findUser(vo);
		return result;
	}

	// ================= 로그인 =================
	@PostMapping("/login")
	public UserVO login(@RequestBody UserVO vo, HttpSession session) {
		UserVO result = repo.findUser(vo);

		if (result == null) { // db에 아이디 없으면 로그인 실패
			return result;
		}
		if ("admin".equals(vo.getUserId()) && "1111".equals(vo.getUserPassword())) { // 관리자 계정 하드코딩
			session.setAttribute("role", "ADMIN"); // 관리자 구분용 키밸류
			session.setAttribute("userId", "admin");
			return result;
		}
		session.setAttribute("role", "USER"); // 일반 사용자 구분용 키밸류
		session.setAttribute("userId", result.getUserId()); // 있으면 세션에 userId 저장
		return result;
	}

	// ================= 이름이랑 주소로 아이디 찾기 =================
	@PostMapping("/find-id")
	public String findId(@RequestBody UserVO vo) {
		String userId = repo.findUserId(vo);
		return userId;
	}

	// ================= 이름, 이메일, 아이디로 비밀번호 찾기 메서드 =================
	@PostMapping("/find-pw")
	public String findPw(@RequestBody UserVO vo) {
		String userPw = repo.findUserPw(vo);
		return userPw;
	}

	// ================= 관리자 및 회원 : 마이페이지에 놀이기구 예약 내역 출력하는 메서드 =================
	@GetMapping
	public Map<String, Object> myPage(HttpSession session) {
		String role = (String) session.getAttribute("role");
		String userId = (String) session.getAttribute("userId"); // 세션에 저장해둔 로그인 사용자 id 꺼내오기
		if (userId == null) {
			return response("fail", "로그인 필요");
		}
		// 관리자일 경우 전체 예약 내역 최신순 조회 -- 구현 예정
		// 회원일 경우 본인 예약 내역 조회 -- 구현 예정
		return null;
	}

	// ================= 관리자 및 회원 : 마이페이지에 놀이기구 예약 내역 출력하는 메서드 =================

	// 관리자

	// ================= 중복코드 메서드 =================
	private Map<String, Object> response(String status, String message) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", status); // 성공, 실패 여부
		res.put("message", message); // 사용자에게 보여줄 메세지
		return res;
	}

}
