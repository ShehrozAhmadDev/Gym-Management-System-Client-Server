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

public class AddMember extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTextField contactTextField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMember frame = new AddMember();
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
	public AddMember() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1370, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	    
		JLabel lblNewLabel = new JLabel("Add Member");
		lblNewLabel.setBackground(new Color(51, 51, 51));
		lblNewLabel.setForeground(new Color(255, 255, 102));
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 50));
		lblNewLabel.setBounds(552, 35, 259, 78);
		lblNewLabel.setOpaque(true);

		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UDPClient1 c=new UDPClient1();
					c.viewMembers();
					dispose();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
			}}
		});
		btnNewButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		btnNewButton.setForeground(new Color(255, 255, 102));
		btnNewButton.setBackground(new Color(51, 51, 51));
		btnNewButton.setBounds(194, 606, 137, 50);
		contentPane.add(btnNewButton);
		
		JButton addMemberBtn = new JButton("Add Member");
		addMemberBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(idTextField.getText());
				String name=nameTextField.getText();
				int age=Integer.parseInt(ageTextField.getText());
				String contact=contactTextField.getText();
				try {
					UDPClient1 c=new UDPClient1();
					c.addMember(id,name,age, contact);
					dispose();
					UDPClient1.viewMembers();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		addMemberBtn.setForeground(new Color(255, 255, 102));
		addMemberBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		addMemberBtn.setBackground(new Color(51, 51, 51));
		addMemberBtn.setBounds(549, 606, 222, 50);
		contentPane.add(addMemberBtn);
		
		JLabel idLbl = new JLabel("ID:");
		idLbl.setForeground(new Color(255, 255, 102));
		idLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		idLbl.setBounds(227, 202, 104, 45);
		contentPane.add(idLbl);
		
		idTextField = new JTextField();
		idTextField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		idTextField.setBounds(227, 258, 310, 38);
		contentPane.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel nameLbl = new JLabel("Name:");
		nameLbl.setForeground(new Color(255, 255, 102));
		nameLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		nameLbl.setBounds(773, 202, 104, 45);
		contentPane.add(nameLbl);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		nameTextField.setColumns(10);
		nameTextField.setBounds(773, 258, 310, 38);
		contentPane.add(nameTextField);
		
		JLabel ageLbl = new JLabel("Age:");
		ageLbl.setForeground(new Color(255, 255, 102));
		ageLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		ageLbl.setBounds(227, 380, 196, 45);
		contentPane.add(ageLbl);
		
		ageTextField = new JTextField();
		ageTextField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		ageTextField.setColumns(10);
		ageTextField.setBounds(227, 436, 310, 38);
		contentPane.add(ageTextField);
		
		contactTextField = new JTextField();
		contactTextField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		contactTextField.setColumns(10);
		contactTextField.setBounds(773, 436, 310, 38);
		contentPane.add(contactTextField);
		
		JLabel contactLbl = new JLabel("Contact:");
		contactLbl.setForeground(new Color(255, 255, 102));
		contactLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		contactLbl.setBounds(773, 380, 104, 45);
		contentPane.add(contactLbl);
		
		
		JLabel picture = new JLabel("");

		
		picture.setBounds(-273, -54, 1677, 944);
		contentPane.add(picture);
		Image image =new ImageIcon(this.getClass().getResource("/aslv.jpg")).getImage();
		picture.setIcon(new ImageIcon(image));


	}

}
