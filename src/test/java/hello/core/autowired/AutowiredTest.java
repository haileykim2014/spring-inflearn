package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{

        @Autowired(required = false)//의존관계가 없다면 이 메소드 호출이 안된다
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1="+noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){ // 호출된다.
            System.out.println("noBean2="+noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){//값이없으면 empty
            System.out.println("noBean3="+noBean3);
        }
    }
}
