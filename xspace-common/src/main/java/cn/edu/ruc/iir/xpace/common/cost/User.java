package cn.edu.ruc.iir.xpace.common.cost;

import java.util.Arrays;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xpace.common.cost
 * @ClassName: User
 * @Description:
 * @author: tao
 * @date: Create in 2019-01-22 15:26
 **/
public class User {
    private int id;
    private int userId;
    private String username;
    private double cost;
    private double average;
    private int[] users;

    public User(int id, int userId, String username, double cost, int[] users) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.cost = cost;
        this.average = cost / (users.length + 1);
        this.users = users;
    }

    public User(int id, int userId, String username, String cost, int[] users) {
        this(id, userId, username, Double.valueOf(cost), users);
    }

    public int[] getUsers() {
        return users;
    }

    public int getUserId() {
        return userId;
    }

    public double getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", cost=" + cost +
                ", average=" + average +
                ", users=" + Arrays.toString(users) +
                '}';
    }
}
