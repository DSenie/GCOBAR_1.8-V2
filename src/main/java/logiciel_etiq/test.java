package logiciel_etiq;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class test{
    DefaultTableModel model;
    JTable table;
    public static void main(String[] args) {
        new test();
    }

    public test(){
        JFrame frame = new JFrame("Remove a column from a JTable");
        JPanel panel = new JPanel();
        String data[][] = {{"Vinod","MCA","Computer"},
                {"Deepak","PGDCA","History"},
                {"Ranjan","M.SC.","Biology"},
                {"Radha","BCA","Computer"}};
        String col[] = {"Name","Course","Subject"};
        model = new DefaultTableModel(data, col);
        table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.yellow);
        //remove column at second position
        Remove(table,2);
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);
        frame.add(panel);
        frame.setSize(500,150);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Remove(JTable table, int col_index){
        TableColumn tcol = table.getColumnModel().getColumn(col_index);
        table.removeColumn(tcol);
    }
}