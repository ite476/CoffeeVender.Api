# Commit & PR Convention

## 🎯 목적
- 변경 히스토리를 명확히 기록하고, 코드 리뷰 및 배포 관리의 생산성을 높인다.
- 커밋은 **항상 기능 단위로 잘게 쪼개어** 작성한다.

---

## 📝 Commit Message Format

```markdown
{커밋키워드}: {커밋 제목}

> {바디, 모든 라인을 마크다운 blockquote (>) 로 들여쓰기 처리}
> {코드 일부를 표현하는 경우, 백틱으로 감싸서 표현.}
> {(예: `@SpringBootApplication`, `SecurityConfig` (클래스, 객체 명))}
> {엔드 포인트를 표현하는 경우, 백틱으로 감싸서 표현.}
> {(예: `POST /api/v1/auth/login`)}

- {선택 영역 (없는 경우 완전 생략 가능)}
- {꼬리, 관련 GitHub Issue/PR 링크}
- {예: #123}
```

### ✅ Example

```markdown
feat: 사용자 인증 API 추가

> `POST /api/v1/auth/login` 엔드포인트 구현
> Spring Security JWT Filter 기반 인증 구조 추가
> `PasswordEncoder` Bean 설정

- #45
```

```markdown
refactor: UserService 의존성 주입 방식 개선

> 생성자 주입 방식으로 변경하여 불변성 보장
> `@Autowired` 어노테이션 제거로 명시적 의존성 표현
> 테스트 코드에서 Mock 객체 주입 방식 단순화
```

---

### 🔑 **Available Keywords**

| 키워드 | 설명 |
|---|---|
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| refactor | 리팩토링 (동작 변경 없이 구조 개선) |
| chore | 빌드, 의존성, 설정 변경 등 기타 작업 |
| docs | 문서 수정 |
| test | 테스트 코드 추가 또는 수정 |
| style | 코드 스타일, 포맷팅 변경 (로직 변경 없음) |
| ci | CI/CD 설정 변경 |

---

### 💡 **Commit 작성 가이드**

1. **커밋 제목**
   - 현재형 동사 사용: “추가”, “수정” 대신 “Add”, “Update” 의 느낌 (Korean-Style OK)
   - 50자 이내 권장

2. **바디**
   - 무엇을, 왜 했는지 간략히 작성
   - > 마크다운 blockquote 로 전 라인 들여쓰기 처리

3. **꼬리**
   - 관련된 Issue, PR 번호를 `- #번호` 형식으로 작성 (GitHub 자동 연결 지원)

---

## 🔀 **Pull Request Convention**

### 🏷️ **PR Title**

```markdown
[{버전}] {PR 제목}


- **버전**: 릴리즈 태그, sprint 명, milestone 등 상황에 따라 지정
- **PR 제목**: 추가/수정 기능의 명확한 설명 (예: “Login 기능 추가”, “UserService 리팩토링”)
```

### 📝 **PR Description Template**

```markdown
# 개요
{대략적인 PR 내용. 추가/수정되는 기능의 간단한 요약 및 의도, 요구사항 히스토리, 맥락을 설명}

# 작업 내용
- {상세한 작업 내용을 설명}
- {작업한 각 개별 요소에 대한 나열}
- {예: 특정 파일 추가, ~~~한 경우에 발생하던 현상을 ~~~하도록 변경}

# 작업자 의견
> {선택 영역. 필요 시 기술적인 세부 구현사항에 대한 자세한 설명을 동봉}
> {대안, trade-off, 현재 선택 이유 등을 대화형으로 작성}
> {예: 이번 PR에서는 기존 방식 대신 A 방식을 선택했습니다. 이유는 ~ 입니다. 대안으로 B도 고려했으나 ~ 때문에 제외했습니다.}

# 참고
- {연결된 이슈, PR 링크 등을 연결}

---
{구분자: 스쿼시 머지 시 커밋 메시지 바디 최상단과 PR 내용 구분 용도}

{추가: 이후 커밋 메시지 히스토리가 스쿼시 머지 바디에 붙음}
```

---

### ✅ **PR Example**

```markdown
# [1.0.0] Login 기능 추가

# 개요
사용자 로그인 API를 신규 개발했습니다.  
JWT 기반 인증을 적용하였고, Spring Security FilterChain을 구성하였습니다.

# 작업 내용
- POST /api/v1/auth/login 구현
- AuthController, AuthService, JwtProvider 구성
- PasswordEncoder Bean 설정
- JWT Secret Key 환경 변수 설정 (.env)

# 작업자 의견
> 이번 구현에서는 Spring Security의 UsernamePasswordAuthenticationFilter를 커스터마이징하는 대신,
> JWT 전용 필터(JwtAuthenticationFilter)를 Security FilterChain에 추가하는 방식을 선택했습니다.
> 이유는 UsernamePasswordAuthenticationFilter 커스터마이징 시 기존 FormLogin 로직과 충돌이 발생할 수 있고,
> JWT 기반의 Stateless 인증 구조에서는 별도의 전용 Filter를 두는 것이 유지보수 및 역할 분리 측면에서 적합하다고 판단했습니다.
> 
> 대안으로는 Spring Security OAuth2 Resource Server 기능을 사용하는 방법도 고려했으나,
> 현재 프로젝트는 OAuth2 Provider 없이 내부 발급 JWT만 사용하는 구조이므로 별도 설정 Complexity를 줄이기 위해
> JwtProvider + Custom Filter 방식을 우선 적용했습니다.


# 참고
- #45

```


---

## 🔒 **주의사항**

- 커밋은 **항상 기능 단위로 잘게 쪼개어** 작성한다.
- PR은 반드시 CI/CD 통과 후 Reviewer 1인 이상 Approve 후 Merge
- PR Merge 방식은 **Squash Merge**를 기본으로 한다.

---

> 이 Convention 파일을 `.github/COMMIT_CONVENTION.md` 로 관리하고,  
> PR Template(`.github/PULL_REQUEST_TEMPLATE.md`)에서 링크하여 리뷰어가 즉시 확인할 수 있게 하자.
