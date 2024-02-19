import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class Registration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtPre_password;
	private Login login;
	private static DBConnection connect = new DBConnection();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setLocationRelativeTo(null);
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
	public Registration() {
		setResizable(false);
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(new Color(0, 128, 128));
		sidePanel.setBounds(0, 0, 228, 461);
		contentPane.add(sidePanel);
		sidePanel.setLayout(null);
		
		JLabel lblMema = new JLabel("Mema");
		lblMema.setForeground(Color.WHITE);
		lblMema.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 40));
		lblMema.setBounds(0, 210, 228, 44);
		lblMema.setHorizontalAlignment(JLabel.CENTER);
		sidePanel.add(lblMema);
		
		JLabel lblInventory = new JLabel("Student Inventory System");
		lblInventory.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
		lblInventory.setForeground(new Color(222, 184, 135));
		lblInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventory.setBounds(0, 265, 228, 36);
		sidePanel.add(lblInventory);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/student-icon.png"));
		JLabel lblIcon = new JLabel(icon);
		lblIcon.setBounds(58, 88, 116, 111);
		sidePanel.add(lblIcon);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 15));
		lblUsername.setBounds(253, 48, 87, 19);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(253, 78, 308, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEmail.setBounds(253, 119, 87, 19);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(253, 149, 308, 30);
		contentPane.add(txtEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassword.setBounds(253, 190, 87, 19);
		contentPane.add(lblPassword);
		
		txtPre_password = new JPasswordField();
		txtPre_password.setBounds(253, 220, 308, 30);
		contentPane.add(txtPre_password);
		
		JLabel lblReenterPassword = new JLabel("Re-Enter Password");
		lblReenterPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lblReenterPassword.setBounds(253, 261, 142, 19);
		contentPane.add(lblReenterPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(253, 291, 308, 30);
		contentPane.add(txtPassword);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.setFont(new Font("Arial", Font.BOLD, 18));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Username = txtUsername.getText();
				String Email = txtEmail.getText();
				String prePassword = new String(txtPre_password.getPassword());
				String Password = new String(txtPassword.getPassword());
				
				// Check Input Error
				if(Username.equals("") || Email.equals("") || prePassword.equals("") || Password.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				else if(!Password.equals(prePassword)) {
					JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						connect.prep_stmt = connect.conn.prepareStatement(
								"SELECT admin_username FROM admins WHERE admin_username = ? OR admin_email = ?");
						connect.prep_stmt.setString(1, Username);
						connect.prep_stmt.setString(2, Email);
						connect.resultSet = connect.prep_stmt.executeQuery();
						// Checks if username already exist
						if(connect.resultSet.next()) {
							JOptionPane.showMessageDialog(new JFrame(), 
									"Username OR Email Already Exist!", "Alert", JOptionPane.WARNING_MESSAGE);
						} else {
							connect.prep_stmt = connect.conn.prepareStatement(
									"INSERT INTO admins (admin_username, admin_email, admin_password) VALUES (?, ?, ?)");
							connect.prep_stmt.setString(1, Username);
							connect.prep_stmt.setString(2, Email);
							connect.prep_stmt.setString(3, Password);
							int row = connect.prep_stmt.executeUpdate();
							// Checks if data inserted to database
							if(row == 1) {
								JOptionPane.showMessageDialog(new JFrame(), "You Successfully Registered");
								txtUsername.setText("");
								txtEmail.setText("");
								txtPre_password.setText("");
								txtPassword.setText("");
								login = new Login();				
								login.setVisible(true);
								login.setLocationRelativeTo(null);
								dispose();
							} else {
								JOptionPane.showMessageDialog(new JFrame(),
										"Registration Error, Please Try Again!", "Alert", JOptionPane.WARNING_MESSAGE);
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					} finally {	
						if(connect.resultSet != null || connect.prep_stmt != null) {
							try {
								connect.resultSet.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							connect.resultSet = null;
							try {
								connect.prep_stmt.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							connect.prep_stmt = null;
						}
					}
				}	
			}
		});
		btnSignUp.setBorder(null);
		btnSignUp.setBackground(new Color(0, 255, 127));
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setBounds(253, 347, 308, 40);
		btnSignUp.setFocusable(false);
		btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand Cursor
		contentPane.add(btnSignUp);
		
		JLabel lblHaveAcc = new JLabel("Already have an Account?");
		lblHaveAcc.setFont(new Font("Arial", Font.PLAIN, 12));
		lblHaveAcc.setBounds(299, 423, 142, 14);
		contentPane.add(lblHaveAcc);
		
		JButton btnSignIn = new JButton("Click Here");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnSignIn.setBorder(null);
		btnSignIn.setFont(new Font("Arial", Font.BOLD, 12));
		btnSignIn.setBounds(438, 419, 67, 23);
        btnSignIn.setBorder(null);
        btnSignIn.setBorderPainted(false);
        btnSignIn.setContentAreaFilled(false);
        btnSignIn.setFocusable(false);
        btnSignIn.setOpaque(false);
        btnSignIn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand Cursor
		contentPane.add(btnSignIn);
	}
}
