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
    양수일때 빈자리는 0으로 채워지고, 밀린 3비트는 버려진다.
<img src="https://github.com/accidentlywoo/TIL/blob/main/images/2<<3.png" width="60%" height="30%" display="inline-block" alt="연산자 우선순위"/>

    16 >> 3
<img src="https://github.com/accidentlywoo/TIL/blob/main/images/16>>3.png" width="60%" height="30%" display="inline-block" alt="연산자 우선순위"/>

    -16 >> 3
    음수의 경우 
    빈자리는 1로 채워진다.
<img src="https://github.com/accidentlywoo/TIL/blob/main/images/-16>>3.png" width="60%" height="30%" display="inline-block" alt="연산자 우선순위"/>

    -16 >>> 3
    오직 자바에만 있는 연산. 
    >>와 다르게 양수 음수 상관없이 무조건 0으로 빈칸이 채워진다.
 <img src="https://github.com/accidentlywoo/TIL/blob/main/images/-16>>>3.png" width="60%" height="30%" display="inline-block" alt="연산자 우선순위"/>   

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

    이 연산자는 Java 11에서 deprecated되었다. 
    클래스 이름으로만 타입을 검증하는 것이 정확하지 않기 때문에(다른 패키지 같은 이름 클래스 생성 가능),
    FQCN(Full Qualify ClassName :: 패키지 경로 포함한 클래스명) 타입체크를 권장하고 있다.
    (관련 자료 찾기*) 

[Java 11 Deprecated API](https://docs.oracle.com/en/java/javase/11/docs/api/deprecated-list.html)

## 6. assignment(=) operator
    할당 연산자는 말 그대로 할당해주는 연산자이다.
    이 연산자는 Object에 참조값을 할당할 수 있다. 

## 7. 화살표(->) 연사자
    화살표 연산자는 람다식을 표햔한다.
    람다식은 매개변수의 데이터 유형을 생략할 수 있다.
    또, 매개변수가 하나일 경우 괄호를 생략할 수 있다.

    단일 표현식을 지정하면 Java 런타임 표현식을 평가 한 다음 해당 값을 반환한다.
```
p -> {
    return p.getGendrt() == Person.Sex.MALE
    && p.getAge() >= 18
    && p.getAge() <= 25;
}
```
    return문은 표현식이 아니다. 그래서 람다식에서 구문을 {}로 묶어야 한다.
    그러나 void 메서드는 {}로 안묶어도 된다.
    아래는 유효한 람다식이다.
```
email -> System.out.println(email);
```

## 8. 3항 연산자(ternary conditional operator)
    if-else 구문에서 사용할 수 있다.

```
booleanExpression ? expression1 : expression2
```

## 9. 연산자 우선 순위
<img src="https://github.com/accidentlywoo/TIL/blob/main/images/operator.png" width="30%" height="30%" display="inline-block" alt="연산자 우선순위"/>

## 10. (optional) Java 13. switch 연산자
    기존 switch 문은 그대로 존재하고, Java 12부터 switch 연산자가 추가된것
    헷갈리지 말자!

    Java 12는 switch 표현식과 마찬가지로 단일 값으로 평가되고 명령문에서 사용할 수 있는 표현식을 도입했다.
    또한 break 명령문이 필요하지 않은 '화살표' 레이블을 도입했다..
    Java 13은 switch 식에 yield를 도입했다.

```
public enum Day { SUNDAY, MONDAY, TUESDAY,
    WEDNESDAY, THURSDAY, FRIDAY, SATURDAY; } // base 
```
    
```
int numLetters = 0;
    Day day = Day.WEDNESDAY;
    switch (day) {
        case MONDAY:
        case FRIDAY:
        case SUNDAY:
            numLetters = 6;
            break;
        case TUESDAY:
            numLetters = 7;
            break;
        case THURSDAY:
        case SATURDAY:
            numLetters = 8;
            break;
        case WEDNESDAY:
            numLetters = 9;
            break;
        default:
            throw new IllegalStateException("Invalid day: " + day);
    }
    System.out.println(numLetters);
```

    java 12에서 switch 구문을 표현식으로 쓸 수 있어졌다. 
```
Day day = Day.WEDNESDAY;    
    System.out.println(
        switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY                -> 7;
            case THURSDAY, SATURDAY     -> 8;
            case WEDNESDAY              -> 9;
            default -> throw new IllegalStateException("Invalid day: " + day);
        }
    ); 
```
    값을 대입할 수 있다.
```
 int numLetters = 0;
    Day day = Day.WEDNESDAY;
    switch (day) {
        case MONDAY, FRIDAY, SUNDAY -> numLetters = 6;
        case TUESDAY                -> numLetters = 7;
        case THURSDAY, SATURDAY     -> numLetters = 8;
        case WEDNESDAY              -> numLetters = 9;
        default -> throw new IllegalStateException("Invalid day: " + day);
    };
    System.out.println(numLetters);
```

    Java 13에서 yeild명령문이 도입된다. 화살표 표현말고 : 을 사용해도되지만, -> 가 더 우아해 보인다.
```
 int numLetters = switch (day) {
        case MONDAY, FRIDAY, SUNDAY -> {
            System.out.println(6);
            yield 6;
        }
        case TUESDAY -> {
            System.out.println(7);
            yield 7;
        }
        case THURSDAY, SATURDAY -> {
            System.out.println(8);
            yield 8;
        }
        case WEDNESDAY -> {
            System.out.println(9);
            yield 9;
        }
        default -> {
            throw new IllegalStateException("Invalid day: " + day);
        }
    };  
```

## 빛기선님의 유튭으로 얻은 허니팁
1. it, ital 을 인텔리J에서 치면 for문 자동 생성
2. 중간값을 구하는 로직에서 꿀팁
    특히, 멀티 쓰레드 프로그래밍 환경에서 주의해야할 사항
    아래의 단순한 예제(받아들이는 값의 최대치는 검증되고 들어왔다고 생각하고 반올림 계산)
```
public class Operator{
    public static void main(String[] args){
        int start = Integer.MAX_VALUE;
        int end = Integer.MAX_VALUE;
        int mid = (start + end) / 2;
        System.out.println("Stck Over Flow ! : " + mid); // -1
        System.out.println("binary mid : " + intToBinaryString(mid));
        // 1_1111_1111_1111_1111_1111_1111_1111_111
    }
    static String intToBinaryString(int b){
        String builder = "";
        for(int i = 0; i < 16; i ++){
            builder+=((0x80000000 >>> i) & b) == 0 ? '0' : '1';
           if(i != 15 && i%4 == 0)
                builder+='_';
        }
        return builder;
    }
}
```
    int는 4byte이기 때문에 -2^31 ~ 2^31-1범위를 넘어가면 데이터 유실이 발생한다.
    큰값을 계산할 수 있거나, 멀티 쓰레드 환경에서 예상치 못한 결과값이 나오기 때문에,회피하는 것이 좋다.
```
public class Operator{
    public static void main(String[] args){
        int start = Integer.MAX_VALUE;
        int end = Integer.MAX_VALUE;
        int mid = start + (end - start) / 2;
        System.out.println("Stack Over Flow Evasion : " + mid); 
        // 2147483647
        System.out.println("binary mid : " + intToBinaryString(mid));
        // 0_1111_1111_1111_1111_1111_1111_1111_111
    }
    static String intToBinaryString(int b){
        String builder = "";
        for(int i = 0; i < 32; i ++){
            builder+=((0x80000000 >>> i) & b) == 0 ? '0' : '1';
           if(i != 15 && i%4 == 0)
                builder+='_';
        }
        return builder;
    }
}
```
    갓기선님이 알려주신 간지 비법 시프트 연산자 활용
```
public class Operator{
    public static void main(String[] args){
        int start = Integer.MAX_VALUE;
        int end = Integer.MAX_VALUE;
        int  mid = (start + end) >>> 1;
        System.out.println("Ssap Gangi : " + mid);
        // 2147483647
        System.out.println("binary mid : " + intToBinaryString(mid));
        // 0_1111_1111_1111_1111_1111_1111_1111_111
    }
    static String intToBinaryString(int b){
        String builder = "";
        for(int i = 0; i < 32; i ++){
            builder+=((0x80000000 >>> i) & b) == 0 ? '0' : '1';
           if(i%4 == 0)
                builder+='_';
        }
        return builder;
    }
}
```
    아래와 같은 >> 연산은 OverFlow발생시, 
    부호연산을 담당하는 맨앞비트까지 영향이 간다.
    따라서, >> 연산은 음수 결과가 나온다.
```
        mid = (start + end) >> 1;
        System.out.println("Ssap Gangi fail: " + mid);
        System.out.println("binary mid fail: " + intToBinaryString(mid));
```
    
[위 예제도 바로 실행가능하지만 Full 실습코드](https://github.com/accidentlywoo/TIL/tree/main/JavaStudy-WhiteShip/Week3-Java-Operator/Operator.java)

1. xor연산으로 배열의 중복값 검출해보기

### 참고 사이트
- [공홈 Java8 Tutorials](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html)
- [공홈 Java13 Tutorials](https://docs.oracle.com/en/java/javase/13/language/switch-expressions.html)
- [코딩 팩토리 : 비트 쉬프트](https://coding-factory.tistory.com/521)