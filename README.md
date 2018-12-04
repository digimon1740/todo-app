# TodoApp For KaKao

* Lombok 플러그인이 설치되어있어야합니다.
* 테스트의 편의를 위하여 Embedded Database 를 사용하였습니다.

* Java8
* Spring Boot 2.1 
* Spring Data JPA, Spring Security and others
* Javascript ES6+
* AngularJS

회고
------------------------------------------------ 
해당 애플리케이션은 프론트,백엔드가 한 프로젝트에 들어가 있는 형태라 
가장 구현이 쉬운 session 을 사용하였습니다.
만약 프론트 프로젝트가 나뉘어 있거나 모바일 앱등에서의 인증을 위해
OAuth, JWT 같은 방식의 인증방식으로 구현했으면 좋았을것 같습니다.

현재는 유저별로 todo list 전체를 가져오도록 설계되었습니다.
페이징 처리를 따로 하진 않았지만 현재 UI 에서 페이징 처리를 한다면 
Infinity scroll 이나 더보기 방식의 페이징 처리로 개선할 수 있을거 같습니다. 





