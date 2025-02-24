package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.Database;

/**
 * Log in panel for existing users.
 * 
 * @author Jordan Love and Lana Phan
 *
 */
public class LoginPanel extends JPanel {

	private JTextField txtUsername;
	private JTextField txtPassword;
	private Database database;
	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public LoginPanel() throws Exception {
		database = new Database();
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setColumns(10);
		txtUsername.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	txtUsername.setText("");
            }
        });
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		txtPassword.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	txtPassword.setText("");
            }
        });
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    //Check Sign in
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				if (!username.contains("@")) {
					JOptionPane.showMessageDialog(getParent(),
						    "Please enter a valid email address.");
				} else {
					try {
						Connection con = database.getConnection();
						PreparedStatement select = con.prepareStatement ("SELECT password FROM User WHERE email = '"+username+"';");
						ResultSet result = select.executeQuery();
						result.next();
						String testPassword = result.getString("password");
						if (testPassword.equals(password)) {
							//you've signed in
							//prompt new screen
							MainMenuPanel previous = new MainMenuPanel();
							previous.setSignIn(true, username);
							JFrame frame = (JFrame) getTopLevelAncestor();
							frame.setContentPane(previous);
							frame.repaint();
							frame.printAll(frame.getGraphics());
							
						} else {
							JOptionPane.showMessageDialog(getParent(),
								    "Password is wrong!");
						}
					} catch (Exception error) {
						JOptionPane.showMessageDialog(getParent(),
								"This username does not exist");
					}
		
					
				}
			}
		});
		
		JButton btnNewUser = new JButton("New User?");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    //New User Screen
				try {
				NewUserPanel aUser = new NewUserPanel();
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.setContentPane(aUser);
				frame.repaint();
				frame.printAll(frame.getGraphics());
				} catch (Exception error) {
					System.out.println(error);
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel previous = new MainMenuPanel();
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.setContentPane(previous);
				frame.repaint();
				frame.printAll(frame.getGraphics());
			}
		});
		
		JButton button = new JButton("Forgot Password?");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = JOptionPane.showInputDialog(getParent(),
						"What is your email?");
				String password = JOptionPane.showInputDialog(getParent(),
						"New Password");
				try {
					Connection con = database.getConnection();
					PreparedStatement update = con.prepareStatement("UPDATE User SET password='"+password+"' WHERE email='" + email + "';");
					update.executeUpdate();
				} catch (Exception error) {
					System.out.println(error);
				}
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(125)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnLogIn)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
						.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
					.addGap(132))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(250, Short.MAX_VALUE)
					.addComponent(btnNewUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnNewUser))
					.addGap(43)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogIn)
						.addComponent(button))
					.addGap(65))
		);
		this.setLayout(gl_panel);
	}
}
