package cn.edu.ruc.iir.xpace.common.cost;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xpace.common.cost
 * @ClassName: Relation
 * @Description:
 * @author: tao
 * @date: Create in 2019-01-22 15:30
 **/
public class Relation {
    private int id;
    private int fromId;
    private int toId;
    private double cost;
    private boolean used;

    public Relation(int id, int fromId, int toId, double cost) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.cost = cost;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    public double getCost() {
        return cost;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", cost=" + cost +
                ", used=" + used +
                '}';
    }

    public String toString2() {
        return "Relation{" +
                "id=" + id +
                ", toId=" + toId +
                ", fromId=" + fromId +
                ", cost=" + cost +
                ", used=" + used +
                '}';
    }
}
