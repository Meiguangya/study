package thread_local_demo.demo4;

/**
 * @author water33
 */
public class ThreadLocalDemo4 {

    public static void main(String[] args) {
        new ThreadLocalDemo4().method1(4);
    }

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static ThreadLocal<Integer> getThreadLocalHandler() {
        return threadLocal;
    }

    public void method1(int i) {
        ThreadLocal<Integer> handler = ThreadLocalDemo4.getThreadLocalHandler();
        handler.set(i);
        method2();
    }

    public void method2() {
        ThreadLocal<Integer> handler = ThreadLocalDemo4.getThreadLocalHandler();
        Integer i = handler.get();
        System.out.println(i);
        handler.remove();
    }

}
