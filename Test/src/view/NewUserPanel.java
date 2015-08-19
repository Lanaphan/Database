package view;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.Database;

/**
 * This creates the gui for creating a new user
 *
 */
public class NewUserPanel extends JPanel {
	private JTextField txtEmailAddress;
	private JTextField txtPassword;
	private JTextField txtConfirmPassword;
	private JTextField txtBirthdayxxxxxxxx;
	private JTextField txtDisplayName;
	private Database database;
	
	/**
	 * Create the panel.
	 * Allows the panel to receive data and talk to the database
	 * @throws Exception 
	 */
	public NewUserPanel() throws Exception {
		database = new Database();
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setText("Email Address");
		txtEmailAddress.setColumns(10);
		txtEmailAddress.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                txtEmailAddress.setText("");
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
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setText("Confirm Password");
		txtConfirmPassword.setColumns(10);
		txtConfirmPassword.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	txtConfirmPassword.setText("");
            }
        });
		
		txtBirthdayxxxxxxxx = new JTextField();
		txtBirthdayxxxxxxxx.setText("Birthday (YYYY-MM-DD)");
		txtBirthdayxxxxxxxx.setColumns(12);
		txtBirthdayxxxxxxxx.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	txtBirthdayxxxxxxxx.setText("");
            }
        });
		
		txtDisplayName = new JTextField();
		txtDisplayName.setText("Display Name");
		txtDisplayName.setColumns(10);
		txtDisplayName.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	txtDisplayName.setText("");
            }
        });
		
		JCheckBox chckbxMusicArtist = new JCheckBox("Music Artist");
		
		JCheckBox chckbxProducer = new JCheckBox("Producer");
		
		JButton btnNext = new JButton("Next   ->");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    //Check booleans
				if (chckbxMusicArtist.isSelected() && !chckbxProducer.isSelected()) {
					//Music artist only
					String recordCompany = JOptionPane.showInputDialog(getParent(),
						    "Who is your record company? (Leave blank if not signed).");
					
					String password = txtPassword.getText();
					String copyPassword = txtConfirmPassword.getText();
					if (password.equals(copyPassword)) {
						// Creating normal user
						String email = txtEmailAddress.getText();
						String displayName = txtDisplayName.getText();
						String dob = txtBirthdayxxxxxxxx.getText();
						try {
							Connection con = database.getConnection();
							PreparedStatement insert = con.prepareStatement("INSERT INTO User VALUES ('"+email+"', '"+password+"', '"+displayName+"', '"+ dob +"');");									insert.executeUpdate();
							insert.executeUpdate();
					
						} catch (Exception error) {

						}
						
						try {
							Connection con = database.getConnection();
							
							PreparedStatement insertArt = con.prepareStatement("INSERT INTO Artist VALUES ('" + email + "', '" + recordCompany + "');");
							insertArt.executeUpdate();
							// Display Finished Screen
							FinishedNewUserPanel finish = new FinishedNewUserPanel();
							JFrame frame = (JFrame) getTopLevelAncestor();
							frame.setContentPane(finish);
							frame.repaint();
							frame.printAll(frame.getGraphics());
						} catch (Exception error) {

						}
						
					} else {
						JOptionPane.showMessageDialog(getParent(),
							    "Passwords do not match.");
					}
						
					
				} else if (!chckbxMusicArtist.isSelected() && chckbxProducer.isSelected()) {
					//Producer only
					String companyName = JOptionPane.showInputDialog(getParent(),
						    "What is your company name?");
					String contactInfo = JOptionPane.showInputDialog(getParent(), 
							"Contact Information?");
					
					String password = txtPassword.getText();
					String copyPassword = txtConfirmPassword.getText();
					if (password.equals(copyPassword)) {
						// Creating normal user
						String email = txtEmailAddress.getText();
						String displayName = txtDisplayName.getText();
						String dob = txtBirthdayxxxxxxxx.getText();
						try {
							Connection con = database.getConnection();
							PreparedStatement insert = con.prepareStatement("INSERT INTO User VALUES ('"+email+"', '"+password+"', '"+displayName+"', '"+ dob +"');");									insert.executeUpdate();
							insert.executeUpdate();
							
						} catch (Exception error) {

						}
						try {
							Connection con = database.getConnection();
							
							PreparedStatement insertPro = con.prepareStatement("INSERT INTO Producer VALUES ('" + email + "', '" + companyName + "', '" + contactInfo + "');");
							insertPro.executeUpdate();
							// Display Finished Screen
							FinishedNewUserPanel finish = new FinishedNewUserPanel();
							JFrame frame = (JFrame) getTopLevelAncestor();
							frame.setContentPane(finish);
							frame.repaint();
							frame.printAll(frame.getGraphics());
						} catch (Exception error) {

						}
					} else {
						JOptionPane.showMessageDialog(getParent(),
							    "Passwords do not match.");
					}
					
				} else if (chckbxMusicArtist.isSelected() && chckbxProducer.isSelected()) {
					//Both
					JOptionPane.showMessageDialog(getParent(), "Cannot be both a artist and a producer");
				} else {
					//None
					String password = txtPassword.getText();
					String copyPassword = txtConfirmPassword.getText();
					if (password.equals(copyPassword)) {
						// Creating normal user
						String email = txtEmailAddress.getText();
						String displayName = txtDisplayName.getText();
						String dob = txtBirthdayxxxxxxxx.getText();
						try {
							Connection con = database.getConnection();
							PreparedStatement insert = con.prepareStatement("INSERT INTO User VALUES ('"+email+"', '"+password+"', '"+displayName+"', '"+ dob +"');");									insert.executeUpdate();
							insert.executeUpdate();
						
							// Display Finished Screen
							FinishedNewUserPanel finish = new FinishedNewUserPanel();
							JFrame frame = (JFrame) getTopLevelAncestor();
							frame.setContentPane(finish);
							frame.repaint();
							frame.printAll(frame.getGraphics());
						} catch (Exception error) {
							JOptionPane.showMessageDialog(getParent(),
								    "This email address has already been registered!");
						}
		
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
				LoginPanel back;
				try {
					back = new LoginPanel();
					JFrame frame = (JFrame) getTopLevelAncestor();
					frame.setContentPane(back);
					frame.repaint();
					frame.printAll(frame.getGraphics());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

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
