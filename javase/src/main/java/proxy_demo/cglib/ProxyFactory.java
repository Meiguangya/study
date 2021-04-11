package proxy_demo.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author water33
 */
public class ProxyFactory implements MethodInterceptor{

    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }

    //获取代理对象
    public Object getProxyInstance(){
        //1。创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.设置回调函数
        enhancer.setCallback(this);
        //4.创建子类对象 即代理对象
        return enhancer.create();
    }

    //重写intercept 会调用目标对象的方法
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理生成");
        Object result = method.invoke(target, args);
        System.out.println("cglib代理结束");
        return result;
    }

}
