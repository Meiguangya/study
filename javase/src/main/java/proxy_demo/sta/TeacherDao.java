package proxy_demo.sta;

public interface TeacherDao {

    void teach();

    default void work(){
        System.out.println("work");
    }

}
