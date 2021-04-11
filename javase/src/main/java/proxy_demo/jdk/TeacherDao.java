package proxy_demo.jdk;

public interface TeacherDao {

    void teach();

    void work(String msg);

    int sum(int a,int b);

    default void test(){
        System.out.println("default");
    }

}
