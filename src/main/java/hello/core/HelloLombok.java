package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter //롬복이 게터세터를 자동으로 만들어준다
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args){
        HelloLombok helloLombok= new HelloLombok();
        helloLombok.setName("hailey");

        String name = helloLombok.getName();
        System.out.println("name="+name);

        System.out.println("helloLombok="+helloLombok);


    }


}
