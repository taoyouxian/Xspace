package cn.edu.ruc.iir.xspace.pixels;

import java.util.Scanner;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.pixels
 * @ClassName: TestXiaoMi
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2019-02-28 20:05
 **/
public class TestXiaoMi {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            // please write your code here
            String[] figure = line.split(" ");
            long min = Long.valueOf(figure[0]);
            long max = Long.valueOf(figure[1]);
            max = (max - min) % 15 + min;

            long sum = 0L;
            while (true) {
                sum += min;
                min++;
                if (min > max)
                    break;
            }
            System.out.println(sum % 15);

            // System.out.println("answer");
        }
    }

}
