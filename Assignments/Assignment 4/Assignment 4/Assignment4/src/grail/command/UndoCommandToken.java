package grail.command;
import util.annotations.Tags;

@Tags({"undo"})
public class UndoCommandToken extends CommandStoreToken {

	public UndoCommandToken(String input) {
		super(input);
	}

}
