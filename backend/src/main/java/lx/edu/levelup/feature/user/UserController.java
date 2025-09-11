package lx.edu.levelup.feature.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lx.edu.levelup.feature.user.domain.UserVO;

// Controller ==> 요청 받기, Repository 호출

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ApplicationContext context;

	@Autowired
	UserRepository repo;
	
	// 회원가입
	@RequestMapping("/insert.do")
	public String insert(UserVO vo) {
		repo.insertDB(vo);
		return "회원가입 성공";
	}
	
	// 로그인
	
}
