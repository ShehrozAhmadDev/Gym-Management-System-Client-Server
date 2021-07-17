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
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1370, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel mainLabel = new JLabel("Main Menu");
		mainLabel.setBackground(new Color(51, 51, 51));
		mainLabel.setForeground(new Color(255, 255, 102));
		mainLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 70));
		mainLabel.setBounds(69, 75, 335, 78);
		contentPane.add(mainLabel);
		
		JButton equipmentBtn = new JButton("Equipments");
		equipmentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					UDPClient1 c=new UDPClient1();
					c.viewEquipment();
					dispose();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});
		equipmentBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 40));
		equipmentBtn.setForeground(new Color(255, 255, 102));
		equipmentBtn.setBackground(new Color(51, 51, 51));
		equipmentBtn.setBounds(66, 267, 296, 48);
		contentPane.add(equipmentBtn);
		
		JButton memberBtn = new JButton("Members");
		memberBtn.addActionListener(new ActionListener() {
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
		memberBtn.setForeground(new Color(255, 255, 102));
		memberBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 40));
		memberBtn.setBackground(new Color(51, 51, 51));
		memberBtn.setBounds(69, 398, 296, 48);
		contentPane.add(memberBtn);
		
		JButton usageBtn = new JButton("Usage");
		usageBtn.addActionListener(new ActionListener() {
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
		usageBtn.setForeground(new Color(255, 255, 102));
		usageBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 40));
		usageBtn.setBackground(new Color(51, 51, 51));
		usageBtn.setBounds(69, 530, 296, 48);
		contentPane.add(usageBtn);
		
		JLabel lblGym = new JLabel("Gym");
		lblGym.setForeground(new Color(51, 51, 51));
		lblGym.setFont(new Font("Rockwell Condensed", Font.BOLD, 79));
		lblGym.setBackground(new Color(51, 51, 51));
		lblGym.setBounds(919, 203, 395, 78);
		contentPane.add(lblGym);
		JLabel lblGym_1 = new JLabel("Management");
		lblGym_1.setForeground(new Color(51, 51, 51));
		lblGym_1.setFont(new Font("Rockwell Condensed", Font.BOLD, 79));
		lblGym_1.setBackground(new Color(51, 51, 51));
		lblGym_1.setBounds(919, 298, 435, 78);
		contentPane.add(lblGym_1);
		
		JLabel lblSystem = new JLabel("System");
		lblSystem.setForeground(new Color(51, 51, 51));
		lblSystem.setFont(new Font("Rockwell Condensed", Font.BOLD, 79));
		lblSystem.setBackground(new Color(51, 51, 51));
		lblSystem.setBounds(919, 387, 335, 78);
		contentPane.add(lblSystem);
		
		
		JLabel picture = new JLabel("");
		picture.setBounds(-58, -30, 1616, 992);
		contentPane.add(picture);
		Image image =new ImageIcon(this.getClass().getResource("/main8-01.jpg")).getImage();
		picture.setIcon(new ImageIcon(image));
		
		
		
		
	}
}
