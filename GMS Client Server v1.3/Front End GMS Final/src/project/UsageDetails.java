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
import javax.swing.border.EmptyBorder;

public class UsageDetails extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsageDetails frame = new UsageDetails();
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

	public UsageDetails(){

	}
	public UsageDetails(Usage usage) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1370, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	    
		JLabel mainLbl = new JLabel("Usage Details");
		mainLbl.setBackground(new Color(51, 51, 51));
		mainLbl.setForeground(new Color(255, 255, 102));
		mainLbl.setFont(new Font("Rockwell Condensed", Font.BOLD, 50));
		mainLbl.setBounds(539, 31, 371, 78);

		contentPane.add(mainLbl);
		
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UDPClient1 c=new UDPClient1();
					c.viewUsage();
					dispose();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				}
			}
		});
		backBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		backBtn.setForeground(new Color(255, 255, 102));
		backBtn.setBackground(new Color(51, 51, 51));
		backBtn.setBounds(628, 618, 137, 50);
		contentPane.add(backBtn);
		
		JLabel idLbl = new JLabel("Equipment ID:");
		idLbl.setForeground(new Color(255, 255, 102));
		idLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		idLbl.setBounds(227, 202, 156, 45);
		contentPane.add(idLbl);
		

		
		JLabel nameLbl = new JLabel("Equipment Name:");
		nameLbl.setForeground(new Color(255, 255, 102));
		nameLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		nameLbl.setBounds(724, 202, 180, 45);
		contentPane.add(nameLbl);
		
		
		JLabel manufacurerLbl = new JLabel("Manufacturer:");
		manufacurerLbl.setForeground(new Color(255, 255, 102));
		manufacurerLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		manufacurerLbl.setBounds(227, 273, 196, 45);
		contentPane.add(manufacurerLbl);
		
		
		
		JLabel costLbl = new JLabel("Cost:");
		costLbl.setBackground(new Color(51, 51, 51));
		costLbl.setForeground(new Color(255, 255, 102));
		costLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		costLbl.setBounds(726, 273, 104, 45);
		contentPane.add(costLbl);
		
		JLabel eqIdLabel = new JLabel("10");
		eqIdLabel.setForeground(new Color(255, 255, 102));
		eqIdLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		eqIdLabel.setBounds(433, 202, 241, 45);
		contentPane.add(eqIdLabel);
		
		JLabel manufaturerLabel = new JLabel("Apollo Sports");
		manufaturerLabel.setForeground(new Color(255, 255, 102));
		manufaturerLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		manufaturerLabel.setBounds(433, 273, 241, 45);
		contentPane.add(manufaturerLabel);
		
		JLabel costLabel = new JLabel("200000$");
		costLabel.setBackground(new Color(51, 51, 51));
		costLabel.setForeground(new Color(255, 255, 102));
		costLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		costLabel.setBounds(935, 273, 262, 45);
		contentPane.add(costLabel);
		
		JLabel eqNameLabel = new JLabel("Inclined Bench");
		eqNameLabel.setForeground(new Color(255, 255, 102));
		eqNameLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		eqNameLabel.setBounds(926, 202, 295, 45);
		contentPane.add(eqNameLabel);


		JLabel lblEquipmentDetails = new JLabel("Equipment Details:");
		lblEquipmentDetails.setForeground(new Color(255, 255, 102));
		lblEquipmentDetails.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		lblEquipmentDetails.setBounds(86, 132, 317, 45);
		contentPane.add(lblEquipmentDetails);
		
		JLabel memberidLbl = new JLabel("Member ID:");
		memberidLbl.setForeground(new Color(255, 255, 102));
		memberidLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		memberidLbl.setBounds(227, 419, 156, 45);
		contentPane.add(memberidLbl);
		
		JLabel ageLbl = new JLabel("Age:");
		ageLbl.setForeground(new Color(255, 255, 102));
		ageLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		ageLbl.setBounds(227, 489, 196, 45);
		contentPane.add(ageLbl);
		
		JLabel ageLabel = new JLabel("20");
		ageLabel.setForeground(new Color(255, 255, 102));
		ageLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		ageLabel.setBounds(433, 489, 241, 45);
		contentPane.add(ageLabel);
		
		JLabel memberIdLabel = new JLabel("4");
		memberIdLabel.setForeground(new Color(255, 255, 102));
		memberIdLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		memberIdLabel.setBounds(433, 419, 241, 45);
		contentPane.add(memberIdLabel);
		
		JLabel nameLbl_1 = new JLabel("Member Name:");
		nameLbl_1.setForeground(new Color(255, 255, 102));
		nameLbl_1.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		nameLbl_1.setBounds(724, 419, 180, 45);
		contentPane.add(nameLbl_1);
		
		JLabel memberNameLabel = new JLabel("Inclined Bench");
		memberNameLabel.setForeground(new Color(255, 255, 102));
		memberNameLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		memberNameLabel.setBounds(926, 419, 295, 45);
		contentPane.add(memberNameLabel);
		
		JLabel contactcostLbl = new JLabel("Contact:");
		contactcostLbl.setForeground(new Color(255, 255, 102));
		contactcostLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		contactcostLbl.setBackground(new Color(51, 51, 51));
		contactcostLbl.setBounds(726, 489, 104, 45);
		contentPane.add(contactcostLbl);
		
		JLabel contactLabel = new JLabel("200000$");
		contactLabel.setForeground(new Color(255, 255, 102));
		contactLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		contactLabel.setBackground(new Color(51, 51, 51));
		contactLabel.setBounds(935, 489, 262, 45);
		contentPane.add(contactLabel);
		
		JLabel lblMemberDetails = new JLabel("Member Details:");
		lblMemberDetails.setForeground(new Color(255, 255, 102));
		lblMemberDetails.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		lblMemberDetails.setBounds(86, 344, 156, 45);
		contentPane.add(lblMemberDetails);

		eqIdLabel.setText(String.valueOf(usage.getEquipment().getId()));
		eqNameLabel.setText(usage.getEquipment().getName());
		manufaturerLabel.setText(usage.getEquipment().getManufacturer());
		costLabel.setText(String.valueOf(usage.getEquipment().getCost()));
		memberIdLabel.setText(String.valueOf(usage.getMember().getId()));
		memberNameLabel.setText(usage.getMember().getName());
		ageLabel.setText(String.valueOf(usage.getMember().getAge()));
		contactLabel.setText(usage.getMember().getContact());

		JLabel picture = new JLabel("");

		
		picture.setBounds(-273, -54, 1677, 944);
		contentPane.add(picture);
		Image image =new ImageIcon(this.getClass().getResource("/all1.jpg")).getImage();
		picture.setIcon(new ImageIcon(image));
		

	}
}
