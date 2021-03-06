package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration//설정정보
@ComponentScan(
        //지정하지 않으면 ComponentScan이 붙은 클래스의 패키지가 시작위치
        //프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기 때문에 프로젝트 시작 루트 위치에 두는 것이 좋다.
        //basePackages = "hello.core.member",//기본루트설정,탐색할 패키지의 시작위치 설정(안하면 모든 자바코드를 다 찾게된다)
        //basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)

)//스프링빈을 자동으로 끌어올린다.
public class AutoAppConfig {

    //수동빈이 자동빈을 오버라이딩해버린다. 수동 빈 등록이 우선권을 가진다
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
