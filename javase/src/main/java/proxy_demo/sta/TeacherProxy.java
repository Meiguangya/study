package proxy_demo.sta;

/**
 * @author water33
 */
public class TeacherProxy implements TeacherDao{

    private TeacherDao target;

    public TeacherProxy(TeacherDao target){
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理...");

        target.teach();

        System.out.println("代理结束...");
    }



}
