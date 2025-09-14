package lx.edu.levelup.feature.heatmap;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * MyBatis Mapper 인터페이스.
 * resources/mapper/mapper-heatmap.xml 파일의 SQL을 호출하는 역할을 합니다.
 */
@Mapper
public interface HeatMapRepository {


    /**
     * 특정 시간 범위 내의 위치 데이터 로그를 조회합니다.
     * @param params startTime, endTime을 담고 있는 Map 객체
     * @return HeatmapVO 객체들의 리스트
     */
    List<HeatMapVO> findRecentLogs(Map<String, Object> params);

}