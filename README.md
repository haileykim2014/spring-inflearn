# 인프런 - 스프링 핵심 원리 기본편
</br>

## :pushpin: Intro

인프런 스프링 강의 내용을 정리하는 공간입니다.

</br>

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

주문 도메인 객체 다이어그램
주문 도메인 객체 다이어그램 1

회원을 메모리에서 조회하고, 정액 할인 정책(고정 금액)을 지원해도 주문 서비스를 변경하지 않아도 된다.

주문 도메인 객체 다이어그램 2

회원을 메모리가 아닌 실제 DB에서 조회하고, 정률 할인 정책(주문 금액에 따라 % 할인)을 지원해도 주문 서비스를 변경하지 않아도 된다.

⭐ 각각 역할 간의 협력 관계는 그대로 유지된다.


</div>
</details>
 
