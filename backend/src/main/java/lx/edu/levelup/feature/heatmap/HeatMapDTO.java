package lx.edu.levelup.feature.heatmap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 히트맵 데이터 전송 객체 (Data Transfer Object)
 * Controller가 프론트엔드와 데이터를 주고받을 때 사용합니다.
 */
@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode 등을 모두 포함
@NoArgsConstructor // 파라미터 없는 기본 생성자
@AllArgsConstructor // 모든 필드를 포함하는 생성자
public class HeatMapDTO {
    private double lat;
    private double lng;
}