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
import grail.helpers.ComponentBuilder;
import grail.paint.ObservablePainter;
import grail.scene.BasicBridgeScene;
import grail.scene.BridgeScene;
import grail.toolkit.BasicCommandController;
import grail.toolkit.ToolkitCommandViewer;
import grail.view.BasicConsole;
import grail.toolkit.CommandController;
import grail.toolkit.AdvancedCommandViewer;
import util.annotations.PropertyNames;
import util.misc.ThreadSupport;

@PropertyNames({"Progress"})
public class Assignment11Demo extends BasicDemo implements NineDemo {
	
	private int progress;
	
	public Assignment11Demo() {
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
		
		CommandInterpreter commander = new BasicCommandInterpreter(scene);
		
		AdvancedCommandViewer commandView = new ToolkitCommandViewer(commander);
		new BasicConsole(scene);

		CommandController commandControl = new BasicCommandController(commander,commandView);
		commandControl.setScene(scene);
		BridgeController bridgeControl = new BasicBridgeController(scene);
		bridgeControl.setModel(singleComponentModel);
		
		//Make frame and put ObservablePainter in the frame as a component

		OEFrame editor = ObjectEditor.edit(scene);
		editor.hideMainPanel();
		editor.setSize(frameWidth,frameHeight);
		
		JFrame frame = new JFrame("Window");	
		frame.add(((Component) singleComponentModel));
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		commandView.placeFrameOnTop();
		commandView.getFrame().setLocation(0, interpreterViewLocation);
		
		final int interpreterX = 1200;
		final int interpreterY = 700;
		OEFrame editor2 = ObjectEditor.edit(commander);
		editor2.setLocation(interpreterX, interpreterY);
		
		animateScene(scene, commander);
		
	}

	
	private void animateScene(BridgeScene scene, CommandInterpreter command) {
		final int reasonableWaitTime = 2000;
		final int animationTime = 15000;
		int progressOutOfSeven = 0;
		
		scene.getArthur().setBasicText("I will move the same time as galahad");
		scene.getGalahad().setBasicText("I will move with arthur and then move again afterward.");
		updateProgressBar(++progressOutOfSeven);
		ThreadSupport.sleep(reasonableWaitTime);
		
		command.asynchronousArthur();
		command.asynchronousGalahad();
		command.asynchronousGalahad();
		updateProgressBar(++progressOutOfSeven);
		ThreadSupport.sleep(animationTime);
		
		scene.setOriginalPositions();
		scene.getArthur().setBasicText("Demonstrate Commands!");
		scene.getGalahad().setBasicText("Command Time!");
		updateProgressBar(++progressOutOfSeven);
		ThreadSupport.sleep(reasonableWaitTime);
	
		scene.getLancelot().setBasicText("I'm going to repeat Command!");
		scene.getGalahad().setBasicText("I'm going to move with him!");
		ThreadSupport.sleep(reasonableWaitTime);
		command.setCommand("repeat 4 { move lancelot + 10 + 10 move galahad + 10 + 10 }");
		command.setCommand("{ { approach arthur say \"Guard Speaks\" } move robin 100 100  { say \"nested brackets work!\" say \"Guard Talks Again!\" } }");
		updateProgressBar(++progressOutOfSeven);
		
		ThreadSupport.sleep(reasonableWaitTime);
		scene.setOriginalPositions();
		scene.getRobin().setBasicText("I'll show + and - !");
		command.setCommand("move robin + 100 - 100");
		updateProgressBar(++progressOutOfSeven);
		
		scene.getGuard().setBasicText("I'm going to show the clapping!");
		ThreadSupport.sleep(reasonableWaitTime);
		command.setCommand("define guardArmsIn { rotateLeftArm guard 7 rotateRightArm guard - 7 }");
		command.setCommand("define guardArmsOut { rotateLeftArm guard - 7 rotateRightArm guard 7 }");
		command.setCommand("define beat { call guardArmsIn sleep 1000 call guardArmsOut sleep 1000 }");
		command.setCommand("define beats repeat 5 call beat");
		scene.getGuard().setBasicText("I'm Ready to Clap now that beats is defined!");
		updateProgressBar(++progressOutOfSeven);
		
		ThreadSupport.sleep(reasonableWaitTime);
		scene.getGuard().setBasicText("Clapping!!");
		command.setCommand("thread beats");
		
		final int clapTime = 9000;
		ThreadSupport.sleep(clapTime);
		updateProgressBar(++progressOutOfSeven);
		scene.getGuard().setBasicText("Use the command editor to put in your custom commands!");
		command.setCommand("print");
	}
	
	private void updateProgressBar(int number) {
		final int progressIncrement = 14;
		int oldVal = this.progress;
		this.progress = number * progressIncrement;
		if (propertyListener != null) {
			propertyListener.notifyAllListeners(new PropertyChangeEvent(this,"Progress", oldVal, this.progress));
		}
		
	}
	
	public int getProgress() {return this.progress;}
	
	
}
