package cn.edu.ruc.iir.xspace.tc.domain;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.tc.domain
 * @ClassName: AppInterference
 * @Description:
 * @author: tao
 * @date: Create in 2018-07-05 16:01
 **/
public class AppInterference {

    private String id1;
    private String id2;
    private String num;

    public AppInterference() {
    }

    public AppInterference(String id1, String id2, String num) {
        this.id1 = id1;
        this.id2 = id2;
        this.num = num;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "AppInterference{" +
                "id1='" + id1 + '\'' +
                ", id2='" + id2 + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
