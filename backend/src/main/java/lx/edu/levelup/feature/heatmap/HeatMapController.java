package lx.edu.levelup.feature.heatmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/heatmap")
public class HeatMapController {

    @Autowired
    private HeatMapService heatmapService;

    /**
     * 최근 히트맵 데이터를 JSON 형태로 제공하는 API
     * GET /api/heatmap/points
     * @return HeatmapDTO 리스트 (JSON 배열)
     */
    @GetMapping("/points")
    public List<HeatMapDTO> getHeatmapPoints() {
        // 서비스 레이어를 호출하여 최근 30분간의 데이터를 조회합니다.
        return heatmapService.getRecentHeatmapPoints(30);
    }
}