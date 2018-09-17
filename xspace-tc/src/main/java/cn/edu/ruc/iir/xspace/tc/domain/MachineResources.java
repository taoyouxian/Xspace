package cn.edu.ruc.iir.xspace.tc.domain;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.tc.domain
 * @ClassName: MachineResources
 * @Description:
 * @author: tao
 * @date: Create in 2018-07-05 15:59
 **/
public class MachineResources {

    private String id;
    private String cpu;
    private String mem;
    private String disk;
    private String P;
    private String M;
    private String PM;

    public MachineResources() {
    }

    public MachineResources(String id, String cpu, String mem, String disk, String p, String m, String PM) {
        this.id = id;
        this.cpu = cpu;
        this.mem = mem;
        this.disk = disk;
        P = p;
        M = m;
        this.PM = PM;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getP() {
        return P;
    }

    public void setP(String p) {
        P = p;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    public String getPM() {
        return PM;
    }

    public void setPM(String PM) {
        this.PM = PM;
    }

    @Override
    public String toString() {
        return "MachineResources{" +
                "id='" + id + '\'' +
                ", cpu='" + cpu + '\'' +
                ", mem='" + mem + '\'' +
                ", disk='" + disk + '\'' +
                ", P='" + P + '\'' +
                ", M='" + M + '\'' +
                ", PM='" + PM + '\'' +
                '}';
    }
}
