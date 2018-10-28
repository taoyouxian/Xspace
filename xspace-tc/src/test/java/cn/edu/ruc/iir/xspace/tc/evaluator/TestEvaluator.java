package cn.edu.ruc.iir.xspace.tc.evaluator;

import cn.edu.ruc.iir.xspace.tc.domain.AppInterference;
import cn.edu.ruc.iir.xspace.tc.domain.AppResources;
import cn.edu.ruc.iir.xspace.tc.domain.InstanceDeploy;
import cn.edu.ruc.iir.xspace.tc.domain.MachineResources;
import cn.edu.ruc.iir.xspace.tc.util.CsvUtil;
import org.junit.Test;

import java.util.List;

public class TestEvaluator {

    @Test
    public void testGetQuery() {
        List<AppInterference> appInterferenceList = CsvUtil.getAppInterference();
        List<AppResources> appResourcesList = CsvUtil.getAppResources();
        List<InstanceDeploy> instanceDeployList = CsvUtil.getInstanceDeploy();
        List<MachineResources> machineResourcesList = CsvUtil.getMachineResources();


    }

}
