package grail.toolkit;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public interface CommandController extends ActionListener {
	public JMenuItem getMenuItem();
	public JMenuItem getMenuItemTwo();
	public JButton getButton();
	public JButton getButtonTwo();
	public JButton getButtonThree();
	public JButton getButtonFour();
}
