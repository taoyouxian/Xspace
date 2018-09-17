package cn.edu.ruc.iir.xspace.exercise.cashe;

import java.nio.ByteBuffer;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.exercise.cashe
 * @ClassName: CasheMap
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-03-02 11:06
 **/
public class CasheMap {
    private static CasheMap instance = null;
    private static ByteBuffer buffer;

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public static CasheMap Instance() {
        if (instance == null) {
            buffer = ByteBuffer.allocateDirect(10);
            instance = new CasheMap();
        }
        return instance;
    }

    public static void main(String[] args) {
        CasheMap c = CasheMap.Instance();
        c.buffer.put(new byte[]{1, 2, 3, 4});
        System.out.println("刚写完数据 " + c.buffer);

        buffer.flip();
        System.out.println("flip之后 " +buffer);
        byte[] target = new byte[buffer.limit()];
        buffer.get(target);//自动读取target.length个数据
        for(byte b : target){
            System.out.println(b);
        }
        System.out.println("读取完数组 " +buffer);
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(++i);
        }

    }
}
