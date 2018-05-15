package grail.demo;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.avatar.Avatar;
import grail.avatar.BasicAvatar;
import grail.commander.BasicCommandInterpreter;
import grail.commander.CommandInterpreter;
import grail.controllers.BasicBridgeController;
import grail.controllers.BridgeController;
import grail.helpers.AScannerBean;
import grail.helpers.ComponentBuilder;
import grail.interfaces.ScannerBean;
import grail.paint.ObservablePainter;
import grail.scene.BasicBridgeScene;
import grail.scene.BridgeScene;
import grail.toolkit.BasicCommandController;
import grail.toolkit.ToolkitCommandViewer;
import grail.toolkit.CommandController;
import grail.toolkit.AdvancedCommandViewer;
import util.annotations.PropertyNames;
import util.misc.ThreadSupport;

@PropertyNames({"Progress"})
public class Assignment9Demo extends BasicDemo implements NineDemo {
	
	private int progress;
	
	public Assignment9Demo() {
	  progress = 0;
	}
	
	@Override
	public void run() {
		final int frameWidth = 1600;
		final int frameHeight = 1000;
		final int interpreterViewLocation = 600;
		
		Avatar arthur = new BasicAvatar("arthur.jpg");
		arthur.setBodyColor(Color.YELLOW);
		arthur.setTextColor(Color.YELLOW);
		
		Avatar lancelot = new BasicAvatar("lancelot.jpg");
		lancelot.setBodyColor(Color.RED);
		lancelot.setTextColor(Color.RED);
		
		Avatar robin =  new BasicAvatar("robin.jpg");
		robin.setBodyColor(Color.CYAN);
		robin.setTextColor(Color.CYAN);
		
		Avatar galahad = new BasicAvatar("galahad.jpg");
		galahad.setBodyColor(Color.PINK);
		galahad.setTextColor(Color.PINK);
		
		Avatar guard = new BasicAvatar("guard.jpg");
		guard.setBodyColor(Color.DARK_GRAY);
		guard.setTextColor(Color.DARK_GRAY);
		
		BridgeScene scene = new BasicBridgeScene(arthur, lancelot, robin, galahad, guard);
			
		ObservablePainter singleComponentModel = ComponentBuilder.buildObservablePainterFromScene(scene);
		

		ScannerBean bean = new AScannerBean();
		CommandInterpreter commander = new BasicCommandInterpreter(scene, bean);
		
		AdvancedCommandViewer commandView = new ToolkitCommandViewer(commander);

		CommandController commandControl = new BasicCommandController(commander,commandView);
		BridgeController bridgeControl = new BasicBridgeController(scene);
		bridgeControl.setModel(singleComponentModel);
		
		//Make frame and put ObservablePainter in the frame as a component

		
		OEFrame editor = ObjectEditor.edit(scene);
		editor.hideMainPanel();
		editor.setSize(frameWidth,frameHeight);
		
		JFrame frame = new JFrame("Window");	
		frame.add(((Component) singleComponentModel));
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		commandView.placeFrameOnTop();
		commandView.getFrame().setLocation(0, interpreterViewLocation);
		
		animateScene(scene);
	}

	
	private void animateScene(BridgeScene scene) {
		final int reasonableWaitTime = 2000;
		final int ammountToMove = 100;
		int progressOutOfFive = 0;
		ThreadSupport.sleep(reasonableWaitTime);
		
		scene.approach(scene.getArthur());
		scene.say("Arthur has Approached");
		updateProgressBar(++progressOutOfFive);
		ThreadSupport.sleep(reasonableWaitTime);
		
		scene.say("Robin Please Move");
		scene.getRobin().setBasicText("Moved!");
		updateProgressBar(++progressOutOfFive);
		ThreadSupport.sleep(reasonableWaitTime);
		
		scene.getRobin().move(ammountToMove, ammountToMove);
		updateProgressBar(++progressOutOfFive);
		ThreadSupport.sleep(reasonableWaitTime);
		
		final int amountToRotate = 4;
		scene.getArthur().getArms().getRightLine().rotate(amountToRotate);
		scene.getArthur().setBasicText("I waved!");
		updateProgressBar(++progressOutOfFive);
		ThreadSupport.sleep(reasonableWaitTime);
		
		scene.getArthur().setBasicText("Demo is over!");
		scene.getLancelot().setBasicText("Play with the Command Controller!");
		scene.getGuard().setBasicText("Move the knights by clicking and using keyboard!");
		updateProgressBar(++progressOutOfFive);
		ThreadSupport.sleep(reasonableWaitTime);
	}
	
	private void updateProgressBar(int number) {
		final int progressIncrement = 20;
		int oldVal = this.progress;
		this.progress = number * progressIncrement;
		if (propertyListener != null) {
			propertyListener.notifyAllListeners(new PropertyChangeEvent(this,"Progress", oldVal, this.progress));
		}
		
	}
	
	public int getProgress() {return this.progress;}
	
	
}
