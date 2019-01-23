package cn.edu.ruc.iir.xpace.common.cost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xpace.common.cost
 * @ClassName: SplitTable
 * @Description: Split the cost in a group
 * @author: tao
 * @date: Create in 2019-01-22 15:24
 **/
public class SplitTable {

    static int num = 0;
    static String costFile = "/home/tao/cost.txt";
    static String splitFile = "/home/tao/split.txt";
    static String giveFile = "/home/tao/give.txt";
    static String[] includeUser = null;
    static String[] includeUsername = null;

    static List<User> userList = new ArrayList<>();
    static List<Relation> relationList = new ArrayList<>();
    static List<Relation> costSplitList = new ArrayList<>();

    public static void main(String[] args) {
        // Example: jgd, wyx, tyx, hyz, ty, cyj, gyh, xh, lby

        // register
        register();
        // split
        split();
        // result
        result();
        // give
        give();
    }

    private static void give() {
        try (BufferedWriter costWriter = new BufferedWriter(new FileWriter(giveFile))) {
            costWriter.write("split id,fromUser,toUser,give to(¥)\n");
            System.out.println("Split List: ");
            int i = 0;
            for (int toId = 0; toId < includeUser.length; toId++) {
                for (Relation relation : costSplitList) {
                    if (relation.getToId() == Integer.valueOf(toId)) {
                        System.out.println(relation.toString2());
                        costWriter.write(i + "," + includeUsername[relation.getToId()] + "," + includeUsername[relation.getFromId()] + "," + relation.getCost() + "\n");
                        if (i % 10 == 0) {
                            costWriter.flush();
                        }
                        i++;
                    }
                }
            }
            System.out.println("=======================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void result() {
        try (BufferedWriter costWriter = new BufferedWriter(new FileWriter(splitFile))) {
            // username, cost, users
            int num = 0;
            for (int i = 0; i < relationList.size(); i++) {
                Relation r = relationList.get(i);
                if (!r.isUsed()) {
                    r.setUsed(true);
                    double cost = r.getCost();
                    for (Relation r1 : relationList) {
                        if (!r1.isUsed()) {
                            // look a.from = b.to && a.to = b.from
                            if (r.getFromId() == r1.getToId() && r.getToId() == r1.getFromId()) {
                                r1.setUsed(true);
                                cost -= r1.getCost();
                            }
                            // look a.from = b.from && a.to = b.to
                            else if (r.getFromId() == r1.getFromId() && r1.getToId() == r.getToId()) {
                                r1.setUsed(true);
                                cost += r1.getCost();
                            }
                        }
                    }
                    Relation r2 = new Relation(num++, r.getFromId(), r.getToId(), cost);
                    costSplitList.add(r2);
                }
            }

            costWriter.write("split id,fromUser,toUser,pay for(¥)\n");
            System.out.println("Split List: ");
            int i = 0;
            for (Relation relation : costSplitList) {
                System.out.println(relation.toString());
                costWriter.write(i + "," + includeUsername[relation.getToId()] + "," + includeUsername[relation.getFromId()] + "," + relation.getCost() + "\n");
                if (i % 10 == 0) {
                    costWriter.flush();
                }
                i++;

            }
            System.out.println("=======================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void split() {
        int relationId = 0;
        for (int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            int[] users = u.getUsers();

            for (int userId : users) {
                Relation relation = new Relation(relationId, u.getUserId(), userId, u.getAverage());
                relationList.add(relation);
                relationId++;
            }
        }

        System.out.println("Relation List: ");
        for (Relation r : relationList) {
            System.out.println(r.toString());
        }
        System.out.println("=======================");
    }

    private static void register() {
        try (BufferedReader costReader = new BufferedReader(new FileReader(costFile))) {
            String line;
            line = costReader.readLine();
            includeUser = line.split(",");
            line = costReader.readLine();
            includeUsername = line.split(",");

            // username, cost, users
            while ((line = costReader.readLine()) != null) {
                String[] splits = line.split(" ");
                String[] users = splits[2].split(",");

                int[] userId = new int[users.length];
                for (int i = 0; i < users.length; i++) {
                    int useId = findUserIdByName(users[i]);
                    if (useId != -1)
                        userId[i] = useId;
                    else
                        throw new Exception("Find user error in line " + (num + 1));
                }

                int fromId = findUserIdByName(splits[0]);
                User u = new User(num++, fromId, splits[0], splits[1], userId);
                userList.add(u);
            }

            System.out.println("Cost List: ");
            for (User u : userList) {
                System.out.println(u.toString());
            }
            System.out.println("=======================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int findUserIdByName(String user) {
        for (int i = 0; i < includeUser.length; i++) {
            if (user.equals(includeUser[i]))
                return i;
        }
        return -1;
    }

}
