/**
 * Created by jcduf on 12/6/2016.
 */
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserInterface extends JFrame implements ActionListener {

    public JButton productButton, employeeButton, compareButton, stockButton, deptButton;
    public JTextField inputField;
    public Query query;
    public JtableHelper tableHelper;
    public MyTableModel model;
    public Object[][] data = new Object[5][7];
    public String[] columns = new String[] { " ", " ", " ", " ", " ", " ", " "};
    public JTable table;

    public Object[][] tdata = new Object[][] {
                {1, "John", 40.0, false },
                {2, "Rambo", 70.0, false },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true },
                {3, "Zorro", 60.0, true }
        };

    public String[] tcolumns = new String[] {"Id", "Name", "Hourly Rate", "Part Time"};

    public UserInterface(){
        this.query = new Query();
        this.setLayout(new BorderLayout());

        Container contentPane = this.getContentPane();


        JPanel buttonPanel = new JPanel();
        JPanel tablePanel = new JPanel();
        JPanel searchPanel = new JPanel();

        //JButton searchButton = new JButton("search");
        inputField = new JTextField(40);
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(inputField);
        //searchPanel.add(searchButton);

        productButton = new JButton("Find Product");
        productButton.setToolTipText("Enter product name.");
        productButton.addActionListener(this);
        //button1.setBounds(0, 25, 100, 50);
        compareButton = new JButton("Compare Products");
        compareButton.setToolTipText("Enter product names separated by a comma.");
        compareButton.addActionListener(this);
        //button2.setBounds(100, 25, 100, 50);
        employeeButton = new JButton("Find Employee");
        employeeButton.setToolTipText("Enter product name.");
        employeeButton.addActionListener(this);
        //button3.setBounds(200, 25, 100, 50);
        stockButton = new JButton("Check Stock");
        stockButton.setToolTipText("Enter product name and zip code of store separated by a comma.");
        stockButton.addActionListener(this);
        //button4.setBounds(300, 25, 100, 50);
        deptButton = new JButton("Search Department");
        deptButton.setToolTipText("Enter department name.");
        deptButton.addActionListener(this);
        //button5.setBounds(400, 25, 100, 50);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(productButton);
        buttonPanel.add(compareButton);
        buttonPanel.add(employeeButton);
        buttonPanel.add(stockButton);
        buttonPanel.add(deptButton);

        table = new JTable(data, columns);
        //table.setBounds(300, 200, 400, 200);
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setBounds(300, 200, 400, 200);
        tablePanel.add(scrollPane);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(searchPanel, BorderLayout.NORTH);
        this.add(tablePanel, BorderLayout.SOUTH);
        this.setTitle("Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInterface();
            }
        });
    }

    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if (inputField.getText() != null) {
            String input = inputField.getText();
            if (source == productButton) {
                tableHelper = query.findProduct(input);
                //model = new MyTableModel(tcolumns, tdata);
                model = new MyTableModel(tableHelper.getHeaders(), tableHelper.getData());
                table.setModel(model);
                model.fireTableDataChanged();
            } else if (source == employeeButton) {
                tableHelper = query.findEmp();
                model = new MyTableModel(tableHelper.getHeaders(), tableHelper.getData());
                table.setModel(model);
                model.fireTableDataChanged();
            } else if (source == stockButton) {
                String[] strings = breakUpString(input);
                tableHelper = query.inStock(strings[0], Integer.parseInt(strings[1]));
                model = new MyTableModel(tableHelper.getHeaders(), tableHelper.getData());
                table.setModel(model);
                model.fireTableDataChanged();
            } else if (source == deptButton) {
                tableHelper = query.deptSearch(input);
                model = new MyTableModel(tableHelper.getHeaders(), tableHelper.getData());
                table.setModel(model);
                model.fireTableDataChanged();
            } else if (source == compareButton) {
                String[] strings = breakUpString(input);
                tableHelper = query.compareProduct(strings[0], strings[1]);
                model = new MyTableModel(tableHelper.getHeaders(), tableHelper.getData());
                table.setModel(model);
                model.fireTableDataChanged();
            }
        }
    }

    public String[] breakUpString(String origString){
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        //remove any whitespace
        origString = origString.replaceAll("\\s+","");
        //separate two words divided by a comma
        int commaIndex = 0;
        for(int i = 0; i < origString.length(); i++){
            if(origString.charAt(i) == ','){
                commaIndex = i;
                break;
            }
        }

        for(int i = 0; i < commaIndex; i++){
            sb.append(origString.charAt(i));
        }

        for(int i = commaIndex + 1; i < origString.length(); i++){
            sb2.append(origString.charAt(i));
        }

        return new String[] {sb.toString(), sb2.toString()};
    }
}