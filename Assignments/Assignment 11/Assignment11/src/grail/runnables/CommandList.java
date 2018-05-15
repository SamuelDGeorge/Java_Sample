package grail.runnables;

import java.util.ArrayList;
import java.util.List;

import util.annotations.Tags;

@Tags({"CommandList"})
public class CommandList implements RunnableList {
	private List<Runnable> commandList;
	
	public CommandList() {
		commandList = new ArrayList<Runnable>();
	}

	public void run() {
		for(int i = 0; i < commandList.size(); i++) {
			commandList.get(i).run();
		}
	}
	
	@Tags({"add"})
	public void add(Runnable toAdd) {
		this.commandList.add(toAdd);
		
	}

	public void clear() {
		this.commandList.clear();
	}

}
