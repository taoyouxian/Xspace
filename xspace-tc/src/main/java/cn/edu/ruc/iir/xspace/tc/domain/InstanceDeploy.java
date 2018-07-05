package cn.edu.ruc.iir.xspace.tc.domain;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.tc.domain
 * @ClassName: InstanceDeploy
 * @Description:
 * @author: tao
 * @date: Create in 2018-07-05 16:03
 **/
public class InstanceDeploy {

    private String id;
    private String app;
    private String machine;

    public InstanceDeploy() {
    }

    public InstanceDeploy(String id, String app, String machine) {
        this.id = id;
        this.app = app;
        this.machine = machine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    @Override
    public String toString() {
        return "InstanceDeploy{" +
                "id='" + id + '\'' +
                ", app='" + app + '\'' +
                ", machine='" + machine + '\'' +
                '}';
    }
}
