package lx.edu.levelup.feature.aop;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lx.edu.levelup.feature.aop.domain.RequestLogVO;

@Repository
public class RequestLogRepository {
	
	@Autowired
	SqlSession session;

	public int insertLogDB(RequestLogVO vo) throws Exception {
		return session.insert("mapper-log.insertLogDB", vo);
	}


}
