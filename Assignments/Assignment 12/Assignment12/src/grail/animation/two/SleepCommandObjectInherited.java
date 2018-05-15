package grail.animation.two;

import grail.avatar.Avatar;
import grail.helpers.ClearanceManager;

public class SleepCommandObjectInherited extends AbstractAnimationCommandObject {
	private ClearanceManager manager;
	
	public SleepCommandObjectInherited(AnimatorInterface animate, Avatar avatar, String command) {
		super(animate, avatar, command);
	}
	
	public SleepCommandObjectInherited(AnimatorInterface animate, Avatar avatar, ClearanceManager manager, String command) {
		super(animate,avatar,command);
		this.manager = manager;
	}

	public void run() {
		if (this.manager == null) {
			super.run();
		} else {
			this.manager.waitForProceed();
			super.run();
		}
	}

}
