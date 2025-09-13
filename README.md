## 백  :스프링 6.2.10+Mybatis 3.5.19 + mysql +lombok +java21 + tomcat 10.1
## 프론트 : Vue3 + Pinia + (v-calendar), axios


## 프로젝트 내에서 사용할 단어선택

## css/ui 프레임워크 >  bootstrap 사용

## 환경설정
config > java

## jdk 버전
java - jdk 17

## 서버
tomcat 10.1

## Spring 아키텍쳐
DDD(도메인 방식)

## pom.xml(maven) > dependency

spring-aspects : 6.2.10

log4j : 1.2.17
jackson-databind : 2.15.2
jaxb-api : 2.3.1
spring-jdbc : 6.2.10
mybatis : 3.5.19
mybatis-spring : 3.0.5
commons-dbcp2 : 2.13.0
mysql-connector-j : 8.2.0
spring-context : 6.2.10
spring-webmvc : 6.2.10
jakarta.servlet.jsp.jstl-api : 3.0.2
slf4j-api : 1.7.36
slf4j-log4j12 : 1.7.36

src/main/java의 구조
lx.edu.levelup 
 └─  
     ├─ login
     │   ├─  domain
     │   ├─  dto
     │   └─  controller 
     │   └─  service
     │   └─  repository
     ├─ heatmap
     │   ├─  domain
     │   ├─  dto
     │   └─  controller 
     │   └─  service
     │   └─  repository
     ├─ taxi 
     │   ├─  domain
     │   ├─  dto
     │   └─  controller 
     │   └─  service
     │   └─  repository
     ├─ ticket
     │   ├─  domain
     │   ├─  dto
     │   └─  controller 
     │   └─  service
     │   └─  repository 
     ├─ locationsharing 
     │   ├─  domain
     │   ├─  dto
     │   └─  controller 
     │   └─  service
     │   └─  repository 
     │
     ├─ config
     │   └─  WebmvcConfig  #인터셉터, cors, 메시지 컨버터
     │   └─  MyBatisConfig   # DataSource, SqlSession, Mapper
     │   └─  AOPConfig 
     ├─ aop
     └─ interceptor 

src/main/resources
 ├─ application.properties        # 전체 환경설정 (DB, 포트, 로깅 등)
 ├─ mapper                        # SQL 매퍼 XML
 │   ├─user-mapper.xml
 │   ├─ ticket-mapper.xml

 │   ├─ taxi-mapper.xml
 │   ├─ heatmap-mapper.xml
 │   └─ locationsharing-mapper.xml
 ├─ static/                       # 정적 리소스 (JS, CSS, 이미지)
 │   ├─ css/
 │   ├─ js/
 │   └─ img/
 └─ 




우리 프로젝트는 완벽한 DDD는 아니다 DDD-lite정도
기능
 ├─ domain        # 엔티티/VO/도메인 서비스(규칙)만
 │   ├─ model     # Entity/VO/Enum
 │   └─ service   # 도메인 서비스(규칙 모듈) *선택
 ├─ application   # 유스케이스(Service: 트랜잭션/흐름, DTO 변환 X)
 ├─ infrastructure# MyBatis 매퍼, 외부 API, 구현체
 └─ interfaces    # Controller, DTO, Mapper(엔티티↔DTO)
