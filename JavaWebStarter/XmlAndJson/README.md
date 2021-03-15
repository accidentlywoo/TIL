# 데이터로 소통하기위한 XML과 JSON

## index
- [데이터로 소통하기위한 XML과 JSON](#데이터로-소통하기위한-xml과-json)
  - [index](#index)
  - [What is XML](#what-is-xml)
  - [What is JSON](#what-is-json)
  - [JSON vs XML](#json-vs-xml)
  - [참고 사이트](#참고-사이트)

## What is XML
XML이란, XML(EXtensible Markup Language)의 약자힙니다.

XML은 HTML과 매우 비슷한 문자 기반의 마크업 언어(text-based markup language)입니다.

이 언어는 사람과 기계가 동시에 읽기 편한 구조로 되어 있습니다.


XML은 HTML처럼 데이터를 보여주는 목적이 아닌, 데이터를 저장하고 전달할 목적으로 만들어졌습니다.

또한, XML태그(<>)는 HTML처럼 미리 정의되어 있지 않고, 사용자가 직접 정의할 수 있습니다.

XML이 어떻게 생겼는지 볼까요?

```
<dog>
    <name>식빵</name>
    <family>웰시코기<family>
    <age>1</age>
    <weight>2.14</weight>
</dog>
```

같은 데이터를 JSON으로 표현해 봅시다.

```
{
    "name": "식빵",
    "family": "웰시코기",
    "age": 1,
    "weight": 2.14
}
```
## What is JSON
JSON이란, JSON(JavaScript Object Notation)의 약자입니다.

JSON은 사람이 읽을 수 있는 텍스트 기반의 데이터 교환 표준입니다.

JSON은 XML의 대안으로서 좀 더 쉽게 데이터를 교환하고 저장하기 위해 고안되었습니다.

## JSON vs XML
JSON과 XML의 공통점

1. 둘 다 데이터를 저장하고 전달하기 위해 고안되었습니다.
2. 둘 다 기계뿐만아니라 사람도 쉽게 읽을 수 있습니다.
3. 둘 다 계층적인 데이터 구조를 가집니다.
4. 둘 다 다양한 프로그래밍 언어에 의해 파싱될 수 있습니다.
5. 둘 다 XMLHttpRequest객체를 이용하여 서버로부터 데이터를 전송받을 수 있습니다.

XML로 데이터를 전달하는걸 본적이 오래입니다. 물론, 사용할 수도있지만,특히 웹 환경에서는 JSON 사용비률이 압도적으로 높습니다.

차이점을 알아볼까요?

1. JSON은 종료태그를 사용하지 않습니다.
2. JSON은 구문이 XML의 구문보다 더 짧습니다. -> 성능과 속도에 영향
3. XML은 배열을 사용할 수 없지만, JSON은 배열을 사용할 수 있습니다.
4. XML은 XML파서로 파싱되며, JSON은 자바스크립트 표준 함수인 eval()함수로 파싱됩니다.

XML은 XML DOM(Document Object Model)을 이용하여 해당 문서에 접근합니다. HTML이랑 비슷하죠.

 JSON은 문자열을 전송받은 후에 해당 문자열로 파싱해서, XML보다 훨씬 빠른 처리속도를 보여줍니다.

 JSON은 XML보다 같은 데이터를 표현하는데, 제약사항이 더 적고, 더 짧고, 더 빠른 처리속도를 보여줍니다.

 하지만, 데이터릐 무결성을 사용자가 직접 검증해야하는 리스크가있습니다.

## 참고 사이트
[TCP School - JSON](http://www.tcpschool.com/json/intro)