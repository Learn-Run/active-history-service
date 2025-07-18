# Active History Service

## 1. 프로젝트 소개

사용자의 활동 이력(게시글 작성, 댓글 작성, 리뷰 작성 등)을 관리하는 마이크로서비스입니다.

## 2. 기술 스택

- **언어:** Java 17
- **프레임워크:** Spring Boot 3.5.0
- **데이터베이스:** MongoDB
- **메시지 큐:** Kafka
- **기타:**
    - **Eureka:** 서비스 디스커버리
    - **Swagger:** API 문서 자동화
    - **Lombok:** 코드 간소화
    - **Docker:** 컨테이너화

## 3. 주요 기능

### 3.1. 활동 이력 조회

- 특정 사용자의 활동 이력을 종류별(게시글, 댓글, 작성한 리뷰, 받은 리뷰)로 필터링하여 조회할 수 있습니다.
- 오프셋 기반 페이지네이션을 지원합니다.

**API Endpoint:** `GET /api/v1/active-history/{memberUuid}`

### 3.2. 활동 이력 개수 조회

- 특정 사용자의 활동 이력 개수를 기간별(전체, 오늘)로 조회할 수 있습니다.
- 게시글, 댓글, 작성한 리뷰, 받은 리뷰 각각의 개수와 전체 합계를 제공합니다.

**API Endpoint:** `GET /api/v1/active-history/{memberUuid}/count`

### 3.3. Kafka를 이용한 이벤트 기반 활동 이력 생성 및 삭제

- 다른 서비스(게시글, 댓글, 리뷰 서비스 등)에서 발생하는 이벤트를 Kafka를 통해 구독(subscribe)하여 활동 이력을 생성하거나 삭제합니다.
- 현재 구독 중인 토픽은 다음과 같습니다.
    - `comment-created`: 댓글 생성
    - `review-created`: 리뷰 생성
    - `post-create-send-read`: 게시글 생성
    - `comment-deleted`: 댓글 삭제

## 4. 실행 방법

### 4.1. Docker Compose를 이용한 실행

1. **Kafka 실행:**
   ```bash
   docker-compose -f docker-compose-kafka.yml up -d
   ```

2. **Active History Service 실행:**
   - `.env` 파일에 환경변수를 설정해야 합니다.
   ```bash
   docker-compose -f docker-compose-active-history.yml up -d
   ```

### 4.2. 로컬 환경에서 직접 실행

1. **애플리케이션 설정:**
   - `src/main/resources/application.yml` 파일과 각 프로파일(`dev`, `prod`)에 맞는 설정 파일을 확인하고 필요한 정보를 입력합니다. (Eureka, MongoDB, Kafka 등)

2. **애플리케이션 실행:**
   - IDE에서 `ActiveHistoryServiceApplication.java` 파일을 직접 실행하거나, 아래 명령어를 사용하여 프로젝트를 빌드하고 실행합니다.
   ```bash
   ./gradlew build
   java -jar build/libs/active-history-service-0.0.1-SNAPSHOT.jar
   ```

## 5. API 문서

- 애플리케이션 실행 후, 다음 URL에서 Swagger API 문서를 확인할 수 있습니다.
  - `http://{host}:{port}/swagger-ui.html`
