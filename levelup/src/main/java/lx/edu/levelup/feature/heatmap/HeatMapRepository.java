package lx.edu.levelup.feature.heatmap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/*
 * DB 접근 전담
 */

@Repository
public class HeatMapRepository {

	@Autowired
	SqlSession session;
	
	/*	
	public AddrBookVO getDB(int abId) {
		return session.selectOne("getDB",abId);
	}
	*/
	
}
