package cn.edu.ruc.iir.xspace.blog;

import java.io.Serializable;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.blog
 * @ClassName: User
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-17 19:30
 **/
public class User implements Serializable {

    private int id;

    private String name;

    private String cardNo;

    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", cardNo='"
                + cardNo + '\'' + ", description='" + description + '\'' + '}';
    }
}
