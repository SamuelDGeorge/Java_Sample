package grail.animation.two;

import grail.factories.SingletonsCreator;
import util.annotations.Tags;

@Tags({"CoordinatingAnimatingCommand"})
public class LockstepAnimatorInherited extends AbstractAnimator {

	protected synchronized void sleepOrWait() {
		SingletonsCreator.broadcastingClearnaceManagerMethod().waitForProceed();
		
	}

	protected synchronized void proceedOrNothing() {
		SingletonsCreator.broadcastingClearnaceManagerMethod().proceedAll();
	}

}
