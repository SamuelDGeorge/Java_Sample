package main;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.draw.LineRotatingShape;
import grail.draw.RotatingShape;
import grail.helpers.ScannerBean;
import grail.interfaces.ArrayStore;
import util.misc.ThreadSupport;

public class Assignment4 {
	
	public static void main(String[] args) {
        demonstrateScannerBean();
        demonstrateShape();
	}
	
	
	private static void demonstrateScannerBean() {
		final int windowPixelWidth = 750;
		final int windowPixelHeight = 350;
		final int appropriateSleepTime = 4000;
		ArrayStore scannerbean = new ScannerBean();
        OEFrame editor = ObjectEditor.edit(scannerbean);
        scannerbean.setScannedString("\"First try all the non-command cases:\"  0098 WORD  {  }  +  -  err0r");
        editor.select(scannerbean, "Tokens");
        editor.setSize(windowPixelWidth, windowPixelHeight);
        editor.refresh();
        ThreadSupport.sleep(appropriateSleepTime);
        scannerbean.setScannedString("\"Next we do first set of commands\" call define move proceedall REDO");
        editor.refresh();
        ThreadSupport.sleep(appropriateSleepTime);
        scannerbean.setScannedString("\"Next we do second set of commands\" REPEAT RotateLeftArm RotateRightArm");
        editor.refresh();
        ThreadSupport.sleep(appropriateSleepTime);
        scannerbean.setScannedString("\"Next we do final set of commands\" Say SLEep Thread Undo Wait");
        editor.refresh();
        ThreadSupport.sleep(appropriateSleepTime);
        scannerbean.setScannedString("\"Last we show errors:\" \"MissingEndQuote  m!x   {NOSPACE }NOSPACE   @manySpaces");
        editor.refresh();
        ThreadSupport.sleep(appropriateSleepTime);
        editor.dispose();
	}
	
	private static void demonstrateShape() {
		//setup initial conditions for code
		final int xPositionStart = 155;
		final int yPositionStart = 130;
		final double radiusInitial = 100;
		final double angleVerticalUp = ((Math.PI/2) * 3);
		final int windowPixelWidth = 300;
		final int windowPixelHeight = 300;
		final int appropriateSleepTime = 3000;
		
		//create line object in editor
		RotatingShape line = new LineRotatingShape(xPositionStart,yPositionStart,radiusInitial, angleVerticalUp);
        OEFrame editor = ObjectEditor.edit(line);
        editor.setSize(windowPixelWidth, windowPixelHeight);
        editor.refresh();
        ThreadSupport.sleep(appropriateSleepTime);
       
        //rotate and move the line
        int count = 0;
        final int fullTurn = 60;
        final int oneSecondInMS = 1000;
        final int waitTimeTillMove = 2;
        
        while(count < fullTurn) {
        	line.rotate(1);
        	if (count > waitTimeTillMove) {
        		line = boxMoveLine(line, xPositionStart, yPositionStart, count);
        	}
        	editor.refresh();
        	ThreadSupport.sleep(oneSecondInMS);
        	count++;
        }
        editor.dispose();
        
	}
	
	private static RotatingShape boxMoveLine(RotatingShape line, int xPos, int yPos , int count) { 
		final int boxSize = 20;
		final int corners = 4;
		if (count % corners == 0) {
			line.setX(xPos);
			line.setY(yPos);
		} else if (count % corners == 1) {
			line.setX(xPos);
			line.setY(yPos - boxSize);
		} else if (count % corners == 2) {
			line.setX(xPos - boxSize);
			line.setY(yPos - boxSize);
		} else {
			line.setX(xPos - boxSize);
			line.setY(yPos);
		}
		
		return line;
	}
	
}
