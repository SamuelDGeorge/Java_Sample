package grail.runnables;

import grail.helpers.Table;
import util.annotations.Tags;

@Tags({"DefineCommand"})
public class DefineCommand implements Runnable{
	private String commandName;
	private Runnable commandToExecute;
	private Table commandList;
	
	public DefineCommand(String input, Runnable command, Table list) {
		this.commandName = input;
		this.commandToExecute = command;
		this.commandList = list;
	}

	public void run() {
		this.commandList.put(this.commandName, this.commandToExecute);
	}

}
