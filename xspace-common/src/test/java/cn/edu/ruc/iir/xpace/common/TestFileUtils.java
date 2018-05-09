package cn.edu.ruc.iir.xpace.common;


import org.junit.Test;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xpace.common
 * @ClassName: TestFileUtils
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-04-25 10:57
 **/
public class TestFileUtils {

    @Test
    public void testReadFileToString() {
        String fileName = "D:\\blog\\text\\导出.txt";
        String content = FileUtils.readFileToString(fileName);
        System.out.println(content);
    }
}
