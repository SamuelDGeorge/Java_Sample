package grail.exceptions;

import java.io.IOException;

import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({"ParsingException"})
public class ParsingException extends IOException {

	public ParsingException(String message) {
		super(message);
	}


}
