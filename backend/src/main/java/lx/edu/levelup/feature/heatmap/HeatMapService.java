package lx.edu.levelup.feature.heatmap;

import lx.edu.levelup.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HeatMapService {

    @Autowired
    private HeatMapRepository heatmapRepository;

    // 클래스 상단에 Logger 객체를 생성합니다.
    private static final Logger logger = LoggerFactory.getLogger(HeatMapService.class);

    /**
     * 최근 N분간의 히트맵 데이터를 조회하여 DTO 리스트로 반환합니다.
     * @param minutes 조회할 최근 시간(분)
     * @return HeatmapDTO 리스트
     */
    public List<HeatMapDTO> getRecentHeatmapPoints(int minutes) {

        /*
        LocalDate now = new LocalDate();
        //현재 년 월 일
        int year = now.getYear();         //년
        int month = now.getMonthValue();  //월
        int day = now.getDayOfMonth();    //일
        */

        LocalDateTime endTime = LocalDateTime.of(2025, 9, 14, 9, 30, 0);
        //LocalDateTime endTime = LocalDateTime.now();  //현재 시간이 9~18시사이가 아닐경우 now로 하면 오류가 발생함 why?      지금 시간에서 30분전 데이터를 조회하는건데  20시30분인데 30분전은 20시라서 놀이동산은 18시까지임 당연히 데이터가 없음
        LocalDateTime startTime = endTime.minusMinutes(minutes);

        // Mapper에 파라미터를 전달하기 위한 Map 생성
        Map<String, Object> params = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);

        // Repository(Mapper)를 호출하여 DB에서 VO 리스트를 가져옵니다.
        List<HeatMapVO> logs = heatmapRepository.findRecentLogs(params);
        logger.info("======= heatmapRepository.findRecentLogs(params) 실행됨 =======");

        // Stream API를 사용하여 VO 리스트를 DTO 리스트로 변환합니다.
        // Lombok의 @AllArgsConstructor 덕분에 new HeatmapDTO(...)가 가능합니다.
        return logs.stream()
                .map(log -> new HeatMapDTO(log.getLatitude(), log.getLongitude()))
                .collect(Collectors.toList());
    }
}