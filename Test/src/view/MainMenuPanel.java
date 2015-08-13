package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		JPanel view = new JPanel();
		JScrollPane scrollable = new JScrollPane(view);
		
		JButton btnPopular = new JButton("Popular");
		
		JButton btnNewButton = new JButton("Genre");
		
		JButton btnNew = new JButton("Just Released");
		
		JButton btnProducersChoice = new JButton("<html>" + "Producer's<br>Choice" + "</html>");
		GroupLayout gl_view = new GroupLayout(view);
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
					.addComponent(btnProducersChoice)
					.addGap(40))
		);
		gl_view.setVerticalGroup(
			gl_view.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_view.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_view.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnProducersChoice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_view.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addComponent(btnPopular, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
						.addComponent(btnNew, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
					.addGap(101))
		);
		view.setLayout(gl_view);
		btnProducersChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
		
		JLabel lblTile = new JLabel("Music Voyage");
		lblTile.setForeground(Color.WHITE);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel loginInstance = new LoginPanel();
				JFrame frame = (JFrame) getTopLevelAncestor();
				frame.setContentPane(loginInstance);
				frame.repaint();
				frame.printAll(frame.getGraphics());
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblTile)
					.addPreferredGap(ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
					.addComponent(btnSignIn))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTile)
						.addComponent(btnSignIn))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

	}
}
