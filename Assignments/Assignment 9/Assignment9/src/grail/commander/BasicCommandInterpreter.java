package grail.commander;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import grail.avatar.Avatar;
import grail.command.MoveCommandToken;
import grail.command.SayCommandToken;
import grail.helpers.Table;
import grail.interfaces.ScannerBean;
import grail.interfaces.CommandToken;
import grail.interfaces.NumberToken;
import grail.interfaces.StoreToken;
import grail.model.APropertyListenerSupport;
import grail.model.PropertyListenerSupport;
import grail.scene.BridgeScene;
import grail.tokens.MinusStoreToken;
import grail.tokens.PlusStoreToken;
import grail.tokens.QuotedStoreToken;
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
	private ScannerBean scanner;
	private String currentCommand;
	private String errors;
	private Table knightList;
	private int currentToken;
	protected PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	
	public BasicCommandInterpreter(BridgeScene scene, ScannerBean scanner) {
		this.scene = scene;
		this.scanner = scanner;
		this.currentCommand = "";
		this.errors = "";
		this.knightList = scene.getKnightList();
		currentToken = 0;
	}

	
	public String getCommand() {
		return currentCommand;
		
	}

	
	public void setCommand(String commandLine) {
		String oldCommand = this.currentCommand;
		this.currentCommand = commandLine;
		scanner.setScannedString(commandLine);
		currentToken = 0;
		this.errors = "";
		processCommandArray(scanner.getTokens());
		if (propertyListenerSupport != null) {
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Command",oldCommand,this.currentCommand ));
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Errors", null, this.errors));
		}
		
	}


	@Tags({"ErrorResilient"})
	public String getErrors() {
		return this.errors;
	}
	
	private void processCommandArray(StoreToken[] input) {
		final String defaultError = "Command Found!";
		while (currentToken < input.length) {
			if (input.length > 0 && input[currentToken] instanceof CommandToken) {
				this.errors = defaultError;
				executeCommand(input, input[currentToken]);
			
			} else {
				//Do Nothing;
			}
			currentToken++;
		}
		
		if ("".equalsIgnoreCase(this.errors)) {
			this.errors = "No Command was detected!";
		} else if (defaultError.equals(this.errors)) {
			this.errors = "No Errors Found!";
		}

	}

	private void executeCommand(StoreToken[] input , StoreToken commandToken) {
		if (commandToken instanceof SayCommandToken) {
			sayCommand(input);
		} else if (commandToken instanceof MoveCommandToken) {
			moveCommand(input);
		} else {
			this.errors = "Sorry, unable to implement \"" + commandToken.getInput() + "\" at this time!";
		}
	}
	
	private void sayCommand(StoreToken[] input) {
	    int remainderLength = input.length - currentToken;
		if (remainderLength > 1  && input[currentToken + 1] instanceof QuotedStoreToken) {
			scene.say(input[currentToken + 1].getInput());
		} else {
			this.errors = "\"Say\" did not follow proper syntax. Syntax is: \"Say\" <QuotedString>";
		}
		
	}
	
	private void moveCommand(StoreToken[] input) {
		 int remainderLength = input.length - currentToken;
		 int startPosition = currentToken;
		 final int shortFormLength = 3;
		 final int mediumFormLength = 4;
		 final int longFormLength = 5;
		 final int avatarPosition = ++startPosition;
		 final int firstPosition = ++startPosition;
		 final int secondPosition = ++startPosition;
		 final int thirdPosition = ++startPosition;
		 final int fourthPosition = ++startPosition;
		 
		 if (remainderLength > shortFormLength && isAvatar(input[avatarPosition]) && isNumber(input[firstPosition]) && isNumber(input[secondPosition])) {
			Avatar currentAvatar = (Avatar) (this.knightList.get(input[currentToken + 1].getInput().toLowerCase()));
			int valueX = ((NumberToken) input[firstPosition]).getValue();
			int valueY = ((NumberToken) input[secondPosition]).getValue();
			currentAvatar.move(valueX, valueY);
			
		} else if (remainderLength > mediumFormLength && isAvatar(input[avatarPosition]) && isSign(input[firstPosition]) &&  isNumber(input[secondPosition]) && isNumber(input[thirdPosition]))  {
			Avatar currentAvatar = (Avatar) (this.knightList.get(input[avatarPosition].getInput().toLowerCase()));
			int whichSignX = (input[firstPosition] instanceof PlusStoreToken)? 1:-1; 
			int valueX = whichSignX * ((NumberToken) input[secondPosition]).getValue();
			int valueY = ((NumberToken) input[thirdPosition]).getValue();
			currentAvatar.move(valueX, valueY);
			
		} else if (remainderLength > mediumFormLength && isAvatar(input[avatarPosition]) && isNumber(input[firstPosition]) && isSign(input[secondPosition]) && isNumber(input[thirdPosition])) {
			Avatar currentAvatar = (Avatar) (this.knightList.get(input[avatarPosition].getInput().toLowerCase()));
			int whichSignY = (input[secondPosition] instanceof PlusStoreToken)? 1:-1; 
			int valueX = ((NumberToken) input[firstPosition]).getValue();
			int valueY = whichSignY * ((NumberToken) input[thirdPosition]).getValue();
			currentAvatar.move(valueX, valueY);
			
		} else if (remainderLength > longFormLength && isAvatar(input[avatarPosition]) && isSign(input[firstPosition]) && isNumber(input[secondPosition]) && isSign(input[thirdPosition]) && isNumber(input[fourthPosition]) ) {
			Avatar currentAvatar = (Avatar) (this.knightList.get(input[avatarPosition].getInput().toLowerCase()));
			int whichSignX = (input[firstPosition] instanceof PlusStoreToken)? 1:-1; 
			int valueX = whichSignX * ((NumberToken) input[secondPosition]).getValue();
			int whichSignY = (input[thirdPosition] instanceof PlusStoreToken)? 1:-1; 
			int valueY = whichSignY * ((NumberToken) input[fourthPosition]).getValue();
			currentAvatar.move(valueX, valueY);
			
		} else {
			this.errors = "\"Move\" did not follow proper syntax. Syntax is: \"Move\" Avatar <Number> <Number>";
		}
	}
		
	private boolean isAvatar(StoreToken input) {
		 	return (this.knightList.contains(input.getInput().toLowerCase()));
		 
	}	
	
		
	private boolean isSign(StoreToken input) {
		return (input instanceof PlusStoreToken || input instanceof MinusStoreToken);
	}
	
	private boolean isNumber(StoreToken input) {
		return input instanceof NumberToken;
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyListenerSupport.addElement(arg0);
		
	}
	
	
	
}
