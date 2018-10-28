package cn.edu.ruc.iir.xspace.githubapi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.githubapi.util
 * @ClassName: HttpUtil
 * @Description: deal with post & get request
 * @author: Tao
 * @date: Create in 2017-09-30 8:49
 **/
public class HttpUtil {

    public static Object acHttpPost(String aUrl, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(aUrl);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
            conn.setRequestProperty("Authorization",
                    "token token_needed");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("POST error！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String aUrl = "https://api.github.com/repos/taoyouxian/taoyouxian.github.io/issues";
        String param = "{\"title\":\"Hello World | gcoder.tao的博客\",\"labels\":[\"gitment\",\"Thu Apr 05 2018 10:30:51 GMT+0800\"],\"body\":\"https://taoyouxian.github.io/2018/04/05/hello-world/\\n\\n\"}";
        String res = (String) HttpUtil.acHttpPost(aUrl, param);
        System.out.println(res);
    }
}
