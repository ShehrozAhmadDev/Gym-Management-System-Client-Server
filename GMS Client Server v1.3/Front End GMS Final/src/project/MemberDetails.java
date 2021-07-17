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

public class MemberDetails extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberDetails frame = new MemberDetails();
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

	public MemberDetails(){

	}
	public MemberDetails(Member member) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1370, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	    
		JLabel mainLbl = new JLabel("Member Details");
		mainLbl.setBackground(new Color(51, 51, 51));
		mainLbl.setForeground(new Color(255, 255, 102));
		mainLbl.setFont(new Font("Rockwell Condensed", Font.BOLD, 50));
		mainLbl.setBounds(535, 25, 337, 78);

		contentPane.add(mainLbl);
		
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UDPClient1 c=new UDPClient1();
					c.viewMembers();
					dispose();
				} catch (Exception var3) {
					var3.printStackTrace();
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
		
		
		JLabel ageLbl = new JLabel("Age:");
		ageLbl.setForeground(new Color(255, 255, 102));
		ageLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		ageLbl.setBounds(227, 335, 196, 45);
		contentPane.add(ageLbl);
		
		
		
		JLabel contactLbl = new JLabel("Contact:");
		contactLbl.setBackground(new Color(51, 51, 51));
		contactLbl.setForeground(new Color(255, 255, 102));
		contactLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		contactLbl.setBounds(773, 335, 104, 45);
		contentPane.add(contactLbl);
		
		JLabel idLabel = new JLabel("10");
		idLabel.setForeground(new Color(255, 255, 102));
		idLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		idLabel.setBounds(433, 202, 241, 45);
		contentPane.add(idLabel);
		
		JLabel ageLabel = new JLabel("20");
		ageLabel.setForeground(new Color(255, 255, 102));
		ageLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		ageLabel.setBounds(433, 335, 241, 45);
		contentPane.add(ageLabel);
		
		JLabel contactLabel = new JLabel("03235454023");
		contactLabel.setBackground(new Color(51, 51, 51));
		contactLabel.setForeground(new Color(255, 255, 102));
		contactLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		contactLabel.setBounds(914, 335, 262, 45);
		contentPane.add(contactLabel);
		
		JLabel nameLabel = new JLabel("Shehroz Ahmad");
		nameLabel.setForeground(new Color(255, 255, 102));
		nameLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		nameLabel.setBounds(914, 202, 295, 45);
		contentPane.add(nameLabel);

		idLabel.setText(String.valueOf(member.getId()));
		nameLabel.setText(member.getName());
		ageLabel.setText(String.valueOf(member.getAge()));
		contactLabel.setText(member.getContact());

		JLabel picture = new JLabel("");

		
		picture.setBounds(-273, -54, 1677, 944);
		contentPane.add(picture);
		Image image =new ImageIcon(this.getClass().getResource("/all1.jpg")).getImage();
		picture.setIcon(new ImageIcon(image));
		
		
}

}
