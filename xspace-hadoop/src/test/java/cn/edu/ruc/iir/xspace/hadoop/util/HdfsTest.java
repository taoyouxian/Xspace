package cn.edu.ruc.iir.xspace.hadoop.util;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.rainbow.web
 * @ClassName: HdfsTest
 * @Description: Test HDFS util
 * @author: Tao
 * @date: Create in 2017-09-13 9:14
 */
public class HdfsTest {
    private final Logger log = Logger.getLogger(HdfsTest.class.getName());
    HdfsUtil hUtil = HdfsUtil.getHdfsUtil();

    String filePath = "/6_pixels.pxl";
    String directoryPath = "/pixels/v1";

    @Test
    public void isTableExistsTest() {
        try {
            Assert.assertEquals(true, hUtil.isTableExists(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listAllTest() {
        try {
            List<String> listFile = hUtil.listAll(directoryPath);
            Assert.assertEquals(true, listFile.size() > 0);
            log.info("Size: " + listFile.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
