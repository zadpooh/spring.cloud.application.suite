# Deep Night Spring Demo Code

## Spring Cloud Service List
  - [spring-config-service](#spring-config-service)
  - [spring-discovery-service](#spring-discovery-service)
  - [spring-gateway-service](#spring-gateway-service)
  - [demo-service](#demo-service)

## Spring Cloud Service
> Spring 프레임 워크기반의 마이크로 서비스 아키텍쳐 구성을 쉽계 설계 구성 할수있는 프레임 워크입니다.
  
## spring-config-service
``` 
마이크로 서비스의 분산 클라이언트 서비스에 환경 정보 및 설정 정보의 관리 및 동적으로 분리 함으로서 
설정 정보만 변경 하여 재배포 환경을 축소 할수 있습니다.
쉽게 풀어서 yml, properties 설정 값을 모아두는곳 github, gitlab에 동적으로 관리 가능함 
refresh를 통해 클라이언트에 변경된 설정 값 배포 없이 적용 가능  
```

## spring-discovery-service
``` 
마이크로서비스의 Spring Cloud Netflix Eureka를 이용한 디스커버리 서비스 
클라우드 환경이 되면서 서비스가 IP가 동적으로 변경.
서비스 클라이언트가 IP주소와 포트를 알아낼 수 있는 기능을 
서비스 디스커버리(Service discovery)라고 부름
```
## spring-gateway-service
``` 
백엔드 시스템 및 서비스에 대한 액세스를 제어하는 방법.
Reverse Proxy 처럼 클라이언트 앞 단에 위치하며 모든 서버로의 요청을 단일지점을 거쳐서 처리하도록 한다.
이를 통해 공통된 로직 처리나 인증 및 인가, 라우팅 등을 할 수 있다.
```
## demo-service
``` 
 클라우드 환경에서 최적화된 방식으로 설계,
 개발 및 배포된 애플리케이션을 말합니다. 
 이렇게 구성된 애플리케이션은 클라우드의 장점을 최대한 활용하여 높은 가용성, 확장성, 유연성 및 탄력성을 제공합니다.
```
 