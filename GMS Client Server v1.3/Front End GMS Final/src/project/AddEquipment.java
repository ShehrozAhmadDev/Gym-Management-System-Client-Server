package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

public class AddEquipment extends JFrame {

	private JPanel contentPane;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField manufacturerTextField;
	private JTextField costTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEquipment frame = new AddEquipment();
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
	public AddEquipment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1370, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	    
		JLabel lblNewLabel = new JLabel("Add Equipment");
		lblNewLabel.setBackground(new Color(51, 51, 51));
		lblNewLabel.setForeground(new Color(255, 255, 102));
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 50));
		lblNewLabel.setBounds(517, 27, 316, 78);
		lblNewLabel.setOpaque(true);

		contentPane.add(lblNewLabel);
		
		
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
		backBtn.setBounds(194, 606, 137, 50);
		contentPane.add(backBtn);
		
		JButton addEquipmentBtn = new JButton("Add Equipment");
		addEquipmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(idTextField.getText());
				String name=nameTextField.getText();
				String manufacturer=manufacturerTextField.getText();
				int cost=Integer.parseInt(costTextField.getText());
				try {
					UDPClient1 c=new UDPClient1();
					c.addEquipment(id,name,manufacturer, cost);
					dispose();
					c.viewEquipment();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		addEquipmentBtn.setForeground(new Color(255, 255, 102));
		addEquipmentBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		addEquipmentBtn.setBackground(new Color(51, 51, 51));
		addEquipmentBtn.setBounds(549, 606, 222, 50);
		contentPane.add(addEquipmentBtn);
		
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
		
		JLabel manufacturerLbl = new JLabel("Manufacturer:");
		manufacturerLbl.setForeground(new Color(255, 255, 102));
		manufacturerLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		manufacturerLbl.setBounds(227, 380, 196, 45);
		contentPane.add(manufacturerLbl);
		
		manufacturerTextField = new JTextField();
		manufacturerTextField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		manufacturerTextField.setColumns(10);
		manufacturerTextField.setBounds(227, 436, 310, 38);
		contentPane.add(manufacturerTextField);
		
		costTextField = new JTextField();
		costTextField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		costTextField.setColumns(10);
		costTextField.setBounds(773, 436, 310, 38);
		contentPane.add(costTextField);
		
		JLabel costLbl = new JLabel("Cost:");
		costLbl.setForeground(new Color(255, 255, 102));
		costLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		costLbl.setBounds(773, 380, 104, 45);
		contentPane.add(costLbl);
		
		
		JLabel picture = new JLabel("");

		
		picture.setBounds(-273, -54, 1677, 944);
		contentPane.add(picture);
		Image image =new ImageIcon(this.getClass().getResource("/aslv.jpg")).getImage();
		picture.setIcon(new ImageIcon(image));
		
		
		
		
		
	}

}
