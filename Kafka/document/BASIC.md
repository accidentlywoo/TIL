
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

## Broker, Zookeeper
### Kafka Broker 
Kafka Broker는 Partition에 대한 Read 및 Write를 관리하는 소프트웨어

- Kafka Server라고 부르기도  함
- Topic 내의 Partition들을 분산, 유지 및 관리
- 각각의 Broker들은 ID로 식별됨 (단, ID는 숫자)
- Topic의 일부 Partition들을 포함
  -> Topic 데이터의 일부분(Partition)을 갖을 뿐 데이터 전체를 갖고 있지 않음
- Kafka Cluster : 여러 개의 Broker들로 구성됨
- Client는 특정 Broker에 연결하면 전체 클러스터에 연결됨
- 최소 3대 이상의 Broker를 하나의 Cluster로 구성해야 함
  -> 4대 이상을 권장함

Kafka Broker ID와 Partition ID는 아무런 관계도 없음
- Topic을 구성하는 Partition들은 여러 Broker상에 분산됨
- Topic 생성시 Kafka가 자동으로 Topic을 구성하는 전체 Partition들을 모든 Broker에게 할당해주고 분배해 줌

Bootstrap Servers는 Broker Servers를 의미
- 모든 Kafka Broker는 Bootstraps server라고 부름
- 하나의 Broker에만 연결하면 Cluster 전체에 연결됨
  -> 하지만, 특정 Broker 장애를 대비하여, 전체 Broker List(IP, Port)를 파라미터로 입력 권장
- 각각의 Broker는 모든 Broker, Topic, Partition에 대해 알고 있음(Metadata)


### Zookeeper
Zookeeper는 Broker를 관리 (Broker 들의 목록/설정을 관리)하는 소프트웨어
- Zookeeper는 변경사항에 대해 Kafka에 알림
  -> Topic 생성/제거, Broker 추가/제거 등
- Zookeeper 없이는 Kafka가 작동할 수 없음
  -> KIP-500을 통해서 Zookeeper 제거가 진행중
  -> 2022년에 Zookeeper를 제거한 정식 버전 출시 예정 중

- Zookeeper는 홀수의 서버로 작동하게 설계되어 있음
 (최소 3, 권장 5)
- Zookeeper에는 Leader(writes)가 있고 나머지 서버는 Follower(reads)

Zookeeper 아키텍처
- Leader/Follower 기반 Master/Slave 아키텍처
- Zookeeper는 분산형 Configuration 정보 유지, 분산 동기화 서비스를 제공하고 대용량 분산 시스템을 위한 네이밍 레지스트리를 제공하는 소프트웨어
- 분산 작업을 제어하기 위한 Tree 형태의 데이터 저장소
  -> Zookeeper를 사용하여 멀티 Kafka Broker들 간의 정보(변경 사항 포함) 공유, 동기화 등을 수행

Zookeeper Failover
- Quorum 알고리즘 기반
- Ensemble은 Zookeeper 서버의 클러스터
- Quorum(쿼럼)은 '정족수'이먀, 합의체가 의사를 진행시키거나 의결을 하는데 필요한 최소한도의 인원수를 뜻함
- 분산 코디네이션 환경에서 예상치 못한 장애가 발생해도 분산 시스템의 일관성을 유지시키기 위해서 사용
- Ensemble이 3대로 구성되었다면 Quorum은 2, 즉 Zookeeper 1대가 장애가 발생하더라도 정상 동작
- Ensemble이 5대로 구성되었다면 Quorum은 3, 즉 Zookeeper 2대가 장애가 발생하더라도 정상 동작

### 정리하기
Zookeeper와 Broker는 서로 다르다.
- Broker는 Partition에 대한 Read 및 Write를 관리하는 소프트웨어
- Broker는 Topic내의 Partition 들을 분산, 유지 및 관리
- 최소 3대 이상의 Broker를 하나의 Cluster로 구성해야 함
  -> 4대 이상을 권장함
- Zookeeper는 Broker를 관리 (Broker 들의 목록/설정을 관리)하는 소프트웨어
- Zookeeper는 홀수의 서버로 작동하게 설계되어 있음(최소3, 권장 5)

## Producer
Apache Kafka 주요 요소 : Producer, Consumer, Consumer Group

- Producer : 메시지를 생산(Produce)해서 Kafka의 Topic으로 메시지를 보내는 애플리케이션
- Consumer : Topic의 메시지를 가져와서 소비(Consume)하는 애플리케이션
- Consumer Group : Topic의 메시지를 사용하기 위해 협력하는 Consumer들의 집합
- 하나의 Consumer는 하나의 Consumer Group에 포함되며, Consumer Group내의 Consumer들은 협력하여 Topic의 메시지를 병렬 처리함

Producer와 Consumer의 분리(Decoupling) : Producer와 Consumer의 기본 동작 방식
- Producer와 Consumer는 서로 알지 못하며, Producer와 Consumer는 각각 고유의 속도로 Commit Log에 Write 및 Read를 수행
- 다른 Consumer Group에 속한 Consumer들은 서로 관련이 없으며, Commit Log에 있는 Event(Message)를 동시에 다른 위치에서 Read할 수 있음

Record(Message) 구조 : Header, Key, Value
- Message == Record == Event == Data
- Record : Header(Topic, Partition, Timestamp, etc) - Metadata, (Key, Value) - Body(Business Relevant Data)

Serializer / Deserializer : To / From Byte Array
- Kafka는 Record(데이터)를 Byte Array로 저장
- JSON, String, Avro, Protobuf --Producer(SERIALIZERS)--> Byte Array --Consumer(DESERIALIZERS)-->

```
private Properties props = new Properties();

props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker101:9092, broker102:9092");
props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);

KafkaProducer producer = new KafkaProducer(props);
```

Producing to Kafka : High-Level Architecture
- Required -> Producer Record -> send() -> Serializer -> Partitioner -> Compress(optional) -> RecordAccumulator -> Kafka -> 성공 / 재시도 -> 성공 시 metadata 리턴 / 실패 시 throw exception
 
Partitioner의 역할 : 메시지를 Topic의 어떤 Partition으로 보낼지 결정
- Partition = Hash(Key) % Number of Partitions
- 전제 조건 : Key가 null이 아닐 때

Partitioner 의 종류 : 성능, 작동 방식이 다양함
- 2.4 버전 전후로 default partition의 알고리즘이 변경되었다. : RR -> Sticky한 뭐식깽이로

### 정리하기
- Message == Record == Event == Data
- Message는 Header 와 Key 그리고 Value로 구성
- Kafka는 Record(데이터)를 Byte Array로 저장
- Producer는 Serializer, Consumer는 Deserializer를 사용
- Producer는 Message의 Key 존재 여부에 따라서 Partitioner를 통한 메시지 처리 방식이 다름
