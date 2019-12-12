package edu.unl.cse.csce361.package_tracker.frontend;

import edu.unl.cse.csce361.package_tracker.backend.Sender;
import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

import java.util.Scanner;

public class UserInterFace {
    private static final logicFacade logic = logicFacade.getInstance();
    private static Scanner scnr = new Scanner(System.in);
    private static String userName;
    private static Sender userFile;

    public static String getUserName () {
        return userName;
    }
    
    public static Sender getSender() {
    	return userFile;
    }

    public static void setUserName (String userName) {
        UserInterFace.userName = userName;
    }
    
    public static void setSender (String userFile) {
    	UserInterFace.userFile = logic.searchSender(userFile);
    }

    public static void main (String[] args) {
    	logic.addDrone();
        boolean programOn = true;
        while (programOn) {
            String inputMain;
            Printer.printMainMenu();
            inputMain = scnr.nextLine();
            switch (inputMain) {
                case "1":
                    // **ADMIN**
                    Menu.adminMenu();
                    break;
                case "2":
                    // **USER**
                    Printer.printAskUserName();
                    userName = scnr.nextLine();
                    String status = logic.checkUser(userName);
                    if ("VIP".equals(status)) {
                    	setSender(userName);
                        Menu.vipMenu();
                    } else if ("Active".equals(status)) {
                    	setSender(userName);
                        Menu.userMenu();
                    } else {
                        Printer.printAskRegister();
                    }
                    break;
                case "3":
                    Menu.register();
                    break;
                case "4":
                    programOn = false;
                    Printer.printExit();
                    break;
                default:
                    logic.printInvalid();
            }
        }
    }
}
