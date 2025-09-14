package lx.edu.levelup.feature.ticket;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/*
 * DB 접근 전담, mapperInterface 역할
 */
@Mapper
public interface TicketMapper {

	
		void insertDB(TicketVO vo);
		
		List<TicketResponseDTO> getTicketList(@Param("user_no") int user_no);
		

	
}
