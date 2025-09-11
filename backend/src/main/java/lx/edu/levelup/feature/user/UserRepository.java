package lx.edu.levelup.feature.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lx.edu.levelup.feature.user.domain.UserVO;

// Repository ==> DB접근, DAO랑 동일한 기능

@Repository
public class UserRepository {

	@Autowired
	SqlSession session;
	
	// 회원가입
	public int insertDB(UserVO vo) {
		return session.insert("mapper-user.insertDB", vo);
	}
	
	// 로그인
	
	// 아이디 찾기
	
	// 비밀번호 찾기
	
}
