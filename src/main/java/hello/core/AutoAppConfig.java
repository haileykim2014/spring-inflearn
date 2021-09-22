package hello.core;

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

}
