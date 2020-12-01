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
    - [4. Run](#4-run)
    - [Debug](#debug)

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
이 파일은 Docker데몬에 이미지 빌드 방법을 지시한다.

[Dockerfile Command Reference](https://docs.docker.com/engine/reference/builder/#known-issues-run)

이제 노드 애플리케이션을 작성하고 그 후에 이미지를 빌드란다.

간단한 노드 애플리케이션 만들기..(이렇게 간단하게 서버를 뚝딱하다니...)
```
cat > app.js <<EOF
const http = require('http');

const hostname = '0.0.0.0';
const port = 80;

const server = http.createServer((req, res) => {
    res.statusCode = 200;
      res.setHeader('Content-Type', 'text/plain');
        res.end('Hello World\n');
});

server.listen(port, hostname, () => {
    console.log('Server running at http://%s:%s/', hostname, port);
});

process.on('SIGINT', function() {
    console.log('Caught interrupt signal and will exit');
    process.exit();
});
EOF
```
이것은 포트 80에서 수신 대기하고 "Hello World"를 반환하는 간단한  HTTP서버.

이제 이미지를 만들어보자

" . " 은 Dockerfile이 있는 디렉토리 내에서 명령을 실행해야하므로 현재 디렉토리를 의미한다.
```docker build -t node-app:0.1```
이 명령이 실행을 완료하는 데 몇 분 정도 걸릴 수 있다.
출력 예시
```
Sending build context to Docker daemon 3.072 kB
Step 1 : FROM node:6
6: Pulling from library/node
...
...
...
Step 5 : CMD node app.js
 ---> Running in b677acd1edd9
 ---> f166cd2a9f10
Removing intermediate container b677acd1edd9
Successfully built f166cd2a9f10
```
```name:tag``` 구문으로 "-t"옵션은 이름과 태그이다.
이미지의 ```name```은 ```node-app```이고 ```0.1```은 tag이다.
tag는 도커 이미지 빌드시 매우 추천된다.
만약 tag를 지정하지 않으면 기본값으로 설정되며 최신의 이미지와 구별하기 어려워진다.
또한 Dockerfile위의 각 줄 이미지가 빌드 될 때 중간 컨테이너 레이어가되는 방식을 확인한다.
(?)

다음 명령을 실행해 빌드 한 이미지를 확인해라
```docker images```
출력 예시
```
REPOSITORY     TAG      IMAGE ID        CREATED            SIZE
node-app       0.1      f166cd2a9f10    25 seconds ago     656.2 MB
node           6        5a767079e3df    15 hours ago       656.2 MB
hello-world    latest   1815c82652c0    6 days ago         1.84 kB
```
참고 node는 기본 이미지이며 사용자가 만든 이미지가 node-app입니다.
먼저 node-app을 지우지 않고 node를 지울 수 없다.
이미지의 크기는 VM에 비해 상대적으로 작다. 
같은 노드 이미지의 다른 버전 node:slim과node:alpine 더작고 쉽게 휴대할 수 있는 이미지를 제공한다.

### 4. Run
아래 코드를 사용하여 빌드 한 이미지를 기반으로 컨테이너를 실행한다.
```docker run -p 4000:80 --name my-app node-app:0.1```
명령 출력
```
Server running at http://0.0.0.0:80/
``` 
--name 플래그는 선호하는 이름을 지정할 수 있다.
-p Docker에 호스트의 포트 4000을 컨테이너의 포트 80에 매핑하도록 지시한다.
이제 ```http://localhost:4000``에서 서버에 연결할 수 있다.
포트 매핑이 없으먄 localhost의 컨테이너에 도달 할 수 없다.

다른 터미널을 열고 서버를 테스트한다.
```
curl http://localhost:4000
```
명령 출력
```
Hello World
```
컨테이너는 초기 터미널이 실행되는 동안 실행된다. 
컨테이너가 백그라운드에서 실행되도록하려면(터미널 세션에 연결되지 않음) -d 플래그를 지정해야 한다.

초기 터미널을 닫고 다음 명령을 실행하여 컨테이너를 중지하고 제거한다.
```
docker stop my-app && docker rm my-app
```

다음 명령을 실행하여 백그라운드에서 컨테이너를 시작한다.
```
docker run -p 4000:80 --name my-app -d node-app:0.1

docker ps
```
출력 예시
```
CONTAINER ID   IMAGE          COMMAND        CREATED         ...  NAMES
xxxxxxxxxxxx   node-app:0.1   "node app.js"  16 seconds ago  ...  my-app
```
```docker ps``의 결과물로 컨테이너 실행중을 알 수 있다.
```docker logs [container_id]``를 실행시켜 로그를 볼 수 있다.

```docker logs [container_id]```
출력 예시
```
Server running at http://0.0.0.0:80/
```

애플리케이션을 변경해보자. 
맨처음 만들었던 test 디렉토리로 이동하자
```cd test```

app.js를 텍스트 에디터로 변경해보자.
"Hello World" -> "Welcome to Cloud"로 변경
```
....
const server = http.createServer((req, res) => {
    res.statusCode = 200;
      res.setHeader('Content-Type', 'text/plain');
        res.end('Welcome to Cloud\n');
});
....
```

새로운 이미지를 tag를 0.2 와 빌드하자
```docker build -t node-app:0.2```
출력물
```
Step 1/5 : FROM node:6
 ---> 67ed1f028e71
Step 2/5 : WORKDIR /app
 ---> Using cache
 ---> a39c2d73c807
Step 3/5 : ADD . /app
 ---> a7087887091f
Removing intermediate container 99bc0526ebb0
Step 4/5 : EXPOSE 80
 ---> Running in 7882a1e84596
 ---> 80f5220880d9
Removing intermediate container 7882a1e84596
Step 5/5 : CMD node app.js
 ---> Running in f2646b475210
 ---> 5c3edbac6421
Removing intermediate container f2646b475210
Successfully built 5c3edbac6421
Successfully tagged node-app:0.2
```
step 2에서 기존 캐시 레이어를 사용하고 있다.
step 3과 이후로, app.js가 바꼈기때문에 레이어가 바꼈다.

다른 버전의 컨테이너를 실행하자.
80포트 대신 호스트의 포트 8080에 매핑하는 방법에 주목하자
호스트 포트 4000은 이미 사용 중이므로 사용할 수 없다.
```
docker run -p 8080:80 --name my-app-2 -d node-app:0.2
docker ps
```
출력 예시
```
CONTAINER ID     IMAGE             COMMAND            CREATED             
xxxxxxxxxxxx     node-app:0.2      "node app.js"      53 seconds ago      ...
xxxxxxxxxxxx     node-app:0.1      "node app.js"      About an hour ago   ...
```

컨테이너를 테스트한다.
```
curl http://localhost:8080
```
출력 물
```
Welcome to Cloud
```

(백그라운드에서 실행 중인) 첫번째 컨테이너를 테스트 한다.
```
curl http://localhost:4000
```
출력 물
```
Hello World
```

### Debug
