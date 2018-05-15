package grail.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.StructurePatternNames;

@Tags({"Plus"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class PlusStoreToken extends BasicStoreToken {

	public PlusStoreToken(String input) {
		super(input);
	}

}