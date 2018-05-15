package grail.runnables;

import grail.helpers.Table;
import util.annotations.Tags;

@Tags({"ThreadCommand"})
public class ThreadCommand implements Runnable{
	private String commandToExecute;
	private Table toList;
	
	public ThreadCommand(String command, Table list) {
		this.commandToExecute = command;
		this.toList = list;
	}

	public void run() {
		if (this.toList.get(this.commandToExecute) != null) {
			Thread toRun = new Thread(((Runnable) this.toList.get(this.commandToExecute)));
			toRun.start();
		} else {
			//do nothing
		}
		
	}

}
