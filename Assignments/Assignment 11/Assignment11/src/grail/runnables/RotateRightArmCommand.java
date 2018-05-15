package grail.runnables;

import grail.avatar.Avatar;
import util.annotations.Tags;

@Tags({"RotateRightArmCommand"})
public class RotateRightArmCommand implements Runnable {
	private Avatar cAvatar;
	private int toRotate;
	
	public RotateRightArmCommand(Avatar avatar, int rotate) {
		this.cAvatar = avatar;
		this.toRotate = rotate;
	}

	public void run() {
		this.cAvatar.getArms().getRightLine().rotate(this.toRotate);

	}

}
