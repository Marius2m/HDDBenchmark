import java.awt.*;
import java.sql.*;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


@SuppressWarnings("serial")
public class DatabaseTable extends JFrame {
	JTable table;
	DefaultTableModel model;
	JTextField filteredText;
	TableRowSorter<DefaultTableModel> sorter;
	
	public DatabaseTable() {
		setLayout(new FlowLayout());
		
		Object [] columnNames = {"ID", "Nickname", "Drive Type", "Laptop/Desktop Model", "Score"};
		model = new DefaultTableModel(null, columnNames);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(700, 400));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		sorter = new TableRowSorter<DefaultTableModel>(model);
		sorter.setComparator(0, new IntComparator());
		sorter.setComparator(4, new DoubleComparator());
		table.setRowSorter(sorter);
		
		JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);
		
		filteredText = new JTextField();
		filteredText.setPreferredSize(new Dimension(100, 30));
		//Whenever filterText changes, invoke newFilter.
        filteredText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        JPanel form = new JPanel();
        JLabel l1 = new JLabel("Search", SwingConstants.TRAILING);
        l1.setLabelFor(filteredText);
        form.add(l1);
        form.add(filteredText);
        
        add(form);
      
		setTitle("Benchmark Results");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);

		
		
	}
	public void DisplayTable(Statement myStmt) throws SQLException {
		String sql = "select * from benchmark_hdd";
		ResultSet myRS = myStmt.executeQuery(sql);
		while(myRS.next()) {
			Object[] row = new Object[] {myRS.getInt(1), myRS.getString(2), myRS.getString(3), myRS.getString(4), myRS.getDouble(5)};
			model.addRow(row);
		}
	}
	//helper method for column sorting
	private class IntComparator implements Comparator<Object> {
        public int compare(Object o1, Object o2) {
            Integer int1 = (Integer)o1;
            Integer int2 = (Integer)o2;
            return int1.compareTo(int2);
        }
    }
	//helper method for column sorting
	private class DoubleComparator implements Comparator<Object> {
        public int compare(Object o1, Object o2) {
            Double double1 = (Double)o1;
            Double double2 = (Double)o2;
            return double1.compareTo(double2);
        }
    }
	//method that enables filtering using the JTextField "search"
	public void newFilter() {
		RowFilter<DefaultTableModel, Object> rf = null;
		try {
			rf = RowFilter.regexFilter(filteredText.getText(), 0, 1, 2, 3, 4);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}	
}
