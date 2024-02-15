import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.*;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
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
	
	public static void Connect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			// Checks for Connection
			if(conn != null) {
				System.out.println("Sucessfully Connected to Database!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
					Login frame = new Login();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
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
	public Login() {
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(new Color(0, 128, 128));
		sidePanel.setBounds(356, 0, 228, 461);
		contentPane.add(sidePanel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 15));
		lblUsername.setBounds(26, 90, 87, 19);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(26, 120, 308, 30);
		contentPane.add(txtUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassword.setBounds(26, 161, 87, 19);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(26, 191, 308, 30);
		contentPane.add(txtPassword);
		
		JButton btnSignIn = new JButton("SIGN IN");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameTxt = txtUsername.getText();
				String passwordTxt = new String(txtPassword.getPassword());
				
				if(usernameTxt.equals("") || passwordTxt.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
				}
				
				try {
					String query = "SELECT * FROM admins WHERE admin_username = ? AND admin_password = ?";
					prep_stmt = conn.prepareStatement(query);
					prep_stmt.setString(1, usernameTxt);
					prep_stmt.setString(2, passwordTxt);
					resultSet = prep_stmt.executeQuery();
					
					while(resultSet.next()) {
						String username = resultSet.getString("admin_username");
						String password = resultSet.getString("admin_password");
						
						if(!usernameTxt.equals(username) && !passwordTxt.equals(password)) {
							JOptionPane.showMessageDialog(new JFrame(), "Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "You Successfully Logged In!!");
						}
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSignIn.setForeground(Color.WHITE);
		btnSignIn.setFont(new Font("Arial", Font.BOLD, 18));
		btnSignIn.setFocusable(false);
		btnSignIn.setBorder(null);
		btnSignIn.setBackground(new Color(0, 255, 127));
		btnSignIn.setBounds(26, 263, 308, 40);
		btnSignIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnSignIn);
		
		JLabel lblDontAcc = new JLabel("Don't Have an Account?");
		lblDontAcc.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDontAcc.setBounds(62, 400, 129, 14);
		contentPane.add(lblDontAcc);
		
		JButton btnSignUpHere = new JButton("Sign Up Here");
		btnSignUpHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration registration = new Registration();
				registration.setVisible(true);
				registration.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnSignUpHere.setOpaque(false);
		btnSignUpHere.setFont(new Font("Arial", Font.BOLD, 12));
		btnSignUpHere.setFocusable(false);
		btnSignUpHere.setContentAreaFilled(false);
		btnSignUpHere.setBorderPainted(false);
		btnSignUpHere.setBorder(null);
		btnSignUpHere.setBounds(194, 396, 79, 23);
		btnSignUpHere.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnSignUpHere);
	}
}
