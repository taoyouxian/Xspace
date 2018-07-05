package cn.edu.ruc.iir.xspace.tc.util;

import cn.edu.ruc.iir.xspace.tc.domain.AppInterference;
import cn.edu.ruc.iir.xspace.tc.domain.AppResources;
import cn.edu.ruc.iir.xspace.tc.domain.InstanceDeploy;
import cn.edu.ruc.iir.xspace.tc.domain.MachineResources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.tc.util
 * @ClassName: CsvUtil
 * @Description:
 * @author: tao
 * @date: Create in 2018-07-05 16:04
 **/
public class CsvUtil {

    static String resDir = "/home/tao/software/data/tianchi/";
    static String AppInterference_PATH = resDir + "scheduling_preliminary_app_interference_20180606.csv";
    static String AppResources_PATH = resDir + "scheduling_preliminary_app_resources_20180606.csv";
    static String InstanceDeploy_PATH = resDir + "scheduling_preliminary_instance_deploy_20180606.csv";
    static String MachineResources_PATH = resDir + "scheduling_preliminary_machine_resources_20180606.csv";
    static String submitFile = resDir + "submit_20180705_151821.csv";

    public static List<AppInterference> getAppInterference() {
        List<AppInterference> appInterferenceList = new ArrayList<>();
        try (BufferedReader queryReader = new BufferedReader(new FileReader(AppInterference_PATH))) {
            String line;
            String[] split;
            while ((line = queryReader.readLine()) != null) {
                split = line.split(",");
                AppInterference appInterference = new AppInterference(split[0], split[1], split[2]);
                appInterferenceList.add(appInterference);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appInterferenceList;
    }

    public static List<AppResources> getAppResources() {
        List<AppResources> appResourcesList = new ArrayList<>();
        try (BufferedReader queryReader = new BufferedReader(new FileReader(AppResources_PATH))) {
            String line;
            String[] split;
            while ((line = queryReader.readLine()) != null) {
                split = line.split(",");
                AppResources appResources = new AppResources(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
                appResourcesList.add(appResources);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return appResourcesList;
    }

    public static List<InstanceDeploy> getInstanceDeploy() {
        List<InstanceDeploy> instanceDeployList = new ArrayList<>();
        try (BufferedReader queryReader = new BufferedReader(new FileReader(InstanceDeploy_PATH))) {
            String line;
            String[] split;
            while ((line = queryReader.readLine()) != null) {
                split = line.split(",");
                InstanceDeploy instanceDeploy = new InstanceDeploy(split[0], split[1], split[2]);
                instanceDeployList.add(instanceDeploy);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return instanceDeployList;
    }

    public static List<MachineResources> getMachineResources() {
        List<MachineResources> machineResourcesList = new ArrayList<>();
        try (BufferedReader queryReader = new BufferedReader(new FileReader(MachineResources_PATH))) {
            String line;
            String[] split;
            while ((line = queryReader.readLine()) != null) {
                split = line.split(",");
                MachineResources machineResources = new MachineResources(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
                machineResourcesList.add(machineResources);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return machineResourcesList;
    }

}
