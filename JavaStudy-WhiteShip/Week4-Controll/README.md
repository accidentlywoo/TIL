# 4 주차 :: 제어문

> > 철저한 준비가 스스로의 운을 만든다 -조 포이어

이번주부터 과제가 있다.

0. JUnit 5 학습하세요.
- 인텔리J, 이클립스, VS Code에서 JUnit 5로 테스트 코드 작성하는 방법에 익숙해 질 것.
- 이미 JUnit 알고 계신분들은 다른 것 아무거나!
- 더 자바, 테스트 강의도 있으니 참고하세요~

1. [과제 live-study 대시 보드를 만드는 코드를 작성하세요.](#1-과제-live-study-대시-보드를-만드는-코드를-작성하세요)
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

3. [Stack을 구현하세요.](#3-stack을-구현하세요)
- int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.

4. [앞서 만든 ListNode를 사용해서 Stack을 구현하세요.](#4-앞서-만든-listnode를-사용해서-stack을-구현하세요)
- ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.
  
5. Queue를 구현하세요.

---

## 1. 과제 live-study 대시 보드를 만드는 코드를 작성하세요.
- 깃헙 이슈 1번부터 18번까지 댓글을 순회하며 댓글을 남긴 사용자를 체크 할 것.
- 참여율을 계산하세요. 총 18회에 중에 몇 %를 참여했는지 소숫점 두자리가지 보여줄 것.
- Github 자바 라이브러리를 사용하면 편리합니다.
- 깃헙 API를 익명으로 호출하는데 제한이 있기 때문에 본인의 깃헙 프로젝트에 이슈를 만들고 테스트를 하시면 더 자주 테스트할 수 있습니다.

## 2. LinkedList를 구현하세요.
### LinkedList에 대해 공부하세요.

    상속 구조 : 
    1. Object
        : clone, equals, getClass, hashCpde, toString, wait,notify
    2. AbstractCollection 
        : add, addAll, clear, contains, isEmpty, iterator, remove, removeAll, toArray
    3. AbstractList
    4. AbstractSequentialList
        : 
    5. LinkedList 

    메모리의 물리적 위치가 주어지지 않는 데이터 요소들의 선형 집합이다.

- 정수를 저장하는 ListNode 클래스를 구현하세요.
- ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.
- ListNode remove(ListNode head, int positionToRemove)를 구현하세요.
- boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.

## 3. Stack을 구현하세요.
- int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.

## 4. 앞서 만든 ListNode를 사용해서 Stack을 구현하세요.
- ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.
- void push(int data)를 구현하세요.
- int pop()을 구현하세요.
  
## 5. Queue를 구현하세요.

---

### 참고 사이트
- [Java 11 API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/LinkedList.html)
