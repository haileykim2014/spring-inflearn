package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10,000원 주문
        int userAPrice = statefulService1.order("userA",10000);
        //ThreadB : B사용자가 20,000원 주문
        int userBPrice =statefulService2.order("userA",20000);
        //ThreadA :사용자A가 주문 금액 조회
       //int price = statefulService1.getPrice();
        System.out.println("price="+userAPrice);//10,000원을 기대했지만 20,000원이 출력된다. 같은 인스턴스를 쓰므로..
        //1만원이 2만원으로 덮어씌워진다.

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);


    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}