package cn.edu.ruc.iir.xspace.station.directbuffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.station.directbuffer
 * @ClassName: Main
 * @Description:
 * @author: tao
 * @date: Create in 2019-03-15 21:24
 **/
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        directIO();
        mmapedIO();
    }

    private static void directIO() throws FileNotFoundException, InterruptedException {
        File data = new File("/tmp/data.txt");
        FileChannel fileChannel = new RandomAccessFile(data, "rw").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4 * 1024 * 1024);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(1000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        fileChannel.read(buffer);
                        buffer.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void mmapedIO() throws IOException, InterruptedException {
        File data = new File("/tmp/data.txt");
        data.createNewFile();
        FileChannel fileChannel = new RandomAccessFile(data, "rw").getChannel();
        for (int i = 0; i < 1000; i++) {
            fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024 * 1024 * 1024);
        }
        System.out.println("Map finished");
        new CountDownLatch(1).await();
    }
}
