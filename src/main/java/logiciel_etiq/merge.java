package logiciel_etiq;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class merge extends JFrame {

    private JButton browseButton;
    private JFileChooser fileChooser;


    String jdbcURL = "jdbc:sqlite:C:/GCOBAR/IEsqllite.db";



    public merge() {
        super("Excel Database Updater");

        browseButton = new JButton("Browse Excel File");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileChooser();
            }
        });

        JPanel panel = new JPanel();
        panel.add(browseButton);

        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showFileChooser() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xls", "xlsx");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String excelFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            updateDatabaseFromExcel(excelFilePath);
        }
    }

    private void updateDatabaseFromExcel(String excelFilePath) {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            connection = DriverManager.getConnection(jdbcURL);
        }

        catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(excelFilePath);

             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            for (Row row : sheet) {
                Cell primaryKeyCell = row.getCell(0);
                Cell valueCell = row.getCell(1);

                // Assuming your primary key is of String type in the database
                String primaryKeyValue = primaryKeyCell.getStringCellValue();
                String newValue = valueCell.getStringCellValue();

                updateDatabaseField(connection, primaryKeyValue, newValue);
            }

            JOptionPane.showMessageDialog(this, "Database updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating database: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDatabaseField(Connection connection, String primaryKeyValue, String newValue) {
        // Replace "your_table_name" with the actual table name in your database
        String sql = "UPDATE etiquette_tablette SET key_licence = ? WHERE sn = ? ";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newValue);
            statement.setString(2, primaryKeyValue);
            statement.executeUpdate();
            System.out.println("Updated value for primary key: " + primaryKeyValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new merge();
    }
}
