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

public class MembersFE extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MembersFE frame = new MembersFE();
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
	public MembersFE(){

	}

	public MembersFE(ArrayList<Member> members) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1370, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Object columnNames[] = { "Member ID", "Member Name", "Age", "Contact" };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 236, 953, 339);
		contentPane.add(scrollPane);
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		Object[] rowData=new Object[4];
		for (int i=0;i<members.size();i++){
			rowData[0]=members.get(i).getId();
			rowData[1]=members.get(i).getName();
			rowData[2]=members.get(i).getAge();
			rowData[3]=members.get(i).getContact();
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
	    
		
		JLabel mainLbl = new JLabel("Members Data");
		mainLbl.setBackground(new Color(51, 51, 51));
		mainLbl.setForeground(new Color(255, 255, 102));
		mainLbl.setFont(new Font("Rockwell Condensed", Font.BOLD, 60));
		mainLbl.setBounds(468, 35, 349, 78);
		mainLbl.setOpaque(true);

		contentPane.add(mainLbl);
		
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu m=new MainMenu();
				m.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		btnNewButton.setForeground(new Color(255, 255, 102));
		btnNewButton.setBackground(new Color(51, 51, 51));
		btnNewButton.setBounds(198, 627, 137, 38);
		contentPane.add(btnNewButton);
		
		

		searchTextField = new JTextField();
		searchTextField.setFont(new Font("Rockwell", Font.PLAIN, 30));
		searchTextField.setBackground(new Color(255, 255, 255));
		searchTextField.setBounds(966, 171, 185, 38);
		contentPane.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id= Integer.parseInt(searchTextField.getText());
				if(id==0){

				}
				else{
					try {
						UDPClient1 c=new UDPClient1();
						c.searchMember(id);
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}

			}
		});
		searchBtn.setForeground(new Color(255, 255, 102));
		searchBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		searchBtn.setBackground(new Color(51, 51, 51));
		searchBtn.setBounds(1182, 171, 137, 38);
		contentPane.add(searchBtn);
		
		JLabel idLbl = new JLabel("ID:");
		idLbl.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		idLbl.setForeground(new Color(255, 255, 102));
		idLbl.setBounds(858, 171, 85, 38);
		contentPane.add(idLbl);
		
		JButton addMemberBtn = new JButton("Add Member");
		addMemberBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMember am=new AddMember();
				am.setVisible(true);
				dispose();
			}
		});
		addMemberBtn.setForeground(new Color(255, 255, 102));
		addMemberBtn.setFont(new Font("Rockwell Condensed", Font.PLAIN, 30));
		addMemberBtn.setBackground(new Color(51, 51, 51));
		addMemberBtn.setBounds(198, 171, 222, 38);
		contentPane.add(addMemberBtn);
		
		JLabel picture = new JLabel("");
		picture.setBounds(-273, -54, 1677, 944);
		contentPane.add(picture);
		Image image =new ImageIcon(this.getClass().getResource("/aslv.jpg")).getImage();
		picture.setIcon(new ImageIcon(image));
		

	}

}
