package grail.runnables;

import grail.avatar.Avatar;
import util.annotations.Tags;

@Tags({"RotateLeftArmCommand"})
public class RotateLeftArmCommand implements Runnable {
	private Avatar cAvatar;
	private int toRotate;
	
	public RotateLeftArmCommand(Avatar avatar, int rotate) {
		this.cAvatar = avatar;
		this.toRotate = rotate;
	}

	public void run() {
		this.cAvatar.getArms().getLeftLine().rotate(this.toRotate);
	}

}
