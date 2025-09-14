package lx.edu.levelup.feature.ticket;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;


/*
 * DB 접근 전담, mapperInterface 역할
 */
@Mapper
public interface TicketRepository {

	
		void insertDB(TicketVO vo);
		
		List<TicketResponseDTO> getTicketList(@RequestParam("user_no") int user_no);

		int updateMoneyStatusUser(@Param("ticket_no") int ticket_no);
		
		int updateMoneyStatusManager(@Param("ticket_no") int ticket_no);

	
}
