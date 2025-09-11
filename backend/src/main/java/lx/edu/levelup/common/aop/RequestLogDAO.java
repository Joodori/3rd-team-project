package lx.edu.levelup.common.aop;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestLogDAO {
	
	@Autowired
	SqlSession session;

	public int insertLogDB(RequestLogVO vo) throws Exception {
		return session.insert("insertLogDB", vo);
	}


}
