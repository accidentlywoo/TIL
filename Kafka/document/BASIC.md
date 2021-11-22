
## Apache Kafka 란?
데이터 이모션 프로그램, 이벤트 스트리밍 플렛폼

Real-time Event Streams -> Kafka -> Real-time Event Streams 

- Event 란?
 Event는 비즈니스에서 일어나는 모든 일(데이터)을 의미
  -> 웹사이트 클릭, 청구서 발행, 송금, 택시 호출 GPS 좌표

- Event Stream은 무엇인가?
 연속적인 많은 이벤트들의 흐름

Event BigData의 특징을 가짐

 -> 비즈니스의 모든 영역에서 광범위하게 발생

 -> 대용량의 데이터 (Big Data) 발샐

 Event Stream은 연속적인 많은 이벤트들의 흐름을 의미

- Apache Kafka의 탄생 
LinkedIn내에서 개발

기존의 Messaging Platform(MQ)로 처리 불가능

이벤트 스트림 처리를 위해 개발

2011에 Apache 재단에 기부

- Apache Kafka의 특징 : 3가지 주요 특징
1. 이벤트 스트림을 안전하게 전송 : Publish & Subscribe
2. 이벤트 스트림을 디스킁에 저장 : Write to Disk
3. 이벤트 스트림을 분석 및 처리 : Processing & Ananlysis

## Topic, Partition, Segment

### Topic
Kafaka 안에서 메시지가 저장되는 장소. 논리적인 표현

producers.. -> topic -> consumers..

- Producer : 메시지를 생산(Produce)해서 Kafka의 Topic으로 메시지를 보내는 애플리케이션
- Consumer : Topic의 메시지를 가져와서 소비(Consume)하는 애플리케이션
- Consumer Group : Topic의 메시지를 사용하기 위해 협력하는 Consumer들의 집합
- 하나의 Consumer는 하나의 Consumer Group에 포함되며, Consumer Group내의 

### Producer와 Consumer의 기본 동작 방식
Producer와 Consumer의 분리(Decoupling)

- Producer와 Consumer는 서로 알지 못하며, Producer와 Consumer는 각각 고유의 속도로 Commit Log에 Write 및 Read를 수행
- 다른 Consumer Group에 속한 Consumer들은 서로 관련이 없으며, Commit Log에 있는 Event(Message)를 동시에 다른 위치에서 Read할 수 있음

### Kafka Commit Log
추가만 가능하고 변경 불가능한 데이터 스트럭처

- Commit Log : 추가만 가능하고 변경 불가능한 데이터 스트럭처. 데이터(Event)는 항상 로그 끝에 추가되고 변경되지 않음
- Offset : Commit Log에서 Event의 위치

### Kafka Offset
Commit Log에서 Event의 위치

- Producer가 Write라는 ```LOG-END-OFFSET```과 Consumer Group의 Consumer가 Read하고,
처리한 후에 Commit한 ```CURRENT-OFFSET``과의 차이(Consumer Lag)가 발생할 수 있음.

---

논리적 정의를 정리해보자!

- Topic : Kafka 안에서 메시지가 저장되는 장소, 논리적인 표현
- Partition : Commit Log, 하나의 Topic은 하나 이상의 Partition으로 구성. 병렬처리(Throughput 향상)를 위해서 다수의 Partition 사용
- Segment : 메시지(데이터)가 저장되는 실제 물리 File. Segment File이 지정된 크기보다 크거나 지정된 기간보다 오래되면 새 파일이 열리고 메시지는 새 파일에 추가됨.

물리적 정의를 정리해보자!

- Topic 생성시 Partition 개수를 지정하고, 각 Partition은 Broker들에 분산되며 Segment File들로 구성됨

- Rolling Strategy : log.segment.bytes(default 1GB), log.roll.hours(default 168 hours)

Partition당 오직 하나의 Segment가 활성화(Active)되어 있음.(가장 마지막 Segment)
-> 데이터가 계속 쓰여지고 있는 중

### 정리하기
- Topic 생성시 Partition 개수를 지정. 개수 변경 가능하나 운영시에는 변경 권장하지 않음
- Partition 번호는 0부터 시작하고 오름차순
- Topic내의 Partition 들은 서로 독립적임
- Event(Message)의 위치를 나타내는 offset이 존재
- Offset은 하나의 Partition에서만 의미를 가짐. Partition 0의 offset 1 != Partition 1의 offset1
- Offset 값은 계속 증가하고 0으로 돌아가지 않음
- Event(Message)의 순서는 하나의 Partition내에서만 보장
- Partition에 저장된 데이터(Message)는 변경이 불가능(Immutable)
- Partition에 Write되는 데이터는 맨 끝에 추가되어 저장됨
- Partition은 Segment File들로 구성됨. Rolling 정책: log.segment.bytes(default 1 GB), log.roll.hours(default 168 hours)
