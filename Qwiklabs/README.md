# GCP qwiklabs
GCP 기반 실습을 할 수 있는 좋은 곳~
여기서 해본 공부를 기록하자!

## 카테고리
1. [Docker 소개](#1-docker-소개)
   

--- 

## 1. Docker 소개
- [GCP qwiklabs](#gcp-qwiklabs)
  - [카테고리](#카테고리)
  - [1. Docker 소개](#1-docker-소개)
    - [1. Cloud Shell 활성](#1-cloud-shell-활성)
    - [2. Hello World](#2-hello-world)
    - [3. Build](#3-build)
- [Use an official Node runtime as the parent image](#use-an-official-node-runtime-as-the-parent-image)
- [Set the working directory in the container to /app](#set-the-working-directory-in-the-container-to-app)
- [Copy the current directory contents into the container at /app](#copy-the-current-directory-contents-into-the-container-at-app)
- [Make the container's port 80 available to the outside world](#make-the-containers-port-80-available-to-the-outside-world)
- [Run app.js using node when the container launches](#run-appjs-using-node-when-the-container-launches)

Dokcer는 애플리케이션을 개발, 제공 및 실행하기위한 개방형 플랫폼.
Docker를 사용하면 애플리케이션을 인프라에서 분리하고 인프라를 관리 형 애플리케이션처럼 취급 할 수 있다.
Docker를 사용하면 코드를 더 빠르게 제공하고, 더 빠르게 테스트하고, 더 빠르게 배포하고, 코드 작성돠 코드 실행 사이의 주기를 단축 할 수 있다.

Docker는 커널 컨테이너화 기능을 애플리케이션 관리 및 배포에 도움이되는 워크 플로 및 도구와 결합아여이를 수행한다.

Docker 컨테이너는 Kubernetes에서 직접 사용할 수 있으므로 Kubernetes Engine에서 쉽게 실행할 수 있다. Docker의 필수 사항을 학습 한 후에는 Kubernetes 및 컨테이너화 된 애플리케이션 개발을 시작할 수 있는 기술을 갖게된다.

이번 실습을 통해 배울 것
- Docker 컨테이너를 빌드, 실행 및 디버그하는 방법
- Docker Hub 및 Google Container Registry에서 Docker 이미지를 가져 오는 방법
- Docker이미지는 Google Container Registry로 푸시하는 방법

최근에 Node.js 공홈 실습을 통해 node.js서버를 도커로 뚝딱뚝딱 뭔가 서버를 띄운 경험이있다. 이와 비교를 해보자!

### 1. Cloud Shell 활성
    Cloud Shell은 개발 도구와 함께로드되는 가상 머신이다.
    영구 5GB 홈 디렉토리를 제공하고 Google Cloud에서 실행된다.
    Cloud Shell은 Google Cloud 리소스에 대한 명령 줄 액세스를 제공한다.

    Cloud Console에서 Cloud Shell활설화 버튼을 클릭한다.
    ```gcloud```Google Cloud의 Command - line tool 이다.
    Cloud Shell에 사전 설치되어 있으며 탭완성을 지원한다.

    ```gcloud auth list``` -> 활성 계정 이름을 나열
    산출 예시
    ```
    Credentialed accounts:
    - google1623327_student@qwiklabs.net
    ``` 
    

    ```gcloud config list project``` -> 프로젝트 ID 나열
    산출 예시
    ```
    [core]
    project = qwiklabs-gcp-44776a13dea667a6
    ```

### 2. Hello World
Cloud Shell을 열고 다음 명령어를 입력하여 hello world 컨테이너를 실행하여 시작
```docker run hello-world```
산출 예시
```
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
9db2ca6ccae0: Pull complete
Digest: sha256:4b8ff392a12ed9ea17784bd3c9a8b1fa3299cac44aca35a85c90c5e3c7afacdc
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.
...
```
이 간단한 컨테이너는 ```Hello from Docker!``화면으로 돌아간다. 
먕량은 간단하지만 출력에서 수행 한 단계 수를 확인하시오.
docker 데몬은 hello-world이미지를 검색하고 로컬에서 이미지를 찾지 못하고 Docker Hub라는 공용 레지스트리에서 이미지를 가져와 해당 이미지에서 컨테이너를 생성하고 컨테이너를 실헹했다.

다음 명령을 실행하여 Docker Hub에서 가져온 컨테이너 이미지를 살펴보자.
```docker images```
산출 예시
```
REPOSITORY     TAG      IMAGE ID       CREATED       SIZE
hello-world    latest   1815c82652c0   6 days ago    1.84 kB
```
Docker Hub 공용 레지스트리에서 가져온 이미지이다. 
이미지 ID는 SHA256해시 형식이다. 이 필드는 프로비저닝된 Docker 이미지를 지정한다.
Docker 데몬이 로컬에서 이미지를 찾을 수 없는 경우 기본적으로 공용 레지스트리에서 이미지를 검색한다.
컨테이너를 다시 실행 해 보자
```docker run hello-world```
산출 예시
```
Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
...
```
두 번째로 실행하면 docker 데몬이 로컬 레지스트리에서 이미지를 찾고 해당 이미지에서 컨테이너를 실행한다. Docker Hub에서 이미지를 가져올 필요가 없다.

마지막으로 다음 명령을 실행하여 실행중인 컨테이너를 확인
```docker ps```
산출 예시
```
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```
실행중인 컨테이너가 없다. 이전에 실행 한 hello-world 컨테이너가 이미 종료되었다.
실행이 완료된 컨테이너를 포함하여 모든 컨테이너를 보려면 다음을 실행하시오
```docker ps -a```
산출 예시
```
CONTAINER ID      IMAGE           COMMAND      ...     NAMES
6027ecba1c39      hello-world     "/hello"     ...     elated_knuth
358d709b8341      hello-world     "/hello"     ...     epic_lewin
```
그러면 ```Container ID``` 컨테이너를 식별하기 위해 Docker에서 생성 한 UUID, 실행에 대한 추가 메타 데이터가 표시된다.
컨테이너 ```Names``도 임의로 생성되지만 지정할 수 있다 
 -> ```docker run --name [container-name] hello-world```

 ### 3. Build
 간단한 노드 애플리케이션을 기반으로하는 Docker 이미지를 빌드해 보자.
 다음 명령을 실행하여 폴더를 만들고 이동하자
 ```mkdir test && cd test```

 ```Dockerfile```만들기
    ``` 
cat > Dockerfile <<EOF
# Use an official Node runtime as the parent image
FROM node:6

# Set the working directory in the container to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

# Make the container's port 80 available to the outside world
EXPOSE 80

# Run app.js using node when the container launches
CMD ["node", "app.js"]
EOF
    ```