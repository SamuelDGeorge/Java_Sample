package grail.commander;
import util.models.PropertyListenerRegisterer;

public interface CommandInterpreter extends PropertyListenerRegisterer  {
	public String getCommand();
	public void setCommand(String commandLine);
	public String getErrors();
	public void asynchronousArthur();
	public void asynchronousGalahad();
	public void asynchronousLancelot();
	public void asynchronousRobin();
	public void asynchronousGuard();
}
