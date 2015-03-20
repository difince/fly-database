package com.db.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.db.DBManager;
import com.db.statement.SQLStatement;
import com.db.statement.SelectStatement;
import com.db.statement.ShowTableStatement;
import com.db.stucture.Cursor;
import com.db.stucture.Table;
import com.db.utils.Messages;

public class DBQueryBrowser extends JFrame implements ActionListener, TableModelListener  {
	private static final long serialVersionUID = -175186506409202053L;
	private static String selectTemplate = "select * from %s";
	
	private int tabNameIdx = 1;
	private final int tabMaxIdx= 15;
	private TabCreator tabCreate;
	private TabRemover tabRemove;
	
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	private JButton executeBut = new JButton("Execute");
	private JButton stopButton = new JButton("   Clear  ");
	
	private JMenuItem removeMenueItem = new JMenuItem("Remove tab");
	private JMenuItem addMenueItem = new JMenuItem("Create new tab");
	
	private TablesModel rightDataModel = new TablesModel();
	private JTable rightTable = new JTable(rightDataModel);
	
	private DBManager dbManager;
	
	
	public DBQueryBrowser(DBManager dbManger){
		this.dbManager = dbManger;
		dbManager.database.addObserver(rightDataModel);
	}
	
	public void run(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Fly-DataBase Query Browser");
		JPanel mainPanle = new JPanel(new GridBagLayout());
		JTextAreaWithHistory txtArea = new JTextAreaWithHistory(5,5);
		JTextArea stateArea = new JTextArea(1,5);
		txtArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		createMenueBar();
		
		JTable mainTable = new JTable(new DefaultTableModel());
		JScrollPane errScrPanel = new JScrollPane(stateArea,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane mainTableScrPanel = new JScrollPane(mainTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane txtScrollPanel = new JScrollPane(txtArea,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JSplitPane leftSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, txtScrollPanel, mainTableScrPanel);
		JSplitPane mainSpPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, leftSplitPanel, errScrPanel);
		mainSpPanel.setResizeWeight(1);
		
		tabbedPane.addTab("Tab " + tabNameIdx, mainSpPanel);
		initTabComponent();
		Component rightScrPanel = createRightPanel(dbManager);
		JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, rightScrPanel);
		
		addListeners();

		splitPanel.setResizeWeight(1);
		mainPanle.add(splitPanel,new GridBagConstraints(0 , 0, 1, 1, 1, 1,GridBagConstraints.CENTER, GridBagConstraints.BOTH,new Insets(0, 5, 0, 5), 0, 0));
		
		this.add(mainPanle);
		this.setPreferredSize(new Dimension(900, 700));
		this.setMinimumSize(new Dimension(750, 650));
		pack();
		setVisible(true);
		DBConnector.setFraimLocation(this);
	}

	private void addListeners() {
		executeBut.setContentAreaFilled(false);
		executeBut.addActionListener(this);
		executeBut.setMnemonic(KeyEvent.VK_ENTER);
		
		stopButton.setContentAreaFilled(false);
		stopButton.addActionListener(this);
		
		rightTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable) e.getSource();
				if (e.getClickCount() == 2) {
					JTextArea txt = getQueryArea();
					String tableName = (String) t.getValueAt(t.getSelectedRow(), t.getSelectedColumn());
					(txt).setText(String.format(selectTemplate, tableName));
				}
			}

		});
	}

	private void createMenueBar() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		tabCreate = new TabCreator();
		tabRemove = new TabRemover();
		
		addMenueItem.addActionListener(tabCreate);
		addMenueItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		
		removeMenueItem.addActionListener(tabRemove);
		removeMenueItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		
		fileMenu.add(addMenueItem);
		fileMenu.add(removeMenueItem);
		
		JMenu editMenu = new JMenu("Edit");
		mb.add(fileMenu);
		mb.add(editMenu);
		setJMenuBar(mb);
	}

	private Component createRightPanel(DBManager dbManager) {
		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightTable.setCellSelectionEnabled(true);
		rightTable.setAutoCreateRowSorter(true);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(rightTable.getModel());
		rightTable.setRowSorter(sorter);
		rightDataModel.addColumn("Tables");
		LinkedList<Table> tables = dbManager.database.tables;
		for (Table t : tables) {
			rightDataModel.insertRow(rightDataModel.getRowCount(), new Object[]{t.name});
		}
		JScrollPane scrollPane = new JScrollPane(rightTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(156, 100));
		rightPanel.add(executeBut,new GridBagConstraints(0 , 0, 1, 1, 0, 0,GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 5, 5, 5), 0, 0));
		rightPanel.add(stopButton,new GridBagConstraints(0 , 1, 1, 1, 0, 0,GridBagConstraints.WEST, GridBagConstraints.NONE,new Insets(5, 5, 5, 5), 0, 0));
		rightPanel.add(scrollPane,new GridBagConstraints(0 , 2, 1, 1, 1, 1,GridBagConstraints.WEST, GridBagConstraints.BOTH,new Insets(0, 5, 5, 5), 0, 0));
		return rightPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == executeBut) {
			JTextAreaWithHistory queryArea = getQueryArea(); 
			String query = queryArea.getText().trim();
			queryArea.addQuery(query);
			executeQuery(query);
		}else if(e.getSource() == stopButton){
			
		}
	}

	private void executeQuery(String query) {
		JTextArea stateArea = getStateArea();
		try {
			SQLStatement sqlStatement = (SQLStatement)dbManager.executeQuery(query);
			DefaultTableModel dataModel = (DefaultTableModel) getCurrTable().getModel();
			dataModel.getDataVector().removeAllElements();
			dataModel.setColumnIdentifiers(new String[]{});
			if(sqlStatement instanceof SelectStatement){
				Cursor cursor = ((SelectStatement)sqlStatement).cursor;
				dataModel.setColumnIdentifiers(cursor.getFieldNames());
				while (cursor.next()) {
					Object[] rslt = cursor.read();
					if (!isEmptyResult(rslt))
						dataModel.addRow(rslt);
				}
			}else if(sqlStatement instanceof ShowTableStatement){
				dataModel.setColumnIdentifiers(new String[]{ShowTableStatement.COLUMN_NAME});
				for (Object name : (List<Object>)((ShowTableStatement)sqlStatement).execute()) {
					dataModel.addRow(new Object[]{name});
				}
			}
			stateArea.setForeground(Color.black);
			stateArea.setText(Messages.QUERY_SUCCESSFULLY_EXECUTED.getMessage());
		} catch (Exception exp) {
			exp.printStackTrace();
			stateArea.setForeground(Color.red);
			stateArea.setText(exp.getMessage());
		}
	}

	private boolean isEmptyResult(Object[] rslt) {
		for (Object object : rslt) {
			if(object != null)
				return false;
		}
		return true;
	}

	private JTable getCurrTable() {
		JSplitPane splitPane = (JSplitPane)tabbedPane.getSelectedComponent();
		JScrollPane scrPane = (JScrollPane)((JSplitPane)splitPane.getComponents()[0]).getComponents()[1];
		return  (JTable)((JViewport) scrPane.getComponent(0)).getComponent(0);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
	}

	class TabCreator implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tabbedPane.getTabCount() < tabMaxIdx) {
				++tabNameIdx;
				tabbedPane.add("Tab " + (tabNameIdx), initNewTab());
				initTabComponent();
				tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
			}
			updateStateOnCreate();
			tabRemove.updateTabRemoveState();
		}

		private void updateStateOnCreate() {
			if (tabMaxIdx == tabbedPane.getTabCount())
				addMenueItem.setEnabled(false);
		}

		private void updateTabCreateState() {
			addMenueItem.setEnabled(true);
		}
	}
	
	class TabRemover implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (tabbedPane.getTabCount() > 1)
				tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
			updateStateOnRemove();
			tabCreate.updateTabCreateState();
		}

		private void updateStateOnRemove(){
			if (tabbedPane.getTabCount() == 1)
				removeMenueItem.setEnabled(false);
		}
		
		private void updateTabRemoveState() {
			removeMenueItem.setEnabled(true);
		}
	}
    
   private void initTabComponent() {
	   tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1,
                new ButtonTabComponent(tabbedPane,tabRemove));
	   
   }   
    
		private Component initNewTab() {
			JTextAreaWithHistory area = new JTextAreaWithHistory(5,5);
			JTextArea stateArea = new JTextArea();
			JTable table = new JTable(new DefaultTableModel());
			area.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			JScrollPane mainTableScrPanel = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JScrollPane txtScrPane = new JScrollPane(area,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JSplitPane leftSplitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, txtScrPane, mainTableScrPanel);
			JScrollPane errScrPanel = new JScrollPane(stateArea,  ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, leftSplitPanel, errScrPanel);
			sp.setResizeWeight(1);
			return sp;
		}
		
		private JTextAreaWithHistory getQueryArea() {
			JSplitPane splitPane = (JSplitPane)tabbedPane.getSelectedComponent();
			JScrollPane scrPane = (JScrollPane)((JSplitPane)splitPane.getComponents()[0]).getComponents()[0];
			return (JTextAreaWithHistory) ((JViewport)scrPane.getComponent(0)).getComponent(0);
		}
		
		private JTextArea getStateArea() {
			JSplitPane splitPane = (JSplitPane)tabbedPane.getSelectedComponent();
			JScrollPane scrPane =(JScrollPane)splitPane.getComponents()[1];
			return (JTextArea) ((JViewport)scrPane.getComponent(0)).getComponent(0);
		}
}
