package cn.edu.ruc.iir.xspace.exercise.cashe;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.exercise.cashe
 * @ClassName: TestCashe
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-03-02 13:38
 **/
public class TestCashe {

    public static void main(String[] args) {
        CasheMap c = CasheMap.Instance();
        c.getBuffer().flip();
        System.out.println("flip之后 " + c.getBuffer());
        byte[] target = new byte[c.getBuffer().limit()];
        c.getBuffer().get(target);//自动读取target.length个数据
        for (byte b : target) {
            System.out.println(b);
        }
        System.out.println("读取完数组 " + c.getBuffer());

    }
}
