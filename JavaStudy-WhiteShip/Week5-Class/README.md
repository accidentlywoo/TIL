# 5 주차 :: 클래스

> > 너무 하기 귀찮아도 한번만 더, 한 걸음만 더

- [5 주차 :: 클래스](#5-주차--클래스)
  - [클래스를 정의하는 방법](#클래스를-정의하는-방법)
  - [객체 만드는 방법 (new 키워드 이해하기)](#객체-만드는-방법-new-키워드-이해하기)
  - [메소드 정의라는 방법](#메소드-정의라는-방법)
  - [생성자 정의하는 방법](#생성자-정의하는-방법)
  - [this 키워드 이해하기](#this-키워드-이해하기)
  - [참고 사이트](#참고-사이트)

---

[과제 코드 저장소](https://github.com/accidentlywoo/TIL/tree/main/JavaStudy-WhiteShip/java-study)
6. int값을 가지고 있는 이진 트리를 나타내는 Node라는 클래스를 정의하세요.
7. int value, Node left,right를 가지고 있어야 한다.
8. BinaryTree라는 클래스를 정의라고 주어진 노드를 기준으로 출력하는 bfs(Node node)
9. DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요

---

## 클래스를 정의하는 방법
    클래스는 다음과 같은 방식으로 정의 한다.
```
class ClassName{
    // field, constructor, and
    // method declarations
}
```

    이것이 클래스 선언이다. 클래스 바디(컬리브레이스 사이{})에서 생성 된 객체의 라이프 사이클을 제공 모든 코드가 포함되너, 클래스와 객체의 상태를 제공 필드에 대한 새 개체를 초기화하는 생성자 선언을 하고 클래스 및 해당 개체의 동작을 구현하는 메서드.

    클래스 앞에는 pulic또는 private과 같은 modifier가 붙을 수 있다.
    클래스 뒷 부분에는 상속과 관련된 extends, implements 가 붙을 수 있다.

    일반적으로 클래스 선언에 다름 구성요소가 순서대로 포함될 수 있다.

1. public, private 및 여러 modifier
2. 클래스이름은 첫 글자가 대문자인게 국룰이다.
3. 클래스의 부모(super class)이름 (있는 경우) extends키워드 를 붙인다. 자바는 단일상속만 지원
4. 인터페이스의 구현은 implements키워드를 사용하고, 반점(,)으로 구분 되여 둘 이상의 인터페이스를 구현할 수 있다.
5. 중괄호(컬리 브레이스) {} 로 둘러싸인 부분이 클래스 본문이다.

## 객체 만드는 방법 (new 키워드 이해하기)
    클래스는 객체에 대한 청사진(blueprints)을 제공한다
    클래스에서 객체를 만든다. 
    다른 표현으로 객체화한다.
    또, 다른 표현으로 인스턴스를 생성한다 라고 표햔한다.
    클래스를 인스턴스화해야지 힙메모리에 하나의 객체가 생성되고 사용할 수 있게 된다.

    new 키워드는 객체를 생성하는 Java연산자이다.

    클래스를 인스턴스화하는 방법(초기화 방법)은 new 키워드와 생성사를 호출하는 구문을 사용하면 된다.

    이는 특수한 구문으로 생성자 호출은 반드시 new키워드와 사용할 수 있다.

```
Point originOne = new Point(23, 94);
Rectangle rectOne = new Rectangle(originOne, 100, 200);
Rectangle rectTwo = new Rectangle(50, 100);
```
    위 예시처럼 객체(인스탄스)를 참조하는 변수를 선언할 때
```type name;``` 형식으로 작성한다. 이는 컴파일러에게 해당 타입의 데이터를 참조하기 위해 name이라는 변수명을 사용할 것임을 알린다.

    primitive type을 type으로 사용하게 되면, 해당 구문은 지정된 메모리 크기를 변수에 할당한다. ex.```int number; s```

    사용자는 참조값을 선언할 수 있다. ex.```Point originOne;```
    이렇게 선언된 ```originOne```은 객체가 실제로 생성되어 할당 될 때까지 값이 결정되지 않는다.

## 메소드 정의라는 방법
    일반적인 메서드 선언의 예
```
public double calculateAnswer (double wingSpan, int numberOfEngines,
                              double length, double grossTons) {
    // do something..
}
```
    메서드 선언의 유일한 필수 요소는 메서드의 반환 유형, 이름, 괄호 쌍 (), 및 중괄호 사이의 본문, {} 이다.

    일반적으로 메서드 선언에는 순서대로 6개의 구성 요소가 있다.
1. 수정자(mdifier) : public, private...등
2. 반환 유형(타입), 없을땐 void 
3. 메서드 이름. 메서드 이름을 잘지어야 한다. 아-주 자알
4. 괄호 안에 매개변수 타입-변수명 쌍으로 쉼펴(,) 구분. 매개변수가 없다면 빈 괄호 사용()
5. 예외 목록
6. 중괄호에 묶인 {} 메서드 본문  

메서드 시그니처라는 개념이 있다. 메서드 명, 매개변수 타입 리스트(순서 고려됨)로 구성된다
위 예시의 메서드의 시그니처는 ```calculateAnswer (double , int , double , double )```

메서드는 힙메모리에 적제된 인스턴스 정보내부에서 메소드 시그니처만 같게된다.
같은 클래스에서 메소드 시그니처가 같은 메소드를 정의하면 컴파일 에러가 난다.
다른말로하면, 같은 메서드 명으로 매개변수를 다르게하면, 다른 메소드 시그니처로 인식한다.이를 **오버로딩**이라 부른다.

반환 타입은 메서드 시그니처 요소가 아니기 때문에, 같은 이름, 같은 파라미터 변수면 컴파일에러가 난다.
 <img src="./../images/methodsignature.png" width="60%" height="60%" display="inline-block" alt="메서드 시그니처"/> 

오버로딩(Overloading)의 예시
```
public class DataArtist { 
    ... 
    public void draw (String s) { 
        ... 
    } 
    public void draw (int i) { 
        ... 
    } 
    public void draw (double f) { 
        ... 
    } 
    public void draw (int i, 이중 f) { 
        ... 
    } 
}
```
## 생성자 정의하는 방법
    클래스를 이용해, 객체화, 인스턴스화 하기 위해 생성자를 사용한다.
    생성자는 메서드처럼 생겼지만 클래스 이름을 사용하고, 반환유형이 없다.
    메서드와 비슷한듯 엄청난 차이가 있고, 특별한 녀석이기때문에 사용하는데에 제약사항이 많다.
    팩토리 패턴을 사용해라

    Bicycle클래스의 생성자 예시
```
public Bicycle(int startCadence, int startSpeed, int startGear) {
    gear = startGear;
    cadence = startCadence;
    speed = startSpeed;
}
```
    새로운 myBike 이라불리는 Bicycle 객체를 생성하기 위해 new 연산자와 생성자를 사용한다

```
Bicycle myBike = new Bicycle(30, 0, 8);
```

    생성자를 통해 인스턴스 초기화 시 필드값을 지정할 수 있다.

## this 키워드 이해하기
    인스턴스 메서드 또는 생성자 내에는 현재 객체(메서드 또는 생성자가 호출되는 객체 this)에 대한 참조가 있다. 
    this를 사용하여 인스턴스 메서드 또는 생성자 내에서 현재 객체의 모든 멤버를 참조할 수 있다.

    생성자에서  this 사용 예시
```
public class Point {
    public int x = 0;
    public int y = 0;
        
    //생성자
    public Point (int a, int b) {
        x = a;
        y = b;
    }
}
```
    위의 예시에서 생성자에서 필드와 구분되는 파라미터 변수명을 작성했다.

    프로그래밍에서 변수명 짓기가 가장 어렵고 시간이 많이들며 프로그래밍의 복잡도를 저세상으로 끌어올린다.

```
public class Point {
    public int x = 0;
    public int y = 0;
        
    //생성자
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
``` 
    위 예에서 생성자에서 this를 사용해 클래스 필드에 접근하고 있다.
    this키워드를 빼면, 변수명은 가장 가까운 Scope(자바는 {}기준)가 생성자이기 때문에
    생성자 파라미터의 변수에 변수를 대입하는 무 창조 행위를 한다.

   - 참고
   메소드와 생성자의 파라미터는 메소드 본문에 argument로써 내부변수처럼 선언되어 진다.
   따라서 메소드와 생성자 파라미터와 같은 변수명과 타입을 본문에 사용하면 컴파일 에러가 발생한다.

---

## 참고 사이트
- [Oracle Java 11 Tutorials](https://docs.oracle.com/javase/tutorial/java/javaOO/classdecl.html)