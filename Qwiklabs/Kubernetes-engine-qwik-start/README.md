# Kubernetes Engine: Qwik Start :ship: :cyclone:

--- 

 [Kubernetes Engine: Qwik Start :ship: :cyclone:](#kubernetes-engine-qwik-start-ship-cyclone)
- [Kubernetes Engine: Qwik Start :ship: :cyclone:](#kubernetes-engine-qwik-start-ship-cyclone)
  - [쿠버 엔진 퀵 스타트! Intro](#쿠버-엔진-퀵-스타트-intro)
    - [Google Kubernetes Engine을 사용한 클러스터 오케스트레이션](#google-kubernetes-engine을-사용한-클러스터-오케스트레이션)
    - [Google Cloud의 Kubernetes](#google-cloud의-kubernetes)
  - [Task 1 : Set a default compute zone](#task-1--set-a-default-compute-zone)
  - [Task 2 : Create a GKE Cluster](#task-2--create-a-gke-cluster)
  - [Task 3 : Get authentication credentials for the cluster](#task-3--get-authentication-credentials-for-the-cluster)
  - [Task 4 : Deploy an application to the cluster](#task-4--deploy-an-application-to-the-cluster)
  - [Task 5 : Deleting the cluster](#task-5--deleting-the-cluster)

## 쿠버 엔진 퀵 스타트! Intro
Google Kubernetes Engine(GKE)은 Google 인프라를 사용하여 컨테이너화 된 애플리케이션을 배포, 관리, 확장하기위한 관리 환경을 제공한다.
Kubernetes Engine 환경은 컨테이너 클러스터를 향성하기 위해 그룹화 된 여러 머신(특히 Compute Engine 인스턴스)으로 구성된다.
이 챕터에서는 GKE를 사용한 컨테이너 생성 및 배포를 실습합니다.

- 참고 자료
  -  [컨테이너 클러스터](https://cloud.google.com/kubernetes-engine/docs/concepts/cluster-architecture)
  - [Compute Engine](https://cloud.google.com/compute)
  - [쿠버네티스 OpenSource](https://kubernetes.io/)

### Google Kubernetes Engine을 사용한 클러스터 오케스트레이션
    GKE 클러스터는 Kubernetes 오픈 소스 클러스터 관리 시스템으로 구동된다.
    Kubernetes는 컨테이너와 클러스와 상호 작용하는 메커니즘을 제공한다.
    Kubernetes 명령 및 리소스를 사용하여 애플리케이션을 배포 및 관리하고, 관리 작업을 수행하고, 정책을 설정하고, 배포 된 워크로드의 상태를 모니터링한다.

    Kubernetes는 Google서비스를 실행하는 동일 설계 원칙을 기반으로 애플리케이션 컨테이너에 대한 자동 관리, 모니터링 및 활설상태 프로브, 자동 확장, 롤링 업데이트 등 동일한 이점을 제공한다.
    컨테이너 클러스터에서 애플리케이션을 실행할 때 컨테이너에서 프로덕션 워크로드를 실행 한 google의 10년 이상의 경험을 기반으로 한 기술을 사용하게 된다.

### Google Cloud의 Kubernetes
    GKE 클러스터를 실행하면 GCP에서 제공하는 고급 클러스터 관리 기능의 이점도 얻을 수 있다.
  - Compute Engine 인스턴스의 부하 분산
  - 추가 유연성을 위해 클러스터 내에서 노드의 하위 집합을 지정하는 노드 풀
  - 클러스터의 노드 인스턴스 수 자동확장
  - 클러스터의 노드 소프트웨어에 대한 자동 업그레이드
  - 노드 상태 및 가용성을 유지하기위한 노드 자동복구
  - 클러스터에 대한 가시성을 위해 Stackdriver Monitoring으로 로깅 및 모니터링

## Task 1 : Set a default compute zone

## Task 2 : Create a GKE Cluster

## Task 3 : Get authentication credentials for the cluster

## Task 4 : Deploy an application to the cluster

## Task 5 : Deleting the cluster