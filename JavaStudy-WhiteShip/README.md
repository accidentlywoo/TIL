# JavaStudy-WhiteShip
매주 토요일 10시 반 유튜브 스트리밍
    ::[과제 제출](https://github.com/whiteship/live-study/issues)
## 스터디 목적
기본기 + 알파를 위한 꾸준한 노력과 정성, 함께자라는 즐거움 + 갓기선님의 허니팁 챙기기

(:open_book: - 공부 중, :ledger: - 읽기 끝, :closed_book: - 정리 끝)

### 1. [1 주차 :: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가](https://github.com/accidentlywoo/TIL/tree/main/JavaStudy-WhiteShip/Week1-JVM-HowToRunJavaCode) :closed_book:
1. JVM이란 무엇인가
2. 컴파일 하는 방법
3. 실행하는 방법
4. 바이트코드란 무엇인가
5. JIT 컴파일러란 무엇이며 어떻게 동작하는지
6. JVM 구성 요소
7. JDK 와 JRE 의 차이
  
### 2. [2 주차 :: 자바 데이터 타입, 변수 그리고 배열](https://github.com/accidentlywoo/TIL/tree/main/JavaStudy-WhiteShip/Week2-Java-DataType-Variable-Array) :open_book:
1. 프리미티브 타입 종류와 값의 범위 그리고 기본 값
2. 프리미티브 타입과 레퍼런스 타입
3. 리터럴
4. 변수 선언 및 초기화하는 방법
5. 변수의 스코프와 라이프타임
6. 타입 변환, 캐스팅 그리고 타입 프로모션
7. 1차 및 2차 배열 선언하기
8. 타입 추론, var
### 3. [3 주차 :: 연산자](https://github.com/accidentlywoo/TIL/tree/main/JavaStudy-WhiteShip/Week3-Java-Operator) :closed_book:
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
### 4. [4 주차 :: 제어문](https://github.com/accidentlywoo/TIL/tree/main/JavaStudy-WhiteShip/Week4-Controll):open_book:
0. JUnit 5 학습하세요.
- 인텔리J, 이클립스, VS Code에서 JUnit 5로 테스트 코드 작성하는 방법에 익숙해 질 것.
- 이미 JUnit 알고 계신분들은 다른 것 아무거나!
- 더 자바, 테스트 강의도 있으니 참고하세요~
  
1. 과제 live-study 대시 보드를 만드는 코드를 작성하세요.
- 깃헙 이슈 1번부터 18번까지 댓글을 순회하며 댓글을 남긴 사용자를 체크 할 것.
- 참여율을 계산하세요. 총 18회에 중에 몇 %를 참여했는지 소숫점 두자리가지 보여줄 것.
- Github 자바 라이브러리를 사용하면 편리합니다.
- 깃헙 API를 익명으로 호출하는데 제한이 있기 때문에 본인의 깃헙 프로젝트에 이슈를 만들고 테스트를 하시면 더 자주 테스트할 수 있습니다.

2. [LinkedList를 구현하세요.](#2-linkedlist를-구현하세요)
- LinkedList에 대해 공부하세요.
- 정수를 저장하는 ListNode 클래스를 구현하세요.
- ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
- ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
- boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.

3. Stack을 구현하세요.
- int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.

4. 앞서 만든 ListNode를 사용해서 Stack을 구현하세요.
- ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.
  
5. Queue를 구현하세요.
### 5. [5 주차 :: 클래스](https://github.com/accidentlywoo/TIL/tree/main/JavaStudy-WhiteShip/Week5-Class) :open_book:
1. 클래스를 정의하는 방법
2. 객체 만드는 방법 (new 키워드 이해하기)
3. 메소드 정의라는 방법
4. 생성자 정의하는 방법
5. this 키워드 이해하기

---

6. int값을 가지고 있는 이진 트리를 나타내는 Node라는 클래스를 정의하세요.
7. int value, Node left,right를 가지고 있어야 한다.
8. BinaryTree라는 클래스를 정의라고 주어진 노드를 기준으로 출력하는 bfs(Node node)
9. DFS는 왼쪽, 루트, 오른쪽 순으로 순회하세요

### 6. [6 주차 :: 상속](./Week6-Extension)
1. 자바 상속의 특징
2. super 키워드
3. 메소드 오버라이딩
4. 다이나믹 메소드 디스패치(Dynamic Method Dispatch)
5. 추상 클래스
6. final 키워드
7. Object 클래스

### 7. 7 주차 :: 패키지
### 8. 8 주차 :: 인터페이스
### 9. 9 주차 :: 예외 처리
### 10. 10 주차 :: 멀티쓰레드 프로그래밍
### 11. 11 주차 :: Enum
### 12. 12 주차 :: Annotation
### 13. 13 주차 :: I/O
### 14. 14 주차 :: 제네릭
### 15. 15 주차 :: 람다식
### 16. 16 주차 :: 문자열
### 17. 17 주차 :: 콜렉션
### 18. 18 주차 :: 스트림