package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /*
    * @return 할인 대상 ㄱ ㅡㅁ액
    * */

    int discount(Member member, int price);
}
