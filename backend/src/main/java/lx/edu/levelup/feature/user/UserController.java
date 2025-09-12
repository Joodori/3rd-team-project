package lx.edu.levelup.feature.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lx.edu.levelup.feature.locationsharing.LocationSharingController;
import lx.edu.levelup.feature.ticket.TicketRepository;
import lx.edu.levelup.feature.user.domain.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository repo;

	@Autowired
	TicketRepository ticketRepo; // 티켓 관련 db 접근 (마이페이지-티켓내역 조회시 사용)

	// ================= 중복코드 메서드 =================
	private Map<String, Object> response(String status, String message) {
		Map<String, Object> res = new HashMap<>();
		res.put("status", status); // 성공, 실패 여부
		res.put("message", message); // 사용자에게 보여줄 메세지
		return res;
	}

	// ================= 회원가입 메서드 =================
	@RequestMapping("/insert")
	public Map<String, Object> insert(UserVO vo) {
		int result = repo.insertDB(vo);
		if (result > 0) { // 영향받은 행 개수 1개 이상이면 성공
			return response("success", "회원가입 성공");
		} else {
			return response("fail", "회원가입 실패");
		}
	}

	// ================= 아이디 중복확인 메서드 ================= (중복확인 버튼이 있는 가정 하에)
	@RequestMapping("/check-id")
	public Map<String, Object> checkId(UserVO vo) {
		if (repo.findUser(vo) != null) { // DB에 해당 userId 이미 존재하는지 조건
			return response("fail", "이미 존재하는 아이디"); // 있으면 fail 응답
		} else {
			return response("success", "사용 가능한 아이디"); // 없으면 success 응답
		}
	}

	// ================= 로그인 메서드 =================
	@RequestMapping("/login")
	public Map<String, Object> login(UserVO vo, HttpSession session) {
		UserVO user = repo.findUser(vo); // 아이디, 비번 일치하는 사용자 찾기
		if (user == null) { // 없으면 로그인 실패
			return response("fail", "아이디 또는 비밀번호 불일치");
		}
		if ("admin".equals(vo.getUserId()) && "1111".equals(vo.getUserPassword())) { // 관리자 계정 하드코딩
			session.setAttribute("role", "ADMIN"); // 관리자 구분용 키밸류
			session.setAttribute("userId", "admin");
			return response("success", "관리자 로그인 성공");
		}
        session.setAttribute("role", "USER"); // 일반 사용자 구분용 키밸류
		session.setAttribute("userId", user.getUserId()); // 있으면 세션에 userId 저장
		return response("success", "로그인 성공");
	}
	
	// ================= 로그인 상태 확인용 메서드 =================
	// 프론트에선 피냐 같은 공통저장소에 넣어놓고, onMounted로 로그인 상태 확인 해주면 될 듯? 아마도?
	@RequestMapping("/check-login")
	public Map<String, Object> checkLogin(HttpSession session) {
	    String userId = (String) session.getAttribute("userId");
	    if (userId == null) {
	        return response("fail", "로그인 안 됨");
	    }
	    Map<String, Object> res = response("success", "로그인 유지됨");
	    res.put("userId", userId);
	    res.put("role", session.getAttribute("role"));
	    return res;
	}

	// ================= 로그아웃 메서드 =================
	@RequestMapping("/logout")
	public Map<String, Object> logout(HttpSession session) {
		session.invalidate(); // 세션 전체 삭제
		return response("success", "로그아웃 성공"); // 로그아웃 성공 메세지 반환
	}

	// ================= 아이디 찾기 메서드 =================
	@RequestMapping("/find-id")
	public Map<String, Object> findId(UserVO vo) {
		String user = repo.findUserId(vo); // 조건에 맞는 userId 찾기
		if (user == null) { // 없으면 실패 메세지
			return response("fail", "해당 정보로 가입된 아이디 없음.");
		}
		Map<String, Object> res = response("success", "아이디 찾기 성공");
		res.put("userId", user); // 있으면 조회된 아이디를 응답에 추가해서 반환
		return res;
	}
	
	// ================= 비밀번호 찾기 메서드 =================
		@RequestMapping("/find-pw")
		public Map<String, Object> findPw(UserVO vo) {
			String user = repo.findUserPw(vo); // 조건에 맞는 userId 찾기
			if (user == null) { // 없으면 실패 메세지
				return response("fail", "해당 정보로 가입된 아이디 없음.");
			}
			Map<String, Object> res = response("success", "아이디 찾기 성공");
			res.put("userId", user); // 있으면 조회된 아이디를 응답에 추가해서 반환
			return res;
		}

	// ================= 관리자 및 회원 : 마이페이지에 놀이기구 예약 내역 출력하는 메서드 =================
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

}
