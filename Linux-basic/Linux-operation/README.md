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
  2. [디스크 추가하기](#디스크-추가하기)

## 응용 프로그램 다루기
  1. [패키지 설치하기, 업데이트 및 업그레이드](#패키지-설치하기-업데이트-및-업그레이드)
  2. [데몬 서비스 관리하기](#데몬-서비스-관리하기)

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

- 파일 시스템 주요 디렉토리 - /boot
grub 및 커널, initrd, 멀티 부팅 시 메모리 테스트를 위한 memtest 도구

- 파일 시스템 주요 디렉토리 - /home
사용자 디렉토리 및 슈퍼유저(root)의 홈 디렉토리 /root

- 파일 시스템 주요 디렉토리 - /etx
시스템 프로세스의 각종 설명파일들, 웹서버, DB서버, loglotate

- 파일 시스템 주요 디렉토리 - /etc/* -release
운영체제 정보를 갖고 있는 파일들
```cat /etc/*-release```

배포판에 따라.. - os-release, centos-release, redhat-release

- 파일 시스템 주요 디렉토리 - /var 및 /var/log
시스템 프로세스의 각종 임시 파일들 및 로그 파일들, log, PID, locking, 멀티 프로그래밍

### 파일 시스템 명령어 - 검색 (find)
find [OPTIONS][PATH][EXPRESSION] 원하는 파일의 검색

- 내 현재 디렉토리에서 확장자가 .txt인 파일을 찾는 법
```find *.txt```

- 내 현재 디렉토리에서 부터 확장자가 .txt인 파일을 찾는 법
```find . -name "*.txt"```

- 내 현재 디렉토리에서 파일명이 hello로 시작하는 "파일"만 찾는 법
```find . -name "hello*" -type d```

- 내 현재 디렉토리에서 용량이 100,000,000 바이트(100M) 보다 큰 파일 찾는 법
```find . -size +100000000c``` (100000000c대신 100000k 또는 100M 사용 가능)

- 최든 생성된 파일만 찾아보기
```find -newerct "15 May 2020" -ls``` (newerct대신 newermt로 할 경우 최근 변경된 파일)

- 최근 2일에서 5일 사이에 변경된 파일 찾기
```find . -mtime +2 -a =mtime -5 -ls```

### 파일 시스템 명령어 - 속성 (stat)
stat[OPTIONS][FILE]원하는 파일의 속성 (주로 시간)확인

시간의 유형(atime, mtime, ctime)
- Access : 파일에 최근 접근 시간 (요즘은 read한다고 변경 x)
- Modify : 파일의 내용 변경 시간
- Change : 파일의 수정 시간 (inode 관점에서의 변화 시간 - 생성, 변경, 속성수정 등)

ls 명령어에서의 시간 확인 : ```ls -l```: 기본값(mtime), ```ls -l -u``` : atime, ```ls -l -c``` : ctime

### 파일 시스템 명령어 - 검색 (find) advanced
루트 디렉토리로부터 파일 사이즈가 100M 이상인 파일을 찾아서 ls로 상세 표시하기
- find / -size 100M -exec ls -l {}\;2>/dev/null

루트 디렉토리로부터 txt파일을 찾아서 그 안에 "HELP"라는 글자가 포함된 파일과 내용 표시
- find / -name "*.txt" -exec grep "HELP"{}\;-print 2>/dev/null

루트 디렉토리로부터 특정 [조건]의 파일 찾아서 특정 [디렉토리]로 복사하기
- find / [조건문] -print -exec cp {} [경로]\;2>/dev/null

### 검색 명령어 - 필터링 (grep)
grep [OPTION] PATTERN [FILE]
특정 패턴 검색 (또는 정규표현식 패턴 검색)

파일 내에서 usage라는 단어 검색
- grep "usage" [FILE]

파일 내에서 "vim"또는 "Vim"이라는 단어 각각 찾는 법 (및 대소문자 구분 없이)
- grep "vim" [FILE] (대소문자 구분)
- grep "Vim" [FILE]
- grep -i "vim" [FILE] (대소문자 무시)

하위 디렉토리 모두 검색
- grep -r "vim" [PATH]

**정규표현식(RegEx)**
- 기본 정규 표현식 :: grep -n "PATTERN" /urs/share/doc/vim/copyright
  1. ^s : 문장의 시작이 s 로 시작하는 줄
  2. e$ : 문장의 끝이 e 로 끝나는 줄
  3. ..e$ : 문장의 끝이 3글자 중 e로 끝나는 줄
  4. app* : 문장의 시작/중간/끝 이 ap와 p의 "0개 혹은 그 이상"의 개수를 갖고 있는 줄
  5. ^[at] : 문장의 시작 첫 단어가 a또는 t로 시작하는 줄
  6. [0-9] : 문장의 중간에 숫자 0~9까지를 포함하고 있는 줄
 - 확정 정규 표현식 :: grep -E "PATTERN" /urs/share/doc/vim/copyright (grep -E는 egrep과 동일)
  1. [p]{2} : 문장 내 p라는 글자가 연속 두번 나오는 경우
  2. ^[a-zA-Z0-9]{3,9} : 문장의 시작이 소문자/대문자/숫자로 시작하는 3~9길이

보통 grep는 파이프로 연결해서 많이 사용
- 파일 목록에서 특정 단어 검색 ```ls -al | grep txt```

- 로그 파일에서 경고만 검색 ``` cat /var/log/syslog | grep -i "warn"```

- 프로세스 목록에서 특정 단어 검색 (및 특정 단어 예외) 
```ps x | grep "/bin"```
```ps x | grep "/bin" | grep -i "warn"```

- 특정 포트가 열려있는지 확인
```nestat -a | grep 80```
```nestat -a | grep ":80"```

 -> 활용해서 업무에서 많이쓰는 기능 짜보기!

 ## 디스크 추가하기

 ## 패키지 설치하기, 업데이트 및 업그레이드

 ## 데몬 서비스 관리하기
 DoIt List
 1. NginX 설치 해보기
 2. DB 설치해보기