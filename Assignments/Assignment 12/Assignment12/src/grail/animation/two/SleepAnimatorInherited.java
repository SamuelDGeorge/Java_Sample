package grail.animation.two;

import util.misc.ThreadSupport;

public class SleepAnimatorInherited extends AbstractAnimator {

	protected synchronized void sleepOrWait() {
		final int sleepBetweenTimeMS = 200;
		ThreadSupport.sleep(sleepBetweenTimeMS);
	}

	protected synchronized void proceedOrNothing() {
		//do nothing
	}


}
