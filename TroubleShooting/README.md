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

