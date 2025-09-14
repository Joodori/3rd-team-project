package lx.edu.levelup.feature.heatmap;

import lx.edu.levelup.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heatmap")
public class HeatMapController {

    @Autowired
    private HeatMapService heatmapService;

    // 클래스 상단에 Logger 객체를 생성합니다.
    private static final Logger logger = LoggerFactory.getLogger(HeatMapController.class);

    /**
     * 최근 히트맵 데이터를 JSON 형태로 제공하는 API
     * GET /api/heatmap/points
     * @return HeatmapDTO 리스트 (JSON 배열)
     */
    @GetMapping("/points")
    public List<HeatMapDTO> getHeatmapPoints(@RequestParam("minutes") int minutes) {
        // 서비스 레이어를 호출하여 최근 30분간의 데이터를 조회합니다.
        logger.info("======= getHeatmapPoints 실행됨 =======");
        return heatmapService.getRecentHeatmapPoints(minutes);
    }
}