package grail.animation.two;

import grail.avatar.Avatar;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"CoordinatedAnimator"})
public abstract class AbstractAnimator implements AnimatorInterface {

	@Tags({"animateAvatar"})
	public synchronized void walkAvatar(Avatar toAnimate) {
		final int distanceToMarch = 100;
		final int moveIncrementX = 10;
		final int moveIncrementY = 0;
		final int rotationAmount = 8;
		int distanceMarched = 0;
		int position = 0;
		while (distanceMarched < distanceToMarch) {
			if (position == 0) {
				sleepOrWait();
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(-rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				position = 1;
			} else {
				sleepOrWait();
				toAnimate.move(moveIncrementX, moveIncrementY);
				toAnimate.getLegs().rotate(rotationAmount);
				distanceMarched = distanceMarched + moveIncrementX;
				position = 0;
			}
		}

	}

	public synchronized void clapAvatar(Avatar toAnimate) {
		final int armRotation = 7;
		final int sleepTime = 1000;
		final int timesToClap = 5;
		int index = 0;
		
		while (index < timesToClap) {
			toAnimate.getArms().getLeftLine().rotate(armRotation);
			toAnimate.getArms().getRightLine().rotate(-armRotation);
			ThreadSupport.sleep(sleepTime);
			proceedOrNothing();
			toAnimate.getArms().getLeftLine().rotate(-armRotation);
			toAnimate.getArms().getRightLine().rotate(armRotation);
			ThreadSupport.sleep(sleepTime);
			proceedOrNothing();
			index++;
		}

	}
	
	protected abstract void sleepOrWait();
	protected abstract void proceedOrNothing();
}
