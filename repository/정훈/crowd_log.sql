-- =================================================================
-- 놀이공원 좌표 및 랜덤 시간 로그 생성 (저장 프로시저 버전)
-- =================================================================

-- 1. 테이블 생성
-- -----------------------------------------------------------------
drop table if exists `crowd_log`;

create table `crowd_log` (
    `id`          bigint          not null auto_increment comment '좌표 고유 id',
    `latitude`    decimal(15, 12) not null comment '위도',
    `longitude`   decimal(16, 12) not null comment '경도',
    `log_time`    timestamp       not null default current_timestamp comment '좌표가 찍힌 시간',
    primary key (`id`)
) engine=innodb comment='사용자 위치 좌표 정보';


-- 2. 데이터 삽입용 저장 프로시저 생성
-- -----------------------------------------------------------------
drop procedure if exists InsertRandomCoordinates;

-- delimiter: 명령어의 끝을 ; 대신 $$로 임시 변경
delimiter $$

create procedure InsertRandomCoordinates()
begin
    -- 반복을 위한 변수 선언
    declare i int default 1;

    -- 랜덤 시간 범위를 설정하기 위한 변수 선언
    declare v_start_time datetime default '2025-09-14 09:00:00'; -- 시작 시간
    declare v_end_time   datetime default '2025-09-14 18:00:00'; -- 종료 시간

    -- 시작 시간과 종료 시간을 초 단위(unix 타임스탬프)로 변환
    declare v_start_unix int default unix_timestamp(v_start_time);
    declare v_end_unix   int default unix_timestamp(v_end_time);

    -- 시간 범위의 총 초(second)를 계산
    declare v_time_range int default v_end_unix - v_start_unix;

    -- i가 100이 될 때까지 반복
    while i <= 100 do
        insert into `crowd_log` (latitude, longitude, log_time)
        values (
            -- 최소 위도 + (랜덤값 * (최대 위도 - 최소 위도))
            37.493611 + (rand() * (37.495912 - 37.493611)),
            -- 최소 경도 + (랜덤값 * (최대 경도 - 최소 경도))
            126.886075 + (rand() * (126.889436 - 126.886075)),
            -- 시작 시간(초) + (랜덤값 * 시간 범위(초))를 다시 datetime으로 변환
            from_unixtime(v_start_unix + floor(rand() * v_time_range))
        );
        set i = i + 1;
    end while;
end$$

-- delimiter: 명령어의 끝을 다시 ;로 원복
delimiter ;


-- 3. 프로시저 실행 및 결과 확인
-- -----------------------------------------------------------------
-- 위에서 생성한 프로시저를 호출하여 데이터를 삽입
call InsertRandomCoordinates();

-- 생성된 데이터 확인 (log_time이 랜덤하게 들어갔는지 확인)
select * from `crowd_log` order by log_time asc limit 100;
