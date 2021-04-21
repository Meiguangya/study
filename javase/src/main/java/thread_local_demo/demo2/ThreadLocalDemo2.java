package thread_local_demo.demo2;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author water33
 */
public class ThreadLocalDemo2 {


    public static void main(String[] args) {
        User u = new User("张三");
        new ThreadLocalDemo2().method1(u);

    }

    public void method1(User u){
        UserContextHolder.userThreadLocal.set(u);
        System.out.println("method1.."+u.toString());
        method2();
        UserContextHolder.userThreadLocal.remove();
    }

    public void method2(){
        User u = UserContextHolder.userThreadLocal.get();
        System.out.println("method2..."+u);
        method3();
    }

    public void method3(){
        User u = UserContextHolder.userThreadLocal.get();
        System.out.println("method3..."+u);
    }


    static class UserContextHolder{
       public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
    }

    @AllArgsConstructor
    @Data
    static class User{
        String name;
    }
}
