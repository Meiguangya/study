package proxy_demo.sta;

/**
 * @author water33
 */
public class Client {

    public static void main(String[] args) {

        TeacherDaoImpl impl = new TeacherDaoImpl();
        TeacherProxy teacherProxy = new TeacherProxy(impl);

        teacherProxy.teach();

    }
}
