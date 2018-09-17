package cn.edu.ruc.iir.xspace.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Response
        implements Serializable {
    private String action;
    private Map<String, String> params = new HashMap<>();

    public Response(String action) {
        this.action = action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public static Response parse(String reqParms) {

        String[] splits = reqParms.split("==");
        Response res = new Response(splits[0]);
        if (splits.length > 1) {
            String[] params = splits[1].split("&");
            for (String param : params) {
                String[] kv = param.split("=");
                res.params.put(kv[0], kv[1]);
            }
        }
        return res;
    }

    public String getAction() {
        return this.action;
    }

    public void setParam(String key, String value) {
        this.params.put(key, value);
    }

    public String getParam(String key) {
        return this.params.get(key);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.action);
        builder.append("==");
        for (String key : this.params.keySet()) {
            builder.append(key).append("=").append(this.params.get(key)).append("&");
        }
        return builder.toString();
    }
}
