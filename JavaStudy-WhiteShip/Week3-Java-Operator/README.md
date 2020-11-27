# 3 주차 :: 연산자

> > 고통을 주지 않는 것은 쾌락도 주지 않는다.

1. 산술 연산자
2. 비트 연산자
3. 관계 연산자
4. 논리 연산자
5. instanceof
6. assignment(=) operator
7. 화살표(->) 연사자
8. 3항 연산자
9. 연산자 우선 순위
10. (optional) Java 13. switch 연산자

---

## 1. 산술 연산자 (Arithmetic Operator)
    자바의 산술연산자 : +, -, *, /(몫), %(나머지)
    + 는 문자열 연결에도 사용한다.

    단항 연산자 : +(생략가능), -, ++, --, !
    ex) int i = +1;
    
    증감 연산자 (++, --)는 피연산자 앞이나 뒤에 붙일 수 있다.
    그런데, 프로그래밍 언어에서 '평가'라는 개념이 있는데,
    피연산자 앞에 붙은 증감 연산자는 연산된 값으로 평가되는 반면 
    피연산자 뒤에 붙은 증감 연산자는 연산되기전의 원래 값으로 평가된다. 

## 2. 비트 연산자
    자바는 정수 유형에 대해 비트, 비트 쉬프트 연산자를 제공한다. 
    일반적인 상황에서 사용되지 않기때문에 간단히 소개한다

    단항 비트 보수 연산자 "~"는 비트 패턴을 반전한다.
    모든 0 -> 1로 만들고 1 -> 0으로 만드는 모든 정수 유형에 적용할 수 았다
    
    ex) a byte는 8 비트로 구성되어있다. 
    비트 패턴이 00000000 인 값에 이 연산자를 적용하면 -> 11111111로 변경된다

    시프트 연산자는 >> , << 방향에 따라 비트 이동하고  
    >>> 는 맨 왼쪽으로 비트를 이동한다. 
    양수, 음수일때 다른동작을 하니 구분해보자!
    
    ex) 자바 기본 정수타입은 int는 4바이트
    2 << 3 
    2를 32비트로 분해한다음 왼쪽으로 3비트 이동

    -16 >> 3

    -16 >>> 3

    비트 & 연산자는 비트 and 연산을 수행

    비트 ^ 연산자는 비트 배타적 or 연산 수행 (short cut 없는 or연산인듯)

    비트 | 연산자는 비트 포함 or 연산을 수행

## 3. equality and Relational Operators
    동등과 관계 연산자는 값이 큰지 작은지 등을 비교하고, 
    primitive 타입의 값에서 사용한다.

    == : 동등비교
    != : 같지 않음 비교
    > : 초과 비교
    >= : 이상 비교
    < : 미만 비교
    <= : 이하 비교

## 4. 논리 연산자
    &&와 || 연산자는 boolean 값에서 Conditional-AND와 Conditional-OR 연산을 실행한다.
    이 연산자는 "short-circuiting"이 존재한다.

    && 에서 "short-circuiting"
    (1)ture && (2)false :: (1),(2) 모두 평가 후 false반환
    (1)false && (2)true :: (1)이 false이기 때문에 구문 평가를 종료하고 false반환

    || 에서 "short-circuiting"
    (1)ture && (2)false :: (1)이 true이기 때문에 구문 평가를 종료하고 true반환
    (1)false && (2)true :: (1),(2) 모두 평가 후 true반환
    (1)false && (2)false :: (1),(2) 모두 평가 후 false반환

## 5. instanceof (The Type Comparison Operator instanceof)
    instanceof 연산자는 객체를 특정 타입과 비교한다. 
    사용자는 클래스, 서브클래스, 특정 인터페이스를 구현하는 클래스의 객체인지 확인할 수 있다.

    이 연산자는 Java 11에서 deprecated되었다. [Java 11 Deprecated API](https://docs.oracle.com/en/java/javase/11/docs/api/deprecated-list.html)
    클래스 이름으로만 타입을 검증하는 것이 정확하지 않기 때문에(다른 패키지 같은 이름 클래스 생성 가능),
    FQCN(Full Qualify ClassName :: 패키지 경로 포함한 클래스명) 타입체크를 권장하고 있다.
    (관련 자료 찾기) 

## 6. assignment(=) operator

## 7. 화살표(->) 연사자
## 8. 3항 연산자
## 9. 연산자 우선 순위
## 10. (optional) Java 13. switch 연산자

### 참고 사이트
- [공홈 Java Tutorials](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [코딩 팩토리 : 비트 쉬프트](https://coding-factory.tistory.com/521)