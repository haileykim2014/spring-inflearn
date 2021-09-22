package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//스프링컨테이너 1.스프링빈 등록하기
//2. 연간관계 자동주입

@Component
@RequiredArgsConstructor//final붙은 필드의 파라미터생성자를 만들어준다
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;//관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
    private final DiscountPolicy discountPolicy;//생성자에 의해 값이 할당된다.인터페이스에만 의존하고 구체적인 클래스에 의존하지않는다, 어떤repository가 넣어질지 모르는상태
    //final를 넣으면 생성자에서 값을 넣어주도록 한다.

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
