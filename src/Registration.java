import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Registration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JPasswordField txtPre_password;
	private Login login;
	
	
	// Declaring SQL classes
	static Connection conn;
	static PreparedStatement prep_stmt;
	static ResultSet resultSet;
	
	// DB Credentials
	private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private static final String dbName = "studentinventory";
	private static final String dbURL = "jdbc:mysql://localhost:3306/" + dbName;
	private static final String dbUsername = "root";
	private static final String dbPassword = "";
	
	
	// Connection to Database Method
	public static void Connect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			// Checks for Connection
			if(conn != null) {
				System.out.println("Sucessfully Connected to Database!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
					Registration frame = new Registration();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					Connect();
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
						prep_stmt = conn.prepareStatement(
								"INSERT INTO admins (admin_username, admin_email, admin_password) VALUES (?, ?, ?)");
						prep_stmt.setString(1, Username);
						prep_stmt.setString(2, Email);
						prep_stmt.setString(3, Password);
						int i = prep_stmt.executeUpdate();
						if(i == 1) {
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
							JOptionPane.showMessageDialog(new JFrame(), "Registration Error, Please Try Again!", "Alert", JOptionPane.WARNING_MESSAGE);
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
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
