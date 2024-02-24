import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private DBConnection connect = new DBConnection(); // Database Connection Class
	
	
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
		connect.Connect();
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				
				try {
					if(usernameTxt.equals("") || passwordTxt.equals("")) { // Check for empty fields
						JOptionPane.showMessageDialog(new JFrame(), 
								"Please Fill out all Fields", "Alert", JOptionPane.WARNING_MESSAGE);
					} else {
						connect.prep_stmt = connect.conn.prepareStatement("SELECT * FROM admins WHERE admin_username = ?");
						connect.prep_stmt.setString(1, usernameTxt);
						connect.resultSet = connect.prep_stmt.executeQuery();
						
						// Checks if username exist
						if(!connect.resultSet.next()) {
							JOptionPane.showMessageDialog(new JFrame(), 
									"Username does not exist, Please sign up first!", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							String admin_password = connect.resultSet.getString("admin_password");
							// Checks if password is correct
							if(!passwordTxt.equals(admin_password)) {
								JOptionPane.showMessageDialog(new JFrame(), 
										"Password is Incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(new JFrame(), "You Successfully Logged In!!");
								connect.conn.close();
							}
						}
					}
						
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(new JFrame(), 
							"An error occurred. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(null);
		sidePanel.setBackground(new Color(0, 128, 128));
		sidePanel.setBounds(356, 0, 228, 461);
		contentPane.add(sidePanel);
		
		JLabel lblMema = new JLabel("Mema");
		lblMema.setHorizontalAlignment(SwingConstants.CENTER);
		lblMema.setForeground(Color.WHITE);
		lblMema.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 40));
		lblMema.setBounds(0, 210, 228, 44);
		sidePanel.add(lblMema);
		
		JLabel lblInventory = new JLabel("Student Inventory System");
		lblInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventory.setForeground(new Color(222, 184, 135));
		lblInventory.setFont(new Font("Rockwell Condensed", Font.BOLD, 20));
		lblInventory.setBounds(0, 265, 228, 36);
		sidePanel.add(lblInventory);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/student-icon.png"));
		JLabel lblIcon = new JLabel(icon);
		lblIcon.setBounds(58, 88, 116, 111);
		sidePanel.add(lblIcon);
	}
}
