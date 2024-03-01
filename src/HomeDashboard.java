import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import database.DBConnection;
import modals.AddAdminModal;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class HomeDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchTxt;
	private JTable adminTbl = new JTable();
	private DBConnection connect = new DBConnection();
	
	public void readData() {
		try {	
			connect.prep_stmt = connect.conn.prepareStatement(
					"SELECT * FROM admins");
			connect.resultSet = connect.prep_stmt.executeQuery();
			connect.rsmd = connect.resultSet.getMetaData();
			DefaultTableModel model = (DefaultTableModel) adminTbl.getModel();
			model.setRowCount(0); // Resets the row
			
			// Setting Column Names
			int cols = connect.rsmd.getColumnCount();
			String[] colName = new String[cols];
			for(int i = 0; i < cols; i++) {
				colName[i] = connect.rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			// Iterate each row
			String admin_id, admin_username, admin_email, admin_password;
			while(connect.resultSet.next()) {
				admin_id = connect.resultSet.getString(1);
				admin_username = connect.resultSet.getString(2);
				admin_email = connect.resultSet.getString(3);
				admin_password = connect.resultSet.getString(4);
				
				String[] row = {admin_id, admin_username, admin_email, admin_password};
				model.addRow(row);
			}
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeDashboard frame = new HomeDashboard(0, null, null, null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private int admin_id;
	private String admin_username;
	private String admin_email;
	private String admin_password;
	private JPasswordField yourPasswordTxt;
	
	public HomeDashboard(int aId, String aUsername, String aEmail, String aPassword) {
		
			this.admin_id = aId;
			this.admin_username = aUsername;
			this.admin_email = aEmail;
			this.admin_password = aPassword;
			
//			if(admin_id != 0 && admin_username != "" && admin_email != "") { // Check Check if the user logged in
				connect.Connect();
				readData();
//				setVisible(true);
//				setLocationRelativeTo(null);
				setResizable(false);
				setBounds(100, 100, 1000, 650);
				setUndecorated(true);
				contentPane = new JPanel();
		//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
				contentPane.setLayout(new BorderLayout(0, 0));
				
				JPanel headerPanel = new JPanel();
				headerPanel.setBorder(new EmptyBorder(5, 5, 5, 12));
				headerPanel.setBackground(new Color(0, 153, 102));
				headerPanel.setPreferredSize(new Dimension(800, 60));
				contentPane.add(headerPanel, BorderLayout.NORTH);
				
				JLabel lblClose = new JLabel("");
				lblClose.setBounds(960, 11, 30, 30);
				lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblClose.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
				});
				headerPanel.setLayout(null);
				lblClose.setIcon(new ImageIcon(HomeDashboard.class.getResource("/img/icons-close.png")));
				headerPanel.add(lblClose);
				
				JLabel lblNewLabel = new JLabel("Student Inventory System");
				lblNewLabel.setForeground(new Color(255, 255, 204));
				lblNewLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
				lblNewLabel.setBounds(33, 15, 269, 30);
				headerPanel.add(lblNewLabel);
				
				JPanel sidePanel = new JPanel();
				sidePanel.setBackground(new Color(153, 102, 51));
				sidePanel.setPreferredSize(new Dimension(180, 10));
				contentPane.add(sidePanel, BorderLayout.WEST);
				sidePanel.setLayout(null);
				
				
				CardLayout cl_bodyPnl = new CardLayout();
				
				JPanel bodyPnl = new JPanel();
				contentPane.add(bodyPnl, BorderLayout.CENTER);
				bodyPnl.setLayout(cl_bodyPnl);
				
				JPanel homePnl = new JPanel();
				homePnl.setBackground(Color.WHITE);
				bodyPnl.add(homePnl, "home");
				homePnl.setLayout(null);
				
				JLabel lblNewLabel_1 = new JLabel("Welcome, ");
				lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
				lblNewLabel_1.setBounds(235, 42, 158, 57);
				homePnl.add(lblNewLabel_1);
				
				JLabel lblUserName = new JLabel("");
				lblUserName.setText(admin_username);
				lblUserName.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
				lblUserName.setBounds(389, 42, 181, 57);
				homePnl.add(lblUserName);
				
				JPanel studentsPnl = new JPanel();
				studentsPnl.setBackground(Color.WHITE);
				bodyPnl.add(studentsPnl, "students");
				
				JPanel adminsPnl = new JPanel();
				adminsPnl.setBackground(Color.WHITE);
				bodyPnl.add(adminsPnl, "admins");
				adminsPnl.setLayout(null);
				
				JButton btnAddAdmin = new JButton("Add Admin");
				btnAddAdmin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddAdminModal frame = new AddAdminModal();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						frame.addWindowListener(new WindowAdapter() {
							@Override
				            public void windowClosed(WindowEvent e) {
				                // This method is called when the window is closed.
				                readData();
				            }
						});
					}
				});
				btnAddAdmin.setBounds(50, 57, 104, 32);
				adminsPnl.add(btnAddAdmin);
				
				searchTxt = new JTextField();
				searchTxt.setBounds(549, 57, 151, 32);
				adminsPnl.add(searchTxt);
				searchTxt.setColumns(10);
				
				JButton btnSearch = new JButton("Search");
				btnSearch.setBounds(699, 57, 75, 32);
				adminsPnl.add(btnSearch);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(50, 100, 728, 468);
				adminsPnl.add(scrollPane);
			    
		        adminTbl.getTableHeader().setReorderingAllowed(false);
		        adminTbl.setDefaultEditor(Object.class, null);
				scrollPane.setViewportView(adminTbl);
				
				JPanel profilePnl = new JPanel();
				profilePnl.setBackground(Color.WHITE);
				bodyPnl.add(profilePnl, "profile");
				profilePnl.setLayout(null);
				
				JLabel lblNewLabel_2 = new JLabel("Profile");
				lblNewLabel_2.setBounds(10, 11, 155, 30);
				lblNewLabel_2.setPreferredSize(new Dimension(55, 30));
				lblNewLabel_2.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
				profilePnl.add(lblNewLabel_2);
				
				JLabel lblNewLabel_3 = new JLabel("YOUR INFO:");
				lblNewLabel_3.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_3.setBounds(343, 54, 134, 30);
				profilePnl.add(lblNewLabel_3);
				
				JLabel lblUsername = new JLabel("Username:");
				lblUsername.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
				lblUsername.setBounds(246, 150, 99, 22);
				profilePnl.add(lblUsername);
				
				JLabel lblYourUsername = new JLabel("New label");
				lblYourUsername.setFont(new Font("Microsoft YaHei", Font.ITALIC, 15));
				lblYourUsername.setBounds(355, 150, 213, 22);
				lblYourUsername.setText(admin_username);
				profilePnl.add(lblYourUsername);
				
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
				lblEmail.setBounds(246, 197, 99, 22);
				profilePnl.add(lblEmail);
				
				JLabel lblYourEmail = new JLabel("New label");
				lblYourEmail.setFont(new Font("Microsoft YaHei", Font.ITALIC, 15));
				lblYourEmail.setBounds(355, 197, 213, 22);
				lblYourEmail.setText(admin_email);
				profilePnl.add(lblYourEmail);
				
				JLabel lblYourPassword = new JLabel("Password:");
				lblYourPassword.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
				lblYourPassword.setBounds(246, 245, 99, 22);
				profilePnl.add(lblYourPassword);
				
				yourPasswordTxt = new JPasswordField();
				yourPasswordTxt.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
				yourPasswordTxt.setEchoChar('\u25CF');
				yourPasswordTxt.setEnabled(false);
				yourPasswordTxt.setBounds(355, 245, 213, 22);
				profilePnl.add(yourPasswordTxt);
				yourPasswordTxt.setText(admin_password);
				
				JCheckBox chckbxShow = new JCheckBox("Show");
				chckbxShow.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(chckbxShow.isSelected()) {
							// show pass
							yourPasswordTxt.setEchoChar((char) 0);
						} else {
							// hide
							yourPasswordTxt.setEchoChar('\u25CF');
						}
					}
				});
				chckbxShow.setFocusable(false);
				chckbxShow.setBackground(null);
				chckbxShow.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
				chckbxShow.setBounds(503, 274, 65, 25);
				profilePnl.add(chckbxShow);
				
				
				JLabel lblNavHome = new JLabel("Home", SwingConstants.CENTER);
				lblNavHome.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
		//				lblNavBlue.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
						cl_bodyPnl.show(bodyPnl, "home");
					}
				});
				lblNavHome.setForeground(new Color(255, 255, 204));
				lblNavHome.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
				lblNavHome.setBounds(0, 84, 180, 46);
				lblNavHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
				sidePanel.add(lblNavHome);
				
				JLabel lblNavStudents = new JLabel("Students", JLabel.CENTER);
				lblNavStudents.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
		//				lblNavRed.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
						cl_bodyPnl.show(bodyPnl, "students");
					}
				});
				lblNavStudents.setForeground(new Color(255, 255, 204));
				lblNavStudents.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
				lblNavStudents.setBounds(0, 132, 180, 46);
				lblNavStudents.setCursor(new Cursor(Cursor.HAND_CURSOR));
				sidePanel.add(lblNavStudents);
				
				JLabel lblNavAdmins = new JLabel("Admins", SwingConstants.CENTER);
				lblNavAdmins.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
		//				lblNavGreen.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
						cl_bodyPnl.show(bodyPnl, "admins");
					}
				});
				lblNavAdmins.setForeground(new Color(255, 255, 204));
				lblNavAdmins.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
				lblNavAdmins.setBounds(0, 181, 180, 46);
				lblNavAdmins.setCursor(new Cursor(Cursor.HAND_CURSOR));
				sidePanel.add(lblNavAdmins);
				
				JButton btnLogOut = new JButton("LOG OUT");
				btnLogOut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(new JFrame() , "Are you sure you want to Log out?", 
								"Logout",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(result == JOptionPane.YES_OPTION) {
							admin_id = 0;
							admin_username = "";
							admin_email = "";
							dispose();
							JOptionPane.showMessageDialog(new JFrame(), "You logged out Successfully!");
							Login login = new Login();
							login.setVisible(true);
							login.setLocationRelativeTo(null);
						}
					}
				});
				btnLogOut.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
				btnLogOut.setBounds(34, 528, 111, 23);
				btnLogOut.setFocusable(false);
				sidePanel.add(btnLogOut);
				
				JLabel lblNavProfile = new JLabel("Profile", SwingConstants.CENTER);
				lblNavProfile.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cl_bodyPnl.show(bodyPnl, "profile");
					}
				});
				lblNavProfile.setForeground(new Color(255, 255, 204));
				lblNavProfile.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
				lblNavProfile.setBounds(0, 227, 180, 46);
				lblNavProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));
				sidePanel.add(lblNavProfile);
			
//		} else {
//			JOptionPane.showMessageDialog(null, "You cannot Access, Please log in first!", "ERROR", JOptionPane.ERROR_MESSAGE);
//			Login login = new Login();
//			login.setVisible(true);
//			login.setLocationRelativeTo(null);
//		}	
	}
}
