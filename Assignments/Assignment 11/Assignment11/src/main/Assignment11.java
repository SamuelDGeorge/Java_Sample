package main;

import grail.demo.Assignment11Demo;
import grail.demo.NineDemo;
import grail.view.BasicProgressView;
import grail.view.ProgressView;


public class Assignment11 {
	
	public static void main(String[] args) {
		NineDemo assignmentDemo = new Assignment11Demo();
		ProgressView view = new BasicProgressView();
		assignmentDemo.addPropertyChangeListener(view);
		
		assignmentDemo.run();
	}
	
}
