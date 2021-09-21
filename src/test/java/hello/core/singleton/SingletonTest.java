package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1.조회: 호출 할때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2.조회 : 호출 할떄마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른것을 확인
        System.out.println("memberService1="+memberService1);
        System.out.println("memberService2"+memberService1);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
        //JVM에 메모리가 계속 생성되서 올라간다.
        //고객요청이 많은데 계속 객체가 생성될것 -비효율
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1="+singletonService1);//같은 인스턴스 객체가 출력된다.
        System.out.println("singletonService2="+singletonService2);//같은 인스턴스 객체가 출력된다.

        assertThat(singletonService1).isSameAs(singletonService2);
        //isSameAs 객체참조비교
        //equal 문자열비교
    }
}
