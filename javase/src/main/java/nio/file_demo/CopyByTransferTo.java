package nio.file_demo;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author water33
 */
public class CopyByTransferTo {

    public static void main(String[] args) throws IOException {
        //1.拿到输入输出
        FileInputStream fileInputStream = new FileInputStream(new File("1.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("1_copy.txt"));

        //2.拿到channel
        FileChannel source = fileInputStream.getChannel();
        FileChannel target = fileOutputStream.getChannel();

        source.transferTo(0, source.size(), target);

        System.out.println("over...");


    }


}
