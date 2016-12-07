import javax.swing.table.AbstractTableModel;

/**
 * Created by jcduf on 12/6/2016.
 */
public class MyTableModel extends AbstractTableModel{
    private int rowCount;
    private int columnCount;
    private String[] columns;
    private Object[][] data;

    public MyTableModel(String[] cols, Object[][] dat){
        columns = cols;
        data = dat;
        columnCount = columns.length;
        rowCount = dat.length;
    }

    @Override
    public int getRowCount(){
        return rowCount;
    }

    @Override
    public Object getValueAt(int i, int j){
        return data[i][j];
    }

    @Override
    public int getColumnCount(){
        return columnCount;
    }
}
