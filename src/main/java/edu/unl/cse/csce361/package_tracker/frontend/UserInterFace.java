package edu.unl.cse.csce361.package_tracker.frontend;

import edu.unl.cse.csce361.package_tracker.logic.logicFacade;

import java.util.Scanner;

public class UserInterFace {
    private static logicFacade logic = logicFacade.getInstance();
    private static Scanner scnr = new Scanner(System.in);
    private static String userName;

    public static String getUserName () {
        return userName;
    }

    public static void setUserName (String userName) {
        UserInterFace.userName = userName;
    }

    public static void main (String[] args) {
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
                    logic.searchSender(userName);
                    if ("VIP".equals(status)) {
                        Menu.vipMenu();
                    } else if ("Active".equals(status)) {
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
