package main.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class reportsController {

    @FXML
    private ComboBox<String> choose;

    @FXML
    private PieChart pie;

    @FXML
    void choice(ActionEvent event) {

    }

    @FXML
    void generate(ActionEvent event) throws FileNotFoundException {

        try{
//            FileInputStream input = new FileInputStream(new File("first.jrxml")) ;JasperDesign jd= JRXmlLoader .load(input) ;
//            JasperReport jr=JasperCompileManager.compileReport(jd);
//            JasperPrint jp= JasperFillManager.fillReport(jr , null , con) ;
//            JFrame f = new JFrame("Stocktaking") ;
//            f.getContentPane().add(new JRViewer(jp)) ;
//            f.pack();
//            f.setVisible(true);
        }
        catch ( Exception ex)
        {
             ex.printStackTrace();
        }
    }

}

