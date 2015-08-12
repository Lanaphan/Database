package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

public class LoginPanel extends JPanel {

	private JTextField txtUsername;
	private JTextField txtPassword;
	
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		
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
					//Successful Log In
				}
			}
		});
		
		JButton btnNewUser = new JButton("New User?");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    //New User Screen
				NewUser aUser = new NewUser();
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.setContentPane(aUser);
				frame.repaint();
				frame.printAll(frame.getGraphics());
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(this);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(125)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnLogIn)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
								.addComponent(txtUsername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
							.addGap(132))))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(323, Short.MAX_VALUE)
					.addComponent(btnNewUser))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnNewUser)
					.addGap(43)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnLogIn)
					.addGap(65))
		);
		this.setLayout(gl_panel);
	}

}
