package grail.helpers;

public interface CommandParser {
	public void setCommandText(String text);
	public Runnable getCommandObject();
	public String getErrors();
	public Runnable parseCommand();
	public Runnable parseSay();
	public Runnable parseMove();
	public Runnable parseApproach();
	public Runnable parsePass();
	public Runnable parseFail();
	public Runnable parseCommandList();
	public Runnable parseRepeat();
	public int numberParser();
	public Runnable parseLeftArm();
	public Runnable parseRightArm();
	public Runnable parseSleep();
	public Runnable parseDefine();
	public Runnable parseCall();
	public Runnable parseThread();
	public Runnable parsePrint();
}
