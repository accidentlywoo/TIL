# JPA 정리
JPA 킹 갓 짱이 되고싶다.

갓영한님 인프런 강의내용 곱씹기

## JPA 기본
  3. 영속성 관리
  4. 엔티티 매핑
  5. 연관관계 매핑 기초
  6. 다양한 연관관계 매핑
  7. 고급 매핑
  8. 프록시와 연관관계 관리
  9. [값 타입](#값-타입)
  10. [객체지향 쿼리 언이1 (기본 문법)](#객체지향-쿼리-언이1-기본-문법)

--- 

## 값 타입

### 값 타입과 불변 객체
값 타입은 복잡한 객체 세상을 조금이라도 단순화하려고 만든 개념이다. 따라서 값 타입은 단순하고 안전하게 다룰 수 있어야 한다.

  - 값 타입 공유 참조
    - 임베디드 타입 같은 값 타입을 여러 엔티티에서 공유하면 위험함
    - 부작용(side effect) 발생

  - 값 타입 복사
    - 값 타입의 실제 인스턴스인 값을 공유하는 것은 위험
    - 대신 값(인스턴스)를 복사해서 사용

  - 객체 타입의 한계
    - 항상 값을 복사해서 사용하면 공유 참조로 인해 발생하는 부작용을 피할 수 있다.
    - 문제는 임베디드 타입처럼 직접 정의한 값 타입은 자바의 기본 타입이 아니라 객체 타입이다.
    - 자바 기본 타입에 값을 대입하면 값을 복사한다.
    - 객체 타입은 참조 값을 직접 대입하는 것을 막을 방법이 없다.
    - 객체의 공유 참조는 피할 수 없다.

  기본 타입(primitive type)

  ```
  int a = 10;
  int b = a; // 기본 타입은 값을 복사
  b = 4;
  ```

  객체 타입

  ```
  Address a = new  Address("Old");
  Address b = a; // 객체 타입은 참조를 전달
  b.setCity("New");
  ```

    - 불변 객체
      - 객체 타입을 수정할 수 없게 만들면 부작용을 원천 차단
      - 값 타입은 불변 객체(immutable object)로 설계해야함
      - 불변 객체: 생성 시점 이후 절대 값을 변경할 수 없는 객체
      - 생성자로만 값을 설정하고 수정자(Setter)를 만들지 않으면 됨
      - 참고 : Integer, String 은 자바가 제공하는 대표적인 불변 객체
  결론 : 불변이라는 작은 제약으로 부작용이라는 큰 재앙을 막을 수 있다.

### 값 타입 비교
    - 값 타입 : 인스턴스가 달라도 그 안에 값이 같으면 같은 것으로 봐야 함
    - 동일성(identity) 비교 : 인스턴스의 참조 값을 비교, == 사용
    - 동등성(equivalence) 비교 : 인스턴스의 값을 비교, equals()사용
    - 값 타입은 a.equals(b)를 사용해서 동등성 비교를 해야 함

### 값 타입 컬렉션
- 값 타입을 하나 이상 저장할 때 사용
- @ElementCollection, @Collection Table 사용
- 데이터베이스는 컬렉션을 같은 테이블에 저장할 수 없다
- 컬렉션을 저장하기 위한 별도의 테이블이 필요함

   값 타입 컬렉션 사용
- 값 타입 저장 예제
- 값 타입 조회 예제
  - 값 타입 컬렉션도 지연 로딩 전략 사용
- 값 타입 수정 예제
  - 참고 : 값 타입 컬렉션은 영속성 전에(Cascade) + 고아 객체 제거 기능을 필수로 가진다고 볼 수 있다.

  값 타입 컬렉션의 제약사항
- 값 타입은 엔티티와 다르게 식별자 개념이 없다
- 값은 변경하면 추적이 어렵다
- 값 타입 컬렉션에 변경 사항이 발생하면, 주인 엔티티와 연관된 모든 데이터를 삭제하고, 값 타입 컬렉션에 있는 현재 값을 모두 다시 저장한다
- 값 타입 컬렉션을 매핑하는 테이블은 모든 컬럼을 묶어서 기본 키를 구성함
  : null 입력 X, 중복 저장 X

  값 타입 컬렉션 대안
- 실무에서는 상황에 따라 값 타입 컬렉션 대신에 일대다 관계를 고려
- 일대다 관계를 위한 엔티티를 만들고, 여기에 값 타입을 사용
- 영속성 전이(Cascade) + 고아 객체 제거를 사용해서 값 타입 컬렉션 처럼 사룔

  결론 : 실무에선 값타입 안쓴다. 엔터티로 따로 뺴서 사용. (찐으로 써본적 없음.) 찐 간단할때만 써라.

  정리
  - 엔티티 타입의 특징 : 식별자 O, 생명 주기 관리, 공유
  - 값 타입의 특징 :식별자X, 생명주기를 엔티티에 의존, 공유하지 않는 것이 안전(복사해서 사용), 불변 객체로 만드는 것이 안전
  
  값 타입은 정말 값 타입이라 판단될 때만 사용

  엔티티와 값 타입을 혼동해서 엔티티를 값 타입으로 만들면 안됨
  
  식별자가 필요하고, 지속해서 값을 추적, 변경해야 한다면 그것은 값 타입이 아닌 엔티티

## 객체지향 쿼리 언이1 (기본 문법)
1. JPA는 다양한 쿼리 방법을 지원
   1. JPQL : 표준 문법
   2. JPA Criteria : 자바코드를 JPQL로 Code 제너레이터
   3. QueryDSL : 자바코드를 JPQL로 Code 제너레이터
   4. 네이티브 SQL : DB 종속적인 쿼리 필요시
   5. JDBC API 직접사용, MyBatis, SpringJdbxTemplate 함께 사용

2. JPQL
   - JPA를 사용하면 엔티티 객체를 중심으로 개발
   - 문제는 검색 쿼리
   - 검색을 할 때도 **테이블이 아닌 엔티티 객체를 대상으로 검색**
   - 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
   - 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요
   - JPA는 SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
   - SQL과 문법 유서, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원
   - JPQL은 엔티티 객체를 대상으로 쿼리
   - SQL은 데이터베이스 테이블을 대상으로 쿼리


3. Criteria 소개 (망한 스팩)
   기본 JPQL만 사용하면 동적쿼리(if문 등 활용) 만들기 어렵다 -> 쿼리문이 스트링이기 때문에.

   -> 표준 스팩. 자바코드로 동적쿼리 작성할 수 있게 기능제공. -> 오타나 문법적 오류를 컴파일 단계부터 캐치 가능 -> But 문법이 쉽지 않다.(SQL스럽지 않다.) -> 실무에서 사용안함. 유지보수가 안된다.

  - 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
  - JPQL 빌더 역할
  - JPA 공식 기능
  - 단점 : 너무 복잡하고 실용성이 없다. : 망한 표준 스팩
  - Criteria 대신에 QueryDSL(Open Source Lib) 사용 권장

4. QueryDSL 소개
  - 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
  - JPQL 빌더 역할
  - 컴파일 시점에 문법 오류를 찾을 수 있음
  - 동적쿼리 작성 편리함
  - 단순하고 쉬움 (초기 설정은 빡셈)
  - 실무 사용 권장

5. 네이티브 쿼리
   문자열에 sql작성 

6. JDBC 직접 사용, SpringJdbcTemplate 등
  - JPA를 사용하면서 JDBC 커넥션을 직접 사용하거나, 스프링 JdbcTemplate, 마이바티스등을 함께 사용 가능
  - 단 영속성 컨텍스트를 적절한 시점에 강제로 플러시 필요
  - 예) JPA를 우회해서 SQL을 실행하기 직전에 영속성 컨테스트 수동 플래시

### 기본 문법과 쿼리 API
 - JPQL은 객체지향 쿼리 언어다. 따라서 테이블을 대상으로 쿼리하는 것이 아니라 엔티티 객체를 대상으로 쿼리한다.
 - JPQL은 SQL을 추상화해서 특정 데이터베이스 SQL에 의존하지 않는다.
 - JPQL은 결국 SQL로 변환된다.

 1. JPQL 문법
  ```
  select_문 :: = 
    select_절
    from_절
    [where_절]
    [groupby 절]
    ...
  ```
 - select m from Member as m where m.age > 18 :: 대소문자 구분
 - 엔티티와 속성은 대소문자 구분O (Member, age)
 - JPQL 키워드는 대소문자 구분X (SELECT, FROM, where)
 - 엔티티 이름 사용, 테이블 이름이 아님(Member)
 - 별칠른 필수(m) (하이버네이트는 as는 생략가능)

 2. 집합과 정렬
  ```
  select
    COUNT(m),
    SUM(m.age)
    AVG(m.age),
    MAX(m.age),
    MIN(m.age)
  from Member m 
  ```

  3. TypeQuery, Query
  - TypeQuery : 반환 타입이 명확할 때 사용
  ```
  TypeQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);
  ```
  - Query : 반환 타입이 명확하지 않을 때 사용
  ```
  Query query = em.createQuery("SELECT m.username, m.age from Member m", Member.class);
  ```

  -> 반환타입을 사용하지 않고 체이능을 사용하자. 그렇게 설계됨.

  ```
  em.createQuery("SELECT m.username, m.age from Member m where n.username =?1", Member.class)
  .setParameter("username","member1")
  .getSingleResult();
  ```

  4. 결과 조회 API
  - query.getResultList() : 결과가 하나 이상일 때, 리스트 반환
    -> 결과가 없으면 빈 리스트 반환
  - query.getSingleResult() : 결과가 정확히 하나, 단일 객체 반환
    - 결과가 없으면 : javax.persistence.NoResultException 
      : 논란이 많다.try-catch 해야됨. Spring Data JPA -> null, Optional 반환
    - 둘 이상이면 : javax.persistence.NonUniqueResultException

  5. 파라미터 바인딩 - 이름 기준, 위치 기준
  이름 기준
  ```
  SELECT m FROM Member m where m.username =:username
  ```

  ```
  query.setParameter("username", usernameParam);
  ```

  위치 기준
  ```
  SELECT m FROM Member m where m.username = ?1
  ```

  ```
  query.setParameter(1, usernameParam);
  ```

### 프로젝션(SELECT)
의미 -> SELECT 절에 조회할 대상을 지정하는 것

프로젝션 대상 : 엔티티, 임베디드 타입, 스칼라 타입(숫자, 문자등 기본 데이터 타입)

1. 엔티티 프로젝션
```

```

2. 엔티티 프로젝션 (JOIN)
```

```

3. 임베디드 타입 프로젝션
```

```

4. 스칼라 타입 프로젝션
```

```

- 프로젝션 - 여러 값 조회
이거 찾느라 똥뻘짓을 했다.