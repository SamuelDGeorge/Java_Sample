package grail.runnables;

public interface RunnableList extends Runnable {
	public void add(Runnable toAdd);
	public void clear();
}
