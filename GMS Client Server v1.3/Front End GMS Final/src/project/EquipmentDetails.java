package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EquipmentDetails extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EquipmentDetails frame = new EquipmentDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EquipmentDetails(){

	}

	public EquipmentDetails(Equipment equipment) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1370, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	    
		JLabel mainLbl = new JLabel("Equipment Details");
		mainLbl.setBackground(new Color(51, 51, 51));
		mainLbl.setForeground(new Color(255, 255, 102));
		mainLbl.setFont(new Font("Rockwell Condensed", Font.BOLD, 50));
		mainLbl.setBounds(493, 26, 371, 78);

		contentPane.add(mainLbl);
		
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UDPClient1 c=new UDPClient1();
					c.viewEquipment();
					dispose();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		backBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		backBtn.setForeground(new Color(255, 255, 102));
		backBtn.setBackground(new Color(51, 51, 51));
		backBtn.setBounds(628, 596, 137, 50);
		contentPane.add(backBtn);
		
		JLabel idLbl = new JLabel("ID:");
		idLbl.setForeground(new Color(255, 255, 102));
		idLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		idLbl.setBounds(227, 202, 104, 45);
		contentPane.add(idLbl);
		

		
		JLabel nameLbl = new JLabel("Name:");
		nameLbl.setForeground(new Color(255, 255, 102));
		nameLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		nameLbl.setBounds(773, 202, 104, 45);
		contentPane.add(nameLbl);
		
		
		JLabel manufacurerLbl = new JLabel("Manufacturer:");
		manufacurerLbl.setForeground(new Color(255, 255, 102));
		manufacurerLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		manufacurerLbl.setBounds(227, 335, 196, 45);
		contentPane.add(manufacurerLbl);
		
		
		
		JLabel costLbl = new JLabel("Cost:");
		costLbl.setBackground(new Color(51, 51, 51));
		costLbl.setForeground(new Color(255, 255, 102));
		costLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		costLbl.setBounds(773, 335, 104, 45);
		contentPane.add(costLbl);
		
		JLabel idLabel = new JLabel("10");
		idLabel.setForeground(new Color(255, 255, 102));
		idLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		idLabel.setBounds(433, 202, 241, 45);
		contentPane.add(idLabel);
		
		JLabel manufaturerLabel = new JLabel("Apollo Sports");
		manufaturerLabel.setForeground(new Color(255, 255, 102));
		manufaturerLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		manufaturerLabel.setBounds(433, 335, 241, 45);
		contentPane.add(manufaturerLabel);
		
		JLabel costLabel = new JLabel("200000$");
		costLabel.setBackground(new Color(51, 51, 51));
		costLabel.setForeground(new Color(255, 255, 102));
		costLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		costLabel.setBounds(914, 335, 262, 45);
		contentPane.add(costLabel);
		
		JLabel nameLabel = new JLabel("Inclined Bench");
		nameLabel.setForeground(new Color(255, 255, 102));
		nameLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		nameLabel.setBounds(914, 202, 295, 45);
		contentPane.add(nameLabel);

		idLabel.setText(String.valueOf(equipment.getId()));
		nameLabel.setText(equipment.getName());
		manufaturerLabel.setText(equipment.getManufacturer());
		costLabel.setText(String.valueOf(equipment.getCost()));



		JLabel picture = new JLabel("");

		
		picture.setBounds(-273, -54, 1677, 944);
		contentPane.add(picture);
		Image image =new ImageIcon(this.getClass().getResource("/all1.jpg")).getImage();
		picture.setIcon(new ImageIcon(image));

}
}
