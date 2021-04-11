package proxy_demo.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author water33
 */
public class ProxyFactory {

    public Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * public static Object newProxyInstance(ClassLoader loader,
     * Class<?>[] interfaces,
     * InvocationHandler h)
     * //1.ClassLoader loader 指定当前目标对象使用的类加载器
     * //2.Class<?>[] interfaces  指定当前目标对象实现的接口
     * //3.InvocationHandler h    事件处理，执行目标对象的方法时，会触发事件处理器方法
     * 会把当前执行的目标对象方法作为参数传入
     *
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("jdk代理对象开始...");
                        Object result = method.invoke(target, args);
                        System.out.println("jdk代理结束...");
                        return result;
                    }
                });
    }

}
