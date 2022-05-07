package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class reportsController implements Initializable {
    private static Connection con;
    private static final Base main = new Base();
    String selected;
    ObservableList<String> list = FXCollections.observableArrayList("blood type report","disability report");
    @FXML
    private ComboBox<String> choose;

    @FXML
    private PieChart pie;

    @FXML
    void choice(ActionEvent event) {
        selected = choose.getValue();
    }

    @FXML
    void generate(ActionEvent event) throws FileNotFoundException {
        if (selected == "blood type report") {

            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                String url ="jdbc:oracle:thin:@localhost:1521:xe";
                Connection con = DriverManager.getConnection(url , "c##rova", "123456");
                FileInputStream input = new FileInputStream(new File("C:\\Users\\rova\\IdeaProjects\\project\\src\\main\\resources\\JRXMLs\\first.jrxml"));
                JasperDesign jd = JRXmlLoader.load(input);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JFrame f = new JFrame("Blood Type");
                f.getContentPane().add(new JRViewer(jp));
                f.setSize(700,700);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "hi");

            }
        }
        else if (selected=="disability report")
        {
            try {
                DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                String url ="jdbc:oracle:thin:@localhost:1521:xe";
                Connection con = DriverManager.getConnection(url , "c##rova", "123456");
                FileInputStream input = new FileInputStream(new File("C:\\Users\\rova\\IdeaProjects\\project\\src\\main\\resources\\JRXMLs\\second.jrxml"));
                JasperDesign jd = JRXmlLoader.load(input);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
                JFrame f = new JFrame("Disability");
                f.getContentPane().add(new JRViewer(jp));
                f.setSize(700,700);
                f.setVisible(true);
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
        }
    }
    @FXML
    void back(ActionEvent event) {
         main.changeScene("admin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choose.setItems(list);
        if(pie != null) {
            ObservableList<PieChart.Data> chartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Female", DbWrapper.femalecount()),
                            new PieChart.Data("Male", DbWrapper.malecount()));
            pie.setData(chartData);
            pie.setStartAngle(90);
        }
    }
}

