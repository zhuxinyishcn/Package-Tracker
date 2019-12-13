package edu.unl.cse.csce361.package_tracker;


import edu.unl.cse.csce361.package_tracker.logic.AdminLogic;
import edu.unl.cse.csce361.package_tracker.logic.UserLogic;
import org.junit.Test;

public class LogicTests {

    @Test
    public void TestAdminCancelPackage(){
        String exTrackingNumber = "5c0ffcbe-4e51-4e53-86d8-218cf7ab14f6";//packageid=3
        AdminLogic.cancelPackage(exTrackingNumber);
    }
    @Test
    public void TestAdminHoldPackage(){
        String exTrackingNumber = "5c0ffcbe-4e51-4e53-86d8-218cf7ab14f6";//packageid=3
        AdminLogic.holdPackage(exTrackingNumber);
    }
    @Test
    public void TestAdminEstimatePackagearriveTime(){
        String exTrackingNumber = "5c0ffcbe-4e51-4e53-86d8-218cf7ab14f6";//packageid=3
        UserLogic.estimatePackage(exTrackingNumber);
    }

}
