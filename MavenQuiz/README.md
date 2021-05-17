# MavenQuiz
메이븐 쥐뿔도 모릅니다..
갓기선님 블로그보고 따라온 [메이븐 퀴즈퀴즈](http://andresalmiray.com/maven-dependencies-pop-quiz-results/)

## 질문 1. Given the following POM file, which version of Guava will be selected?
```
...
<modelVersion>4.0.0</modelVersion>
<groupId>com.acme</groupId>
<artifactId>base</artifactId>
<version>0.0.0-SNAPSHOT</version>

<dependencies>
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>27.0-jre</version>
    </dependency>
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>28.0-jre</version>
    </dependency>
</dependencies>

```
### 1. 27.0-jre, 2. 28.2-jre, 3.  Build Error
정답은 2번

    Maven은 가장 가까운 버전을 매칭한다.
    IDE에서 경고와 올바른 플래그를 지정한다.

## 질문 2. Given the following POM file, which version of Guava will bw selected? Guava 27.0.1-android is a transitive dependency of Truth 1.0
```
...
<modelVersion>4.0.0</modelVersion>
<groupId>com.acme</groupId>
<artifactId>base</artifactId>
<version>0.0.0-SNAPSHOT</version>

<dependencies>
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>27.0-jre</version>
    </dependency>
    <dependency>
        <groupId>com.google.truth</groupId>
        <artifactId>truth</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>

```

### 1. 27.0.1-android, 2. 28.2-jre, 3. Build Error
정답 2번

## 질문 3. Given the following POM file, which version of Guava will bw selected? Guava 27.0.1-android is a transitive dependency of Truth 1.0
```
...
<modelVersion>4.0.0</modelVersion>
<groupId>com.acme</groupId>
<artifactId>base</artifactId>
<version>0.0.0-SNAPSHOT</version>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>27.0-jre</version>
        </dependency>
    </dependencies>
<dependencyManagement>
<dependencies>
    <dependency>
        <groupId>com.google.truth</groupId>
        <artifactId>truth</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```

### 1. 27.0-jre, 2. 27.0.1-android, 3. Build Error
정답 1번

    <dependencyManagement>블록은 종속성을 해결해야 할 때무다 조회 테이블로 작동한다.
    이 경우 Maven은 일치하는 종속성 (groupId 및 artifactId)이 선언되었는지 확인하고 일치하는 버전을 반환하는지 확인하는 조회 테이블을 살펴본다.


## Maven과 Gradle 좀 더 이해하기
- [우아한 코스 자바 빌드 툴 Maven vs Gradle](https://woowacourse.github.io/javable/post/2020-09-17-java-build-tool/)

-> 핵심 내용
 > Maven is not a perfect tool (nor is Gradle for that matter), we all have heard the Maven/Gradle bashing from colleagues (or ourselves) at some point in our developer career. Instead of spending time raging about the state of the tools, what if we used that energy to create something positive?

 - [그레들 공식홈페이지에서 보여주는 Maven보다 겁나빠른 Gradle 빌드속도](https://gradle.org/maven-vs-gradle/)