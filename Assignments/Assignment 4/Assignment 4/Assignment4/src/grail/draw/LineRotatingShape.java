package grail.draw;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"RotatingLine"})
@StructurePattern(StructurePatternNames.LINE_PATTERN)

public class LineRotatingShape implements RotatingShape{
	private int x, y, width, height;
	private double radius, angle;

	public LineRotatingShape() {
	}
	
	public LineRotatingShape(int x, int y, double radius, double angle) {
		this.x = x;
		this.y = y;
		setRadius(radius);
		setAngle(angle);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
		
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
		
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setRadius(double val) {
		this.radius = val;
		double tempHeight =  this.radius * Math.sin(this.angle);
		this.height = (int) tempHeight;
		double tempWidth = this.radius * Math.cos(this.angle);
		this.width = (int) tempWidth;
		
	}

	@Override
	public void setAngle(double val) {
		this.angle = val;
		double tempHeight =  this.radius * Math.sin(this.angle);
		this.height = (int) tempHeight;
		double tempWidth = this.radius * Math.cos(this.angle);
		this.width = (int) tempWidth;
		
	}

	@Tags({"rotate"})
	public void rotate(int units) {
		final double secondsInAMinute = 60;
		final double fullRotation = Math.PI * 2;
		final double clockUnit = fullRotation/secondsInAMinute;
		double distanceToRotate = units * clockUnit;
		double angleToSet = this.angle + distanceToRotate;
		setAngle(angleToSet);
	}

}
