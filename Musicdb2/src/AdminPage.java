import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class AdminPage extends JFrame{


    private JPanel panel2;
    private JButton albumButton;
    private JButton sarkiButton;
    private JButton sanatciButton;
    private JTabbedPane tabbedPane1;
    private JButton ekleButton;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JTabbedPane tabbedPane2;
    private JTextField sarkiText1;
    private JTextField sarkiText7;
    private JTextField sarkiText6;
    private JTextField sarkiText5;
    private JTextField sarkiText4;
    private JTextField sarkiText2;
    private JTextField sarkiText3;
    private JTextField sarkiText8;
    private JTextField sanatciText1;
    private JTextField sanatciText2;
    private JTextField sanatciText3;
    private JButton sanatciEkleButton;
    private JTextField albumText1;
    private JTextField albumText2;
    private JTextField albumText3;
    private JTextField albumText4;
    private JTextField albumText5;
    private JTextField albumText6;
    private JButton albumEkleButton;
    private JButton sarkiSilButton;
    private JButton albumSilButton;
    private JButton sanatciSilButton;
    private JButton sarkiGuncelleButton;
    private JButton albumGuncelleButton;
    private JButton sanatciGuncelleButton;
    private JTable JTable_Sarki;



    private DBConnection conn = new DBConnection();
    Statement st = null;
    ResultSet rs = null;

    Connection con;
    PreparedStatement pst = null;

    public static void main(String[] args) {

    }

    void sarki_table(){


        try {
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from musicdb.sarki");
            table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
    void sanatci_table(){


        try {
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from musicdb.sanatci");
            table2.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
    void album_table(){


        try {
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from musicdb.album");
            table3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    void sarki_ekle() {

        int sarki_Id = Integer.parseInt(sarkiText1.getText());
        String sarki_Adi = sarkiText2.getText();
        int tarih = Integer.parseInt(sarkiText3.getText());
        String sanatci_Adi = sarkiText4.getText();
        String album = sarkiText5.getText();
        String tur = sarkiText6.getText();
        float sure = Float.parseFloat(sarkiText7.getText());
        int dinlenme_sayisi = Integer.parseInt(sarkiText8.getText());

        String eklesql1 = "insert into musicdb.sarki (sarki_Id,sarki_Adi,tarih,sanatci_Adi,album,tur,sure,dinlenme_sayisi) values (?,?,?,?,?,?,?,?)";

        try {
            Connection con = conn.connDb();

            pst = con.prepareStatement(eklesql1);
            pst.setString(1, String.valueOf(sarki_Id));
            pst.setString(2, sarki_Adi);
            pst.setString(3, String.valueOf(tarih));
            pst.setString(4, sanatci_Adi);
            pst.setString(5, album);
            pst.setString(6, tur);
            pst.setString(7, String.valueOf(sure));
            pst.setString(8, String.valueOf(dinlenme_sayisi));
            int i = pst.executeUpdate();

            if(i>0){
                JOptionPane.showMessageDialog(null, "Veritabanına eklendi");
            }

                    sarkiText1.setText("");
                    sarkiText2.setText("");
                    sarkiText3.setText("");
                    sarkiText4.setText("");
                    sarkiText5.setText("");
                    sarkiText6.setText("");
                    sarkiText7.setText("");
                    sarkiText8.setText("");
                    sarkiText1.requestFocus();

            sarki_table();

        } catch (SQLException e1) {

            e1.printStackTrace();
        }finally {
            try {
                pst.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    }
    void sarki_Sil(){

        try {

            String silsql1 = "delete from musicdb.sarki where sarki_Id="+sarkiText1.getText();
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            st.execute(silsql1);
            JOptionPane.showMessageDialog(null,"başarıyla silindi");

        } catch (Exception e) {
            e.printStackTrace();
        }
        sarki_table();

    }

    void sarki_guncelle(){

            int sarki_Id = Integer.parseInt(sarkiText1.getText());
            String sarki_Adi = sarkiText2.getText();
            int tarih = Integer.parseInt(sarkiText3.getText());
            String sanatci_Adi = sarkiText4.getText();
            String album = sarkiText5.getText();
            String tur = sarkiText6.getText();
            float sure = Float.parseFloat(sarkiText7.getText());
            int dinlenme_sayisi = Integer.parseInt(sarkiText8.getText());

            String gunsql1 = "update musicdb.sarki set sarki_Adi = ?,tarih = ?,sanatci_Adi = ?,album = ?,tur = ?,sure = ?,dinlenme_sayisi = ? where sarki_Id = ?";

            try {
                Connection con = conn.connDb();

                pst = con.prepareStatement(gunsql1);
                pst.setString(1, String.valueOf(sarki_Id));
                pst.setString(2, sarki_Adi);
                pst.setString(3, String.valueOf(tarih));
                pst.setString(4, sanatci_Adi);
                pst.setString(5, album);
                pst.setString(6, tur);
                pst.setString(7, String.valueOf(sure));
                pst.setString(8, String.valueOf(dinlenme_sayisi));
                int i = pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"başarıyla güncellendi");

                sarki_Sil();
                sarki_ekle();
                sarkiText1.setText("");
                sarkiText2.setText("");
                sarkiText3.setText("");
                sarkiText4.setText("");
                sarkiText5.setText("");
                sarkiText6.setText("");
                sarkiText7.setText("");
                sarkiText8.setText("");
                sarkiText1.requestFocus();

                sarki_table();

            }catch (SQLException e1) {

                e1.printStackTrace();
            }

            }

    void sanatci_Ekle(){

        int sanatci_Id = Integer.parseInt(sanatciText1.getText());
        String sanatci_Adi = sanatciText2.getText();
        String ulke = sanatciText3.getText();

        String eklesql2 = "insert into musicdb.sanatci (sanatci_Id,sanatci_Adi,ulke) values (?,?,?)";

        try {
            Connection con = conn.connDb();

            pst = con.prepareStatement(eklesql2);
            pst.setString(1, String.valueOf(sanatci_Id));
            pst.setString(2, sanatci_Adi);
            pst.setString(3, ulke);
            int i = pst.executeUpdate();

            if(i>0){
                JOptionPane.showMessageDialog(null, "Veritabanına eklendi");
            }
            sanatciText1.setText("");
            sanatciText2.setText("");
            sanatciText3.setText("");

            sanatciText1.requestFocus();

            sanatci_table();

        } catch (SQLException e1) {

            e1.printStackTrace();
        }finally {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
    void sanatci_Sil(){

        try {

            String silsql2 = "delete from musicdb.sanatci where sanatci_Id="+sanatciText1.getText();
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            st.execute(silsql2);
            JOptionPane.showMessageDialog(null,"başarıyla silindi");

            sanatci_table();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void sanatci_guncelle(){

        int sanatci_Id = Integer.parseInt(sanatciText1.getText());
        String sanatci_Adi = sanatciText2.getText();
        String ulke = sanatciText3.getText();

        String gunsql2 = "update musicdb.sanatci set sanatci_Adi = ?,ulke = ? where sanatci_Id = ?";

        try {
            Connection con = conn.connDb();

            pst = con.prepareStatement(gunsql2);
            pst.setString(1, String.valueOf(sanatci_Id));
            pst.setString(2, sanatci_Adi);
            pst.setString(3, ulke);


            JOptionPane.showMessageDialog(null, "başarıyla güncellendi");

            sanatci_Sil();
            sanatci_Ekle();

            sanatciText1.setText("");
            sanatciText2.setText("");
            sanatciText3.setText("");

            sanatciText1.requestFocus();

            sanatci_table();

        } catch (SQLException e1) {

            e1.printStackTrace();
        }

    }
    void album_Ekle(){

        int album_Id = Integer.parseInt(albumText1.getText());
        String album_Adi = albumText2.getText();
        int tarih = Integer.parseInt(albumText3.getText());
        String sanatci_Adi = albumText4.getText();
        String tur = albumText5.getText();
        String sarki = albumText6.getText();


        String eklesql3 = "insert into musicdb.album (album_Id,album_Adi,tarih,sanatci_Adi,tur,sarki) values (?,?,?,?,?,?)";

        try {
            Connection con = conn.connDb();

            pst = con.prepareStatement(eklesql3);
            pst.setString(1, String.valueOf(album_Id));
            pst.setString(2, album_Adi);
            pst.setString(3, String.valueOf(tarih));
            pst.setString(4, sanatci_Adi);
            pst.setString(5, tur);
            pst.setString(6, sarki);

            int i = pst.executeUpdate();

            if(i>0){
                JOptionPane.showMessageDialog(null, "Veritabanına eklendi");
            }

            albumText1.setText("");
            albumText2.setText("");
            albumText3.setText("");
            albumText4.setText("");
            albumText5.setText("");
            albumText6.setText("");

            albumText1.requestFocus();

            album_table();

        } catch (SQLException e1) {

            e1.printStackTrace();
        }finally {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    void Album_Sil(){

        try {

            String silsql3 = "delete from musicdb.album where album_Id="+albumText1.getText();
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            st.execute(silsql3);
            JOptionPane.showMessageDialog(null,"başarıyla silindi");

        } catch (Exception e) {
            e.printStackTrace();
        }
        album_table();

    }
    void album_guncelle(){

        int album_Id = Integer.parseInt(albumText1.getText());
        String album_Adi = albumText2.getText();
        int tarih = Integer.parseInt(albumText3.getText());
        String sanatci_Adi = albumText4.getText();
        String tur = albumText5.getText();
        String sarki = albumText6.getText();


        String gunsql3 = "update musicdb.album set album_Adi = ?,tarih = ?,sanatci_Adi = ?,tur = ?,sarki = ? where album_Id = ?";

        try {
            Connection con = conn.connDb();

            pst = con.prepareStatement(gunsql3);
            pst.setString(1, String.valueOf(album_Id));
            pst.setString(2, album_Adi);
            pst.setString(3, String.valueOf(tarih));
            pst.setString(4, sanatci_Adi);
            pst.setString(5, tur);
            pst.setString(6, sarki);

            JOptionPane.showMessageDialog(null, "başarıyla güncellendi");

            Album_Sil();
            album_Ekle();

            albumText1.setText("");
            albumText2.setText("");
            albumText3.setText("");
            albumText4.setText("");
            albumText5.setText("");
            albumText6.setText("");

            albumText1.requestFocus();

            album_table();

        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }

    public AdminPage(){

        sarki_table();
        sanatci_table();
        album_table();

        add(panel2);
        setSize(1200,800);
        setTitle("MÜZİK DOSYAM");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sarki_ekle();
            }
        });
        sanatciEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sanatci_Ekle();
            }
        });
        albumEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                album_Ekle();
            }
        });
        sarkiSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sarki_Sil();
            }
        });
        sanatciSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sanatci_Sil();
            }
        });
        albumSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Album_Sil();
            }
        });
        sarkiGuncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sarki_guncelle();
            }
        });
        sanatciGuncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sanatci_guncelle();
            }
        });
        albumGuncelleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                album_guncelle();
            }
        });
    }

}
