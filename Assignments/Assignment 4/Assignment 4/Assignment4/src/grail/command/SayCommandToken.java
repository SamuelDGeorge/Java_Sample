package grail.command;

import util.annotations.Tags;

@Tags({"say"})

public class SayCommandToken extends CommandStoreToken{

	public SayCommandToken(String input) {
		super(input);
	}

}
