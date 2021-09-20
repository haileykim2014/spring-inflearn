# 인프런 - 스프링 핵심 원리 기본편
</br>

:pushpin: Intro

커리큘럼 : 김영한님 스프링 핵심 원리 강의 뿌시기 👊

분량 : 섹션 1개 강의 들어오기!


</br>

<details>
<summary> 스프링 핵심 원리 이해1 - 스프링</summary>
<div markdown="1">
</br>
 
 
1. 스프링 프레임워크, 스프링 부트    
스프링 부트는 스프링과 관련된 기술을 편리하게 사용하게 만들어준다. 그리고 제일 중요한 것이 스프링 프레임워크이다.  
스프링 프레임워크의 핵심 기술에는, 스프링 의존성 주입(DI), AOP, 이벤트가 있고 웹 기술에는 스프링 MVC등이 있다.  
즉 스프링 부트를 이용해서 스프링 프레임워크를 사용하는 것이다. 스프링 부트는 기본으로 사용한다.  
 예전에는 웹 서버가 따로 있어서 웹서버를 따로 설치해야 했지만 지금은 스프링 부트안에 웹서버가 있기 편리하다.
 
 
2. 스프링을 왜 만들었을까?  
스프링의 핵심 개념  
웹 애플리케이션을 만들고 DB 접근을 편하게 해주는 기술  
이런 것들은 결과물이다. 스프링은 자바 언어 기반의 프레임 워크이다.  
즉 자바 언어의 가장 큰 특징인 객체 지향 언어를 사용하여 스프링이 자바의 객체 지향언어가 가진 강력한 특징을 살려내는 프레임워크이다. 즉, 객체 지향의 장점을 살린 프레임워크이다.  
 
 
3. 좋은 객체 지향 언어?  
유연하고 변경이 용이 -> 컴포넌트를 쉽고 유연하게 변경하면서 개발할 수 있는 방법, 즉 다형성이다.  
다형성의 개념: 역할과 구현으로 세계를 구분. 예로들어, 하나의 자동차 역할이 있으면 이를 다양한 종류의 차가 이 자동차의 역할을 구현하였다. 그러면 운전자는 각 자동차의 구현은 몰라도 되고 오로지 자동차의 인터페이스(역할)만 알면된다.
여기서 중요한 것은 이렇게 역할과 구현을 분리한 것은 운전자를 위해서 이다. 자동차 세상을 무한히 확장할 수 있다. client에 영향을 주지 않고 새로운 자동차를 출시할 수 있게 된다.
-> 이것이 유연하고 변경이 용이하다는 뜻이다.
역할과 구현을 분리: 역할과 구현으로 분리하면 세상이 단순해지고 유연해지며 변경도 편리해진다.
장점:
- 클라이언트는 대상의 역할(인터페이스)만 알면 된다.
- 클라이언트는 구현 대상의 내부 구조를 몰라도 된다.
- 클라이언트는 구현 대상의 내부구조가 변해도 구현 대상 자체가 변해도 영향을 받지 않는다.
 
 
4. 자바 언어
역할: 인터페이스
구현: 인터페이스를 구현한 클래스, 구현 객체  
핵심은 구현보다 역할이 먼저이다.   
자바 언어의 다형성: 오버라이딩  
즉 실행 시점에 인터페이스를 구현한 클래스의 메소드가 실행된다. 예로들면 MemberService를 클라이언트라 보고 MemberRepository를 서버로 본다면, 클라이언트는 MemberRepository만 보이고 Member Repository를 구현한 Memory나 JdbcRepository는 보이지 않는다. 이것이 핵심이다. 즉 MemberService, 클라이언트를 변경하지 않고, MemberRepository, 서버의 구현 기능을 유연하게 변경할 수 있다. 이것이 다형성의 본질이다.
 
 
5. 스프링과 객체지향  
이제 앞에서 배운 스프링과 연결시켜보자. 스프링은 이 다형성을 극대화 이용할 수 있게 해준다. 예로들어, 제어의 역전(IoC), 의존관계 주입(DI)은 이 다형성을 활둉해서 역할과 구현을 편리하게 다룰 수 있도록 지원한다. 즉 스프링이 바로 이 다형성을 지원해준다.  
</div>
</details>

<details>
<summary> 스프링 핵심 원리 이해1 - 예제 만들기</summary>
<div markdown="1">
</br>
스프링 핵심 원리 이해1 - 예제 만들기  
오늘은 순수 자바로 예제를 만들면서 스프링의 핵심 원리를 이해해보자!  


✔ 비즈니스 요구사항과 설계  
다음과 같이 비즈니스 요구사항이 주어졌을 때 설계를 해보자.  

📌 회원
회원을 가입하고 조회할 수 있다.  
회원은 일반과 VIP 두 가지 등급이 있다.  
회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)  
 
 </br>
📌 주문과 할인 정책  
회원은 상품을 주문할 수 있다.  
회원 등급에 따라 할인 정책을 적용할 수 있다.  
할인 정책은 모든 VIP는 1,000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경될 수 있다.)  
할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수도 있다. (미확정)  
요구사항을 보면 회원 데이터 부분이나 할인 정책 부분은 나중에 변경될 가능성이 있다.  
하지만 이러한 부분이 결정될 때까지 언제까지나 개발을 미룰 수는 없다!  


그러면 어떻게 해야할까?  

인터페이스를 만들고 구현체를 언제든지 갈아끼울 수 있도록 설계하면 된다!  
 </br>

⭐ 참고사항  
지금부터는 순수 자바로 개발한다. 하지만 기본 세팅을 편하게 하기 위해서 스프링 부트를 사용한다는 점 기억해두기!  
</br>

✔ 회원 도메인 설계  
먼저 회원 도메인의 협력 관계에 대해 설계를 해보자.  

회원 도메인 협력 관계  


회원 도메인 요구사항  
회원을 가입하고 조회할 수 있다.  

→ 회원 서비스에 회원가입, 회원조회 기능을 제공한다.  

회원은 일반과 VIP 두 가지 등급이 있다.  

→ 회원 도메인 계층에서 설계하도록 만든다.  

회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)  

→ 회원 저장소라는 인터페이스를 별도로 만들고, 세 가지로 나눈다.  

자바 코드로 메모리 회원 저장소라는 단순한 메모리를 만들어준다. (나중에 관련 부분이 확정되면 그때 교체해주면 된다!)  
회원 클래스 다이어그램  


협력 관계 설계를 다하면 이제 클래스 다이어그램을 만든다.  

MemberService 역할을 인터페이스로 만들고, 그 구현을 MemberServiceImpl에서 한다.  
MemberRepository 역할을 인터페이스로 만들고, 그 구현을 MemoryMemberRepository나 DbMemberRepository에서 한다.  
회원 객체 다이어그램  


실제 메모리 참조를 나타낸 것이다.  

클라이언트는 회원 서비스를 바라보고, 회원 서비스는 메모리 회원 저장소를 바라본다.  

✔ 회원 도메인 개발  
실습 내용은 github에 올려두었다. (core 폴더)  

✔ 회원 도메인 설계의 문제점  
위의 설계서 내용을 따라서 자바 코드로 실습까지 마쳤는데 문제점이 발견된다!  

OCP, DIP가 잘 지켜지고 있는지에 대한 의문을 갖게 된다.  

회원 저장소로 지금 굉장히 단순한 메모리인 MemoryMemberRepositoy를 사용하고 있는데, 아직 미확정인 부분이기 때문에 이를 변경할 때 OCP 원칙이 지켜질 수 있을까?  
의존 관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제가 있다.  
→ 이 문제들은 설계를 해가면서 해결해나갈 것이다.  

✔ 주문과 할인 도메인 설계  
주문 도메인 협력, 역할, 책임  
📌 주문과 할인 정책 요구사항  
회원은 상품을 주문할 수 있다.  
회원 등급에 따라 할인 정책을 적용할 수 있다.  
할인 정책은 모든 VIP는 1,000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경될 수 있다.)  
할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수도 있다.  


주문을 하는 동작은 다음과 같다.  

1. 주문 생성
클라이언트는 주문 서비스에 주문 생성을 요청한다.

→ 이때 회원id, 상품명, 상품 가격을 넘겨준다.

2. 회원 조회
할인을 위해서는 회원 등급이 필요하다.
그래서 주문 서비스는 회원 저장소에서 회원을 조회한다.

→ 회원 조회할 때 findById를 이용해서 조회한다.

3. 할인 적용
주문 서비스는 회원 등급에 따른 할인 여부를 할인 정책에 위임하다.

4. 주문 결과 반환
주문 서비스는 할인 결과를 포함한 주문 결과를 반환한다.

주문 도메인 전체


역할과 구현까지 그린 그림이다.

계~속 강조했던 역할과 구현을 분리해서 객체를 자유롭게 조립할 수 있게 설계했다.

이로 인해 미확정된 부분들에 대해 유연하게 대처할 수 있게 되었다.

주문 도메인 클래스 다이어그램


주문 도메인 정책을 클래스 다이어그램을 나타낸 그림이다.  
![image](https://user-images.githubusercontent.com/74589038/130325096-4e1bfbe8-7167-41a0-9d20-fbadb36c9acb.png)


주문 도메인 객체 다이어그램  
![image](https://user-images.githubusercontent.com/74589038/130325105-71823983-418e-46cd-84e4-3ce49aaba361.png)  

주문 도메인 객체 다이어그램 1

회원을 메모리에서 조회하고, 정액 할인 정책(고정 금액)을 지원해도 주문 서비스를 변경하지 않아도 된다.

주문 도메인 객체 다이어그램 2

회원을 메모리가 아닌 실제 DB에서 조회하고, 정률 할인 정책(주문 금액에 따라 % 할인)을 지원해도 주문 서비스를 변경하지 않아도 된다.

⭐ 각각 역할 간의 협력 관계는 그대로 유지된다.


</div>
</details>
 

<details>
<summary> 스프링 핵심 원리 이해2 - 객체 지향 원리 적용 </summary>
<div markdown="1">
 
 📌 새로운 할인 정책 개발 
 다형성 덕분에 새로운 정률 할인 정책 코드를 추가로 개발하는 것 자체는 아무 문제가 없음  
 
 📌 새로운 할인 정책 적용과 문제점  
 새로 개발한 정률 할인 정책을 적용하려고 하니 클라이언트 코드인 주문 서비스 구현체도 함께 변경해야함  
주문 서비스 클라이언트가 인터페이스인 DiscountPolicy 뿐만 아니라, 구체 클래스인  
FixDiscountPolicy 도 함께 의존 DIP 위반  
 
 📌 관심사의 분리  
애플리케이션을 하나의 공연으로 생각  
기존에는 클라이언트가 의존하는 서버 구현 객체를 직접 생성하고, 실행함  
비유를 하면 기존에는 남자 주인공 배우가 공연도 하고, 동시에 여자 주인공도 직접 초빙하는 다양한 책임을 가지고 있음  
공연을 구성하고, 담당 배우를 섭외하고, 지정하는 책임을 담당하는 별도의 공연 기획자가 나올 시점  
공연 기획자인 AppConfig가 등장  
AppConfig는 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임  
이제부터 클라이언트 객체는 자신의 역할을 실행하는 것만 집중, 권한이 줄어듬(책임이 명확해짐)  
 
 📌 AppConfig 리팩터링  
구성 정보에서 역할과 구현을 명확하게 분리  
역할이 잘 들어남  
중복 제거  
 
 📌  새로운 구조와 할인 정책 적용  
정액 할인 정책 정률% 할인 정책으로 변경  
AppConfig의 등장으로 애플리케이션이 크게 사용 영역과, 객체를 생성하고 구성(Configuration)하는 영역으로 분리  
할인 정책을 변경해도 AppConfig가 있는 구성 영역만 변경하면 됨, 사용 영역은 변경할 필요가 없음.  
클라이언트 코드인 주문 서비스 코드도 변경하지 않음  
 
 
 
</div>
</details>
