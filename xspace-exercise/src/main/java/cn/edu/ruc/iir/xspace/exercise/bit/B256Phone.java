package cn.edu.ruc.iir.xspace.exercise.bit;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.exercise
 * @ClassName: B256Phone
 * @Description: [Bit](http://www.cnblogs.com/yellowb/p/3647442.html)
 * @author: taoyouxian
 * @date: Create in 2018-02-23 19:00
 **/
public class B256Phone {
    // 最大短信数量
    private final static int MSG_NUM = 256;
    // long占多少bit
    private final static int LONG_SIZE = 64;
    // 全1的long
    private final static long FULL_BUSY = 0xFFFFFFFFFFFFFFFFL;
    // 64个掩码
    private static long[] masks;
    // 4个long组成的位图
    private static long[] bitMap;

    static {
        bitMap = new long[MSG_NUM / LONG_SIZE];
        masks = new long[LONG_SIZE];
        // 初始化64个掩码
        long mask = 0x8000000000000000L;
        for (int i = 0; i < masks.length; i++) {
            masks[i] = mask;
            mask = mask >>> 1;
        }
    }

    public static int search() {
        for (int i = 0; i < bitMap.length; i++) {
            long val = bitMap[i];
            if ((val & FULL_BUSY) != FULL_BUSY) {
                int bitPos = findBitPos(val);
                // 注意要换算一下才能得到ID的下标
                return bitPos != -1 ? LONG_SIZE * i + bitPos : -1;
            }
        }
        return -1;
    }

    public static int findBitPos(long val) {
        for (int i = 0; i < masks.length; i++) {
            if ((val & masks[i]) == 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        bitMap[0] = 0xFFFFFFFFEFFFFFFFL; //测试数据, 第35个bit设置为0
        int pos = search();
        System.out.println(pos);
    }
}
