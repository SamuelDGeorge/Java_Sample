package grail.toolkit;

import java.beans.PropertyChangeEvent;
import javax.swing.JFrame;
import grail.commander.CommandInterpreter;
import util.annotations.Tags;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


@Tags({"ObservableCommandInterpreter"})
public class ToolkitCommandViewer implements AdvancedCommandViewer {
	private JFrame frame;
	private CommandInterpreter aModel;
	private JButton btnExecuteCommand, btnMoveArthurRight, btnMoveArthurLeft, btnMoveArthurUp, btnMoveArthurDown;
	private JTextArea errorPane;
	private JTextField commandField;
	private JMenu mnSayCommands;
	private JMenuItem mntmArthurSaygoodbye, mntmArthurSayhello;
	
	public ToolkitCommandViewer(CommandInterpreter model) {
		frame = new JFrame("Command Toolkit");
		this.aModel = model;
		this.aModel.addPropertyChangeListener(this);
		final int frameWidth = 500;
		final int frameHeight = 400;
		
		frame.setSize(frameWidth, frameHeight);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(false);
		
		JMenuBar mnSay = new JMenuBar();
		frame.setJMenuBar(mnSay);
		
		mnSayCommands = new JMenu("Say Commands");
		mnSay.add(mnSayCommands);
		
		mntmArthurSayhello = new JMenuItem("Say \"Hello\"");
		mnSayCommands.add(mntmArthurSayhello);
		
		mntmArthurSaygoodbye = new JMenuItem("Say \"Goodbye\"");
		mnSayCommands.add(mntmArthurSaygoodbye);
		
		final int cLabelUX = 30;
		final int cLabelUY = 32;
		final int cLabelLX = 73;
		final int cLabelLY = 16;
		JLabel lblCommand = new JLabel("Command:");
		lblCommand.setBounds(cLabelUX, cLabelUY, cLabelLX, cLabelLY);
		frame.getContentPane().add(lblCommand);
		
		final int eButtonUX = 169;
		final int eButtonUY = 60;
		final int eButtonLX = 155;
		final int eButtonLY = 29;
		btnExecuteCommand = new JButton("Execute Command");
		btnExecuteCommand.setBounds(eButtonUX, eButtonUY, eButtonLX, eButtonLY);
		frame.getContentPane().add(btnExecuteCommand);
		
		final int eLabelUX = 30;
		final int eLabelUY = 211;
		final int eLabelLX = 61;
		final int eLabelLY = 16;
		JLabel lblErrors = new JLabel("Errors:");
		lblErrors.setBounds(eLabelUX, eLabelUY, eLabelLX, eLabelLY);
		frame.getContentPane().add(lblErrors);
		
		final int tAreaUX = 40;
		final int tAreaUY = 239;
		final int tAreaLX = 435;
		final int tAreaLY = 110;
		errorPane = new JTextArea();
		errorPane.setBounds(tAreaUX, tAreaUY, tAreaLX, tAreaLY);
		frame.getContentPane().add(errorPane);
		
		final int pCommandUX = 30;
		final int pCommandUY = 110;
		final int pCommandLX = 155;
		final int pCommandLY = 16;
		JLabel lblPredefinedCommands = new JLabel("Predefined Commands:");
		lblPredefinedCommands.setBounds(pCommandUX, pCommandUY, pCommandLX, pCommandLY);
		frame.getContentPane().add(lblPredefinedCommands);
		
		final int bRightUX = 60;
		final int bRightUY = 138;
		final int bRightLX = 177;
		final int bRightLY = 29;
		btnMoveArthurRight = new JButton("Move Arthur +100 + 0");
		btnMoveArthurRight.setBounds(bRightUX, bRightUY, bRightLX, bRightLY);
		frame.getContentPane().add(btnMoveArthurRight);
		
		final int bLeftUX = 60;
		final int bLeftUY = 180;
		final int bLeftLX = 177;
		final int bLeftLY = 29;
		btnMoveArthurLeft = new JButton("Move Arthur - 100 + 0");
		btnMoveArthurLeft.setBounds(bLeftUX, bLeftUY, bLeftLX, bLeftLY);
		frame.getContentPane().add(btnMoveArthurLeft);
		
		
		final int bUpUX = 272;
		final int bUpUY = 138;
		final int bUpLX = 177;
		final int bUpLY = 29;
		btnMoveArthurUp = new JButton("Move Arthur + 0 +100");
		btnMoveArthurUp.setBounds(bUpUX, bUpUY, bUpLX , bUpLY);
		frame.getContentPane().add(btnMoveArthurUp);
		
		final int bDownUX = 272;
		final int bDownUY = 180;
		final int bDownLX = 177;
		final int bDownLY = 29;
		btnMoveArthurDown = new JButton("Move Arthur + 0 - 100");
		btnMoveArthurDown.setBounds(bDownUX, bDownUY, bDownLX, bDownLY);
		frame.getContentPane().add(btnMoveArthurDown);
		
		final int cFieldUX = 115;
		final int cFieldUY = 26;
		final int cFieldLX = 360;
		final int cFieldLY = 28;
		commandField = new JTextField();
		commandField.setBounds(cFieldUX, cFieldUY, cFieldLX, cFieldLY);
		frame.getContentPane().add(commandField);
		final int columnNumber = 10;
		commandField.setColumns(columnNumber);

		
		frame.setVisible(true);
	}
	

	public void propertyChange(PropertyChangeEvent evt) {
		this.commandField.setText(this.aModel.getCommand());
		this.errorPane.setText(this.aModel.getErrors());
	}
	
	public JButton getExecuteButton() {return this.btnExecuteCommand;}
	
	public JButton getArthurRight() { return this.btnMoveArthurRight;}
	
	public JButton getArthurLeft() {return this.btnMoveArthurLeft;}
	
	public JButton getArthurUp() {return this.btnMoveArthurUp;}
	
	public JButton getArthurDown() {return this.btnMoveArthurDown;}
	
	public JTextField getCommandField() {return this.commandField;}
	
	public JMenuItem getArthurHello() {return this.mntmArthurSayhello;}
	
	public JMenuItem getArthurGoodbye(){return this.mntmArthurSaygoodbye;}

	public void placeFrameOnTop() {this.frame.toFront();}
	
	public JFrame getFrame() {return this.frame;}

	public JTextArea getErrorField() {return this.errorPane;}
	
	
}
