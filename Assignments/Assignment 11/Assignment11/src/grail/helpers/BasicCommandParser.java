package grail.helpers;

import grail.avatar.Avatar;
import grail.command.ApproachCommandToken;
import grail.command.CallCommandToken;
import grail.command.DefineCommandToken;
import grail.command.FailCommandToken;
import grail.command.MoveCommandToken;
import grail.command.PassCommandToken;
import grail.command.PrintStoredCommandToken;
import grail.command.RepeatCommandToken;
import grail.command.RotateLeftArmCommandToken;
import grail.command.RotateRightArmCommandToken;
import grail.command.SayCommandToken;
import grail.command.SleepCommandToken;
import grail.command.ThreadCommandToken;
import grail.interfaces.NumberToken;
import grail.interfaces.ScannerBean;
import grail.interfaces.StoreToken;
import grail.runnables.ApproachCommand;
import grail.runnables.CallCommand;
import grail.runnables.CommandList;
import grail.runnables.DefaultCommand;
import grail.runnables.DefineCommand;
import grail.runnables.FailCommand;
import grail.runnables.MoveCommand;
import grail.runnables.PassCommand;
import grail.runnables.RepeatCommand;
import grail.runnables.RotateLeftArmCommand;
import grail.runnables.RotateRightArmCommand;
import grail.runnables.RunnableList;
import grail.runnables.SayCommand;
import grail.runnables.SleepCommand;
import grail.runnables.ThreadCommand;
import grail.scene.BridgeScene;
import grail.tokens.EndStoreToken;
import grail.tokens.MinusStoreToken;
import grail.tokens.NumberStoreToken;
import grail.tokens.PlusStoreToken;
import grail.tokens.QuotedStoreToken;
import grail.tokens.StartStoreToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Parser"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"CommandText", "CommandObject","Errors"})
@EditablePropertyNames({"CommandText"})
public class BasicCommandParser implements CommandParser {
	private BridgeScene scene;
	private Table knightList;
	private ScannerBean scanner;
	private StoreToken[] tokenArray;
	private String errors;
	private int currentPosition;
	private Runnable returnCommand;
	private Table commandList;
	
	public BasicCommandParser(BridgeScene scene) {
		this.scene = scene;
		this.knightList = scene.getKnightList();
		this.commandList = new BasicTable();
		this.scanner = new AScannerBean();
		this.scanner.setScannedString("Default");
		this.tokenArray = this.scanner.getTokens();
		this.errors = "No Errors Found!";
		this.currentPosition = 0;
		this.returnCommand = new DefaultCommand();
	}

	public void setCommandText(String text) {
		this.scanner.setScannedString(text);
		this.tokenArray = this.scanner.getTokens();
		this.currentPosition = 0;
		this.returnCommand = null;
		this.errors = "The Following items were printed:";
		this.returnCommand = parseCommand();

	}


	public Runnable getCommandObject() {
		return this.returnCommand;
	}

	
	public String getErrors() {
		return this.errors;
		
	}

	@Tags({"parseCommand"})
	public Runnable parseCommand() {
		Runnable returnCommand;
		StoreToken currentToken = (this.currentPosition < this.tokenArray.length)? this.tokenArray[this.currentPosition]:null;
		currentPosition++;
		if (currentToken instanceof SayCommandToken) {
			returnCommand = parseSay();
		} else if (currentToken instanceof MoveCommandToken) {
				returnCommand = parseMove();
		} else if (currentToken instanceof ApproachCommandToken) {
				returnCommand = parseApproach();
		} else if (currentToken instanceof PassCommandToken) {
				returnCommand = parsePass();
		} else if (currentToken instanceof FailCommandToken) {
				returnCommand = parseFail();
		} else if (currentToken instanceof StartStoreToken) {
				returnCommand = parseCommandList();
		} else if (currentToken instanceof RepeatCommandToken) {
				returnCommand = parseRepeat();
				this.errors = (returnCommand == null)? this.errors + "\nNo command found after repeat!":this.errors;
		} else if (currentToken instanceof RotateLeftArmCommandToken) {
				returnCommand = parseLeftArm();
		} else if (currentToken instanceof RotateRightArmCommandToken) {
				returnCommand = parseRightArm();
		} else if (currentToken instanceof SleepCommandToken) {
				returnCommand = parseSleep();
		} else if (currentToken instanceof DefineCommandToken) {
				returnCommand = parseDefine();
		} else if (currentToken instanceof CallCommandToken) {
				returnCommand = parseCall();
		} else if (currentToken instanceof ThreadCommandToken) {
				returnCommand = parseThread();
		} else if (currentToken instanceof PrintStoredCommandToken) {
				returnCommand = parsePrint();
		} else {
			this.errors = this.errors + "\nA Command was not found and parse returned null!";	
			returnCommand = null;
		}
		
		
		if (returnCommand != null) {
			this.errors = this.errors + "\nCommand was parsed successfully! Note: Excess or error tokens ignored.";
			return returnCommand;
		} else {
			this.errors = this.errors + "\nA Token returned null during Parse and a DefaultCommand was returned instead";
			return new DefaultCommand();
		}
	}

	@Tags({"parseSay"})
	public Runnable parseSay() {
	    int remainderLength = tokenArray.length - currentPosition;
		
	    if (remainderLength >= 1  && tokenArray[currentPosition] instanceof QuotedStoreToken) {
			int returnPosition = currentPosition;
	    	currentPosition++;
			return new SayCommand(this.scene, tokenArray[returnPosition].getInput());
		} else {
			this.errors = this.errors + "\nSay command has syntax: say \"Quote\"";
			return null;
		}
	}

	
	@Tags({"parseMove"})
	public Runnable parseMove() {
		 Avatar toMove = (this.currentPosition < this.tokenArray.length && isAvatar(this.tokenArray[this.currentPosition]))? (Avatar) this.knightList.get(this.tokenArray[this.currentPosition].getInput().toLowerCase()):null;
		 if (toMove != null) {
			 currentPosition++;
			 int xAmount = numberParser();
			 int yAmount = numberParser();
			 return new MoveCommand(toMove, xAmount, yAmount);
		} else {
			 this.errors = this.errors + "\nAvatar token was not found. Please use syntax: move <Avatar> <Number> <Number>";
			 return null;
		}
		 
	}

	@Tags({"parseApproach"})
	public Runnable parseApproach() {
		int remainderLength = tokenArray.length - currentPosition;
		
	    if (remainderLength >= 1  && isAvatar(tokenArray[currentPosition])) {
			int returnPosition = currentPosition;
	    	currentPosition++;
			return new ApproachCommand(this.scene, (Avatar) (this.knightList.get(tokenArray[returnPosition].getInput().toLowerCase())));
		} else {
			this.errors = this. errors + "\nApproach command has syntax: approach knight";
			return null;
		}
	}

	
	@Tags({"parsePass"})
	public Runnable parsePass() {
		return new PassCommand(this.scene);
	}

	@Tags({"parseFail"})
	public Runnable parseFail() {
		return new FailCommand(this.scene);
	}

	@Tags({"parseCommandList"})
	public Runnable parseCommandList() {
		RunnableList returnList = new CommandList();
		int artificialEnd = getMatchingEndQuote();
		if (artificialEnd == -1) {
			this.errors = this.errors + "\nCheck { and } to ensure proper grouping!";
			return null;
		}
		while (this.currentPosition < artificialEnd) {
			returnList.add(parseCommand());
		}
		
		this.currentPosition = artificialEnd + 1;
		return (CommandList) returnList;
	}

	@Tags({"parseRepeat"})
	public Runnable parseRepeat() {
		int numberOfRepeats = ((this.currentPosition < this.tokenArray.length) && (this.tokenArray[this.currentPosition] instanceof NumberStoreToken))? ((NumberStoreToken)this.tokenArray[this.currentPosition]).getValue():-1;
		if (numberOfRepeats == -1) {
			this.errors = this.errors + "\nRepeat Command did not have syntax: repeat <int>";
			return null;
		} else {
			currentPosition++;
			Runnable commandToExecute = parseCommand();
			return (commandToExecute != null)? new RepeatCommand(numberOfRepeats, commandToExecute):null;
		}
	}
	
	@Tags({"numberParser"})
	public int numberParser() {
		StoreToken firstToken = (this.currentPosition < this.tokenArray.length)? this.tokenArray[this.currentPosition]:null;
		if (isNumber(firstToken)) {
			this.currentPosition++;
			return ((NumberStoreToken) firstToken).getValue();
		} else if (isSign(firstToken) && this.currentPosition + 1 < this.tokenArray.length && isNumber(this.tokenArray[this.currentPosition + 1]) ) {
			int signNumber = (firstToken instanceof PlusStoreToken)? 1:-1;
			currentPosition++;
			StoreToken secondToken = this.tokenArray[this.currentPosition];
			currentPosition++;
			return signNumber * ((NumberStoreToken) secondToken).getValue();
		 } else {
			this.errors = this.errors + "\nA number was not found in the proper format. 0 was passed by default.";
			return 0;
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
	
	private int getMatchingEndQuote() {
		int counter = 1;
		int endPosition = this.currentPosition;
		try {
			while (counter > 0) {
				StoreToken currentToken = this.tokenArray[endPosition];
				if ( currentToken instanceof StartStoreToken) {
					counter++;
				} else if ( currentToken instanceof EndStoreToken) {
					counter--;
				}
				endPosition++;
			} 
		} catch (StackOverflowError e) {
			return -1;
		} catch (Exception e) {
			return -1;
		}
		return endPosition - 1;
	}

	@Tags({"parseLeftArm"})
	public Runnable parseLeftArm() {
		 Avatar toMove = (this.currentPosition < this.tokenArray.length && isAvatar(this.tokenArray[this.currentPosition]))? (Avatar) this.knightList.get(this.tokenArray[this.currentPosition].getInput().toLowerCase()):null;
		 if (toMove != null) {
			 currentPosition++;
			 int rotateAmount = numberParser();
			 return new RotateLeftArmCommand(toMove, rotateAmount);
		} else {
			 this.errors = this.errors + "\nAvatar token was not found. Please use syntax: rotateLeftArm <Avatar> <Number>";
			 return null;
		}
		
	}

	@Tags({"parseRightArm"})
	public Runnable parseRightArm() {
		Avatar toMove = (this.currentPosition < this.tokenArray.length && isAvatar(this.tokenArray[this.currentPosition]))? (Avatar) this.knightList.get(this.tokenArray[this.currentPosition].getInput().toLowerCase()):null;
		 if (toMove != null) {
			 currentPosition++;
			 int rotateAmount = numberParser();
			 return new RotateRightArmCommand(toMove, rotateAmount);
		} else {
			 this.errors = this.errors + "\nAvatar token was not found. Please use syntax: rotateRighttArm <Avatar> <Number>";
			 return null;
		}
	}

	@Tags({"parseSleep"})
	public Runnable parseSleep() {
		long amountToSleep = (long) numberParser();
		return new SleepCommand(amountToSleep);
	}

	@Tags({"parseDefine"})
	public Runnable parseDefine() {
		String commandName = (this.currentPosition < this.tokenArray.length)? this.tokenArray[this.currentPosition].getInput():null;
		if (commandName != null) {
			currentPosition++;
		} else {
			this.errors = this.errors + "\nDefine is missing a name for command. Use syntax: define <Word> <Command>";
			return null;
		}
		
		
		Runnable command = parseCommand();
		
		if (command != null) {
			return new DefineCommand(commandName, command, this.commandList);
		} else {
			this.errors = this.errors + "\nDefine did not find a command. Use Syntax: define <Word> <Command>";
			return null;
		}
	}

	@Tags({"parseCall"})
	public Runnable parseCall() {
		String commandName = (this.currentPosition < this.tokenArray.length && this.commandList.contains(this.tokenArray[this.currentPosition].getInput()))? this.tokenArray[this.currentPosition].getInput():null;
		if (commandName != null) {
			currentPosition++;
			return new CallCommand(commandName, this.commandList);
		} else {
			this.errors = this.errors + "\nCall did not find a command with that name or syntax was not: call <Name>. Use Define to add commands. Use Print to view stored commands";
			return null;
		}
	}

	@Tags({"parseThread"})
	public Runnable parseThread() {
		String commandName = (this.currentPosition < this.tokenArray.length && this.commandList.contains(this.tokenArray[this.currentPosition].getInput()))? this.tokenArray[this.currentPosition].getInput():null;
		if (commandName != null) {
			currentPosition++;
			return new ThreadCommand(commandName, this.commandList);
		} else {
			this.errors = this.errors + "\nThread did not find a command with that name or syntax was not: Thread <Name>. Use Define to add commands. Use Print to view stored commands";
			return null;
		}
	}
	
	@Tags({"parsePrint"})
	public Runnable parsePrint() {
		this.errors = "The following commands are available to be run:\n";
		this.errors = this.errors + this.commandList.keyList();
		return new DefaultCommand();
	}
	
}
