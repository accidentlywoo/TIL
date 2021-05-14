# Mock
모킹이 모킹?

## 들어가기 전에, Test Double이란?
- [밥아져씨의 테스트 대역 읽으러 가기](https://www.martinfowler.com/bliki/TestDouble.html#:~:text=Test%20Double%20is%20a%20generic,used%20to%20fill%20parameter%20lists)

테스트 대역(Test double)은 테스트 목적의 프로덕션 오브젝트 대체할 수 있는 오브젝트를 부르는 일반적인 용어이다.

- Dummy : 오브젝트는 전달되지만 실제로 사용되지는 않는다. 일반적으로 매개 변수 목록을 채우는데 사용된다.

- Fake : 객체는 실제로 작동하는 구현을 가지고 있지만 일반적으로 프로덕션에 적합하지 않은 단순표현을 쓴다.(ex. InMemoryTestDatabase)

- Stubs : 테스트 중 이루어진 call에 대한 준비된 답변을 제공, 일반적으로 테스트를 위해 프로그래밍 된 내용이외의 항목에는 전혀 응답하지 않는다.

- Spies : 호출 방법에 따라 일부 정보를 기록하는 스텁.(ex 전송 된 메시지 수를 기록하는 이메일 서비스일 수 있다.)

- Mocks : 기대한 응답의 구체적인 호출을 미리 프로그래밍 한다. 목들은 예외를 던질 수도 있습니다.

### 젤 유명한 Mock과 Stub를 구분해보자
- Stub은 테스트 대상이 처리하는 결과, 즉 상태를 테스트할 때 도음을 주기 위해 만들고 사용

- Mock은 테스트 대상의 행위가 어떠한가를 확인하고 싶을 때 사용한다.

## MockK들어가기
[공식 홈페이지](https://mockk.io/)

