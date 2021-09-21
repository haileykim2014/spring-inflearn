package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        //JVM에 메모리가 계속 생성되서 올라간다.
        //고객요청이 많은데 계속 객체가 생성될것 -비효율
    }
}
