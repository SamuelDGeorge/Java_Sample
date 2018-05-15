package grail.commander;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import grail.animation.two.AnimatorInterface;
import grail.animation.two.LockstepAnimatorInherited;
import grail.animation.two.LockstepCommandObjectInherited;
import grail.animation.two.SleepAnimatorInherited;
import grail.animation.two.SleepCommandObjectInherited;
import grail.factories.SingletonsCreator;
import grail.helpers.ClearanceManager;
import grail.helpers.CommandParser;
import grail.interfaces.ScannerBean;
import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;
import grail.scene.BridgeScene;
import util.annotations.ComponentWidth;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;


@Tags({"CommandInterpreter", "SignedMove", "ErrorResilient"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Command", "Errors"})
@EditablePropertyNames({"Command"})
public class BasicCommandInterpreter implements CommandInterpreter{
	private BridgeScene scene;
	private String currentCommand;
	private String errors;
	private Runnable currentCommandRunnable;
	private String walkCommand = "walk";
	private String clapCommand = "clap";
	private String marchCommand = "walk";
	private CommandParser parse;
	protected PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	private AnimatorInterface arthurAnimator, galahadAnimator, lancelotAnimator, robinAnimator, guardAnimator;
	private AnimatorInterface arthurLock, galahadLock, lancelotLock, robinLock, guardLock;
	private ClearanceManager manager;
	private final int componentWidth = 400;
	
	public BasicCommandInterpreter(BridgeScene scene, ScannerBean scan,ClearanceManager manager) {
		this.scene = scene;
		this.currentCommand = "";
		this.errors = "";
		this.manager = manager;	
		this.parse = SingletonsCreator.parserFactoryMethod();
		this.arthurAnimator = new SleepAnimatorInherited();
		this.galahadAnimator = new SleepAnimatorInherited();
		this.lancelotAnimator = new SleepAnimatorInherited();
		this.robinAnimator = new SleepAnimatorInherited();
		this.guardAnimator = new SleepAnimatorInherited();
		this.arthurLock = new LockstepAnimatorInherited();
		this.galahadLock = new LockstepAnimatorInherited();
		this.lancelotLock = new LockstepAnimatorInherited();
		this.robinLock = new LockstepAnimatorInherited();
		this.guardLock = new LockstepAnimatorInherited();
		}
	
	public BasicCommandInterpreter(BridgeScene scene) {
		this(scene, SingletonsCreator.scannerFactoryMethod(), SingletonsCreator.broadcastingClearnaceManagerMethod());
	}
	
	public BasicCommandInterpreter(BridgeScene scene, ScannerBean scan) {
		this (scene,scan,SingletonsCreator.broadcastingClearnaceManagerMethod());
	}
	
	public BasicCommandInterpreter() {
		this(SingletonsCreator.bridgeSceneFactoryMethod(), SingletonsCreator.scannerFactoryMethod(),SingletonsCreator.broadcastingClearnaceManagerMethod());
	}

	
	@ComponentWidth(componentWidth)
	public String getCommand() {
		return currentCommand;
		
	}
	
	public void setCommand(String commandLine) {
		String oldCommand = this.currentCommand;
		this.currentCommand = commandLine;
		this.errors = "";
		
		parse.setCommandText(commandLine);
		this.currentCommandRunnable = parse.getCommandObject();
		this.currentCommandRunnable.run();
		this.errors = parse.getErrors();

		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Command",oldCommand,this.currentCommand ));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Errors", null, this.errors));
		}
		
	}


	@Tags({"ErrorResilient"})
    @ComponentWidth(componentWidth)
	public String getErrors() {
		return this.errors;
	}
	
	
	@Tags({"asynchronousArthur"})
	public void asynchronousArthur() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.arthurAnimator, this.scene.getArthur(), walkCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}

	@Tags({"asynchronousGalahad"})
	public void asynchronousGalahad() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.galahadAnimator, this.scene.getGalahad(), walkCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousLancelot"})
	public void asynchronousLancelot() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.lancelotAnimator, this.scene.getLancelot(), walkCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousRobin"})
	public void asynchronousRobin() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.robinAnimator, this.scene.getRobin(), walkCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousGuard"})
	public void asynchronousGuard() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.guardAnimator, this.scene.getGuard(), clapCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"waitingArthur"})
	public void waitingArthur() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.arthurAnimator, this.scene.getArthur(), this.manager,walkCommand);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"waitingGalahad"})
	public void waitingGalahad() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.galahadAnimator, this.scene.getGalahad(), this.manager,walkCommand);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"waitingLancelot"})
	public void waitingLancelot() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.lancelotAnimator, this.scene.getLancelot(), this.manager,walkCommand);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"waitingRobin"})
	public void waitingRobin() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.robinAnimator, this.scene.getRobin(),this.manager, walkCommand);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"waitingGuard"})
	public void waitingGuard() {
		Runnable animateCommand = new SleepCommandObjectInherited(this.guardAnimator, this.scene.getGuard(), this.manager,clapCommand);
		Thread thread = new Thread(animateCommand);
		thread.start(); 
	}
	
	@Tags({"start animation"})
	public void startAnimation() {
		this.manager.proceedAll();
	}
	
	@Tags({"lockstepArthur"})
	public void lockstepArthur() {
		Runnable animateCommand = new LockstepCommandObjectInherited(this.arthurLock, this.scene.getArthur(),marchCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"lockstepLancelot"})
	public void lockstepLancelot() {
		Runnable animateCommand = new LockstepCommandObjectInherited(this.lancelotLock, this.scene.getLancelot(),marchCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"lockstepGalahad"})
	public void lockstepGalahad() {
		Runnable animateCommand = new LockstepCommandObjectInherited(this.galahadLock, this.scene.getGalahad(),marchCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"lockstepRobin"})
	public void lockstepRobin() {
		Runnable animateCommand = new LockstepCommandObjectInherited(this.robinLock, this.scene.getRobin(),marchCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"lockstepGuard"})
	public void lockstepGuard() {
		Runnable animateCommand = new LockstepCommandObjectInherited(this.guardLock, this.scene.getGuard(),clapCommand);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	

	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyListenerSupport.addElement(arg0);
		
	}
	
	
	
}
