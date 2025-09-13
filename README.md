# 🚕 [프로젝트 이름]

> 프로젝트에 대한 한 줄 소개를 작성합니다. (예: 택시 동승 및 실시간 위치 공유 서비스)

## 📝 개요

이 프로젝트는 OO을 목표로 하는 웹 애플리케이션입니다. Spring Framework와 Vue.js를 기반으로 개발되었으며, 주요 기능으로는 실시간 위치 공유, 택시 호출, 히트맵 기반 정보 제공 등이 있습니다.

---

## 🛠️ 기술 스택 (Tech Stack) & 아키텍처

### 벡엔드 (Backend)
- **Framework**: Spring Framework `6.2.10`
- **Data Access**: MyBatis `3.5.19`
- **Database**: MySQL `8.2.0`
- **Language**: Java `21` (JDK 17 이상)
- **WAS**: Apache Tomcat `10.1`
- **Utility**: Lombok

### 프론트엔드 (Frontend)
- **Framework**: Vue 3
- **State Management**: Pinia
- **UI**: Bootstrap, v-calendar
- **HTTP Client**: Axios

### 🏛️ 아키텍처
본 프로젝트는 **기능별 패키지 구조의 계층형 아키텍처(Layered Architecture with Package-by-Feature)**를 따릅니다. 각 기능(`heatmap`, `user` 등)은 독립적인 패키지로 구성되어 응집도를 높이고 결합도를 낮춥니다.

---


## 📁 프로젝트 구조

### `src/main/java`

```
└── lx.edu.levelup
    ├── common
    │   ├── config
    │   │   ├── SpringConfig.java
    │   │   ├── WebInitializer.java
    │   │   └── WebMVCConfig.java
    │   └── filter
    │       └── Filter.java
    ├── feature
    │   ├── aop.domain
    │   ├── heatmap
    │   │   ├── HeatMapController.java
    │   │   ├── HeatMapService.java
    │   │   ├── HeatMapRepository.java
    │   │   ├── HeatMapDTO.java
    │   │   └── HeatMapVO.java
    │   ├── locationsharing
    │   ├── taxi
    │   ├── ticket
    │   └── user
    │       ├── UserController.java
    │       └── UserRepository.java
    └── HomeController.java
```

### `src/main/resources`

```
└── resources
    ├── mapper
    │   ├── mapper-heatmap.xml
    │   ├── mapper-locationsharing.xml
    │   ├── mapper-taxi.xml
    │   ├── mapper-ticket.xml
    │   └── mapper-user.xml
    ├── application.properties
    └── logback.xml
```


각 도메인(기능)을 독립적인 패키지로 구성하여 응집도를 높이고 다른 기능과의 결합도를 낮춥니다. 예를 들어, `heatmap` 기능에 관련된 모든 클래스(`Controller`, `Service`, `Repository`, `DTO` 등)는 `feature.heatmap` 패키지 내에 위치하여 관리됩니다.

  - **`common`**: 여러 기능에서 공통으로 사용되는 설정(Configuration), 필터(Filter) 등을 관리합니다.
  - **`feature`**: 애플리케이션의 핵심 기능들이 모여있는 패키지입니다.
      - **`Controller`**: HTTP 요청을 처리하고 응답을 반환하는 API 엔드포인트 계층입니다.
      - **`Service`**: 비즈니스 로직을 처리하는 서비스 계층입니다.
      - **`Repository`**: 데이터베이스와의 통신을 담당하는 데이터 접근 계층입니다.
      - **`DTO` (Data Transfer Object)**: 계층 간 데이터 전송을 위해 사용되는 객체입니다.
      - **`VO` (Value Object)**: 값 자체를 표현하는 불변 객체입니다.



---

## ⚙️ 시작하기 (Getting Started)

### Prerequisites (요구 사항)
- Java `21` (JDK 17+)
- Apache Maven `3.8+`
- MySQL `8.0+`
- Node.js `18+`

### Installation & Run (설치 및 실행)

0. **Project Clone**
 # Repository 클론
    git clone [your-repository-url]

1.  **Backend**
    ```bash
   
    cd [backend-directory]

    # application.properties 파일에 DB 정보 설정
    # (src/main/resources/application.properties)

    # DataBase

    MySQL Connections 172.168.10.16 에 접속 
    repository/land_DB.sql 실행 
    

    # Maven 빌드
    mvn clean install

    # Tomcat 서버 실행
    # (IDE의 Tomcat 실행 구성을 사용하거나 Maven 플러그인 사용)
    ```

2.  **Frontend**
    ```bash
    cd [frontend-directory]

    # 의존성 설치
    npm install
    npm.cmd install v-calendar@next @popperjs/core
    npm.cmd install axios   

    # 개발 서버 실행
    npm run dev
    ```

---

## ✨ 주요 기능


- **실시간 위치 공유**: 친구의 위치를 확인하거나 택시 동승자 간의 실시간 위치 정보를 공유하는 기능

- **티켓 예매**: 특정 시간과 경로의 택시 탑승권을 사전에 예매하고 관리하는 기능

- **히트맵**: 사용자의 위치 데이터를 기반으로 택시 수요가 많은 지역을 시각적으로 보여주는 기능




## 📦 주요 의존성 (pom.xml)

| Group ID | Artifact ID | Version | Description |
| :--- | :--- | :--- | :--- |
| `org.springframework` | `spring-context` | `6.2.10` | Spring Core |
| `org.springframework` | `spring-webmvc` | `6.2.10` | Spring MVC |
| `org.springframework` | `spring-jdbc` | `6.2.10` | Spring JDBC |
| `org.springframework` | `spring-aspects` | `6.2.10` | Spring AOP |
| `org.mybatis` | `mybatis` | `3.5.19` | MyBatis Core |
| `org.mybatis` | `mybatis-spring` | `3.0.5` | MyBatis-Spring 연동 |
| `com.fasterxml.jackson.core`| `jackson-databind` | `2.15.2` | JSON 데이터 처리 |
| `org.apache.commons` | `commons-dbcp2` | `2.13.0` | DBCP (Database Connection Pool) |
| `com.mysql` | `mysql-connector-j` | `8.2.0` | MySQL 드라이버 |
| `org.slf4j` | `slf4j-api` | `1.7.36` | 로깅 Facade |
| `org.slf4j` | `slf4j-log4j12` | `1.7.36` | SLF4J-Log4j 바인딩 |
| `log4j` | `log4j` | `1.2.17` | 로깅 라이브러리 |
| `jakarta.servlet` | `jakarta.servlet-api` | `6.1.0` | Jakarta Servlet API |
| `jakarta.servlet.jsp.jstl`| `jakarta.servlet.jsp.jstl-api`| `3.0.2` | JSTL API |
| `jakarta.xml.bind` | `jaxb-api` | `2.3.1` | JAXB API |

-----