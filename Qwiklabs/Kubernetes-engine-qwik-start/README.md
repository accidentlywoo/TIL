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
1. 대략적인 기본 컴퓨팅 영역을 설정하기 위해 ```us-central1-a```를 설정해 보겠다. 
   ```gcloud config set compute/zone us-central1-a```

   출력 결과물
   ```Update property [compute/zone]```

## Task 2 : Create a GKE Cluster
클러스터는 최소한 클러스터 마스터 머신과 노드로 구성된다.
노드는 여러 일을하는 머신이다. 노드는 클러스터의 일부로 만드는 데 필요한 Kubernetes 프로세스를 실행하는 Compute Engine 가상머신(VM) 인스턴스이다.

1. 클러스터를 생성하기 위해, 다음 명령을 실행해라, ```[CLUSTER-NAME]``` 는 적절한 이름으로... 
   ```
    gcloud container clusters create [CLUSTER-NAME]
   ```
   시간이 조금 걸릴 수 있다.

   출력 예시
   ```
   NAME        LOCATION       ...   NODE_VERSION  NUM_NODES  STATUS
    my-cluster  us-central1-a  ...   1.16.13-gke.401  3          RUNNING
  ```

## Task 3 : Get authentication credentials for the cluster
클러스터를 생성 후, 상호 작용하려면 인증 자격 증명이 필요하다.
1. 클러스터를 인증하기위해, 다음 명령을 실행해라
   
   ```gcloud container clusters get-credentials [CLUSTER-NAME]```

   출력 예시
   ```
    Fetching cluster endpoint and auth data.
    kubeconfig entry generated for my-cluster.
   ```

## Task 4 : Deploy an application to the cluster
이제 컨테이너화한 애플리케이션을 클러스터에 배포할 수 있다.
이번 예시에서는 ```hello-app``` 을 만든 클러스터에서 실행해보자
- [애플리케이션의 컨테이너화 알아보기](https://cloud.google.com/kubernetes-engine/docs/concepts/kubernetes-engine-overview)

GKE은 사용자의 클러스터의 자원을 관리하고 생성하기위해 Kubernetes 객체를 사용한다.
Kubernetes는 웹 서버같이 Stateless한 배포를 위해 배포 객체를 제공한다.
- [배포 객체 알아보기](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/)

서비스 객체는 인터넷에서 애플리케이션을 접근하기 위해 규칙을 정의하고 로드밸런싱을 한다.

  1. ```hello-app```라는 컨테이너 이미지 ```hello-server``라는 배포를 새로 만들기위해, 다음 명령을 실행하라
  ```
  kubectl create deployment hello-server --image=gcr.io/google-samples/hello-app:1.0
  ```

  출력 예제
  ```deployment.apps/hello-server created```

  ```hello-server``` 

  이 쿠버네티스 명령은 ```hello-server```를 표현하는 배포 객체를 생성한다.
  이 경우, ```--image``` 배포할 컨테이너 이미지를 구체화한다.
  이 명령은 Container Registry 버킷에서 샘플 이미지를 pull해온다.
  ```gcr.io/google-samples/hello-app:1.0``` 구체적인 이미지버전을 pull받게 나타낸다.
  만약 버전이 명시되지 않으면, 최산 버전을 사용한다.

  2. 애플리케이션을 외부 트래픽에 노출할 수있는 쿠버네티스 리소스인, 쿠버네티스 서비스를 생성하기 위해서, 다음 명령어를 실행하세요.
   ```kubectl expose deployment hello-server --type=LoadBalancer --port 8080```

   - ```--port``` 컨테이너가 노출하는 포트를 지정한다.
   - ```type="LoadBalancer``` 컨테이너에 대한 Compute Engine 로드밸런서를 만든다.

  출력 예시
```service/hello-server exposed```

3. ```hello-server`` 서비스를 검사하기위래 아래 kubectl get명령어 실행
   ```kubectl get service```

  출력 예시
```
  NAME              TYPE              CLUSTER-IP        EXTERNAL-IP      PORT(S)           AGE
  hello-server      loadBalancer      10.39.244.36      35.202.234.26    8080:31991/TCP    65s
  kubernetes        ClusterIP         10.39.240.1       <none>           433/TCP           5m13s
```
외부 IP 만들때 시간이 좀 걸린다. 

4. 애플리케이션을 브라우저로 보기위해 시도해보라~
```http://[EXTERNAL-IP]:8080```

## Task 5 : Deleting the cluster
1. 클러스터 지우기. 다음 명령을 실행하자
   ```gcloud container cluster delete [CLUSTER-NAME]```