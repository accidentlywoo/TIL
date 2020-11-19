# TroubleShooting

## 1. Spring Boot + JPA(Hibernate) + MySQL :: 데이터 저장시 미반영문제
 - 증상 MSA구조(?)의 REST API에서 상태 변경하는 모듈에서 간헐적으로 데이터 변경이 안됨.
  
특징. 
 - MSA REST API에서 기능의 일부분을 Undertow, WebSocket 서비스로 분리함. -> 증상이 더 자주 발생됨
 - 상태변경 정보는 WebSocket이아닌 다른 서비스(브릿지)에서 보내고 있음. 
 - @Transactional 묶인 로직이 난잡함
    -> 노티 보내는 서비스 호출과 조회, 저장 까지 약 200 줄의 코드
 - 서버 로그 조회시 UPDATE 쿼리와 매핑되는 파라미터 값까지 잘 넘어감.
 - But DB 반영 안됨

### Test 환경
 - 해당 상태 변경 모듈만 Local로 서비스 돌리고, 나머지 다 문제 환경으로 연결.
 - 건헐적으로 발생한다고 했으나 로컬에서 바로 증상이 나타남. 
    -> 특정 코드에 대한 로직통과시 발생 의심
  
### Try
 - Transaction 범위 분리
   -> 노티 서비스가 이번트처리가 되지않은 일반 서비스. 
   노티 서비스 호출 메소드의 트랜잭션을 @Transactional(propagation = Propagation.REQUIRES_NEW) 넣어 트랜잭션 분리 작업
   -> 별 효과가 없음
   -> 루트 트랜잭션에 전파범위 없애면 (@Transactional(propagation = Propagation.NOT_SUPPORTED)) 트랜잭션 에러는 발생함

 - 동료와 같이 수정 
    -> 동료는 변경작업 슥삭 잘됨 
    -> ???
    -> 같은 데이터 동료가 변경한 내역이 나는 조회가 안됨 DB Client에서 접속을 끊고 다시 재접속했더니 변경된 데이터 조회
    -> DB Lock의심 

### 연구 방향
 - Transaction 공부 오질나게 하게됨. 한번 정리해야 겠음.
 - MySQL Inno DB에대한 공부와 Connection session에대한 공부가 필요.


## 2. Spring Boot 특정 조건에서 동작하지 않는 Flow
진짜 대환장할 노릇.

예시 

```
if(조건 A){
   obj.set(a);
}else if (조건 B){
   obj.set(b); // 이후 동작안함
}
anotherService.sendRequest(obj);
```

특징. 
   - Spring Boot 기반 Rest API 내부에서 if / else if 조건문을 타고 상태값을 세팅해서 특정 uri에 요청을 보내는 로직 
   - if조건에서 걸린 로직을 타고 else if조건에서 request가 소리소문없이 사라짐. 
   - 상용만 테스트해볼수 있는 복잡한 환경.
   - 팀원과 함께 비명지르며, 봄
   - 같은 uri를 타는데, if / else if 조건에 따라 request가 날라감
   - 특정 service만을 위한 로직임.
   - 위 예제에서 ```anotherService.sendRequest``` @Async 애노테이션이 붙어있고, 부모는 @Transactional이 붙어있음.



### Try
 - 테스트가 매우 조심스러워서 logging을 낱낱히 추가해서 작업해봄.
 - 위 예시에서 ```anotherService.sendRequest```로직을 타지도 않음.
 - 에러도 안남.
 - 동료와 1차 트러블 슈팅 후 비명 두번 지름

### 해결
해결전 진짜 리팩토링과 코드가독성의 중요성을 뼈저리게 느낀다.
50줄에 끝날 코드를 200줄로 만들어주는 직전 퇴사자의 숨겨둔 판도라의 상자를 내부개발자 3(나), 5년차 개발자 2명이 2일을 날리고
2주간 찜찜하게 마음 한켠에 남겨두고, 시니어에게 도움을 요청했으나, 너무 바쁘셔서 오늘 다시 울며 코드를 보는데,
일반 JDK API를 냅두고 굳이 상태코드를 리스트를 이쁘게 쌈싸주시고, 변수명도 XX발 엄청나게 작명해주신걸 찾았다.
너무 화가났다. 퇴사 선물로 남긴 코드일까?
 - 쓸데없이 비교대상이되는 상태코드 배열 리스트에 담아둠
 - if문에서 || 을 길게 한줄 나열
 - 장황하기 짝이없는 코드, 파라미터로 들어오는 상태코드를 new~ DB 조회한 상태코드 old~ 작명센스
 - 문제를 해결하긴 했으나, 진짜 시간과 정성이 너무나 아깝고 처참하다. 화가난다.

