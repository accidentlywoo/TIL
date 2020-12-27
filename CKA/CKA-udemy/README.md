# Udemy 강의로 지르는 CKA

## Index
- [Udemy 강의로 지르는 CKA](#udemy-강의로-지르는-cka)
  - [Index](#index)
  - [Introduction](#introduction)
  - [1.1 Cluster Architecture](#11-cluster-architecture)
    - [Kubernetes Architecture](#kubernetes-architecture)
  - [컨트롤 플레인 컴포넌트](#컨트롤-플레인-컴포넌트)
    - [ETCD For Beginners](#etcd-for-beginners)
    - [ETCD in Kubernetes](#etcd-in-kubernetes)
    - [Kube-API Server](#kube-api-server)
    - [Controller Managers](#controller-managers)
    - [Kube Scheduler](#kube-scheduler)
  - [노드 컴포넌트](#노드-컴포넌트)
    - [Kubelet](#kubelet)
    - [Kube Proxy](#kube-proxy)
    - [컨테이너 런타임](#컨테이너-런타임)
  - [Practice](#practice)

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

  크게 컨트롤 플레인 컴포넌트, 노드 컴포넌트, 애드온, DNS, 웹 UI(대시보드), 컨테이너 리소스 모니터링, 클러스터-레벨 로깅로 구성되어있다. 
## 컨트롤 플레인 컴포넌트
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

## 노드 컴포넌트
노드 컴포넌트는 동작 중인 파드를 유지시키고 쿠버네티스 런타임 환경을 제공하며, 모든 노드 상에서 동작한다.

### Kubelet
클러스터의 각 노드에서 실행되는 에이전트. Kubelet은 파드에서 컨테이너가 확실하게 동작하도록 관리한다.

Kubelet은 다양한 메커니즘을 통해 제공된 파드 스펙(PodSpec)의 집합을 받아서 컨테이너가 해당 파드 스펙에 따라 헬스체크를한다.
Kubelet은 쿠버네티스를 통해 생성되지 않는 컨테이너는 관리하지 않는다.

### Kube Proxy
kube-proxy는 클러스터의 각 노드에서 실행되는 네트워크 프록시로, 쿠버네티스의 서비스 개념의 구현부이다.

Kube-proxy는 노드의 네트워크 규칙을 유지 관리한다. 이 네트워크 규칙이 내부 네트워크 세션이나 클러스터 바깥에서 파드로 네트워크 통신을 할 수 있도록 해준다.

kube-proxy는 운영 체제에 가용한 패킷 필터링 계층이 있는 경우, 이를 사용한다. 그렇지 않으면, kube-proxy는 트래픽 자체를 포워드하낟.

### 컨테이너 런타임
컨테이너 런타임은 컨테이너 실행을 담당하는 소프트웨어이다.

쿠버네티스는 여러 컨테이너 런타임을 지원한다.도커, containerd, CRI-O, Kubernetes CRI(Container Runtime Interface)를 구현한 모든 소프트웨어

## Practice
1. Recap Pods 
2. ReplicaSets 
- 리플리카셋 파일로 생성하기
```
kubectl apply -f replicaset-definition-1.yaml
// or
kubectl create -f replicaset-definition-1.yaml
```
두 명령어의 차이는 모르겠다

- 리플리카셋 지우기
```
kubectl delete replicasets.apps replicaset-name
```

- 리플리카셋 수정하기
```
kubectl edit replicasets.apps replicaset-name
```
해당 리플리카셋에 pod들을 모두 지워서 다시 생성되게 한다.

- 리플리카셋 스케일 변경
pod 갯수 5개로 변경
```
kubectl scale replicaset --replicas=5 replicaset-name
```
3. Deployments
  yaml 구조
```
apiVersion: apps/v1
kind: Deployment #Deployment파일이라는 걸 알려줌,
metadata:
  name: myapp-deployment
  labels:
    app: myapp
    type: front-end
spec:
  template:
    metadata:
      name: myapp-pod
      label:
        app:myapp
        type: front-end
      spec:
        containers:
        - name: nginx-container
          image: nginx
replicas: 3
selector:
  matchLabels:
    type: front-end # spec - template - metadata - label - type : 과 똑같은 값이여야 한다.
```
  Deployments > ReplicaSets > Pods 느낌으로다가~
  아래 명령으로 Deployments > ReplicaSets > Pods를 한번에 볼 수 있다.
```
kubectl get all
```

이미지 조회하기
```
kubectl describe deployments.apps frontend-deployment | grep -i image
```

간단한 deployment yaml없이 cli로 생성하기
```
kubectl create deployment deployment-name --image-imgage-name

kubectl scale deployment --replicas=3
```

- 시험 꿀팁
CLI시험 중 YAML 파일을 복붙하기 어려울 수 있다. ```kubectl run``을 사용하면 YAML 파일을 전혀 만들지 않고도 명령만으로 벗어날 수도 있다.
[참고 자료](https://kubernetes.io/docs/reference/kubectl/conventions/)

4. Namespaces
namespace - Isolation
  네임스페이스는 중요한것같다....


- 네임스페이스 가져오기
```
kubectl get ns

kubectl get ns --no-header | wc -l
```

- 특정 네임스페이스 조회하기
```
kubectl -n 찾을이름 get pods --no-headers | wc -l
```

- 간단한 명령어로 pod만들기
```
kubectl run 포드이름 --image=이미지이름 --dry-run=client -o yaml > pod.yaml
```
 -> 다음으로 생성된 pod.yaml에서 metadata - namespace : 네임스페이스이름 넣고 yaml저장하기

 - 특정 네임스페이스를 갖은 pod찾기
```
kubectl get pods --all-namespaces | grep blue
```

5. Service
서비스는 다양한 컴포넌트들과 커뮤니케이션하도록 도와준다

service-definition.yml
```
apiVersion: v1
kind: Service
metadata:
  name: myapp-service

spec:
  type: NodePort
  ports:
   - targetPort: 80
    *port: 80
     nodeport: 30008
  selector:
    app: myapp
    type: front-end
```
```
kubectl create -f service-definition.yml

kubectl get services

curl http://192.168.1.2:30008
```

6. Services Cluster IP
마이크로 서비스 아키텍처 구조에서 유용한데,

여러개의 프론트앤드 포드와 여러개의 백엔드 포드, 여러개의 DB포드가있다면

프론트앤드 포드들에서 백엔드 포드로 연결될 때, 백엔드 포드를 일일히 찾아가서 연결하는게 아니라, Cluster IP를 제공하여 쉽게 연결할 수 있도록 한다.
```
apiVersion: v1
kind: Service
metadata:
    name: back-end

spec:
    type: ClusterIP
    ports:
    - targetPort: 80
      port: 80

    selector:
      app: myapp
      type: back-end
```
```
kubectl create -f service-definition.yml

kubectl get services
```

7. Service - Loadbalancer
service-definition.yml
```
apiVersion: v1
kind: Service
metadata:
  name: myapp-service

spec:
  type: LoadBalancer # 바뀌는 부분
  ports:
   - targetPort: 80
    *port: 80
     nodeport: 30008
  selector:
    app: myapp
    type: front-end
```

명령어 연습
```
kubectl describe svc kubernetes

kubectl describe deployments.apps simple-webapp-deployment | grep -i image

kubectl expose deployment simple-webapp-deployment --name=webapp-service --target-port=8080 --type=NodePort --port=8080 --dry-run=client -o yaml > svc.yaml

# vi로 svc.yaml열어서 NodePort에 포트넣는 작업해야됨

kubectl apply -f svc.yaml
```

7. Imperative vs Declarative

**Infrastructure as Code**

- Imperative 
  1. Provision a VM by the name 'web-server'
  2. Install NGINX Software on it
  3. Edit configuration file to use port '8080'
  4. Edit configuration file to web path '/var/www/nginx'
  5. Load web pages to '/var/www/nginx' from GIT Repo - X
  6. Start NGINX server

- Devlarative
```
VM Name: web-server
Database: nginx:1.18
Port: 8080
Path: /var/www/nginx
Code: GIT Repo - X
```

**Kubernetes**

- Imperative
  - Create Objects
  1. ```> kubectl run --image=ngix nginx```
  2. ```> kubectl create deployment --image=ngix nginx```
  3. ```> kubectl expose deployment nginx --port 80```
  4. ```> kubectl edit deployment nignx```

  - Update Objects
  5. ```> kubectl scale deployment nginx --replicas=5```
  6. ```> kubectl set image deployment nginx nginx=nginx:1.18```
  7. ```> kubectl create -f nginx.yaml```
  8. ```> kubectl replace -f nginx.yaml```
  9.  ```> kubectl delete -f nginx.yaml```

- Declarative
  ```> kubectl apply -f nginx.yaml ```

- Declarative가 관리가 쉽고 좋은 방법이다.
  
- kubectl 사용한 필수 명령 팁
  대부분 defined 파일을 사용하여 declarative 방식으로 작업한다.
  이는 작업 동안 커맨드 명령을 일회성 작업으로 빠르게 수행되고 쉽게 템플릿작업 생성하는 데 도움이 될 수 있다.
  이 방법은 시험 중 시간 절약할 수 있다.

  시험 시작전 커맨드 작업랄 때 유용할 수 있는 두가지 옵션
  1. ```--dry-run``` : 기본적으로 명령이 실행되는 즉시 리소스 생성. 
                      단순 명령 테스트 시 ```---dry-run=client``옵션 사용
                      이는 리소스를 생성하지 않고 대신 리소스를 생성할 수 있는지 여부와 명령이 올바른지 여부를 알려준다.
  2. ```-o yaml``` : 화면에 YAML 형식의 리소스 정의를 출력한다.

Use the above two in combination to generate a resource definition file quickly, that you can then modify and create resources as required, instead of creating the files from scratch.
- 총정리
**POD**
Create an NGINX Pod

```kubectl run nginx --image=nginx```

Generate POD Manifest YAML file (-o yaml). Don't create it(--dry-run)

```kubectl run nginx --image=nginx  --dry-run=client -o yaml```

**Deployment**
Create a deployment

```kubectl create deployment --image=nginx nginx```s

Generate Deployment YAML file (-o yaml). Don't create it(--dry-run)

```kubectl create deployment --image=nginx nginx --dry-run -o yaml```

Generate Deployment with 4 Replicas

```kubectl create deployment nginx --image=nginx --replicas=4```

You can also scale a deployment using the ```kubectl scale``` command.

```kubectl scale deployment nginx --replicas=4```

Another way to do this is to save the YAML definition to a file.

```kubectl create deployment nginx --image=nginx--dry-run=client -o yaml > nginx-deployment.yaml```

You can then update the YAML file with the replicas or any other field before creating the deployment.

**Service**

Create a Service named redis-service of type ClusterIP to expose pod redis on port 6379

```kubectl expose pod redis --port=6379 --name redis-service --dry-run=client -o yaml```

(This will automatically use the pod's labels as selectors)

Or

```kubectl create service clusterip redis --tcp=6379:6379 --dry-run=client -o yaml  (This will not use the pods labels as selectors, instead it will assume selectors as app=redis. You cannot pass in selectors as an option. So it does not work very well if your pod has a different label set. So generate the file and modify the selectors before creating the service)```

Create a Service named nginx of type NodePort to expose pod nginx's port 80 on port 30080 on the nodes:

```kubectl expose pod nginx --port=80 --name nginx-service --type=NodePort --dry-run=client -o yaml```

(This will automatically use the pod's labels as selectors, but you cannot specify the node port. You have to generate a definition file and then add the node port in manually before creating the service with the pod.)

Or

```kubectl create service nodeport nginx --tcp=80:80 --node-port=30080 --dry-run=client -o yaml```

(This will not use the pods labels as selectors)

Both the above commands have their own challenges. While one of it cannot accept a selector the other cannot accept a node port. I would recommend going with the `kubectl expose` command. If you need to specify a node port, generate a definition file using the same command and manually input the nodeport before creating the service.

8. Command
커멘치는 연습을 많이 하자