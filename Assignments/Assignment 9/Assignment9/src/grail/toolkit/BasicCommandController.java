package grail.toolkit;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import grail.commander.CommandInterpreter;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"CommandInterpreterController"})
@PropertyNames({"MenuItem", "MenuItemTwo" , "Button", "ButtonTwo", "ButtonThree", "ButtonFour"}) 
public class BasicCommandController implements CommandController {
	CommandInterpreter currentInterpreter;
	CommandWidgets aWidget;
	JButton execute, arthurUp, arthurDown, arthurLeft, arthurRight;
	JMenuItem sayHello, sayGoodbye;
	
	public BasicCommandController(CommandInterpreter interpreter, CommandWidgets widget) {
		this.currentInterpreter = interpreter;
		this.aWidget = widget;
		this.execute = this.aWidget.getExecuteButton();
		this.arthurUp = this.aWidget.getArthurUp();
		this.arthurDown = this.aWidget.getArthurDown();
		this.arthurLeft = this.aWidget.getArthurLeft();
		this.arthurRight = this.aWidget.getArthurRight();
		this.sayHello = this.aWidget.getArthurHello();
		this.sayGoodbye = this.aWidget.getArthurGoodbye();
		this.execute.addActionListener(this);
		this.arthurUp.addActionListener(this);
		this.arthurDown.addActionListener(this);
		this.arthurLeft.addActionListener(this);
		this.arthurRight.addActionListener(this);
		this.sayHello.addActionListener(this);
		this.sayGoodbye.addActionListener(this);
	}
	
	public BasicCommandController(CommandInterpreter interpreter) {
		this(interpreter, new ToolkitCommandViewer(interpreter));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.execute) {
			this.currentInterpreter.setCommand(this.aWidget.getCommandField().getText());
		} else if (e.getSource() == this.arthurUp) {
			this.currentInterpreter.setCommand("move arthur + 0 + 100");
		} else if (e.getSource() == this.arthurDown) {
			this.currentInterpreter.setCommand("move arthur + 0 - 100");
		} else if (e.getSource() == this.arthurLeft) {
			this.currentInterpreter.setCommand("move arthur - 100 + 0 ");
		} else if (e.getSource() == this.arthurRight) {
			this.currentInterpreter.setCommand("move arthur + 100 + 0");
		} else if (e.getSource() == this.sayGoodbye) {
			this.currentInterpreter.setCommand("say \"Goodbye\"");
		} else if (e.getSource() == this.sayHello) {
			this.currentInterpreter.setCommand("say \"Hello\"");
		} else {
			//do nothing
		}
		
	}

	public JTextField getTextField(){return this.aWidget.getCommandField();}
	
	public JMenuItem getMenuItem() {return this.sayHello;}

	public JMenuItem getMenuItemTwo() {return this.sayGoodbye;}

	public JButton getButton() {return this.arthurRight;}

	public JButton getButtonTwo() {return this.arthurUp;}

	public JButton getButtonThree() {return this.arthurLeft;}

	public JButton getButtonFour() {return this.arthurDown;}
	
	

}
