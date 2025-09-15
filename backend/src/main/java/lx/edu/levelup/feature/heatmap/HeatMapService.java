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
     * @param startTime, endTime 조회할 데이터
     * @return HeatmapDTO 리스트
     */
    public List<HeatMapDTO> getRecentHeatmapPoints(LocalDateTime startTime, LocalDateTime endTime) {

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