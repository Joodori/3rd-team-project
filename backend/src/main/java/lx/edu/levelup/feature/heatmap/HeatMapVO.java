package lx.edu.levelup.feature.heatmap;

import lombok.*;

import java.math.BigDecimal;

@Data// @Getter @Setter @ToString @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
public class HeatMapVO {
    private Long Id;
    private BigDecimal latitude;
    private BigDecimal longitude;

}