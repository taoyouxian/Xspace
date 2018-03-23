package cn.edu.ruc.iir.xspace.workload.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.workload.util
 * @ClassName: HttpUtilTest
 * @Description: Test http
 * @author: taoyouxian
 * @date: Create in 2018-01-15 23:08
 **/
public class HttpUtilTest {


    @Test
    public void HttpGetTest() {
        Object o = HttpUtil.HttpGet(HttpSettings.PRESTO_QUERY);
        JSONArray jsonArray = JSON.parseArray(o.toString());
        String queryId = null;
        String query = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.size() == 8) {
                queryId = jsonObject.get("queryId").toString();
                query = jsonObject.get("query").toString();
                System.out.println(queryId + "\t" + i + "\t" + query);
//                Query q = new Query(queryId, String.valueOf(i), query);
                // Parser

            }
        }
//        System.out.println(o.toString());
    }
}
