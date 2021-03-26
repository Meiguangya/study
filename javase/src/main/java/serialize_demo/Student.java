package serialize_demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Data
@Slf4j
public class Student implements Serializable{

    //如果不手动生成序列号  每次类改变  serialVersionUID就会变，那么之前序列化的字节文件就会反序列化失败
    private static final long serialVersionUID = 4595987943109733657L;
    private String name;
    private Integer age;
    private String studentNum;



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serialize();
        //deserialize();
    }



    public static void serialize() throws IOException {

        Student student = new Student();
        student.setName("CodeSheep");
        student.setAge( 18 );
        student.setStudentNum("123");

        ObjectOutputStream objectOutputStream =
                new ObjectOutputStream( new FileOutputStream( new File("student.txt") ) );
        objectOutputStream.writeObject( student );
        objectOutputStream.close();

        System.out.println("序列化成功！已经生成student.txt文件");
        System.out.println("==============================================");
    }


    public static void deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream =
                new ObjectInputStream( new FileInputStream( new File("student.txt") ) );
        Student student = (Student) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("反序列化结果为：");
        System.out.println( student );
    }

}
