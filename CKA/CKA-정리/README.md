# 시험 마지막 정리

## 1. 서비스 어카운트
파드가 K8S API와 통신하기 위해 파드네 할당되는 하나의 ID.

파드 생성 시 서비스 어카운트를 할당하고, 해당 서비스 어카운트에 적절한 권한을 부여하여 K8S API서버와 통신할 수 있다. 

### 서비스 어카운트 생성
```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: pod-reader
```

```cli
kubectl create serviceaccount pod-reader
```

### 서비스 어카운트 조회
```cli
kubectl get serviceaccounts/pod-reader -o yaml

# 출력
apiVersion: v1
kind: ServiceAccount
metadata:
  creationTimestamp: 2021-11-07T00:12:59Z
  name: pod-reader
  namespace: default
  resourceVersion: "272500"
  uid: 721ab723-13bc-11e5-aec2-42010af0021e
secrets:
- name: pod-reader-token-bvbk5
```

## 2. RBAC(Role-Based Access Control)
K8S는 역할 기반으로 API 접근을 관리한다. 실제 역할을 만들어 서비스어카운트에 할당하는 것을 바인딩(Binding)이라고 한다.

역할은 Role, ClusterRole 두 가지로 분류된다.

Role은 특정 네임스페이스에 속하게 되며, ClusterRole은 전체 클러스터에 고유한 역할이 된다. 

역할을 서비스어카운트와 바인딩하는 것도 RoleBinding, ClusterRoleBinding 두 가지 방법이 있다.

RoleBinding을 통해 바인딩한 Role과 ClusterRole은 특정 네임스페이스에서만 유효하며, ClusterRoleBinding으로 바인딩 시 모든 네임스페이스에 공통적으로 적용된다.

### Role 생성
```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  namespace: default
  name: pod-read-role
rules:
- apiGroups: [""]
  resources: ["pods"]
  verbs: ["get", "watch", "list"]
```
pod는 core/v1 구륩애 속해있으므로, 해당그룹은 생략가능 ```apiGroups: [""]```

```cli
kubectl create role pod-read-role --verb=get --verb=list --verb=watch --resource=pods
```

default 네임스페이스에 Role을 생성했다.

해당 롤은 pods 리소스에 get, watch, list 권한을 부여하는 Role이다.

### RoleBinding 생성
``` yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: pod-read-role-to-pod-reader
  namespace: default
subjects:
- kind: ServiceAccount
  name: pod-reader
  apiGroup: rbac.authorization.k8s.io
roleRef:
  kind: Role
  name: pod-read-role
  apiGroup: rbac.authorization.k8s.io
```

```cli
kubectl create rolebinding pod-read-role-to-pod-reader \
--role=pod-read-role --serviceaccount=pod-reader --namespace=default
```

실제 시험에서는 pods뿐 아니라 다른 리소스에도 권한을 적용해야 하는데 리소스 별로 API그룹이 다르다.

아래 API Reference Docs를 검색하여 아래의 링크로 확인.

https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.19/

{그룹명}/{버전}으로 API그룹을 표기하며, Deployment의 경우 그룹명은 apps/v1이다.

아래는 Deploymeny 목록을 조회할 수 있는 Role이다. apiGroups 필드에 apps/v1를 기재해야 한다.

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  namespace: default
  name: deployment-list-role
roles:
- apiGroups: ["apps/v1"]
  resources: ["deployment"]
  verbs: ["list"]
```
 
## 3. 노드
노드는 쿠버네티스 클러스터를 구성하는 하나의 VM또는 하드웨어이다.

### 1) 노드가 NotReady상태로 나올 때

K8S의 각 노드에는 kubelet이 실행된다. 

kubelet은 노드 내에서 실제 컨테이너를 수행하는 역할을하는데, 해당 프로세스가 제대로 실행되지 않으면 NotReady 상태로 조회하게 된다.

아래 명령을 통해 ssh로 노드에 직접 접속한 후 kubelet을 재실행시킨다.

```cli
kubectl get node
# node2가 NotReady로 조회됨

ssh node2
# node2로 접속

systemctl status kubelet
# kubelet 상태 조회 <- stop 상태로 조회됨

systemctl restart kubelet
# kubelet 재시작
exit

kubectl get node
# 일정 시간 이후 정상 상태 확인
```

### 2) Tains가 적용된 노드 확인하기
Tains는 Tolerations와 함께 사용된다.

Tains는 노드에 할당되고, Tolerations은 파드에 할당된다.

파드가 스케줄링될 때 어떤 노드에서 실행될지는 중요한 문제인데, Tains를 통해 이를 컨트롤할 수 있다.

Tains는 인종의 검문소 역할을 하고, Tolerations은 해당 검문소를 통과하는 출입증과 같은 역할을 한다.

파드가 스케줄링 될 때 노드의 Tains(검문소)에 대한 Tolerations(출입증)이 없는 경우, 그 노드에는 스케줄링되지 않는다.

Tains가 적용된 노드는 describe 명령을 통해 확인할 수 있다.

```cli
kubectl describe nodes | grep -i taint
# Tains가 적용된 노드의 수 확인

echo {노드갯수} > {파일경로}
# 노드 갯수를 파일 형태로 기록
```

## 4. 클러스터 업그레이드
클러스터 업그레이드 순서는 다음의 순서대로 진행

1. master node drain
2. upgrade master node's kubeadm 
3. upgrade master nodes's kubectl, kubelet
4. master node uncordon
5. worker node drain
6. upgrade worker node's kubeadm
7. upgrade worker nodes's kubectl, kubelet (kubectl이 없을 경우 kubelet만 업그레이드)
8. worker node uncordon

kubectl drain {노드명} 명령은 노드에서 실행 중인 파드를 다른 노드로 옮기고 새로운 파드의 스케줄링을 막는다.

업그레이드 중인 노드 내부에는 데몬셋을 제외한 파드가 없어야 하고 새로운 파드 생성 시 업그레이드 중인 노드에 스케줄링이 되면 안되기 때문에 업그레이드 전 해당 명령을 수행해야 한다.

업그레이드가 종료되면 kubectl uncordon {노드명} 명령을 통해 해당 노드의 스케줄링을 다시 허가한다.

헤당 명령은 단순히 스케줄링만 허가하기 때문에, drain시 다른 노드로 배치되었던 파드가 다시 돌아오지는 않는다.

### 클러스터 업그레이드 실습
1. 노드 상태 확인
```cli
controlplane $ kubectl get no
NAME STATUS ROLES AGE VERSION 
controlplane Ready master 11m v1.18.0 
node01 Ready <none> 10m v1.18.0å
```
master node1대, 워커 노드 1대로 구성된 클러스터이다. 

마스터 노드를 업그레이드 한 후 워커노드를 업그레이드 한다.

2. master node drain
``cli
controlplane $ kubectl drain controlplane --ignore-daemonsets
node/controlplane already cordoned
WARNING: ignoring DaemonSet-managed Pods: kube-system/kube-flannel-ds-amd64-c9h4z, kube-system/kube-keepalived-vip-46r8b, kube-system/kube-proxy-9z5fn
evicting pod kube-system/coredns-66bff467f8-7j9ss
evicting pod kube-system/coredns-66bff467f8-h5wjg
pod/coredns-66bff467f8-7j9ss evicted pod/coredns-66bff467f8-h5wjg evicted node/controlplane evicted
```
마스터 노드를 drain한다. --ignore-daemonsets 옵션을 통해 데몬셋 제외 한 파드를 다른 노드에 배치한다.

3. 마스터 노드의 kubeadm 업그레이드 
```cli
controlplane $ kubeadm version
kubeadm version: &version.Info{Major:"1", Minor:"18", GitVersion:"v1.18.0", GitCommit:"9e991415386e4cf155a24b1da15becaa390438d8", GitTreeState:"clean", BuildDate:"2020-03-25T14:56:30Z", GoVersion:"go1.13.8", Compiler:"gc", Platform:"linux/amd64"}
# 현재 버전 확인

controlplane $ apt update
# apt 업그레이드 수행

controlplane $ apt-cache madison kubeadm
kubeadm | 1.19.4-00 | http://apt.kubernetes.io kubernetes-xenial/main amd64 Packages kubeadm | 1.19.3-00 | http://apt.kubernetes.io kubernetes-xenial/main amd64 Packages kubeadm | 1.19.2-00 | http://apt.kubernetes.io kubernetes-xenial/main amd64 Packages kubeadm | 1.19.1-00 | http://apt.kubernetes.io kubernetes-xenial/main amd64 Packages kubeadm | 1.19.0-00 | http://apt.kubernetes.io kubernetes-xenial/main amd64 Packages ...
# 업그레이드 가능한 kubeadm 버전 확인

$ apt-mark unhold kubeadm && \
apt-get update && apt-get install -y kubeadm=1.19.0-00 && \
apt-mak hold kubeadm
# kubeadm 업그레이드

kubeadm version: &version.Info{Major:"1", Minor:"19", GitVersion:"v1.19.0", GitCommit:"e19964183377d0ec2052d1f1fa930c4d7575bd50", GitTreeState:"clean", BuildDate:"2020-08-26T14:28:32Z", GoVersion:"go1.15", Compiler:"gc", Platform:"linux/amd64"}
# 업그레이드된 kubeadm 버전 확인

$ kubeadm upgrade plan
Components that must be upgraded manually after you have upgraded the control plane with 'kubeadm upgrade apply': 
COMPONENT CURRENT AVAILABLE 
kubelet 2 x v1.18.0 v1.19.12 

Upgrade to the latest stable version: 

COMPONENT CURRENT AVAILABLE 
kube-apiserver v1.18.0 v1.19.12 
kube-controller-manager v1.18.0 v1.19.12 
kube-scheduler v1.18.0 v1.19.12 
kube-proxy v1.18.0 v1.19.12 
CoreDNS 1.6.7 1.7.0 
etcd 3.4.3-0 3.4.9-1
# 컴포넌트 업그레이드 가능 버전 확인

$ kubeadm upgrade apply v1.19.0
[upgrade/version] You have chosen to change the cluster version to "v1.19.0" [upgrade/versions] Cluster version: v1.18.0 [upgrade/versions] kubeadm version: v1.19.0 [upgrade/confirm] Are you sure you want to proceed with the upgrade? [y/N]: y ... [upgrade/successful] SUCCESS! Your cluster was upgraded to "v1.19.0". Enjoy!
# 업그레이드 진행
```
kubeadm은 쿠버네티스 클러스터를 관리하는 관리 도구.kubeadm을 먼저 업그레이드 한 후 kubeadm upgrade plan 명령을 실행하면 업그레이드 가능한 쿠버네티스 버전과 각 컴포넌트들의 버전을 확인할 수 있다. 이후 kubeadm upgrade apply를 적용하여 업그레이드를 진행한다.

4. 마스터 노드의 kubelet, kubectl 업그레이드
```cli
$ apt-mark unhold kubelete kubectl && \
apt-get update && apt-get install -y kubelet=1.19.0-00 kubectl=1.19.0-00 && \
apt-mark hold kubelet kubectl 
# kubelet, kubectl 업그레이드

$ sudo systemctl daemon-reload
$ sudo systemctl restart kubelet
# kubelete 재시작 

$ kubelet --version
Kuvernetes v1.19.0

$ kubectl version
Client Version: version.Info{Major:"1", Minor:"19", GitVersion:"v1.19.0", GitCommit:"e19964183377d0ec2052d1f1fa930c4d7575bd50", GitTreeState:"clean", BuildDate:"2020-08-26T14:30:33Z", GoVersion:"go1.15", Compiler:"gc", Platform:"linux/amd64"}
Server Version: version.Info{Major:"1", Minor:"19", GitVersion:"v1.19.0", GitCommit:"e19964183377d0ec2052d1f1fa930c4d7575bd50", GitTreeState:"clean", BuildDate:"2020-08-26T14:23:04Z", GoVersion:"go1.15", Compiler:"gc", Platform:"linux/amd64"}
# kubelet, kubectl 버전 확인
```

5. 마스터 노드 uncordon
```cli
& kubectl uncordon controlplane
node/controlplane uncordoned
```

6. 워커 노드 접속 및 kubeadm 업그레이드
```cli
$ kubectl get no
NAME STATUS ROLES AGE VERSION 
controlplane Ready master 8m49s v1.19.0 
node01 Ready <none> 8m22s v1.18.0
# 노드 확인

$ kubectl drain node01 --ignore-daemonsets
node/node1 evicted

$ ssh node01

node01$ kubeadm version 
kubeadm version: &version.Info{Major:"1", Minor:"18", GitVersion:"v1.18.0", GitCommit:"9e991415386e4cf155a24b1da15becaa390438d8", GitTreeState:"clean", BuildDate:"2020-03-25T14:56:30Z", GoVersion:"go1.13.8", Compiler:"gc", Platform:"linux/amd64"}
# 노드의  kubeadm 버전 확인

apt-mark unhold hubeadm && \
apt-get update && apt-get install -y kubeadm=1.19.0-00 && \
apt-mark hold kubeadm
# kubeadm 업그레이드

kubeadm upgrade node
...
[upgrade] The configuration for this node was successfully updated!
```

7. kubelet 및 kubectl 업그레이드
```cli
apt-mark unhold kubelet kubectl && \
apt-get update && apt-get install -y kubelet=1.19.0-00 kubectl=1.19.0-00 && \
apt-mark hold kubelet kubectl
# kubelet 및 kubectl 업그레이드

systemctl damon-reload
systemctl restart kubelet
# kubelet 재시작
```

8. 노드 uncordon 및 노드 업그레이드 상태 확인
```cli
node01 $ exit
logout
Connection to node01 closed.
$
$ kubectl get no
NAME           STATUS   ROLES    AGE   VERSION
controlplane   Ready    master   18m   v1.19.0
node01         Ready    <none>   18m   v1.19.0
```

## 5. 클러스터 백업
```cli
$ ETCDCTL_API=3 etcdctl --cacert="/etc/kubernetes/pki/etcd/ca.crt" \
--cert="/etc/kubernetes/pki/etcd/server.crt" \
--key="/etc/kubernetes/pki/etcd/server.key" \
snapshot save /opt/snapsho.db

Snapshot saved at /opt/snapsho.db
```

## 6. 특정 레이블을 가진 파드 중 CPU 사용량이 가장 높은 파드 조회
파드의 CPU 사용량은 kubectl top pod 명령을 통해 확인할 수 있다.

-l 옵션과 레이블명을 추가하여 특정 레이블을 가진 파드의 CPU 사용량을 조회할 수 있다.

문제에서는 해당 파드명을 파일형태로 저장한다.

```cli
$ kubectl top pod -l {LABEL}
$ echo {파드명} > {문제에서 요구하는 파일명}
```

## 7. 특정 노드에 파드 생성
특정 노드에 스케줄링되는 파드를 생성한다.

파드가 스케줄링되는 노드를 지정하려면 nodeSelector 옵션을 사용해야 한다.

해당 옵션은 CLI로는 생성할 수 없고 YAML을 작성해야 한다.

```
nodeSelector:
{문제에서 요구하는 레이블}
```

```cli
$ kubectl run ckatest --image nginx --dry-run=client -o yaml > pod.yaml
# 파드 기본 템플릿 작성

$ vi pod.taml
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    run: ckatest
  name: ckatest
spec:
  containeres:
  - image: nginx
    name: ckatest
    resources: {}
  dnsPolicy: ClusterFirst
  restartPolicy: Always
  nodeSelector:
    disktype: ssd
# pod.yaml 내용 수정

$ kubectl apply -f pod.yaml
```

## 8. 파드의 로그 파일로 출력
파드의 로그는 kubectl logs {파드명}으로 출력한다.

표준출력 > 을 사용해 파일 형태로 출력한다.

```cli
$ kubectl logs {파드명} > {문제의파일경로}
```

## 9. 디플로이먼트 스케일링
```cli
$ kubectl scale deployment {디플로이먼트명} --replicas={요구값}
```

## 10. 멀티 컨테이너 파드 배포
컨테이너를 여러개 가지고 있는 파드를 배포해야 한다.

containers: 하위에 한 세트를 추가하면 된다.

```cli
$ kubectl run ckatest --image nginx --dry-run=client -o yaml > pod.yaml

$ vi pod.yaml
apiVersion: v1
kind: Pod
metadata:
  name: muti-containers
spec:
  containers:
  - name: {containerA}
    image: {ImageA}
  - name: {containerB}
    iamge: {ImageB}
  ...

$ kubectl apply -f pod.yaml
```

## 11. hostPath PV 생성
수동으로 PV를 하나 생성한다. 

파드는 기본적으로 stateless로 파드 삭제 시 내부에 파일 시스템에 작성되었던 내용들도 모두 삭제된다.

이를 막기 위해 영속성이 필요한 데이터들은 PV와 PVC를 활용해 관리한다.

PV는 어떤 스토리지를 사용할 지에 대한 부분이고, PVC는 파드가 어떤 PV를 사용할 지에 대한 정의이다.

PV는 다양한 스토리지와 연결하여 사용할 수 있는대, hostPath의 경우 실제 컨테이너가 실행되는 호스트 머신의 파일시스템을 사용한다. 

```yaml
apiVersion: v1
kind: PersistentVolume
metadata: 
  name: task-pv-volume
  labels:
    type: local
spec:
  storageClassName: manual
  capacity:
    storage: 10Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: "{문제에서 주어지는 경로}"
```

```cli
$ kubectl create -f hostpath.yaml
```

## 12. PV와 PVC 생성하여 nginx파드에 볼륨 마운트
1. PV 생성
아래는 일반적인 PV 생성 YAML 파일이다.

spec 부분에 storage, accessModes, path 부분을 문제에서 요구하는 대로 수정하여 생성한다.

```cli
$ cat pv.yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: take-pv-volume
  labels:
    type: local
spec:
  capacity:
    storage: 10Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: "/mnt/data"
# 파일 생성

$ kubectl apply -f pv.yaml
persistentvolume/task-pv-volume created
# pv 리소스 생성

$ kubectl get pv
NAME CAPACITY ACCESS MODES RECLAIM POLICY STATUS CLAIM STORAGECLASS REASON AGE 
task-pv-volume 10Gi RWO Retain Available 65s
```

2. PVC 생성
생성한 PV에 맞게 PVC를 생성한다.

storage는 PV의 storage보다 작거나 같아야 한다.

```cli
$ cat pvc.yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: task-pv-claim
spec:
  accdessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 3Gi

$ kubectl apply -f pvc.yaml
persistentvolumeclaim/task-pv-claim created
# pvc 리소스 생성

$ kubectl get pv,pvc
NAME CAPACITY ACCESS MODES RECLAIM POLICY STATUS CLAIM STORAGECLASS REASON AGE 
persistentvolume/task-pv-volume 10Gi RWO Retain Bound default/task-pv-claim 5m28s 

NAME STATUS VOLUME CAPACITY ACCESS MODES STORAGECLASS AGE 
persistentvolumeclaim/task-pv-claim Bound task-pv-volume 10Gi RWO 6s
```
PVC 조회 시 위의 출력처럼 Bound가 되야 한다.

Pending으로 계속 유지될 경우 pvc가 요청한 내용에 pv가 일치하지 않은 경우이기 때문에 spec을 다시 확인해봐야 한다.

3. 파드 생성 및 pvc 연결
PVC 생성에서 실제 볼륨 역할을 하는 PV와 이 볼륨을 사용하는 요청인 PVC를 생성했다.

실제 파드 생성시 해당 볼륨을 사용하려면 아래와 같이 파드의 volume부분에 PVC를 등록하면 된다.

```cli
$ cat pod.yaml
apiVersion: v1
kind: Pod
metadata:
  name: task-pv-pod
spec:
  containers:
    - name: task-pv-container
      image: nginx
      ports:
        - containerPort: 80
          name: "http-server"
      volumeMounts:
        - mountPath: "/usr/share/nginx/html"
          name: task-pv-storage
  volumes:
    - name: task-pv-storage
      persistentVolumeClaim:
        claimName: task-pv-claim
# pod.yaml 파일 작성

$ kubectl apply -f pod.yaml
pad/task-pv-pod created
# 파드 생성

$ kubectl get pod
NAME READY STATUS RESTARTS AGE 
task-pv-pod 1/1 Running 0 17s
# 파드 조회

$ kubectl describe po task-pv-pod
... 
    Mounts:
       /usr/share/nginx/html from task-pv-storage (rw) 
... 
Volumes:
  task-pv-storage: 
    Type: PersistentVolumeClaim (a reference to a PersistentVolumeClaim in the same namespace)
    ClaimName: task-pv-claim 
    ReadOnly: false 
...
```

## 13. 스토리지 클래스에 해당하는 PVC 생성
위처럼 PV를 별도로 생성하고 관리할 수도 있지만, 스토리지 클래스는 PVC에 맞는 PV를 자동으로 생성(프로비저닝)해준다.

문제에는 스토리지 클래스가 주어져 있고 해당 스토리지 클래스에서 PV를 생성할 수 있도록 PV를 작성해야 한다.

```cli
$ kubectl describe storageclass {스토리지클래스명}
# 주어진 스토리지 클래스 상세 정보 확인

apiVersion: v1
kind: PersistentVolumeClaim
metadataL
  name: myclaim
spec:
  accessModes:
    - {스토리지클래스와 동일하게 작성}
  volumeMode:  FileSystem
  resources:
    requests:
      storage: {문제에서 요구하는 용량}
  storageClassName: {문제에서 주어진 스토리지 클래스}

$ kubectl get pv,pvc --all-namespace
```˜

## 14. 멀티 컨테이너 파드 배포2(사이드카 패턴)

## 15. 디플로이먼트 Expose

## 16. 인그레스 생성

## 17. NetworkPolicy 생성

