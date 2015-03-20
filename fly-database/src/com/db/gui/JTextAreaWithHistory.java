package com.db.gui;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.TextAction;

public class JTextAreaWithHistory extends JTextArea  {
	private static final long serialVersionUID = 4984112253309939247L;
	private final static int MAX_SIZE = 15;
	private List<String> historyQueue = new LinkedList<>();
    private Integer currPossition = null; 
	
	public JTextAreaWithHistory(int row, int column){
		super(row, column);
		addStroke();
	}
	
	private void addStroke() {
		Keymap parent = this.getKeymap();
		Keymap newmap = JTextComponent.addKeymap("KeymapExampleMap", parent);

		KeyStroke left = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,InputEvent.ALT_MASK);
		Action actionLeft = new LeftAction(this); // an inner class (defined below)
		newmap.addActionForKeyStroke(left, actionLeft);

		KeyStroke right = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,InputEvent.ALT_MASK);
		Action actionRight = new RightAction(this);
		newmap.addActionForKeyStroke(right, actionRight);

		this.setKeymap(newmap);
	}
	
	public void addQuery(String query) {
		if (historyQueue.size() == MAX_SIZE) {
			historyQueue.remove(0);
			historyQueue.add(query);
			currPossition = historyQueue.size() - 1;
		}
		if (!historyQueue.isEmpty() && historyQueue.get(historyQueue.size() - 1).equals(query))
			return;
		historyQueue.add(query);
		currPossition = historyQueue.size()-1;
	}
	

	private class RightAction extends TextAction{

		private static final long serialVersionUID = 1L;
		private JTextAreaWithHistory txtAreaWithHistory;

		public RightAction(JTextAreaWithHistory txtAreaWithHistory) {
			super("RightAction");
			this.txtAreaWithHistory = txtAreaWithHistory;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("right");
			if (currPossition == null){
				currPossition = historyQueue.size()-1;
				return;
			}else{
				if (historyQueue.size() > currPossition + 1) {
					currPossition = currPossition + 1;
					txtAreaWithHistory.setText(historyQueue.get(currPossition));
				}
			}
		}
		
	}
	
	
	private class LeftAction extends TextAction{

		private static final long serialVersionUID = 1L;
		private JTextAreaWithHistory txtAreaWithHistory;

		public LeftAction(JTextAreaWithHistory txtAreaWithHistory) {
			super("LeftAction");
			this.txtAreaWithHistory = txtAreaWithHistory;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("left");
			if (currPossition == null)
				currPossition = historyQueue.size()-1;
			if(currPossition == 0){
				txtAreaWithHistory.setText(historyQueue.get(currPossition));
			} else {
				currPossition = currPossition - 1;
				txtAreaWithHistory.setText(historyQueue.get(currPossition));
			}
		}
		
	}
}
