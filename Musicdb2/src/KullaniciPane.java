import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class KullaniciPane extends JFrame {

    private JPanel panel1;
    private JButton ekleButton;
    private JTextField kullanici_Listesini_Ac_text;
    private JButton kullanici_Listesini_Ac_Button;
    private JTable en_cok_dinlenen1;
    private JTable kull_list_ac;
    private JTable table3;
    private JTable table4;
    private JTextField takip_Et_Text;
    private JButton takipEtButton;
    private JTextField ekle_Text;
    private JTable table2;
    private JButton sarkiEkleButton;
    private JTextField textField1;
    private DBConnection conn = new DBConnection();
    Statement st = null;
    ResultSet rs = null;
    private int kullanici_ID;

    Connection con;
    PreparedStatement pst = null;

    public KullaniciPane(int ID) {
        add(panel1);
        setSize(1800, 1000);
        setTitle("MÜZİK DOSYAM");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        kullanici_ID = ID;

        takip_edilen();
        kull_table();
        kull_list_ac();
        en_cok_dinlenen_list1();

        takipEtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int takip_Edilen_Id = Integer.parseInt(takip_Et_Text.getText());
                String abonelik_tur = "premium";

                try {
                    Connection con = conn.connDb();
                    Statement st = con.createStatement();

                    ResultSet rs = st.executeQuery("select * from kull where kull_Id='" + takip_Edilen_Id + "' and abonelik_tur='" + abonelik_tur + "'");

                    if(rs.next()){

                        String eklesql1 = "insert into musicdb.ark_table (kull_Id,takip_edilen_Id) values (?,?)";

                        try {

                            pst = con.prepareStatement(eklesql1);

                            pst.setString(1, String.valueOf(kullanici_ID));
                            pst.setString(2, String.valueOf(takip_Edilen_Id));

                            int i = pst.executeUpdate();

                            if (i > 0) {
                                JOptionPane.showMessageDialog(null, "Takip iliskisi kuruldu");
                            }

                            System.out.println("bu kullanici preumdur");
                            takip_edilen();

                        } catch (SQLException e1) {

                            e1.printStackTrace();
                        }

                    }
                    else{

                        JOptionPane.showMessageDialog(null, "Basarisiz takip istegi. Kullanici premium degil");
                    }

                }catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        kullanici_Listesini_Ac_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                takip_edilen_kull_list_ac();
            }
        });

        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e) {

                int sarki_Id = Integer.parseInt(ekle_Text.getText());
                int kull_Id = kullanici_ID;

                int takip_edilen_Id =Integer.parseInt( kullanici_Listesini_Ac_text.getText());
                try{
                    Connection con = conn.connDb();
                    Statement st = con.createStatement();

                    ResultSet rs = st.executeQuery("select * from ark_table where kull_Id = '"+kull_Id+"' AND takip_edilen_Id = '"+takip_edilen_Id+"'");

                    if(rs.next()){

                        try{

                            Connection con2 = conn.connDb();
                            Statement st2 = con2.createStatement();

                            ResultSet rs2 = st2.executeQuery("select * from kull_list where kull_Id = '"+takip_edilen_Id+"' AND sarki_Id = '"+sarki_Id+"'");

                            if(rs2.next()){


                                String eklesql1 = "insert into musicdb.kull_list (sarki_Id,kull_Id) values (?,?)";

                                try {
                                    Connection con3 = conn.connDb();

                                    pst = con3.prepareStatement(eklesql1);

                                    pst.setString(1, String.valueOf(sarki_Id));
                                    pst.setString(2, String.valueOf(kull_Id));

                                    int i = pst.executeUpdate();

                                    if (i > 0) {
                                        JOptionPane.showMessageDialog(null, "Veritabanına eklendi");

                                    }

                                    kull_list_ac();
                                    ekle_Text.setText("");
                                    ekle_Text.requestFocus();

                                } catch (SQLException e1) {

                                    e1.printStackTrace();
                                }


                            }


                        }catch(SQLException e2){

                            e2.printStackTrace();
                        }

                    }

                }catch(SQLException e1) {
                        e1.printStackTrace();
            }

            }
        });

        sarkiEkleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int sarki_Id = Integer.parseInt(textField1.getText());
                int kull_Id = kullanici_ID;


                String eklesql1 = "insert into musicdb.kull_list (sarki_Id,kull_Id) values (?,?)";

                try {
                    Connection con = conn.connDb();

                    pst = con.prepareStatement(eklesql1);

                    pst.setString(1, String.valueOf(sarki_Id));
                    pst.setString(2, String.valueOf(kull_Id));

                    int i = pst.executeUpdate();

                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Veritabanına eklendi");

                    }

                    kull_list_ac();
                    ekle_Text.setText("");
                    ekle_Text.requestFocus();

                } catch (SQLException e1) {

                    e1.printStackTrace();
                }


            }
        });
    }


    void takip_edilen() {

        try {
            Connection con = conn.connDb();
            Statement st = con.createStatement();
           // ResultSet rs = st.executeQuery("select a.kull_Id, k.kull_Adi from musicdb.ark_table a inner join musicdb.kull k on a.takip_edilen_Id=k.kull_Id");
            ResultSet rs = st.executeQuery("select * from ark_table");
            kull_list_ac.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    void kull_table() {

        try {
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from musicdb.kull");
            table4.setModel(DbUtils.resultSetToTableModel(rs));


        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    void takip_edilen_kull_list_ac() {

        try {
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            String kull_Id = kullanici_Listesini_Ac_text.getText();

            ResultSet rs1 = st.executeQuery("select * from ark_table where kull_Id='" + kullanici_ID + "' and takip_edilen_Id='" + kull_Id + "'");

            if(rs1.next()) {

                ResultSet rs = st.executeQuery("select s.sarki_Id,s.sarki_Adi from musicdb.kull_list k left outer join musicdb.sarki s on k.sarki_Id=s.sarki_Id where kull_Id='" + kull_Id + "'");

                table2.setModel(DbUtils.resultSetToTableModel(rs));
            }
            else{

                JOptionPane.showMessageDialog(null, "kullanici takip edilmiyorl");

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    void kull_list_ac() {

        try {
            Connection con = conn.connDb();
            Statement st = con.createStatement();


           ResultSet rs = st.executeQuery("select s.sarki_Id,s.sarki_Adi from musicdb.kull_list k left outer join musicdb.sarki s on k.sarki_Id=s.sarki_Id where kull_Id='" + kullanici_ID + "'");

                table3.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    void en_cok_dinlenen_list1() {


        try {
            Connection con = conn.connDb();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from musicdb.sarki order by dinlenme_sayisi desc");
            en_cok_dinlenen1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }



}