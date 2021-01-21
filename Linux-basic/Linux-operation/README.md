# [리눅스 운영](https://github.com/accidentlywoo/TIL/tree/main/Linux-basic/Linux-operation)
## 리눅스 기초와 이론
  1. 리눅스 역사, 배포판 종류
  2. 실습 환경 구축하기 - VM
  3. 실습 환경 구축하기 - VM(원격 접속환경 구축하기)
  4. 우분투와 GUI
  5. [운영체제의 구조와 특징](#운영체제의-구조와-특징)

## 실습 환경 구축하기
  1. VM 원격 실습 환경 구축하기
  2. AWS 원격 접속 환경 구축하기
  3. GCP 원격 접속 환경 구축하기

## 리눅스 쉘과 CLI 명령어
  1. [기본 명령어 다루기](#기본-명령어-다루기)
  2. [사용자, 그룹 및 권한](#사용자-그룹-및-권한)
  3. [BASH 쉘과 친숙해지기](#bash-쉘과-친숙해지기)

## 파일시스템 구조
  1. [파일시스템 살펴보기](#파일시스템-구조-파일시스템-살펴보기)
  2. 디스크 추가하기

## 응용 프로그램 다루기
  1. 패키지 설치하기, 업데이트 및 업그레이드
  2. 데몬 서비스 관리하기

## 실전 응용편
  1. 각종 서버 설치하기 (web, ftp, db 등)
  2. 개발환경 구축하기 ( java, python, docker )
  3. 사용자 모니터링 도구 및 활용
  4. 프로세스 모니터링 도구와 프로세스 분석
  5. 네트워크 설정과 분석 도구
  6. 멀티 테스트 환경 구축하기
  7. 방화벽 ( iptables )
   
## 부록

---

정리 내용

##  운영체제의 구조와 특징

## 기본 명령어 다루기
- 파일 보기 : ls, ls -l, ls -al(ls -a -l)
- 파일 내용 보기 : cat, more, less
  cat(= concatenate)
  ```cat -e /etc/passwd``` : 줄의 맨 뒤에 $붙이기 (히든 캐릭터 공백 등 확인)

  ```cat -n /etc/passwd``` : 줄 번호 보여주기

  less : 파일 내용 보여주기. 페이지 이동(space), 줄단위 이동(enter), 방향키 :: more보다 편리하고 성능도 좋음

- 파일 만들기/지우기 touch, rm
- 파일 복사 : cp
- 파일 이동 : mv
- 파일 숏컷 In (심볼릭 링크)
- 파일 속성 보기 : file
- 터미널 지우기 : clear
  $ : 프롬프트 (prompt)
  ~ : 홈 디렉토리
- 시스템 종료 : reboot, poweroff, shutdown

## 사용자, 그룹 및 권한
- 계정 종류 : root 유저와 사용자 계정,
            계정 살펴보기 /etc/passwd, /etc/shadow, /etc/group, 
            내 권한 (whoami. id),
            그룹 계정 및 권한 (sudoer & sudo)
- 사용자 생성과 그룹 생성 : adduser, useradd, usermod, deluser, userdel, addgroup, delgroup
- 파일 권한 다루기 : chmod, chown, chgrp, umask
- 파일 다루기 상급 : setuid, setgid

- 


## BASH 쉘과 친숙해지기

## 파일시스템 구조 파일시스템 살펴보기
- 파일 관련 명령어 숙지하기!
- 디렉토리 구조 복습
- 파일 시스템 검색 :: find
- 파일 속성 보기 :: stat
- 파일 내용 검색 / 필터링 / 변경 등의 활용을 위한 유틸리티 :: grep, sort, awk, send, uniq, wc
- 파일 시스템 용량 :: du
- 압축 등 관리 :: tar, gz
- 쉘 스크립트 맛보기

 -> 활용해서 업무에서 많이쓰는 기능 짜보기!