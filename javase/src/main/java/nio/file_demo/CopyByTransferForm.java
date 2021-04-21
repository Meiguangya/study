package nio.file_demo;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author water33
 */
public class CopyByTransferForm {

    public static void main(String[] args) throws IOException {
        //1.拿到输入输出
        FileInputStream fileInputStream = new FileInputStream(new File("1.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("1_copy.txt"));

        //2.拿到channel
        FileChannel source = fileInputStream.getChannel();
        FileChannel target = fileOutputStream.getChannel();

        //3.
        target.transferFrom(source,0,source.size());

        //4.close

        System.out.println("over...");
    }

}
