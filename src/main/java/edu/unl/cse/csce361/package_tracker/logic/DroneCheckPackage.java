package edu.unl.cse.csce361.package_tracker.logic;

import edu.unl.cse.csce361.package_tracker.backend.BackendFacade;

public class DroneCheckPackage implements Runnable {
	private static final logicFacade logic = logicFacade.getInstance();
	private final static BackendFacade BACKEND_FACADE = BackendFacade.getBackendFacade();
	private Thread t;
	private String threadName;

	public DroneCheckPackage(String name) {
		threadName = name;
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void run() {
		try {
			while (logic.getisProgramOn()) {
				logic.setDispatchingPackage(BACKEND_FACADE.getDispatchingPackage());
				logic.checkToShip();
				Thread.sleep(100000000);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted. Please contact support for help.");
		}
	}
}
