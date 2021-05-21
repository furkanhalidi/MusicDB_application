import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPage extends JFrame{


    public static void main(String[] args) {

    }

    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JTextField emailText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JTextField AdminEmailText;
    private JPasswordField AdminPassText;
    private JButton AdminLogin;
    private JPanel tabbedKullanici;
    private JPanel tabbedAdmin;
    private DBConnection conn = new DBConnection();
    public int kull_Id;

    public LoginPage() {

        add(panel1);
        setSize(600,400);
        setTitle("MÜZİK DOSYAM");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = emailText.getText();
                String sifre = passwordText.getText();
                String giris = "kullanici";

                try {
                    Connection con = conn.connDb();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from musicdb.kull where email='" + email + "' and sifre='" + sifre + "' and giris='" + giris + "'");

                if (rs.next()) {

                        ResultSet rs1 = st.executeQuery("select kull_Id from musicdb.kull where email='" + email + "'");
                        if(rs1.next()){
                            kull_Id = Integer.parseInt(rs1.getString(1));

                            dispose();
                            KullaniciPane kp = new KullaniciPane(kull_Id);
                            kp.show();
                        }

                    }
                    con.close();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }

        });
        AdminLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String email = AdminEmailText.getText();
                String sifre = AdminPassText.getText();
                String giris = "admin";


                try {
                    Connection con = conn.connDb();
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from musicdb.kull where email='" + email + "' and sifre='" + sifre + "' and giris='" + giris + "'");

                    if (rs.next()) {

                        dispose();
                        AdminPage aP = new AdminPage();
                        aP.show();

                    }

                    con.close();


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });
    }

}

