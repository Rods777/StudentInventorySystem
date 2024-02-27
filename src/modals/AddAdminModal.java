package modals;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;


public class AddAdminModal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameTxt;
	private JTextField emailTxt;
	private JPasswordField pre_passwordTxt;
	private JPasswordField passwordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAdminModal frame = new AddAdminModal();
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
	public AddAdminModal() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(192, 192, 192)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Admin");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(166, 24, 118, 37);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 430, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(35, 89, 99, 26);
		contentPane.add(lblNewLabel_1);
		
		usernameTxt = new JTextField();
		usernameTxt.setBounds(35, 126, 380, 37);
		contentPane.add(usernameTxt);
		usernameTxt.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(35, 174, 99, 26);
		contentPane.add(lblNewLabel_1_1);
		
		emailTxt = new JTextField();
		emailTxt.setColumns(10);
		emailTxt.setBounds(35, 211, 380, 37);
		contentPane.add(emailTxt);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(35, 259, 99, 26);
		contentPane.add(lblNewLabel_1_1_1);
		
		pre_passwordTxt = new JPasswordField();
		pre_passwordTxt.setBounds(35, 296, 380, 37);
		contentPane.add(pre_passwordTxt);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Re-Enter Password");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(35, 344, 140, 26);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(35, 381, 380, 37);
		contentPane.add(passwordTxt);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 18));
		btnSubmit.setFocusable(false);
		btnSubmit.setBorder(null);
		btnSubmit.setBackground(new Color(0, 255, 127));
		btnSubmit.setBounds(18, 469, 414, 51);
		contentPane.add(btnSubmit);
		
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblNewLabel_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(428, 11, 12, 14);
		lblNewLabel_2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(lblNewLabel_2);
	}
}
