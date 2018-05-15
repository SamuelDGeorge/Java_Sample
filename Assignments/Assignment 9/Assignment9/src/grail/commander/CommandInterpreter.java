package grail.commander;

import util.models.PropertyListenerRegisterer;

public interface CommandInterpreter extends PropertyListenerRegisterer  {
	public String getCommand();
	public void setCommand(String commandLine);
	public String getErrors();
}
