package proxy_demo.jdk;

/**
 * @author water33
 */
public class TeacherDaoImpl implements TeacherDao {

    @Override
    public void teach() {
        System.out.println("teach...");
    }

    @Override
    public void work(String msg) {
        System.out.println("i'm working " + msg);
    }

    @Override
    public int sum(int a, int b) {
        return a+b;
    }

}
