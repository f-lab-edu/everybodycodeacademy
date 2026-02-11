# EveryCodeAcademy - Initial Monorepo Setup

요청하신 구조에 맞춰 **frontend / backend 2개 프로젝트**로 초기 셋팅한 상태입니다.

## 프로젝트 구조

```text
.
├─ backend/   # Spring Boot 3.x (Java 17)
├─ frontend/  # React(정적 페이지)
└─ docker-compose.yml  # postgres/backend/frontend 통합 기동
```

## Backend
- Spring Boot `3.5.0`
- 기본 엔드포인트: `GET /api/health`
- DB: PostgreSQL 16 연결 설정 (`application.yml`에서 환경변수 기반)
- Dockerfile 포함

## Frontend
- **Vite 없이 React만 사용**하는 정적 앱 구성
- `index.html`에서 React/ReactDOM CDN 로드
- 초기 랜딩 화면/핵심 기능 섹션 추가
- nginx 기반 Dockerfile 포함

## 보안 관련 설정
- 비밀번호/시크릿은 레포에 하드코딩하지 않고, `.env` 파일로 주입합니다.
- `.env.example`을 복사해 `.env`를 생성한 뒤 값을 바꿔 사용하세요.

```bash
cp .env.example .env
# .env 내 change-me 값을 실제 값으로 변경
```

## Docker 실행
```bash
docker compose up --build
```

- Frontend: http://localhost:5173
- Backend: http://localhost:8080/api/health
- PostgreSQL: localhost:5432

## CI 스캐폴딩
- `.github/workflows/initial-monorepo-ci.yml` 추가
  - backend: gradle test
  - frontend: 정적 파일/React CDN 참조 검증

> 현재 환경에서 외부 레지스트리 접근 제한이 있으면 CI/로컬 설치가 실패할 수 있습니다.
