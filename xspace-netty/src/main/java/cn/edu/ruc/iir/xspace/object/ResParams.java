package cn.edu.ruc.iir.xspace.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResParams
        implements Serializable {

    private String action;
    private String result;
    private Object data;

    public ResParams(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResParams{" +
                "action='" + action + '\'' +
                ", result='" + result + '\'' +
                ", data=" + data +
                '}';
    }
}
