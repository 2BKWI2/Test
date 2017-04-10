package package_710_MitarbeiterMitDB;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class Hauptfenster extends JFrame 
{
	private JLabel lblName;
	private JTextField tfName;
	private JLabel lblAbteilung;
	private JTextField tfAbteilung;
	private JLabel lblGehalt;
	private JTextField tfGehalt;
	private JCheckBox cbxVerheiratet;
	private JButton btnSteuernBerechnen;
	private JButton btnBeenden;
	private JButton btnLschen;
	private JButton btnErfassen;
	private JButton btnSuchen;
	private JButton btnSpeichern;
	private JLabel lblMitarbeiterID;
	private JTextField tfMitarbeiterID;
	
	private Mitarbeiter mitarbeiter1;
	private DBZugriff meinZugriff;
	private JLabel lblSteuern;
	private JButton btnKündigen;
	
	public Hauptfenster() 
	{
		meinZugriff = new DBZugriff();
		meinZugriff.oeffneDbVerbindung();
			
		addWindowListener (new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				meinZugriff.schliesseDbVerbindung();
				System.exit(0);
			}
		});
		
		this.setSize (440,292);
		this.setTitle ("Mitarbeiter");
		
		getContentPane().setLayout(null);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(10, 36, 86, 14);
		getContentPane().add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(200, 33, 130, 20);
		getContentPane().add(tfName);
		tfName.setColumns(10);
		
		lblAbteilung = new JLabel("Abteilung:");
		lblAbteilung.setBounds(10, 61, 86, 14);
		getContentPane().add(lblAbteilung);
		
		tfAbteilung = new JTextField();
		tfAbteilung.setBounds(200, 58, 130, 20);
		getContentPane().add(tfAbteilung);
		tfAbteilung.setColumns(10);
		
		lblGehalt = new JLabel("Gehalt:");
		lblGehalt.setBounds(10, 86, 86, 14);
		getContentPane().add(lblGehalt);
		
		tfGehalt = new JTextField();
		tfGehalt.setBounds(200, 83, 130, 20);
		getContentPane().add(tfGehalt);
		tfGehalt.setColumns(10);
		
		cbxVerheiratet = new JCheckBox("Verheiratet");
		cbxVerheiratet.setBounds(6, 107, 97, 23);
		getContentPane().add(cbxVerheiratet);
		
		btnSteuernBerechnen = new JButton("Steuern berechnen");
		btnSteuernBerechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					if(cbxVerheiratet.isSelected()==true)
					{
						mitarbeiter1.steuerBerechnenVerheiratet();
						lblSteuern.setText(String.valueOf (mitarbeiter1.getSteuer() ) );
					}
					else if(cbxVerheiratet.isSelected()==false)
					{
						mitarbeiter1.steuerBerechnenLedig();
						lblSteuern.setText(String.valueOf (mitarbeiter1.getSteuer() ) );
					}
				}
				catch (Exception mFehler)
				{
					JOptionPane.showMessageDialog (null, "Legen sie bitte zuerst ein Mitarbeiter an. " +"\n"+ " Es ist ein Fehler aufgetreten.");
				}
			}
		});
		btnSteuernBerechnen.setBounds(10, 137, 148, 23);
		getContentPane().add(btnSteuernBerechnen);
		
		btnBeenden = new JButton("Beenden");
		btnBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				meinZugriff.schliesseDbVerbindung();
				System.exit (0);
			}
		});
		btnBeenden.setBounds(325, 226, 89, 23);
		getContentPane().add(btnBeenden);
		
		btnLschen = new JButton("L\u00F6schen");
		btnLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				tfMitarbeiterID.setText("");
				tfName.setText("");
				tfAbteilung.setText("");
				tfGehalt.setText("");
				lblSteuern.setText("");
			}
		});
		btnLschen.setBounds(325, 192, 89, 23);
		getContentPane().add(btnLschen);
		
		btnErfassen = new JButton("Erfassen");
		btnErfassen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					if(cbxVerheiratet.isSelected()==true)
					{
						mitarbeiter1 = new Mitarbeiter (0);
				
						mitarbeiter1.setMitarbeiterID (Integer.parseInt (tfMitarbeiterID.getText() ) );
						mitarbeiter1.setName(tfName.getText() );
						mitarbeiter1.setAbteilung (tfAbteilung.getText() );
						mitarbeiter1.setGehalt(Double.parseDouble (tfGehalt.getText() ) );
					
						mitarbeiter1.setFamilienstand(true);
						meinZugriff.schreibeObjekt(mitarbeiter1);
					}
					else if(cbxVerheiratet.isSelected()==false)
					{
						mitarbeiter1 = new Mitarbeiter (0);
						
						mitarbeiter1.setMitarbeiterID (Integer.parseInt (tfMitarbeiterID.getText() ) );
						mitarbeiter1.setName(tfName.getText() );
						mitarbeiter1.setAbteilung (tfAbteilung.getText() );
						mitarbeiter1.setGehalt(Double.parseDouble (tfGehalt.getText() ) );
					
						mitarbeiter1.setFamilienstand(false);
						meinZugriff.schreibeObjekt(mitarbeiter1);
					}
				}
				catch (Exception mFehler)
				{
					JOptionPane.showMessageDialog (null, "Bitte überprüfen Sie Ihre Eingaben. " +"\n"+ " Es ist ein Fehler aufgetreten.");
				}
			}
		});
		btnErfassen.setBounds(7, 192, 96, 23);
		getContentPane().add(btnErfassen);
		
		btnSuchen = new JButton("Suchen");
		btnSuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				mitarbeiter1 = meinZugriff.leseObjekt(Integer.parseInt(tfMitarbeiterID.getText()) );
				
				if (mitarbeiter1!=null)
				{
					tfMitarbeiterID.setText(String.valueOf (String.valueOf (mitarbeiter1.getMitarbeiterID()) ) );
					tfName.setText (mitarbeiter1.getName() );
					tfAbteilung.setText (mitarbeiter1.getAbteilung() );
					tfGehalt.setText(String.valueOf (mitarbeiter1.getGehalt() ) );
					lblSteuern.setText(String.valueOf (mitarbeiter1.getSteuer() ) );
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Falsche Mitarbiternummer!");
					tfMitarbeiterID.setText("");
					tfName.setText("");
					tfAbteilung.setText("");
					tfGehalt.setText("");
					lblSteuern.setText ("");
				}
			}
		});
		btnSuchen.setBounds(113, 192, 89, 23);
		getContentPane().add(btnSuchen);
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					if(cbxVerheiratet.isSelected()==true)
					{
						mitarbeiter1 = new Mitarbeiter (0);
				
						mitarbeiter1.setMitarbeiterID (Integer.parseInt (tfMitarbeiterID.getText() ) );
						mitarbeiter1.setName(tfName.getText() );
						mitarbeiter1.setAbteilung (tfAbteilung.getText() );
						mitarbeiter1.setGehalt(Double.parseDouble (tfGehalt.getText() ) );
					
						mitarbeiter1.setFamilienstand(true);
						meinZugriff.schreibeObjekt(mitarbeiter1);
					}
					else if(cbxVerheiratet.isSelected()==false)
					{
						mitarbeiter1 = new Mitarbeiter (0);
						
						mitarbeiter1.setMitarbeiterID (Integer.parseInt (tfMitarbeiterID.getText() ) );
						mitarbeiter1.setName(tfName.getText() );
						mitarbeiter1.setAbteilung (tfAbteilung.getText() );
						mitarbeiter1.setGehalt(Double.parseDouble (tfGehalt.getText() ) );
					
						mitarbeiter1.setFamilienstand(false);
						meinZugriff.schreibeObjekt(mitarbeiter1);
					}
				}
				catch (Exception mFehler)
				{
					JOptionPane.showMessageDialog (null, "Bitte überprüfen Sie Ihre Eingaben. " +"\n"+ " Es ist ein Fehler aufgetreten.");
				}
			}
		});
		btnSpeichern.setBounds(7, 226, 96, 23);
		getContentPane().add(btnSpeichern);
		
		lblMitarbeiterID = new JLabel("Mitarbeiter-ID:");
		lblMitarbeiterID.setBounds(10, 11, 86, 14);
		getContentPane().add(lblMitarbeiterID);
		
		tfMitarbeiterID = new JTextField();
		tfMitarbeiterID.setBounds(200, 8, 130, 20);
		getContentPane().add(tfMitarbeiterID);
		tfMitarbeiterID.setColumns(10);
		
		lblSteuern = new JLabel("");
		lblSteuern.setBounds(200, 141, 130, 14);
		getContentPane().add(lblSteuern);
		
		btnKündigen = new JButton("K\u00FCndigen");
		btnKündigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				meinZugriff.loescheKonto(Integer.parseInt (tfMitarbeiterID.getText() ) );
				tfMitarbeiterID.setText("");
				tfName.setText("");
				tfAbteilung.setText("");
				tfGehalt.setText("");
				lblSteuern.setText("");
			}
		});
		btnKündigen.setBounds(113, 226, 202, 23);
		getContentPane().add(btnKündigen);
	}
}
