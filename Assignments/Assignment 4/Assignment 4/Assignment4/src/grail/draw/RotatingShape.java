package grail.draw;

public interface RotatingShape extends Shape {
	public void setRadius(double val);
	public void setAngle(double val);
	public void rotate(int units);
}
