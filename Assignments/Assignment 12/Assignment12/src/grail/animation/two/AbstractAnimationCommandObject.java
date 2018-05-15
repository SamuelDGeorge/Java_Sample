package grail.animation.two;

import grail.avatar.Avatar;
import util.annotations.Tags;

@Tags({"CoordinatedAnimationCommand"})
public abstract class AbstractAnimationCommandObject implements Runnable {
	protected AnimatorInterface animator;
	protected String command;
	protected Avatar avatar;
	public static final String WALK_COMMAND = "walk";
	public static final String CLAP_COMMAND = "clap";
	
	public AbstractAnimationCommandObject(AnimatorInterface animate, Avatar avatar, String command) {
		this.animator = animate;
		this.command = command;
		this.avatar = avatar;
	}
	
	public void run() {
		if (WALK_COMMAND.equalsIgnoreCase(this.command)) {
			this.animator.walkAvatar(this.avatar);
		} else if (CLAP_COMMAND.equalsIgnoreCase(this.command)) {
			this.animator.clapAvatar(this.avatar);
		} else {
			//do nothing
		}
	}

}
