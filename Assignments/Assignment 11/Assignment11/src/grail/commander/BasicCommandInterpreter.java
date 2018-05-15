package grail.commander;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import grail.animation.AnimationCommandObject;
import grail.animation.Animator;
import grail.animation.BasicAvatarAnimator;
import grail.helpers.BasicCommandParser;
import grail.helpers.CommandParser;
import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;
import grail.scene.BridgeScene;
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
	private CommandParser parse;
	protected PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	private Animator arthurAnimator, galahadAnimator, lancelotAnimator, robinAnimator, guardAnimator;
	
	public BasicCommandInterpreter(BridgeScene scene) {
		this.scene = scene;
		this.currentCommand = "";
		this.errors = "";
		this.parse = new BasicCommandParser(this.scene);
		this.arthurAnimator = new BasicAvatarAnimator();
		this.galahadAnimator = new BasicAvatarAnimator();
		this.lancelotAnimator = new BasicAvatarAnimator();
		this.robinAnimator = new BasicAvatarAnimator();
		this.guardAnimator = new BasicAvatarAnimator();
	}

	
	public String getCommand() {
		return currentCommand;
		
	}
	
	public void setCommand(String commandLine) {
		String oldCommand = this.currentCommand;
		this.currentCommand = commandLine;
		parse.setCommandText(commandLine);
		this.currentCommandRunnable = parse.getCommandObject();
		this.errors = parse.getErrors();
		this.currentCommandRunnable.run();
		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Command",oldCommand,this.currentCommand ));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Errors", null, this.errors));
		}
		
	}


	@Tags({"ErrorResilient"})
	public String getErrors() {
		return this.errors;
	}
	
	
	@Tags({"asynchronousArthur"})
	public void asynchronousArthur() {
		final int distanceToWalk = 160;
		Runnable animateCommand = new AnimationCommandObject(this.arthurAnimator, walkCommand, this.scene.getArthur(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}

	@Tags({"asynchronousGalahad"})
	public void asynchronousGalahad() {
		final int distanceToWalk = 160;
		Runnable animateCommand = new AnimationCommandObject(this.galahadAnimator, walkCommand, this.scene.getGalahad(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousLancelot"})
	public void asynchronousLancelot() {
		final int distanceToWalk = 300;
		final int sleepTime = 50;
		Runnable animateCommand = new AnimationCommandObject(this.lancelotAnimator, walkCommand, this.scene.getLancelot(), distanceToWalk,sleepTime);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousRobin"})
	public void asynchronousRobin() {
		final int distanceToWalk = 260;
		Runnable animateCommand = new AnimationCommandObject(this.robinAnimator, walkCommand, this.scene.getRobin(), distanceToWalk);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@Tags({"asynchronousGuard"})
	public void asynchronousGuard() {
		final int secondsToClap = 8;
		Runnable animateCommand = new AnimationCommandObject(this.guardAnimator, clapCommand, this.scene.getGuard(), secondsToClap);
		Thread thread = new Thread(animateCommand);
		thread.start();
	}
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyListenerSupport.addElement(arg0);
		
	}
	
	
	
}
