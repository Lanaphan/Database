package view;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import controller.Database;

/**
 * @author Jordan Love and Lana Phan
 * 
 * Confirmation Panel that a new user has successfully signed in.
 */
public class FinishedNewUserPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public FinishedNewUserPanel() {
		
		JLabel lblYourProfileHas = new JLabel("Your profile has been all set up!");
		
		JLabel lblPleaseClickFinish = new JLabel("Please click finish to log in!");
		
		JButton btnFinish = new JButton("Finish!");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    //go to log in screen
				LoginPanel newLoginInstance;
				try {
					newLoginInstance = new LoginPanel();
					JFrame frame = (JFrame) getTopLevelAncestor();
					frame.setContentPane(newLoginInstance);
					frame.repaint();
					frame.printAll(frame.getGraphics());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});


		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(122)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPleaseClickFinish)
						.addComponent(btnFinish)
						.addComponent(lblYourProfileHas))
					.addContainerGap(128, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(104)
					.addComponent(lblYourProfileHas)
					.addGap(18)
					.addComponent(lblPleaseClickFinish)
					.addGap(33)
					.addComponent(btnFinish)
					.addContainerGap(84, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
