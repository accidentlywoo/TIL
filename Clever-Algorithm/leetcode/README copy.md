# 리릿코드
# DataStructure
## Binary Tree
- 소개
  트리는 계층 트리 구조를 구현하기위해 자주 사용된다.

  각 트리의 노드들은 루트 값을 갖는다 그리고 자식 노드라 불리는 다른노드에대한 참조 리스트가 있다.
  그래프 뷰에서, 트리는 ```N nodes``` 그리고  ```N-1 edges``` 갖는 방향성 순환 그래프라고 정의할 수 있다.

  각 이진 트리는 가장 일반적인 트리 구조이다.
  1. 트리와 이진 트리의 컨셉을 이해하기
  2. 다른 순회 방법들과 친해지기
  3. 이진트리문제를 해결하기위해 재귀를 사용하라

### 트리의 순회 (Traverse a Tree)
1. Pre-order Traversal
    pre-order traversal은 root를 먼저 방문한다. 그리고 왼쪽 하위트리를 순회한다.
    먀지막으로, 오른쪽 하위트리를 순회한다.
3. In-order Traversal
    in-order traversal은 왼쪽 하위트리부터 방문한다. 그리고 root를 방문한다.
    마지막으로, 오른쪽 하위트리를 순회한다.
4. Post-order Traversal
    post-order traversal 왼쪽 하위트리를 순회한다. 그리고 오른쪽 하위트리를 순회한다. 마지막으로 root를 방문한다.
    트리에서 노드를 삭제할때 과정은 post-order로 진행된다는 점이 주목할만하다.
    즉, 노드를 삭제할때, 현재노드를 삭제하기 전에 왼쪽자식을 삭제하고, 오른쪽자식을 삭제한다.

    또한, post-order는 수학적 표현에서 널리 사용된다. 
    프로그램 구문을 쓸때 이는 더 쉽다.
  <img src="https://github.com/accidentlywoo/TIL/blob/main/Clever-Algorithm/diagram/post-order.png" width="30%" height="30%" display="inline-block" alt="post order"/>
  위 그림은 in-order 순회로 쉽게 이해할 수 있다. 하지만 프로그래밍에서 연산자의 우선순위를 체크해야하는 표현을 다루기 어렵다.

  post-order로 이문제를 다루면, stack을 이용해 쉽게 표현식을 다룰 수 있다.
  연산자를 만날때마다 사용자는 stack에서 2개의 요소를 pop해서 수행할 수 있다.
  연산의 결과는 stck에 다시 push한다.

5. Recursive or Iterative
  메서드를 재귀적 또는 반복적으로 구현할 수 있다.
  재귀 및 반복 솔루션을 모두 구현하고 그 차이점을 비교해보십시오.