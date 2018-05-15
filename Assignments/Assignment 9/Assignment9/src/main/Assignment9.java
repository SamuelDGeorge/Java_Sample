package main;

import grail.demo.Assignment9Demo;
import grail.demo.NineDemo;
import grail.view.BasicProgressView;
import grail.view.ProgressView;


public class Assignment9 {
	
	public static void main(String[] args) {
		NineDemo assignmentDemo = new Assignment9Demo();
		ProgressView view = new BasicProgressView();
		assignmentDemo.addPropertyChangeListener(view);
		
		assignmentDemo.run();
	}
	
}
