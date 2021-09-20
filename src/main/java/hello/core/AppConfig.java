package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    //AppConfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입해준다.

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());// 생성자를 통해 객체가 들어간다. ->생성자주입 DI 의존관계주입
    }

    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
