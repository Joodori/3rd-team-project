# 📚 Git 협업 가이드 - 3rd Team Project

<div align="center">
  <h3>🚀 처음 Git을 사용하는 팀원을 위한 완벽 가이드</h3>
  <p>팀 협업 중 발생할 수 있는 문제점들과 해결방법을 함께 알아보세요!</p>
</div>

---

## 🎯 시작하기 전에

이 가이드는 **Git을 처음 사용하는 팀원**들이 안전하고 효율적으로 협업할 수 있도록 도와줍니다.
실제 협업에서 자주 발생하는 문제 상황들과 해결 방법을 예시를 통해 설명합니다.

---

## 📋 목차
1. [초기 설정 및 Repository Clone](#1-초기-설정-및-repository-clone)
2. [기본 Git 작업 흐름](#2-기본-git-작업-흐름)
3. [자주 발생하는 문제 상황들](#3-자주-발생하는-문제-상황들)
4. [협업 규칙 및 베스트 프랙티스](#4-협업-규칙-및-베스트-프랙티스)

---

## 1. Repository Clone

### 1.1 Repository Clone하기

```bash
# 1. 작업할 폴더로 이동
cd Desktop

# 2. Repository 복사하기
git clone https://github.com/Joodori/3rd-team-project.git

# 3. 프로젝트 폴더로 이동
cd 3rd-team-project

# 4. 현재 상태 확인
git status
```

### 1.2 브랜치 확인 및 설정

```bash
# 현재 브랜치 확인
git branch

# 원격 브랜치 모두 보기
git branch -r

# 새로운 브랜치 생성하고 이동
git checkout -b feature/본인이름-기능명
```

### 1.3 브랜치를 잘못 만들어서 삭제하고 싶을 때
```bash
# 1. 다시 master 브랜치로 이동
git checkout master

# 2. 'testbranch' 브랜치 삭제 (아직 커밋 했거나 push 하지 않은 상태라면 안전)
git branch -d testbranch

# 3. 커밋이 남아있는데도 삭제하고싶은 경우
git branch -D testbranch
```

---

## 2. 기본 Git 작업 흐름

### ⭐ **황금 규칙: 작업 전에는 항상 pull, 작업 후에는 항상 push!**

```bash
# 1. 작업 시작 전 - 최신 코드 받아오기
git checkout main          # main 브랜치로 이동
git pull origin main       # 최신 변경사항 받아오기

# 2. 본인 브랜치로 이동 후 최신 코드 반영
git checkout feature/본인브랜치
git merge main             # 또는 git rebase main

# 3. 코드 작업 후 저장
git add .                  # 모든 변경사항 추가
git commit -m "작업내용 설명"

# 4. 원격 저장소에 업로드
git push origin feature/본인브랜치
```

---

## 3. 자주 발생하는 문제 상황들

### 🚨 **상황 1: 여러 명이 동시에 같은 파일을 수정한 경우**

**문제 예시:**
- 성호: `App.js`의 10번째 줄 수정 후 push
- 수현: 같은 `App.js`의 12번째 줄 수정 후 push 시도

```bash
# 수현이 push할 때 발생하는 에러
$ git push origin feature/suhyeon-login
To https://github.com/Joodori/3rd-team-project.git
 ! [rejected] feature/suhyeon-login -> feature/suhyeon-login (non-fast-forward)
error: failed to push some refs to 'https://github.com/Joodori/3rd-team-project.git'
```

**해결 방법:**
```bash
# 1. 원격 저장소의 최신 변경사항 받아오기
git pull origin main

# 2. 자동으로 병합이 되지 않는다면 충돌 해결
# (충돌 발생 시 VS Code에서 직접 수정)

# 3. 충돌 해결 후 다시 커밋
git add .
git commit -m "충돌 해결: App.js 병합"

# 4. 푸시
git push origin feature/suhyeon-login
```

### 🚨 **상황 2: 누군가 main 브랜치에 직접 push해버린 경우**

**문제 예시:**
- 새미: 실수로 `git push origin main` 실행
- 다른 팀원들: 본인 브랜치가 뒤떨어진 상태가 됨

**해결 방법:**
```bash
# 1. main 브랜치의 최신 상태 받아오기
git checkout main
git pull origin main

# 2. 본인 브랜치에 최신 main 내용 반영
git checkout feature/본인브랜치
git merge main

# 3. 충돌이 발생하면 해결 후 계속 작업
```

### 🚨 **상황 3: 실수로 잘못된 파일을 커밋한 경우**

**문제 예시:**
- 현수: 개인 설정 파일이나 비밀번호가 담긴 `.env` 파일을 커밋

**해결 방법:**
```bash
# 1. 아직 push하지 않은 경우 - 마지막 커밋 취소
git reset --soft HEAD~1    # 커밋만 취소 (파일은 유지)
git reset --hard HEAD~1    # 커밋과 파일 모두 취소

# 2. 이미 push한 경우 - 특정 파일만 제거
echo ".env" >> .gitignore  # .gitignore에 추가
git rm --cached .env       # Git 추적에서 제거
git commit -m ".env 파일 제거 및 gitignore 추가"
git push origin feature/hyeonsu-backend
```

### 🚨 **상황 4: 브랜치를 잘못 만들어서 코드가 섞인 경우**

**문제 예시:**
- 원규: `feature/wonkyu-frontend`에서 백엔드 코드 작업
- 영기: `feature/youngki-backend`에서 프론트엔드 코드 작업

**해결 방법:**
```bash
# 1. 새로운 올바른 브랜치 생성
git checkout main
git checkout -b feature/wonkyu-backend-fix

# 2. 필요한 파일만 선택해서 커밋
git add src/backend/specific-file.js  # 특정 파일만 추가
git commit -m "백엔드: 올바른 브랜치로 코드 이동"

# 3. 잘못된 브랜치에서 해당 파일 제거
git checkout feature/wonkyu-frontend
git rm src/backend/specific-file.js
git commit -m "잘못 추가된 백엔드 파일 제거"
```

---

## 4. 협업 규칙 및 베스트 프랙티스

### 📏 **브랜치 네이밍 규칙**

```
feature/이름-기능명     # 새 기능 개발
fix/이름-버그명        # 버그 수정
refactor/이름-개선명   # 코드 개선
```

**예시:**
- `feature/saeми-login-page`
- `fix/sungho-header-bug` 
- `refactor/hyeonsu-api-optimization`

### 📝 **커밋 메시지 규칙**

```bash
# 좋은 커밋 메시지 예시
git commit -m "feat: 로그인 페이지 UI 구현"
git commit -m "fix: 헤더 메뉴 클릭 안되는 버그 수정"
git commit -m "refactor: API 호출 코드 최적화"
git commit -m "docs: README 파일 업데이트"

# 나쁜 커밋 메시지 예시
git commit -m "수정"
git commit -m "버그픽스"
git commit -m "ㅇㅇ"
```

### 🔄 **작업 전 체크리스트**

```bash
# ✅ 매일 작업 시작 전 필수 확인사항
1. git status                    # 현재 상태 확인
2. git checkout main            # main 브랜치로 이동
3. git pull origin main         # 최신 변경사항 받아오기
4. git checkout feature/본인브랜치  # 본인 브랜치로 이동
5. git merge main               # 최신 코드 반영

# ✅ 작업 완료 후 필수 확인사항
1. git add .                    # 변경사항 스테이징
2. git commit -m "명확한 메시지"  # 커밋 생성
3. git push origin feature/본인브랜치  # 원격 저장소에 업로드
4. GitHub에서 Pull Request 생성  # 코드 리뷰 요청
```

### 🆘 **응급상황 대처법**

#### **상황: 모든 것이 꼬여서 처음부터 다시 시작하고 싶을 때**

```bash
# 1. 현재 작업 백업 (중요!)
git stash push -m "응급백업"

# 2. 강제로 원격 저장소 상태와 동일하게 만들기
git fetch origin
git reset --hard origin/main

# 3. 백업된 작업 복구
git stash pop

# 4. 새로운 브랜치에서 다시 시작
git checkout -b feature/본인이름-재시작
```

#### **상황: Push가 안될 때**

```bash
# 1. 강제 푸시 (⚠️ 주의: 팀원과 상의 후 사용)
git push --force origin feature/본인브랜치

# 2. 더 안전한 방법
git pull origin feature/본인브랜치  # 먼저 받아오기
# 충돌 해결 후
git push origin feature/본인브랜치
```

---

## 🎯 **마지막 당부사항**

### ✅ **꼭 지켜야 할 것들**
- **절대 main 브랜치에 직접 push 금지**
- **작업 전에는 항상 pull 먼저**
- **커밋 메시지는 명확하게**
- **문제 발생 시 혼자 해결하려 하지 말고 팀원에게 바로 알리기**

### ❌ **절대 하면 안 되는 것들**
- `git push --force origin main` (main 브랜치 강제 푸시)
- 비밀번호, API 키 등 민감한 정보 커밋
- 의미 없는 커밋 메시지 사용
- 다른 사람 브랜치에 직접 푸시

---

<div align="center">
  <h3>🤝 협업은 소통이 핵심입니다!</h3>
  <p>문제가 생겼을 때는 슬랙이나 카톡으로 바로 공유해주세요.</p>
  <p><strong>Git 명령어보다 중요한 것은 팀원 간의 소통입니다! 💬</strong></p>
</div>
