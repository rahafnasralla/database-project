package main.project;

import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class nominateController {
    private final  Base main = new Base();
    private Connection con;
    private user user = userholder.getInstance().getUser();

    @FXML
    public void nominate() {
        String sql = "update member set ELECTED = ? where SSN = ?";
        try {
            con = main.getConnection();
            PreparedStatement stmt = con.prepareStatement( sql);
            stmt.setInt(1,1);
            stmt.setInt(2, user.getID());
            stmt.executeUpdate();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        main.closePopup();
    }

    @FXML
    public void cancel() {
        main.closePopup();
    }
}
