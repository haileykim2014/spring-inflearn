package hello.core.singleton;

public class SingletonService {
    //자기 자신을 내부에 private으로 하나 가지고있다.
    //static으로 가지고있기 때문에 하나만 존재하게 된다.
    //외부에서 new SingletonService()를 할 수 없다.
    private static final SingletonService instance = new SingletonService();
    //instance를 꺼낼 수있는것은 아래 getInstance()에 의해서만 꺼낼 수 있다. 항상 같은 인스턴스 반환
    public static SingletonService getInstance(){
        return instance;
    }
    //생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
