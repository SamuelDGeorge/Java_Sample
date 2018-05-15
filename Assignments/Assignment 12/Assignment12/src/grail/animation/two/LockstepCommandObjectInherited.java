package grail.animation.two;

import grail.avatar.Avatar;
import util.annotations.Tags;

@Tags({"CoordinatingAnimator"})
public class LockstepCommandObjectInherited extends AbstractAnimationCommandObject {

	public LockstepCommandObjectInherited(AnimatorInterface animate, Avatar avatar, String command) {
		super(animate, avatar, command);
	}


}
