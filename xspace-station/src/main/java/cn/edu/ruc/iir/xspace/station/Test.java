package cn.edu.ruc.iir.xspace.station;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.station
 * @ClassName: Test
 * @Description:
 * @author: tao
 * @date: Create in 2019-03-19 09:23
 **/
public class Test {
    public static void main(String[] args) {
        String s1 = "hello world";
        String s2 = new String("hello world");
        String s3 = "hello world";
        String s4 = new String("hello world");

        StringBuffer sb = new StringBuffer();
        StringBuilder sb1 = new StringBuilder();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s4);
        System.out.println(s2.equals(s4));
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3);
        System.out.println(s4);
    }
}
