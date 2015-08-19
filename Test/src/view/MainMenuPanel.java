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

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;






import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.Database;

/**
 * Main Menu for our program.
 * Will update if a User is already signed in.
 * 
 * @author Jordan Love and Lana Phan
 *
 */
public class MainMenuPanel extends JPanel {

	private boolean isSignedIn = false;
	private JButton btnSignIn;
	private JButton btnLogOut;
	private JPanel view;

	public void setSignIn(boolean status, String email) throws Exception {
		isSignedIn = status;
		changeButton(email);
	}
	
	/**
	 * Changes the log in button to the display name of the user
	 * and it also makes the logout button visible
	 * Vice Versa
	 * @throws Exception 
	 */
	private void changeButton(String email) throws Exception {
		if (isSignedIn) {
			Database database = new Database();
			Connection con = database.getConnection();
			PreparedStatement select = con.prepareStatement ("SELECT display_name FROM User WHERE email = '"+ email  +"';");
			ResultSet result = select.executeQuery();
			result.next();
			String username = result.getString("display_name");
			
			btnSignIn.setText(username);
			btnLogOut.setVisible(true);
		} else {
			btnSignIn.setText("Sign In");
			btnLogOut.setVisible(false);
		}
	}
	
	/**
	 * Returns a Panel that contains the main
	 * functionality of our projects.
	 * Contains buttons that will play song list based on
	 * Popular, Genre, Just Released, and Our Pick.
	 * @return JPanel 
	 */
	private JPanel mainView() {
		JPanel aView = new JPanel();
		JButton btnPopular = new JButton("Popular");
		btnPopular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Grab songs to push into music player
				List<String> songs =  new ArrayList<String>();
				try {
					Database database = new Database();
					Connection con = database.getConnection();
					PreparedStatement update = con.prepareStatement("SELECT filename FROM Music ORDER BY like_counter DESC;");
					
					ResultSet result = update.executeQuery();
					while (result.next()) {
						String filename = result.getString("filename");
						songs.add(filename);
					}
				    MusicPlayer player = new MusicPlayer();
				    player.setMusic(songs);
				    player.initAndShowGUI();
				} catch (Exception error) {
					System.out.println(error);
				}

			}
		});
		
		JButton btnNewButton = new JButton("Genre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> songs =  new ArrayList<String>();
				try {
					Database database = new Database();
					Connection con = database.getConnection();
					PreparedStatement update = con.prepareStatement("SELECT filename FROM Music ORDER BY genre;");
					
					ResultSet result = update.executeQuery();
					while (result.next()) {
						String filename = result.getString("filename");
						songs.add(filename);
					}
				    MusicPlayer player = new MusicPlayer();
				    player.setMusic(songs);
				    player.initAndShowGUI();
				} catch (Exception error) {
					System.out.println(error);
				}

			}
		});
		
		JButton btnNew = new JButton("<html><center>Just<br> Released</html>");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> songs =  new ArrayList<String>();
				try {
					Database database = new Database();
					Connection con = database.getConnection();
					PreparedStatement update = con.prepareStatement("SELECT filename FROM Music ORDER BY year DESC;");
					
					ResultSet result = update.executeQuery();
					while (result.next()) {
						String filename = result.getString("filename");
						songs.add(filename);
					}
				    MusicPlayer player = new MusicPlayer();
				    player.setMusic(songs);
				    player.initAndShowGUI();
				} catch (Exception error) {
					System.out.println(error);
				}
			}
		});
		
		JButton btnOurPick = new JButton("Our Pick");
		btnOurPick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> songs =  new ArrayList<String>();
				try {
					Database database = new Database();
					Connection con = database.getConnection();
					PreparedStatement update = con.prepareStatement("SELECT filename FROM Music;");
					
					ResultSet result = update.executeQuery();
					while (result.next()) {
						String filename = result.getString("filename");
						songs.add(filename);
					}
				    MusicPlayer player = new MusicPlayer();
				    player.setMusic(songs);
				    player.initAndShowGUI();
				} catch (Exception error) {
					System.out.println(error);
				}
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
		
		JTextField lblTile = new JTextField("Music Voyager");
		lblTile.setEditable(false);
		lblTile.setForeground(Color.WHITE);
		lblTile.setBackground(Color.BLUE);
		lblTile.setBorder(null);
		lblTile.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	view = mainView();
				System.out.println("Set to home");
            }
        });
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel loginInstance;
				try {
					loginInstance = new LoginPanel();
					JFrame frame = (JFrame) getTopLevelAncestor();
					frame.setContentPane(loginInstance);
					frame.repaint();
					frame.printAll(frame.getGraphics());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

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
