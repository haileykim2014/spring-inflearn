package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

import java.security.PublicKey;

public class AppConfig {
    //AppConfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입해준다.
    //역할과 구현클래스가 한눈에 들어오게 바꾸기
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());// 생성자를 통해 객체가 들어간다. ->생성자주입 DI 의존관계주입
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
    //    return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
