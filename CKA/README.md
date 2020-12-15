# CKA

공부를 해보고, 시험각을 잡아보자...

---

- [CKA 잘 준비하는 법](https://github.com/accidentlywoo/TIL/tree/main/CKA/CKA잘준비하는법)
- [CKA Udemy](https://github.com/accidentlywoo/TIL/tree/main/CKA/CKA-udemy)
  
## Basic Index
- [CKA](#cka)
  - [Basic Index](#basic-index)
  - [Course Learning Objectives](#course-learning-objectives)
  - [What is Kubernetes](#what-is-kubernetes)
  - [Kubernetes의 구성 요소](#kubernetes의-구성-요소)
  - [도전](#도전)
  - [보그 헤리티지](#보그-헤리티지)
  - [Kubernetes 아키텍처](#kubernetes-아키텍처)


## Course Learning Objectives
By the end of this course, you will learn the following:

- he history and evolution of Kubernetes.​
- Its high-level architecture and components.
- The API, the most important resources that make the 
- API, and how to use them.
- How to deploy and manage an application.
- Some upcoming features that will boost your productivity.

    리눅스 재단

Linux Foundation은 오늘날 모든 산업 부문에 걸쳐 기업을 지원하는 많은 중요한 오픈 소스 프로젝트의 우산입니다.

- 빅 데이터 및 분석 : ODPi , R Consortium
- 네트워킹 : OpenDaylight , OPNFV , ONAP
- 임베디드 : Dronecode , Zephyr
- 웹 도구 : OpenJS Foundation
- 클라우드 컴퓨팅 : Cloud Foundry , Cloud Native Computing
- Foundation , Open Container Initiative
- 자동차 : 자동차 등급 Linux
- 보안 : 핵심 인프라 이니셔티브 , OpenSSF (Open Source
- Security Foundation)
- 블록 체인 : 하이퍼 레저

    CNCF(Cloud Native Computing Foundation)
CNCF는 클라우드 네이티브 컴퓨팅을 보편적이고 지속 가능하게 만드는 데 전념하는 Linux Foundation 산하의 오픈 소스 소프트웨어 재단입니다. 클라우드 네이티브 컴퓨팅은 오픈 소스 소프트웨어 스택을 사용하여 애플리케이션을 마이크로 서비스로 배포하고, 각 부분을 자체 컨테이너에 패키징하고, 이러한 컨테이너를 동적으로 조정하여 리소스 활용도를 최적화합니다. 클라우드 네이티브 기술을 통해 소프트웨어 개발자는 우수한 제품을 더 빠르게 구축 할 수 있습니다.

CNCF는 Kubernetes, Prometheus 및 Envoy를 포함하여 GitHub에서 가장 빠르게 성장하는 여러 프로젝트를위한 공급 업체 중립적 인 홈 역할을하여 업계 최고의 개발자, 최종 사용자 및 공급 업체 간의 협업을 촉진합니다.

## What is Kubernetes
    labtop에서 컨테이너를 실행하는 것은 비교적 간단하다. 그러나 여러 호스트에 걸켜 컨테이너를 연결하고, 확장하고, 다운 타임없이 애플리케이션을 배포하고, 여러 측면에서 서비스를 검색하는 것은 어려울 수 있다.

    Kubernetes는 기본 요소 세트와 강력한 개방적이고 확장 가능한 API를 사용하여 처음부터 이러한 문제를 해결한다. 새로운 개체 및 컨트롤러를 추가하는 기능을 통해 다양한 생성 요구 에 맞게 쉬게 사용자 정의를 할 수 있다.

    쿠버 공홈에서 "컨테이너화된 애플리케이션의 배포, 확장 및 관리를 자동화하기 위한 오픈 소스 시스템"

## Kubernetes의 구성 요소
    컨테이너를 배포하고 Kubernetes를 사용하려면 애플리케이션 배포에 대한 개발 및 시스템 관리 접근 방식을 변경해야 할 수 있다. 기존 환경에서 애플리케이션은 접용 서버에 배치된 모놀리식 애플리케이션이다. 웹 트래픽이 증가하면 응용 프로그램이 조정되고 아마도 더 크고 더 큰 하드웨어로 이동 될 것입니다. 몇 년 후 현재 웹 트래픽 요구 사항을 충족하기 위해 많은 사용자 정의가 수행될 수 있다.

    대규모 서버를 사용하는 대신 Kubernetes는 다수의 소규모 웹 서버 또는 마이크로 서비스를 배포하여 동일한 문제에 접근한다.
    애플리케이션의 서버 및 클라이언트 측은 요청에 응답하는 데 사용할 수 있는 에이전트가 많이 있다고 예상하도록 작성된다.
    또한 클라이언트가 서버 프로세스가 중단되고 교체되어 일시적인 서버 배포로 이어질 것으로 예상하는 것도 중요하다.
    페이지 요청에 응답하는 많은 httpd 데몬이있는 대규모 Apache 웹 서버 대신 각각 응답하는 많은 ngix서버가 있다.

    소규모 서비스의 일시적인 특성은 또한 분리를 허용한다.
    기존 애플리케이션의 각 측면은 전용이지만 일시적인 마이크로 서비스 또는 에이전트로 대체된다. 이러한 에이전트 또는 대체 에이전트를 결합하기 위해 서비스 및 API 호출을 한다. 서비스는 한 에이전트에서 다른 에이전트로 트래픽을 연결하고(ex. 프론트 엔드 웹 서버에서 백엔드 데이터베이스로) 새 IP또는 기타 정보를 처리한다.

    통신은 전적으로 API 호출 기반이므로 유연성이 있다. 클러스터 구성 정보는 etcd 내부에 JSON 형식으로 저장되지만 커뮤니티에서 YAML로 작성하는 경우가 가장 많다.
    Kubernetes 에이전트는 데이터베이스에 대한 지속성전에 YAML을 JSON으로 변환한다.

    Kubernetes 는 C++, Python 및 Java간의 하이브리드화와 같은 이식 가능한 언어 인 Go Language로 작성되었다. Go 각!

## 도전
    컨테이너는 애플리케이션을 패키징, 배송 및 실행할 수 있는 훌륭한 방법을 제공한다.
    이것이 Docker의 모토이다.(근데 도커 버림.)

    컨테이너 덕분에 개발자 경험이 엄청나게 향상되었다. 컨테이너, 특히 Docker는 개발자가 컨테이너 이미지를 쉽게 빌드하고 Docke 레지스트리를 통해 이미지를 간단하게 공유하며 컨테이너를 관리할 수 있는 강력한 사용자 경험을 제공할 수 있도록 지원한다.

    첫번째 단계는 이미지를 빌드, 테스트 및 확인하기 위해 CI/CD 파이프라인을 결정하는 것이다. Spinnaker, Jenkins및 Helm과 같은 도구는 사용 가능한 다른 도구 중에서 유용할 수 있다. 이것은 역동적인 환경의 도전에 도움이 된다.

    두번째로, 컨테이너를 실행할 기본 인프라 역할을하는 머신 클러스터가 필요하다. 또한 컨테이너를 시작하고 실패할때 이를 감시하고 필요에 따하 교체하는 시스템이 필요하다.
    롤링 업데이트와 컨테이너의 손쉬운 롤백은 중요한 기능이며 결국 더 이상 필요하지 않을 때 리소스를 해체한다.

    세번째로, 이 모든 작업에는 유연하고 확장 가능하며 사용하기 쉬운 네트워크 및 스토리지가 필요하다. 컨테이너가 작업자 노드에서 시작되면 네트워크는 다른 컨테이너로부터 트래픽을 안전하게 유지하면서 리소스를 다른 컨테이너에 조인해야 한다.
    또한 스토리지를 원활하게 제공, 유지 또는 재활용하는 스토리지 구조가 필요하다.

    Kubernetes가 이러한 문제에 답할 때 채택에있어 가장 큰 문제 중 하나는 컨테이너 내에서 실행되는 애플리케이션 자체이다. 찐으로 한방을 원한다면 작성하거나 다시 작성해야한다.
    (생각하면 좋은 질문) Chaos Money를 배포한다면 ??

## 보그 헤리티지
    Kubernetes를 다른 시스템과 주로 구별하는 것은 그 헤리티지입니다.
    Kubernetes는 Google에서 애플리케이션(Gmail, Apps, GCE)을 관리하는 데 사용하는 내부 시스템인 Borg에서 영감을 받았다.

    Google이 15년 넘게 Borg를 작성하고 운영하면서 배운 귀중한 교훈을 Kubernetes에 쏟아 붓는 덕분에 컨테이너 관리에 사용할 시스템을 결정해야 할 때 Kubernetes가 안전한 선택이 되었다.
    강력한 도구이지만 Kubernetes의 현재 성장 중 일부는 Google 데이터 센터에서 찾을 수 없는 작업 부하를보다 쉽게 사용하고 처리할 수 있도록한다.
    (ㅇ...이런게 있구나...)
<img src="https://github.com/accidentlywoo/TIL/blob/main/CKA/iamges/borg.png" width="30%" height="30%" display="inline-block" alt="borg"/>

    Borg는 현재의 데이터 센터 시스템과 오늘날 컨테이너 런타임에 사용되는 기본 기술에 영감을 주었다.
    Google은 2007년에 Linux 커널에 cgroup을 제공했다.
    프로세스 모음에서 사용하는 리소스를 제한한다.
    cgroup과 Linux네임 스페이스는 모두 Docker를 포함하여 오늘날 컨테이너의 핵심이다.

    Mesos는 Borg가 여전히 비밀이었을 때 Google과의 토론에서 영감을 받았다.
    실제로 Mesos는 데이터 센터 클러스터를 더 잘 사용하는 것을 목표로하는 다단계 스케줄러를 구축한다.

    Cloud Foundry Foundation은 12가지 요소 애플리케이션 원칙을 수용한다.
    이러한 원칙은 쉽게 확장할 수 있고 클라우드에 배포 할 수 있으며 빌드가 자동화된 웹 애플리케이션을 빌드하기위한 훌륭한 지침을 제공한다.
    Borg와 Kubernetes도 이러한 원칙을 다룬다.

## Kubernetes 아키텍처
    Kubernetes 아키텍처 그래픽을 살펴 보겠다.

<img src="https://github.com/accidentlywoo/TIL/blob/main/CKA/iamges/kubernetes-architecture.png" width="30%" height="30%" display="inline-block" alt="쿠버 아키텍처"/>

    가장 간단한 형태로 Kubernetes는 중앙 관리자(master)와 한때 미니언이라고 불리는 일부 작업자 노드로 구성된다.(테스트 목적으로 단일 노드에서 실제로 모든 것을 실행하는 방법은 후속 장에서 볼 것이다.) 관리자는 API 서버, 스케줄러, 다양한 컨트롤러 및 스토리지 시스템을 실행하여 클러스터 상태, 컨테이너 설정 및 네트워킹 구성을 유지한다.

    Kubernetes는 API 서버를 통해 API를 노출한다.kubectl 이라는 로컬 클라이언트를 사용하여 API와 통신 하거나 자체 클라이언트를 작성하고 curl 명령을 사용할 수 있다. KUBE-스케줄러 API를 오는 컨테이너를 실행하기 위한 요청을 전달하고 용기를 실행할 수있는 적절한 노드를 발견한다.
    클러스터의 각 노드는 kubelet 및 kube-proxy라는 두 가지 프로세스를 실행한다. kubelet은 컨테이너 실행 요청을 수신하고 필요한 리소스를 관리하며 로컬 노드에서 감사합니다.
    kubelet은 기본적으로 Docker인 로컬 컨테이너 엔진과 상호작용하지만 인기가 높아지고있는 rkt또는 cri-o일 수 있다.

    KUBE-프록시 생성하고 네트어크에 컨테이너를 노출 네트워킹 규칙을 관리한다.

    KUBE-프록시 생성하고 네트워크에 컨테이너를 노출 네트워킹 규칙을 관리한다. 

    API 기반 통신 체계를 사용하면 비 Linux작업자 노드 및 컨테이너가 허용된다.
    