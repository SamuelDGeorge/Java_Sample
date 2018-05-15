package grail.exceptions;

import java.io.IOException;

import util.annotations.Tags;

@SuppressWarnings("serial")
@Tags({"ScanningException"})
public class ScanningException extends IOException {

	public ScanningException(String message) {
		super(message);
	}

}
