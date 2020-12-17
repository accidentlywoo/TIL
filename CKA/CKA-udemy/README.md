# Udemy 강의로 지르는 CKA

## Index
- [Udemy 강의로 지르는 CKA](#udemy-강의로-지르는-cka)
  - [Index](#index)
  - [Introduction](#introduction)
  - [1.1 Cluster Architecture](#11-cluster-architecture)
    - [Kubernetes Architecture](#kubernetes-architecture)
    - [ETCD For Beginners](#etcd-for-beginners)
    - [ETCD in Kubernetes](#etcd-in-kubernetes)
    - [Kube-API Server](#kube-api-server)
    - [Controller Managers](#controller-managers)
    - [Kube Scheduler](#kube-scheduler)
    - [Kubelet](#kubelet)
    - [Kube Proxy](#kube-proxy)

---

## Introduction
[Certified Kubernetes Administrator](https://www.cncf.io/certification/cka/)

[Exam Curriculum (Topics)](https://github.com/cncf/curriculum)

[Candidate Handbook](https://www.cncf.io/certification/candidate-handbook)

[Exam Tips](http://training.linuxfoundation.org/go//Important-Tips-CKA-CKAD)

[Q&A](https://github.com/kodekloudhub/certified-kubernetes-administrator-course)

   - Course Object
1. Core Concepts
   1. Cluster Architecture
   2. API Primitives
   3. Services & Other Network Primitives
2. Scheduling
3. Logging Monitoring
4. Applicatiob Lifecycle Management
5. Cluster Maintenance
6. Security
7. Storage
8. Networking 
9. Installation, Configuration & Validation
10. Troubleshooting

## 1.1 Cluster Architecture

### Kubernetes Architecture 
- 쿠버네티스 컴포넌트
  쿠버네티스를 배포하면 **클러스터**를 얻는다.

  쿠버네티스 클러스터는 컨테이너화된 애플리케이션을 실행하는 **노드**라고 하는 워커 머신의 집합. 모든 클러스터는 최소 한 개의 워커 노드를 가진다.

  워커 노드는 애플리케이션의 구성요소인 파드를 호스트한다.
  **컨트롤 플레인**은 워커 노드와 클러스터 내 파드를 관리한다. 프로덕션 환경에서는 일반적으로 컨트롤 플레인이 여러 컴퓨터에 걸쳐 실행되고, 클러스터는 일반적으로 여러 노드를 실행하므로 내결함성과 고가용성이 제공된다.

- 컨트롤 플레인 컴포넌트
  컨트롤 플레인 컴포넌트는 클러스터에 관한 전반적인 결정(ex. 스케줄링)을 수행하고 클러스터 이벤트를 감지
  하고 반응한다.
  
  컨트롤 플레인 컴포넌트 내 어떠한 머신에서든지 동작할 수 있다. 그러나 간결성을 위하여, 구성 스크립트는 보통 동일 머신 상에 모든 컨트롤 플레인 컴포넌트를 구동시키고, 사용자 컨테이너는 해당 머신 상에 동작시키지 않는다. 다중-마스터-VM 설치 예제를 보려면 고가용성 클러스터 구성하기를 확인해본다.

### ETCD For Beginners
모든 클러스터 데이터를 담는 쿠버네티스 뒷단의 저장소로 사용되는 일관성-고가용성 키-값 저장소.

쿠버네티스 클러스터에서 etcd를 뒷단의 저장소로 사용한다면, 이 데이터를 백업하는 계획은 필수이다.

etcd에 대한 자세한 정보는, 공식 문서를 참고한다.

### ETCD in Kubernetes
### Kube-API Server
API 서버는 쿠버네티스 API를 노출하는 쿠버네티스 **컨트롤 플레인** 컴포넌트이다. API 서버는 쿠버네티스 컨트롤 플레인의 프론트 엔드이다.

쿠버네티스 API 서버의 주요 구현은 kube-apiserver 이다. kube-apiserver는 수평으로 확장되도록 디자인되었다. 즉, 더 많은 인스턴스를 배포해서 확장할 수 있다. 여러 kube-apiserver인스턴스를 실행하고, 인스턴스간의 트래픽을 균형있게 조절할 수 있다.

### Controller Managers
- kube-controller-manager
  컨트롤러를 구동하는 마스터 상의 컴포넌트.

  논리적으로, 각 컨트롤러는 개별 프로세스이지만, 복잡성을 낮추기 위해 모두 단일 바이너리로 컴파일되고 단일 프로세스 내에서 실행된다.

  이들 컨트롤러는 다음을 포함한다.
- 노드 컨트롤러 : 노드가 다운되었을 때 통지와 대응에 관한 책임을 가진다.
- 레플리케이션 컨트롤러 : 시스템의 모든 레플리케이션 컨트롤러 오브젝트에 대해 알맞은 수의 파드들을 유지시켜 주는 책임을 가진다.
- 엔드포인트 컨트롤러 : 엔드포인트 오브젝트를 채운다(즉, 서비승롸 파드를 연결시킨다.)
- 서비스 어카운트 & 토큰 컨트롤러 : 새로운 네임스페이스에 대한 기본 계정과 API접근 토큰을 생성한다.

### Kube Scheduler
노드가 배정되지 않은 새로 생성된 파드를 감지하고, 실행할 노드를 선택하는 컨트롤 플레인 컴포넌트.

스케줄링 결정을 위해서 고려되는 요소는 리소스에 대한 개별 및 총체적 요구 사항, 하드웨어/소프트웨어/정책적제약, 어피니티(affinity) 및 안티-어피니티(anti-affinity)명세, 데이터 지역성, 워크로드-간 간섭, 데드라인을 포함한다.

### Kubelet
### Kube Proxy