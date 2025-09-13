package lx.edu.levelup.feature.user;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lx.edu.levelup.feature.user.domain.UserVO;

/*
 * DB 접근 담당
 */

@Repository
public class UserRepository {

	@Autowired
	SqlSession session;

	// 회원가입
	public int insertDB(UserVO vo) {
		return session.insert("mapper-user.insertDB", vo);
	}

	// 아이디 중복확인 or 로그인 (동적 sql 활용함)
	public List<UserVO> findUser(UserVO vo) {
		return session.selectList("mapper-user.findUser", vo);
	}

	// 아이디 찾기
	public String findUserId(UserVO vo) {
		return session.selectOne("mapper-user.findUserId", vo);
	}

	// 비밀번호 찾기
	public String findUserPw(UserVO vo) {
		return session.selectOne("mapper-user.findUserPw", vo);
	}

}
