package hello.core.scan.filter;

import net.minidev.json.writer.BeansMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        Assertions.assertThat(beanA).isNotNull();//값이 조회된다.

        assertThrows(
                NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("beanB",BeanB.class));

    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(/*type = FilterType.ANNOTATION,*/ classes= MyIncludeComponent.class),//스프링빈에 등록
            excludeFilters = @Filter(/*type = FilterType.ANNOTATION, */classes = MyExcludeComponent.class)//스프링빈에 등록되지않는다
            )
    static class ComponentFilterAppConfig{

    }
}
