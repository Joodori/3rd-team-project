-- =================================================================
-- 놀이공원 자연스러운 군집 형태의 좌표 및 랜덤 시간 로그 생성
-- =================================================================

use  land;
-- 1. 테이블 생성
-- -----------------------------------------------------------------
DROP TABLE IF EXISTS `crowd_log`;

CREATE TABLE `crowd_log` (
    `id`        BIGINT        NOT NULL AUTO_INCREMENT COMMENT '좌표 고유 id',
    `latitude`  DECIMAL(15, 12) NOT NULL COMMENT '위도',
    `longitude` DECIMAL(16, 12) NOT NULL COMMENT '경도',
    `log_time`  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '좌표가 찍힌 시간',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='사용자 위치 좌표 정보';


-- 2. 개선된 데이터 삽입용 저장 프로시저 생성
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS InsertNaturalCoordinates;

DELIMITER $$

CREATE PROCEDURE InsertNaturalCoordinates()

BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE total_points INT DEFAULT 2000; -- 생성할 총 포인트 수
    
    -- 랜덤 시간 생성을 위한 변수
    DECLARE v_start_time DATETIME DEFAULT '2025-09-16 09:00:00';
    DECLARE v_end_time   DATETIME DEFAULT '2025-09-16 18:00:00';
    
    DECLARE v_start_unix INT DEFAULT UNIX_TIMESTAMP(v_start_time);
    DECLARE v_end_unix   INT DEFAULT UNIX_TIMESTAMP(v_end_time);
    
    DECLARE v_time_range INT DEFAULT v_end_unix - v_start_unix;

    -- 핫스팟 주변 좌표 생성을 위한 변수
    DECLARE hotspot_index INT;
    DECLARE base_lat DECIMAL(15, 12);
    DECLARE base_lng DECIMAL(16, 12);
    DECLARE spread DECIMAL(10, 8) DEFAULT 0.0005; -- 좌표가 흩어지는 범위 (이 값을 조절하여 군집 크기 변경)

    WHILE i <= total_points DO
        -- 1. 3개의 핫스팟 중 하나를 랜덤으로 선택
        SET hotspot_index = FLOOR(1 + RAND() * 3);

        -- 2. 선택된 핫스팟의 기본 좌표를 설정 (CASE 문 사용)
        CASE hotspot_index
            WHEN 1 THEN -- 롤러코스터
                SET base_lat = 37.495421;
                SET base_lng = 126.887801;
            WHEN 2 THEN -- 회전목마
                SET base_lat = 37.494664;
                SET base_lng = 126.888772;
            WHEN 3 THEN -- 먹거리존
                SET base_lat = 37.494294;
                SET base_lng = 126.887646;
        END CASE;

        -- 3. 선택된 핫스팟 주변에 랜덤 좌표 생성하여 삽입
        INSERT INTO `crowd_log` (latitude, longitude, log_time)
        VALUES (
            -- 기본 위도 ± 랜덤 오프셋
            base_lat + (RAND() * 2 - 1) * spread,
            -- 기본 경도 ± 랜덤 오프셋
            base_lng + (RAND() * 2 - 1) * spread,
            -- 랜덤 시간
            FROM_UNIXTIME(v_start_unix + FLOOR(RAND() * v_time_range))
        );
        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

-- 3. 프로시저 실행 및 결과 확인
-- -----------------------------------------------------------------
CALL InsertNaturalCoordinates();
SELECT * FROM `crowd_log` LIMIT 100;