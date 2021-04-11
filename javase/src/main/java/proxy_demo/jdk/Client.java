package proxy_demo.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author water33
 */
public class Client {

    public static void main(String[] args) {

        //被代理对象
        TeacherDao teacherDao = new TeacherDaoImpl();

        TeacherDao proxyInstance = (TeacherDao) new ProxyFactory(teacherDao).getProxyInstance();

//        System.out.println("proxyInstance="+proxyInstance);
//        System.out.println("proxyInstance.getClass="+proxyInstance.getClass());

       // proxyInstance.teach();
//        proxyInstance.work("我是proxyInstance");

        int sum = proxyInstance.sum(1,5);
        System.out.println(sum);

        /*
         * TeacherDao proxyInstance2 = (TeacherDao) Proxy.newProxyInstance(TeacherDao.class.getClassLoader(),
                new Class[]{TeacherDao.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("jdk...");
                        Object result = method.invoke(new TeacherDaoImpl(), args);
                        System.out.println("jdk...");
                        return result;
                    }
                });

        proxyInstance2.teach();
         */

    }

}
