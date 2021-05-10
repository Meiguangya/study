package classloader_demo;

/**
 * @author water33
 */
public class ClassForNameDemo {

    static int i = 10;

    static {
        System.out.println("我在使用Class.forName的时候被初始化了");
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        Class<?> clazz = Class.forName("classloader_demo.ClassForNameDemo");
//
//        ClassLoader classLoader = clazz.getClassLoader();
//
//        //AppClassLoader
//        System.out.println(classLoader.getClass().getSimpleName());
//
//        ClassLoader curr = classLoader;
//
//        System.out.println(classLoader.getParent().getClass().getSimpleName());
//
//        System.out.println(classLoader.getParent().getParent().getClass().getSimpleName());
       // ClassForNameDemo c = new ClassForNameDemo();

        System.out.println(ClassForNameDemo.i);


    }


}
