package com.db.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.db.DBManager;

public class DBConnector extends JFrame implements ActionListener  {
	private static final long serialVersionUID = 7400126110700452535L;
	
	public JButton chooseButton = new JButton("...");
	public JLabel label = new JLabel("Choose DataBase to connect...");
	public JPanel connectPanel = new JPanel(new GridBagLayout());
	public JButton connectButton = new JButton("Connect");
	public JButton cancelButton = new JButton("Cancel");
	public JTextField txtField  = new JTextField(20);
	

	public static void main(String[] args) {
		DBConnector application = new DBConnector();
		application.run();
	}

	private void run() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Connect...");
		
		txtField.setEditable(false);
		txtField.setEnabled(true);
		
		chooseButton.addActionListener(this);
		connectButton.addActionListener(this);
		connectButton.setMnemonic(KeyEvent.VK_ENTER);
		cancelButton.addActionListener(this);
		cancelButton.setMnemonic(KeyEvent.VK_C);
		
		connectPanel.add(label, new GridBagConstraints(0, 0, 2, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,new Insets(5, 10, 5, 10), 0, 0));
		
		connectPanel.add(txtField, new GridBagConstraints(0, 1, 2, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,new Insets(5, 10, 5, 10), 0, 0));
		connectPanel.add(chooseButton, new GridBagConstraints(2, 1, 1, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,new Insets(5, 10, 5, 10), 0, 0));
		
		connectPanel.add(connectButton,new GridBagConstraints(0, 2, 1, 1, 1, 1,GridBagConstraints.LINE_START,GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));
		connectPanel.add(cancelButton,new GridBagConstraints(1, 2, 1, 1, 1, 1,GridBagConstraints.LINE_START,GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 10), 0, 0));

		add(connectPanel);
		
		pack();
		setVisible(true);
		setFraimLocation(this);
	}

	public static void setFraimLocation(JFrame mainFraim) {
		Dimension ssize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension framesize = mainFraim.getSize();
		int x = (int) ( ssize.getWidth() - framesize.getWidth() ) / 2;
		int y = (int) ( ssize.getHeight() - framesize.getHeight() ) / 2;
		mainFraim.setLocation( x, y );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chooseButton) {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(connectPanel);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				txtField.setText(file.getAbsolutePath());
			}
		} else if (e.getSource() == connectButton) {
			if (txtField.getText() == "") {
				System.out.println("Please choose database file to be used / to be created.");
			} else {
				DBManager dbManager = new DBManager();
				dbManager.connect("//home//difince//DataBase//art.db");
				DBQueryBrowser apFraim = new DBQueryBrowser(dbManager);
				apFraim.run();
				setVisible(false);
				dispose();
			}
		} else if (e.getSource() == cancelButton) {
			setVisible(false);
			dispose();
		}
	}

}
