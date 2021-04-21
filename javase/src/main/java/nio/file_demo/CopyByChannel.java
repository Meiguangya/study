package nio.file_demo;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author water33
 * 复制文件，使用channel完成
 */
public class CopyByChannel {

    public static void main(String[] args) throws FileNotFoundException {
        //1.拿到输入输出
        FileInputStream fileInputStream = new FileInputStream(new File("1.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("1_copy.txt"));

        //2.拿到channel
        FileChannel source = fileInputStream.getChannel();
        FileChannel target = fileOutputStream.getChannel();

        //3.使用bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4.copy
        try{
            while(source.read(buffer)!=-1){
                buffer.flip();
                while(buffer.hasRemaining()){
                    target.write(buffer);
                }
                buffer.clear();
            }
        }catch (IOException e){
            System.out.println("拷贝失败.."+e.getMessage());
        }finally {
            close(target);
            close(source);
        }



    }


    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
