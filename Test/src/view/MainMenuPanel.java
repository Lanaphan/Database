package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MainMenuPanel extends JPanel {

	private boolean isSignedIn = false;
	private JButton btnSignIn;
	private JButton btnLogOut;
	private User aUser;
	private JPanel view;
	
	public void setSignIn(boolean status, User theUser) {
		isSignedIn = status;
		aUser = theUser;
		changeButton();
	}
	
	/**
	 * Changes the log in button to the display name of the user
	 * and it also makes the logout button visible
	 * Vice Versa
	 */
	private void changeButton() {
		if (isSignedIn) {
			btnSignIn.setText(aUser.getDisplayName());
			btnLogOut.setVisible(true);
		} else {
			btnSignIn.setText("Sign In");
			btnLogOut.setVisible(false);
		}
	}
	
	private JPanel popularView() {
		JPanel aView = new JPanel();
		String bip = "/testFiles/testSong.mp3";
		try{
		    AudioInputStream audioInputStream =
		        AudioSystem.getAudioInputStream(
		            this.getClass().getResource(bip));
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return aView;
	}
	
	private JPanel genreView() {
		JPanel aView = new JPanel();
		
		return aView;
	}
	
	private JPanel releaseView() {
		JPanel aView = new JPanel();
		
		return aView;
	}
	
	private JPanel ourPickView() {
		JPanel aView = new JPanel();
		
		return aView;
	}
	
	private JPanel mainView() {
		JPanel aView = new JPanel();
		JButton btnPopular = new JButton("Popular");
		btnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view = popularView();
			}
		});
		
		JButton btnNewButton = new JButton("Genre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view = genreView();
			}
		});
		
		JButton btnNew = new JButton("Just Released");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view = releaseView();
			}
		});
		
		JButton btnOurPick = new JButton("Our Pick");
		btnOurPick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view = ourPickView();
			}
		});
		
		GroupLayout gl_view = new GroupLayout(aView);
		gl_view.setHorizontalGroup(
			gl_view.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_view.createSequentialGroup()
					.addGap(46)
					.addComponent(btnPopular, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOurPick)
					.addGap(40))
		);
		gl_view.setVerticalGroup(
			gl_view.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_view.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_view.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnOurPick, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_view.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addComponent(btnPopular, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
						.addComponent(btnNew, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
					.addGap(101))
		);
		aView.setLayout(gl_view);
		return aView;
	}
	
	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		view = mainView();
		JScrollPane scrollable = new JScrollPane(view);
		
		
		scrollable.setSize(520, 300);
		scrollable.setBackground(Color.BLUE);
		
		add(scrollable);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 475, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 300, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		scrollable.setColumnHeaderView(panel);
		
		JTextField lblTile = new JTextField("Music Voyage");
		lblTile.setForeground(Color.WHITE);
		lblTile.setEditable(false);
		lblTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view = mainView();
			}
		});
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel loginInstance = new LoginPanel();
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.setContentPane(loginInstance);
				frame.repaint();
				frame.printAll(frame.getGraphics());
			}
		});
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setVisible(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblTile)
					.addPreferredGap(ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
					.addComponent(btnLogOut)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSignIn))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTile)
						.addComponent(btnSignIn)
						.addComponent(btnLogOut))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

	}
}
