package grail.helpers;

import java.util.ArrayList;
import java.util.List;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Table"})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public class BasicTable implements Table{
	protected List<Object> valueColumn;
	protected List<String> keyColumn;
	
	public BasicTable() {
		this.valueColumn = new ArrayList<Object>();
		this.keyColumn = new ArrayList<String>();
		
	}
	
	public void put(String key, Object val) {
		int spotToCheck = 0;
		if (this.keyColumn.contains(key) && !(key == null || val == null)) {
			while (spotToCheck < this.keyColumn.size()) {
				String currentKey = this.keyColumn.get(spotToCheck);
				if (currentKey.equals(key)) {
					this.valueColumn.set(spotToCheck, val);
				}
				spotToCheck++;
	
			}
		} else if (!(key == null || val == null)){
			this.keyColumn.add(key);
			this.valueColumn.add(val);
		} else {
			//do nothing
		}
		
	}

	
	public Object get(String key) {
		int spotToCheck = 0;
		while (spotToCheck < this.keyColumn.size()) {
			String currentKey = this.keyColumn.get(spotToCheck);
			if (currentKey.equals(key)) {
				return this.valueColumn.get(spotToCheck);
			}
			spotToCheck++;
		}
		return null;
	}

	public void print() {
		int startingPoint = 0;
		while (startingPoint < this.valueColumn.size()) {
			String toPrint = "Key: " + this.keyColumn.get(startingPoint) + " Value: " + this.valueColumn.get(startingPoint);
			System.out.println(toPrint);
			startingPoint++;
		}
		
	}

	public boolean contains(String key) {
		int spotToCheck = 0;
		while (spotToCheck < this.keyColumn.size()) {
			String currentKey = this.keyColumn.get(spotToCheck);
			if (currentKey.equals(key)) {
				return true;
			}
			spotToCheck++;
		}
		return false;
	}

	@Override
	public String keyList() {
		String toReturn = "";
		for (int i = 0; i < this.keyColumn.size(); i++) {
			toReturn = toReturn + this.keyColumn.get(i) + "\n";
		}
		return toReturn;
	}

	

}
