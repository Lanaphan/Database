package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class NewUser extends JPanel {
	private JTextField txtEmailAddress;
	private JTextField txtPassword;
	private JTextField txtConfirmPassword;
	private JTextField txtBirthdayxxxxxxxx;
	private JTextField txtDisplayName;

	/**
	 * Create the panel.
	 */
	public NewUser() {
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setText("Email Address");
		txtEmailAddress.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("Password");
		txtPassword.setColumns(10);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setText("Confirm Password");
		txtConfirmPassword.setColumns(10);
		
		txtBirthdayxxxxxxxx = new JTextField();
		txtBirthdayxxxxxxxx.setText("Birthday (xx/xx/xxxx)");
		txtBirthdayxxxxxxxx.setColumns(10);
		
		txtDisplayName = new JTextField();
		txtDisplayName.setText("Display Name");
		txtDisplayName.setColumns(10);
		
		JCheckBox chckbxMusicArtist = new JCheckBox("Music Artist");
		
		JCheckBox chckbxProducer = new JCheckBox("Producer");
		
		JButton btnNext = new JButton("Next   ->");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    //Check booleans
				if (chckbxMusicArtist.isSelected() && !chckbxProducer.isSelected()) {
					//Music artist only
					
				} else if (!chckbxMusicArtist.isSelected() && chckbxProducer.isSelected()) {
					//Producer only
					
				} else if (chckbxMusicArtist.isSelected() && chckbxProducer.isSelected()) {
					//Both
					
				} else {
					//None
					String password = txtPassword.getText();
					String copyPassword = txtConfirmPassword.getText();
					if (password.equals(copyPassword)) {
						// Creating normal user
						String email = txtEmailAddress.getText();
						String displayName = txtDisplayName.getText();
						String dob = txtBirthdayxxxxxxxx.getText();
						User createUser = new User(email, password, dob, displayName, false, false);

						// Display Finished Screen
						FinishedNewUser finish = new FinishedNewUser();
						JFrame frame = (JFrame) getTopLevelAncestor();
						frame.setContentPane(finish);
						frame.repaint();
						frame.printAll(frame.getGraphics());
					} else {
						JOptionPane.showMessageDialog(getParent(),
							    "Passwords do not match.");
					}
				}
			}
		});
		
		JLabel lblNewUser = new JLabel("New User");
		
		JButton button = new JButton("<-   Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    //Return back to Login Screen
				LoginPanel back = new LoginPanel();
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.setContentPane(back);
				frame.repaint();
				frame.printAll(frame.getGraphics());
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(230, Short.MAX_VALUE)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNext)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewUser)
						.addComponent(chckbxProducer)
						.addComponent(chckbxMusicArtist)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(txtDisplayName, Alignment.LEADING)
							.addComponent(txtBirthdayxxxxxxxx, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
							.addComponent(txtConfirmPassword, Alignment.LEADING)
							.addComponent(txtPassword, Alignment.LEADING)
							.addComponent(txtEmailAddress, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewUser)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtEmailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtConfirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtBirthdayxxxxxxxx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDisplayName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxMusicArtist)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxProducer)
							.addContainerGap(36, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNext)
								.addComponent(button))
							.addContainerGap())))
		);
		setLayout(groupLayout);

	}
}
