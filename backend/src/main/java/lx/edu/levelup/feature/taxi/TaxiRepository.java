package lx.edu.levelup.feature.taxi;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/*
 * DB 접근 전담
 */

@Repository
public class TaxiRepository {

	@Autowired
	SqlSession session;
	
	/*	
	public AddrBookVO getDB(int abId) {
		return session.selectOne("getDB",abId);
	}
	*/
	
}
