package grail.command;

import grail.interfaces.WordToken;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
public class CommandStoreToken implements WordToken{

	private String storedString = "";
	
	public CommandStoreToken(String input) {
		storedString = input;
	}
	
	public void setInput(String input) {
		storedString = input;
	}
	
	public String getInput() {
		return storedString;
	}

	@Override
	public String getValue() {
		return storedString.toLowerCase();
	}

}
