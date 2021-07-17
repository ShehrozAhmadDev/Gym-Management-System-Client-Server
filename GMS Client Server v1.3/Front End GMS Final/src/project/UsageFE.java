package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class UsageFE extends JFrame {

	private JPanel contentPane;
	private JTextField eqIdTextField;
	private JTextField memIdTextField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsageFE frame = new UsageFE();
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
	public UsageFE(){

	}

	public UsageFE(ArrayList<Usage> usages) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1370, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		Object columnNames[] = { "Equipment ID", "Equipment Name", "Member ID", "Member Name" };
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(506, 188, 761, 383);
		contentPane.add(scrollPane);


		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		Object[] rowData=new Object[4];
		for (int i=0;i<usages.size();i++){
			rowData[0]=usages.get(i).getEquipment().getId();
			rowData[1]=usages.get(i).getEquipment().getName();
			rowData[2]=usages.get(i).getMember().getId();
			rowData[3]=usages.get(i).getMember().getName();
			tableModel.addRow(rowData);
		}

		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
	    JTableHeader header = table.getTableHeader();
	    header.setBackground(new Color(255, 255, 102));
	    header.setForeground(new Color(51, 51, 51));
		
		header.setFont(new Font("Rockwell Condensed", Font.PLAIN, 20));	

	    table.setGridColor(Color.yellow);
	    table.setBackground(new Color(51, 51, 51));
	    table.setForeground(new Color(255, 255, 102));
		table.setFont(new Font("Rockwell Condensed", Font.PLAIN, 18));
		table.setRowHeight(30);
	    
		
		JLabel mainLbl = new JLabel("Equipment Usage Data");
		mainLbl.setBackground(new Color(51, 51, 51));
		mainLbl.setForeground(new Color(255, 255, 102));
		mainLbl.setFont(new Font("Rockwell Condensed", Font.BOLD, 60));
		mainLbl.setBounds(370, 32, 547, 78);
		mainLbl.setOpaque(true);

		contentPane.add(mainLbl);
		
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m=new MainMenu();
				m.setVisible(true);
				dispose();
			}
		});
		backBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		backBtn.setForeground(new Color(255, 255, 102));
		backBtn.setBackground(new Color(51, 51, 51));
		backBtn.setBounds(65, 632, 137, 38);
		contentPane.add(backBtn);
		
		JButton addUsageBtn = new JButton("Add Usage");
		addUsageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eqid=Integer.parseInt(eqIdTextField.getText());
				int memid=Integer.parseInt(memIdTextField.getText());

				try {
					UDPClient1 c=new UDPClient1();
					c.addUsage(eqid,memid);
					dispose();
					UDPClient1.viewUsage();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		addUsageBtn.setForeground(new Color(255, 255, 102));
		addUsageBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		addUsageBtn.setBackground(new Color(51, 51, 51));
		addUsageBtn.setBounds(47, 486, 170, 38);
		contentPane.add(addUsageBtn);
		
		JLabel eqIdLbl = new JLabel("Equipment Id:");
		eqIdLbl.setForeground(new Color(255, 255, 102));
		eqIdLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		eqIdLbl.setBounds(73, 188, 251, 45);
		contentPane.add(eqIdLbl);
		
		eqIdTextField = new JTextField();
		eqIdTextField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		eqIdTextField.setColumns(10);
		eqIdTextField.setBounds(73, 244, 310, 38);
		contentPane.add(eqIdTextField);
		
		JLabel memIdLbl = new JLabel("Member Id:");
		memIdLbl.setForeground(new Color(255, 255, 102));
		memIdLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		memIdLbl.setBounds(73, 321, 196, 45);
		contentPane.add(memIdLbl);
		
		memIdTextField = new JTextField();
		memIdTextField.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		memIdTextField.setColumns(10);
		memIdTextField.setBounds(73, 377, 310, 38);
		contentPane.add(memIdTextField);
		
		JButton searchUsageBtn = new JButton("Search Usage");
		searchUsageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int eqid=Integer.parseInt(eqIdTextField.getText());
				int memid=Integer.parseInt(memIdTextField.getText());

				try {
					UDPClient1 c=new UDPClient1();
					c.searchUsage(eqid,memid);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		searchUsageBtn.setForeground(new Color(255, 255, 102));
		searchUsageBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		searchUsageBtn.setBackground(new Color(51, 51, 51));
		searchUsageBtn.setBounds(256, 486, 170, 38);
		contentPane.add(searchUsageBtn);
		
		JLabel picture = new JLabel("");

		
		picture.setBounds(-273, -54, 1677, 944);
		contentPane.add(picture);
		Image image =new ImageIcon(this.getClass().getResource("/aslv.jpg")).getImage();
		picture.setIcon(new ImageIcon(image));
		

	}

}
