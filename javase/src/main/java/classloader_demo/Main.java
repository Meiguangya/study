package classloader_demo;

/**
 * @author water33
 * <p>
 * <p> Java反射中，Class.forName和ClassLoader的区别
 * Class.forName得到的class是已经初始化完成的
 * Classloder.loaderClass得到的class是还没有链接的
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {

        System.out.println(ClassLoader.getSystemClassLoader().getClass().getSimpleName());


        Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("classloader_demo.ClassForNameDemo");
        System.out.println(clazz.getSimpleName());
        System.out.println("end...");

        Class.forName("classloader_demo.ClassForNameDemo");
        System.out.println("end...");

        Class<?> clazz2 = Class.forName("classloader_demo.ClassForNameDemo", false, ClassLoader.getSystemClassLoader());
        System.out.println(clazz2.getSimpleName());
        System.out.println("end...");
    }

}
