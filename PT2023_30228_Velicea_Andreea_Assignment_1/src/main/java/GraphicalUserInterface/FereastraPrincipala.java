package GraphicalUserInterface;

import BusinessLogic.Operatii;
import DataModels.Monom;
import DataModels.Polinom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FereastraPrincipala extends JFrame {
    private JTextField textFieldPolinom1;
    private JTextField textFieldPolinom2;
    private JTextField textFieldPolinomRezultat;
    public FereastraPrincipala() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(254,233,176));

        JLabel lblNewLabel = new JLabel("Polinom 1");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(15, 20, 70, 15);
        getContentPane().add(lblNewLabel);

        textFieldPolinom1 = new JTextField();
        textFieldPolinom1.setBounds(100, 20, 300, 40);
        getContentPane().add(textFieldPolinom1);
        textFieldPolinom1.setColumns(10);

        final Polinom polinom1 = new Polinom();
        JButton btnNewButtonAdugaPolinom1 = new JButton("Adauga Polinom 1 ");
        btnNewButtonAdugaPolinom1.setBackground(new Color(250,204,80));
        btnNewButtonAdugaPolinom1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    String polinomTextField =  textFieldPolinom1.getText();
                    if(check(polinomTextField)==false){
                        throw new Exception();
                    }
                    parsePolinom(polinomTextField,polinom1);
                }catch(Exception exp){
                    JOptionPane.showMessageDialog(null,"Polinomul introdus nu este de o singura variabila sau ai gresit la introducerea acestuia");
                }
            }
        });
        btnNewButtonAdugaPolinom1.setBounds(410, 20, 200, 40);
        getContentPane().add(btnNewButtonAdugaPolinom1);

        textFieldPolinom2 = new JTextField();
        textFieldPolinom2.setBounds(100, 80, 300, 40);
        getContentPane().add(textFieldPolinom2);
        textFieldPolinom2.setColumns(10);
        final Polinom polinom2 = new Polinom();
        JButton btnNewButtonAdugaPolinom2 = new JButton("Adauga Polinom 2 ");
        btnNewButtonAdugaPolinom2.setBackground(new Color(250,204,80));
        btnNewButtonAdugaPolinom2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{

                    String polinomTextField =  textFieldPolinom2.getText();
                    if(check(polinomTextField)==false){
                        throw new Exception();
                    }
                    parsePolinom(polinomTextField,polinom2);
                }catch(Exception exp){
                    JOptionPane.showMessageDialog(null,"nu e bine!");
                }
            }
        });
        btnNewButtonAdugaPolinom2.setBounds(410, 80, 200, 40);
        getContentPane().add(btnNewButtonAdugaPolinom2);

        JLabel lblNewLabel_1 = new JLabel("Polinom 2");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(15,80 , 70, 15);
        getContentPane().add(lblNewLabel_1);

        JButton btnNewButtonClear = new JButton("Insereaza din nou");
        btnNewButtonClear.setBackground(new Color(250,204,80));
        btnNewButtonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldPolinom1.setText("");
                textFieldPolinom2.setText("");
                textFieldPolinomRezultat.setText("0");
                polinom1.clear();
                polinom2.clear();
            }
        });
        btnNewButtonClear.setBounds(250, 350, 200, 40);
        getContentPane().add(btnNewButtonClear);

        JButton btnNewButton = new JButton("ADUNARE");
        btnNewButton.setBackground(new Color(250,204,80));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operatii operatie = new Operatii();
                textFieldPolinomRezultat.setText(operatie.adunaPolinoame(polinom1,polinom2).toString());
            }
        });
        btnNewButton.setBounds(20, 200, 150, 30);
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("SCADERE");
        btnNewButton_1.setBackground(new Color(250,204,80));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operatii operatie = new Operatii();
                textFieldPolinomRezultat.setText(operatie.scadePolinoame(polinom1,polinom2).toString());
            }
        });
        btnNewButton_1.setBounds(20, 250, 150, 30);
        getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("INMULTIRE");
        btnNewButton_2.setBackground(new Color(250,204,80));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operatii operatie = new Operatii();
                textFieldPolinomRezultat.setText(operatie.inmultestePolinoame(polinom1,polinom2).toString());
            }
        });
        btnNewButton_2.setBounds(20, 300, 150, 30);
        getContentPane().add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("IMPARTIRE");
        btnNewButton_3.setBackground(new Color(250,204,80));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operatii operatie = new Operatii();
                ArrayList<Polinom> listPolinoms= new ArrayList<Polinom>();
                try {
                    listPolinoms = operatie.impartePolinoame(polinom1, polinom2);
                }catch(Exception exp){
                    JOptionPane.showMessageDialog(null,"Gradul deimpartitului trebuie sa fie mai mare decat gradul impartitorului!");
                }
                String rezultat="";
                int i=0;
                for(Polinom polinomIterator: listPolinoms){
                    if(i==0){
                        rezultat+="cat:    ";
                        i++;
                        rezultat+=polinomIterator.toString()+"  ";
                    }
                    else{
                        if(i==1){
                            rezultat+="rest:    ";
                            i++;
                            rezultat+=polinomIterator.toString()+"  ";
                        }
                    }

                }

                textFieldPolinomRezultat.setText(rezultat);
            }
        });
        btnNewButton_3.setBounds(20, 350, 150, 30);
        getContentPane().add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("DERIVARE P1");
        btnNewButton_4.setBackground(new Color(250,204,80));
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operatii operatie =new Operatii();
                textFieldPolinomRezultat.setText(operatie.derivarePolinom(polinom1).toString());
            }
        });
        btnNewButton_4.setBounds(510, 200, 150, 30);
        getContentPane().add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("DERIVARE P2");
        btnNewButton_5.setBackground(new Color(250,204,80));
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operatii operatie = new Operatii();
                textFieldPolinomRezultat.setText(operatie.derivarePolinom(polinom2).toString());
            }
        });
        btnNewButton_5.setBounds(510, 250, 150, 30);
        getContentPane().add(btnNewButton_5);

        JButton btnNewButton_6 = new JButton("INTEGRARE P1");
        btnNewButton_6.setBackground(new Color(250,204,80));
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operatii operatie =new Operatii();
                textFieldPolinomRezultat.setText(operatie.integrarePolinom(polinom1).toString());
            }
        });
        btnNewButton_6.setBounds(510, 300, 150, 30);
        getContentPane().add(btnNewButton_6);

        JButton btnNewButton_7 = new JButton("INTEGRARE P2");
        btnNewButton_7.setBackground(new Color(250,204,80));
        btnNewButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Operatii operatie = new Operatii();
                textFieldPolinomRezultat.setText(operatie.integrarePolinom(polinom2).toString());
            }
        });
        btnNewButton_7.setBounds(510, 350, 150, 30);
        getContentPane().add(btnNewButton_7);

        textFieldPolinomRezultat = new JTextField();
        textFieldPolinomRezultat.setBounds(125, 150, 450, 40);
        getContentPane().add(textFieldPolinomRezultat);
        textFieldPolinomRezultat.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Rezultat operatie ");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(300, 130, 110, 14);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("CALCULATOR");
        lblNewLabel_3.setForeground(new Color(255, 128, 255));
        lblNewLabel_3.setBackground(new Color(255, 128, 255));
        lblNewLabel_3.setFont(new Font("Yu Gothic", Font.BOLD, 14));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(300, 250, 100, 40);
        getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Polinoame");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(300, 280, 100, 40);
        getContentPane().add(lblNewLabel_4);
    }
    boolean check(String s) throws Exception{
        if (s == null) {
            return false;
        }
        int len = s.length();
        int k=0;
        for (int i = 0; i < len; i++) {
            if(s.charAt(i)=='x'||s.charAt(i)=='^'||s.charAt(i)=='.'||s.charAt(i)=='+'||s.charAt(i)=='-'||Character.isDigit(s.charAt(i))==true){
                k++;
            }else{
                throw new Exception("");
            }
        }
        if(k==len){
            return true;
        }

        return false;
    }
    void parsePolinom (String input,Polinom polinom) {
        Pattern p = Pattern.compile("([+-]?\\d*[.]?\\d?)[x](\\^(\\d+))?|([+-]?\\d+)");
        Matcher m = p.matcher(input);
        int grad = 0;
        Double coeficient = 0.0;
        while (m.find()) {
            if (m.group(4) == null) {
                if (m.group(3) == null) {
                    grad = 1;
                } else {
                    grad = Integer.parseInt(m.group(3));
                }
                if (m.group(1) == "") {
                    coeficient = 1.0;
                } else {
                    if (m.group(1).endsWith("-") == true) {
                        coeficient = -1.0;
                    } else {
                        if (m.group(1).endsWith("+") == true) {
                            coeficient = 1.0;
                        } else {
                            coeficient = Double.parseDouble(m.group(1));
                        }
                    }
                }
            } else {
                coeficient = Double.parseDouble(m.group(4));
                grad = 0;
            }
            Monom monom = new Monom(coeficient, grad);
            polinom.addMonom(monom);
        }
    }

}
