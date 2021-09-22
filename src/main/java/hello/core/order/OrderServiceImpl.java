package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//스프링컨테이너 1.스프링빈 등록하기
//2. 연간관계 자동주입

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired private MemberRepository memberRepository;//관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();//DIP위반, 구체클래스에 의존하고있음
    @Autowired private DiscountPolicy discountPolicy;//생성자에 의해 값이 할당된다.인터페이스에만 의존하고 구체적인 클래스에 의존하지않는다, 어떤repository가 넣어질지 모르는상태

//    @Autowired(required = false)//false를 주면 선택값이된다.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository"+memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy"+discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired//생성자에서 여러 의존관계도 한번에 주입받을 수 있다. 생성자가 하나있다면 Autowired 작동적용
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        //생성자 안에있는것은 무조건 값을 넣어줘야하는 의미
//        System.out.println("1.OrderServiceImpl.OrderServiceImpl");
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;//fixed 일지 rate일지 알 수 없음
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);//회원정보조회
        int discountPrice = discountPolicy.discount(member,itemPrice);//할인에 변경이생기면 할인만 고치면된다.
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
