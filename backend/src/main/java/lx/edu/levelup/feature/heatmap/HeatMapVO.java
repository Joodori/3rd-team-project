package lx.edu.levelup.feature.heatmap;

/*
 * domain이란?   (일종의 vo를 모아뒀으며, DB의 정보를 여기서 매칭)
 * 1. User 같은 엔티티
 * 2. Email, Password 같은 VO
 * 3. Role, Status 같은 enum
 * 4. → DB, 비즈니스 규칙과 직접 맞닿아 있음
 */

import lombok.*;

@Data// @Getter @Setter @ToString @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 생성
public class HeatMapVO {
    private Long logId;
    private double latitude;
    private double longitude;

}