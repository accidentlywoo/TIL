## Installation And Configuration

### Minikube Install!
```curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-darwin-amd64```

```chmod +x minikube```

```sudo mv minikube /usr/local/bin```

위 과정이 미니큐브 들고와서 맥북 환경에 세팅하는 과정

이제 터미널에서

```minikube start```

때리면 엄청 이쁜(?) 이모티콘들과 미니큐브 도커 컨테이너가 실행된다.

```kubectl get nodes```

K8S Controller명령어 사용 가능

### Install kubeadm to create a cluster
[참고 사이트](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/create-cluster-kubeadm/)

kubeadm을 사용하면 모범 사례를 준수하는 최소한의 실행 가능한 K8S 클러스터를 만들 수 있다.

kubeadm은 다음과 같이 필요한 경우에 유용하다.

- 처음으로 Kubernetes를 사용해 볼 수있는 간단한 방법이다.
- 기존 사용자가 클러스터 설정을 자동화하고 애플리케이션을 테스트하는 방법이다.
- 더 넓은 범위의 다른 생태계 및 설치 도구의 구성요소 이다.

kubeadm 노트북, 클라우드 서버 세트, Raspberry Pi 등 다양한 컴퓨터에 설치하고 사용할 수 있다.
클라우드에 배포하든 온 프레미스에 배포하든 관계없이 kubeadm Ansible 또는 Terraform과 같은 프로비저닝 시스템에 통합할 수 있다.

뭔가.. 네트워크 설정이 쏟아진다..

### Installing a Pod Network
K8S 클러스터를 초기화하기 전에 네트워크를 고려해야하며 IP 충돌을 피해야한다.

많은 프로젝트에서 CNCF 프로젝트 인 CNI(Container Network Interface)를 언급한다.. C

- Pod Networking
1. Calico
2. Flannel
3. Kube-Router
4. Romana
5. Weave Net

졸귀탱 고양이 그림인 Calico는 다른분 논문에서 스윽봤으니 저걸쓴다!

### Installation Tools
Chef, Puppet, Ansible, Terraform 등 설정 관리 시스템을 사용할 수 있다.

[K8S 하드 웨이~](https://github.com/kelseyhightower/kubernetes-the-hard-way)

뭔지 모르겠으니 이런게 있다.

- Kubespray
- Kops
- kube-aws
- kubicorn
