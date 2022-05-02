package main.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class signupController implements Initializable {
    ObservableList<String> typelist = FXCollections.observableArrayList("monthly","yearly");
    ObservableList<String> asalist = FXCollections.observableArrayList("member","teacher");
    ObservableList<String> bloodlist = FXCollections.observableArrayList("A+","A-","B+","B-","AB+","AB-","O+","O-");
    private Connection con;
    private final  Base main = new Base();
    static membership membership = new membership();
    static user user=new user();
    static family family = new family();
    @FXML
    private TextField idnumber;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    @FXML
    private ToggleGroup sex;
    @FXML
    private RadioButton male;
    @FXML
    private RadioButton female;
    @FXML
    private TextField password;
    @FXML
    private TextField confirm;
    @FXML
    private ComboBox<String> asa = new ComboBox<String>();
    @FXML
    private DatePicker birthdate;

    private static String selected;
    @FXML
    private TextField telephone;
    @FXML
    private TextField disabled;
    @FXML
    private TextField nofamily;
    @FXML
    private TextField nounemployed;
    @FXML
    private TextField nodisabled;
    @FXML
    private TextField income;
    @FXML
    private ToggleGroup mstatus;
    @FXML
    private RadioButton married;
    @FXML
    private RadioButton single;
    @FXML
    private RadioButton divorced;
    @FXML
    private ComboBox<String> bloodtype = new ComboBox<String>();
    @FXML
    private Label msg;
    @FXML
    private Label msg1;
    @FXML
    private ComboBox<String> membertype = new ComboBox<String>();
    @FXML
    private Label price;
    @FXML
    private Label validfor;
    @FXML
    private Label cardno;

    @FXML
    public void getDate() {
      user.setBirthdate(birthdate.getValue());

    }

    @FXML
    public void chooseasa() {
        selected=asa.getValue();

    }
    @FXML
    public void signingup() {
        if(idnumber.getText().isEmpty()||firstname.getText().isEmpty()||lastname.getText().isEmpty()||phonenumber.getText().isEmpty()||address.getText().isEmpty()||email.getText().isEmpty()||
        sex.getSelectedToggle()==null||password.getText().isEmpty()||confirm.getText().isEmpty()||selected==null)
        {
            msg.setText("please do not leave an empty field");
        }
        //right format for integers
        user.setID(Integer.parseInt(idnumber.getText()));
        user.setFname(firstname.getText());
        user.setLname(lastname.getText());
        user.setPnumber(Integer.parseInt(phonenumber.getText()));
        user.set$address(address.getText());
        user.setEmail(email.getText());
        if(female.isSelected())
            user.setGender("female");
        else if (male.isSelected())
            user.setGender("male");
        user.setPass(Integer.parseInt(password.getText()));
        if(user.getPass()==Integer.parseInt(confirm.getText()))
        {
            msg.setTextFill(Color.valueOf("#698ee4"));
            msg.setText("correct");
            if(selected.equals("member"))
            {
                main.changeScene("member.fxml");
                try {
                    con = main.getConnection();
                    String sql = "Insert into member (SSN,F_NAME,L_NAME,PHONE_NUMBER,ADDRESS,EMAIL,GENDER,BIRTHDATE,PASSWORD,PHOTO,BLOOD_TYPE,DISABILITY," +
                            "MARTIAL_STATUS,MFLAG,ELECTED,NOVOTES) " + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement stmt = con.prepareStatement( sql);
                    stmt.setInt(1,user.getID());
                    stmt.setString(2,user.getFname() );
                    stmt.setString(3,user.getLname() );
                    stmt.setInt(4,user.getPnumber() );
                    stmt.setString(5,user.get$address());
                    stmt.setString(6, user.getEmail());
                    stmt.setString(7, user.getGender() );
                    stmt.setDate(8, Date.valueOf(user.getBirthdate()) ); //check
                    stmt.setInt(9,user.getPass() );
                    stmt.setBytes(10,user.getPhoto() );  //will do
                    stmt.setString(11, user.getBlood() );
                    stmt.setString(12, user.getDisable() );
                    stmt.setString(13, user.getStatus() );
                    stmt.setInt(14,user.getMngflag());
                    stmt.setInt(15,user.getElect() );
                    stmt.setInt(16,user.getVotes() );
                    stmt.executeUpdate();
                    stmt.close();
                    con.close();

            }
                catch (Exception ex)
                {
                     ex.printStackTrace();
                }

                }
            else if (selected=="teacher")
            {
                try {
                    con = main.getConnection();
                    String sql = "Insert into TEACHER (T_SSN,FNAME,LNAME,PHONENUMBER,T_ADDRESS,T_EMAIL,T_GENDER,T_BIRTHDATE,PASSWORD) " + "values (?,?,?,?,?,?,?,?,?)";
                    PreparedStatement stmt = con.prepareStatement( sql);
                    stmt.setInt(1,user.getID());
                    stmt.setString(2,user.getFname() );
                    stmt.setString(3,user.getLname() );
                    stmt.setInt(4,user.getPnumber() );
                    stmt.setString(5,user.get$address());
                    stmt.setString(6, user.getEmail());
                    stmt.setString(7, user.getGender() );
                    stmt.setDate(8, Date.valueOf(user.getBirthdate()) ); //check
                    stmt.setInt(9,user.getPass() );
                    stmt.executeUpdate();
                    stmt.close();
                    con.close();

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                main.popupScene("popupsuccesss.fxml");
            }
        }
        else
            msg.setText("the password and the confirm fields aren't identical");



    }
    @FXML
    public void chooseblood(){
        user.setBlood(bloodtype.getValue());
    }
    @FXML
    public void addPhoto(){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if(file != null)
            try {
                InputStream fin = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];

                for (int readNum; (readNum = fin.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum);
                }
                    user.setPhoto(bos.toByteArray());


            } catch (IOException ex) {
                Logger.getLogger("ss");
            }
    }
    @FXML
    public void choosemembership(){
        if (disabled.getText().isEmpty()||mstatus.getSelectedToggle()==null||bloodtype.getValue()==null||nofamily.getText().isEmpty()||telephone.getText().isEmpty()||
        nounemployed.getText().isEmpty()||nodisabled.getText().isEmpty()||income.getText().isEmpty())
        {
            msg1.setText("please fill the fields with all the information");
        }
        family.setTelephone(Integer.parseInt(telephone.getText()));
        family.setMincome(Integer.parseInt(income.getText()));
        family.setNodisable(Integer.parseInt(nodisabled.getText()));
        family.setNounemployed(Integer.parseInt(nounemployed.getText()));
        family.setNomember(Integer.parseInt(nofamily.getText()));
        user.setDisable(disabled.getText());
        if(single.isSelected())
            user.setStatus("single");
        else if (married.isSelected())
            user.setStatus("married");
        else if (divorced.isSelected())
            user.setStatus("divorced");
        String sql = "update member set DISABILITY  = ?, BLOOD_TYPE = ?,MARTIAL_STATUS= ?, PHOTO=? where SSN = ?";
        try {
            con = main.getConnection();
            PreparedStatement stmt = con.prepareStatement( sql);
            stmt.setString(1,user.getDisable());
            stmt.setString(2, user.getBlood());
            stmt.setString(3,user.getStatus());
            stmt.setBytes(4, user.getPhoto());
            stmt.setInt(5, user.getID());
            String sql0 = "Insert into memberfamily (mobilenumber, no_familymembers, no_disabled, no_unemployed, monthlyincome, m_ssn) " + "values (?,?,?,?,?,?)";
            PreparedStatement stmt0 = con.prepareStatement( sql0);
            stmt0.setInt(1, family.getTelephone());
            stmt0.setInt(2, family.getNomember());
            stmt0.setInt(3, family.getNodisable());
            stmt0.setInt(4, family.getNounemployed());
            stmt0.setInt(5, family.getMincome());
            stmt0.setInt(6, user.getID());
            stmt.executeUpdate();
            stmt0.executeUpdate();
            stmt.close();
            stmt0.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         main.popupScene("membership_popup.fxml");
    }
    @FXML
    public void choosetype(){
        membership.setType(membertype.getValue());
        membership.setM_ID(user.getID());
        try {
            con = main.getConnection();
            String sql = "Insert into MEMBERSHIP (STARTINGDATE, expiring_date, membership_type, price, m_ssn) " + "values (?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement( sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1,Date.valueOf(membership.getStarting()));
            stmt.setDate(2, Date.valueOf(membership.getExpiring()) );
            stmt.setString(3,membership.getType());
            stmt.setInt(4,membership.getPrice());
            stmt.setInt(5,membership.getM_ID());
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery("select CARD_SEQ.currval from DUAL");
            if(rs != null && rs.next())
                membership.setCardnumber(rs.getInt(1));
            stmt.close();
            con.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        validfor.setText("Expiring Date :"+membership.getExpiring());
        cardno.setText("Card Number :"+membership.getCardnumber());
        price.setText("Price :"+membership.getPrice());

    }
    @FXML
    public void addmembership(){

        main.changeScene("member_dashboard.fxml");
        main.closePopup();
    }
    @FXML
    public void ok()
    {
        main.changeScene("member_dashboard.fxml");  ///for now
        main.closePopup();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        asa.setItems(asalist);
        bloodtype.setItems(bloodlist);
        membertype.setItems(typelist);

    }
}
