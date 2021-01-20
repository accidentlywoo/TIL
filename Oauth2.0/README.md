# Oauth 2.0 

다른 사이트의 정보를 들고오고 싶을때는? 하고싶은 동작 순서 : 

Resource Owner -> Client -> Resource Server

Resource Server -> Client -> Resource Owner

 -> 보안에 취약

 -> Ouath는 (거의) 인증표준이고, 많은 사이트에서 채택해서 사용중이다.

## Oauth 동작 메커니즘

Client - 인증요청 -> Resource Server 

Resource Server - Client Id, Clitent Secret (Code) -> Client

Client - Id, Secret -> Resource Server

Resource Server - access token -> Client

- 대형 서비스에서는 보안에 더 좋고 매커니즘이 비교적 간단한 SDK를 제공한다.

## 구글에서 만들기!
사용자 인증 정보 만들기 > Oauth 클라이언트 > [option]웹 애플리케이션

 -> 생성 후 .json으로 OAuth 클라이언트 ID 정보를 받을 수 있다.

 서비스 설정과 받을 데이터 형태, 폼을 지정할 수 있다.

 