package view;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import javafx.application.Platform;
import javafx.beans.value.*;
import javafx.embed.swing.JFXPanel;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.util.Duration;

import javax.swing.*;

import controller.Database;

/**
 * Creates a media player in gui and plays the music that is given
 */
public class MusicPlayer {

	private static SceneGenerator aGen;
	private static List<String> songs;

	/**
	 * This creates and shows the jframe and jfxpanel for the media player
	 */
	public static void initAndShowGUI() {
		// This method is invoked on Swing thread
		JFrame frame = new JFrame("Music Player");
		JFXPanel fxPanel = new JFXPanel();
		frame.add(fxPanel);
		frame.setBounds(200, 100, 800, 250);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				aGen.stopMusic();
				frame.dispose();
			}
		});
		frame.setVisible(true);
		Platform.setImplicitExit(false);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					initFX(fxPanel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This allows to set the music list
	 * @param List<String> directory of songs
	 */
	public void setMusic(List<String> songs) {
		this.songs = new ArrayList<String>();
		this.songs.addAll(songs);
	}
	
	/**
	 * Initiate the scene to play the music on a javafx thread
	 * @param JFXPanel
	 * @throws Exception
	 */
	private static void initFX(JFXPanel fxPanel) throws Exception {
		aGen = new SceneGenerator();
		aGen.listOfSongs = new ArrayList<String>();
		aGen.listOfSongs.addAll(songs);
		Scene scene = aGen.createScene();
		fxPanel.setScene(scene);
	}
}

/**
 * Creates a scene with given strings
 *
 */
class SceneGenerator {
	final Label currentlyPlaying = new Label();
	final ProgressBar progress = new ProgressBar();
	private MediaView mediaView;
	private ChangeListener<Duration> progressChangeListener;
	public List<String> listOfSongs;
	private int location = 0;
	private int size = 0;

	/**
	 * Empty constructor
	 */
	SceneGenerator() {
	}

	/**
	 * Creates the gui for the music player
	 * @return Scene
	 * @throws Exception
	 */
	public Scene createScene() throws Exception {
		final StackPane layout = new StackPane();
		

		// determine the source directory for the playlist
		final File dir = new File("C:\\Users\\Lana\\git\\newDatabase\\Test\\testFiles");
		if (!dir.exists() || !dir.isDirectory()) {
			System.out.println("Cannot find video source directory: " + dir);
			Platform.exit();
			return null;
		}

		// create some media players.
		final List<MediaPlayer> players = new ArrayList<MediaPlayer>();
		for (String file : listOfSongs) 
			players.add(createPlayer("file:///" + (dir + "\\" + file).replace("\\", "/").replaceAll(" ", "%20")));
		if (players.isEmpty()) {
			System.out.println("No audio found in " + dir);
			Platform.exit();
			return null;
		}

		// create a view to show the mediaplayers.
		mediaView = new MediaView(players.get(0));
		final Button next = new Button("Next");
		final Button play = new Button("Pause");
		final Button like = new Button("Like");
		final Button message = new Button("Message");

		// play each audio file in turn.
		for (int i = 0; i < players.size(); i++) {
			size = players.size();
			final MediaPlayer player = players.get(i);
			final MediaPlayer nextPlayer = players.get((i + 1) % players.size());
			player.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					player.currentTimeProperty().removeListener(progressChangeListener);
					mediaView.setMediaPlayer(nextPlayer);
					nextPlayer.play();
				}
			});
		}

		// allow the user to skip a track.
		next.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				location++;
				if (location == size)
					location = 0;
				final MediaPlayer curPlayer = mediaView.getMediaPlayer();
				MediaPlayer nextPlayer = players.get((players.indexOf(curPlayer) + 1) % players.size());
				mediaView.setMediaPlayer(nextPlayer);
				curPlayer.currentTimeProperty().removeListener(progressChangeListener);
				curPlayer.stop();
				nextPlayer.play();
			}
		});

		// allow the user to play or pause a track.
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if ("Pause".equals(play.getText())) {
					mediaView.getMediaPlayer().pause();
					play.setText("Play");
				} else {
					mediaView.getMediaPlayer().play();
					play.setText("Pause");
				}
			}
		});

		// allow the user to like a track.
		like.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				if (like.getText().equals("Liked")) {
					// Do nothing
				} else {
					like.setText("Liked");
					Database database;
					try {
						database = new Database();
						Connection con = database.getConnection();
						PreparedStatement update = con.prepareStatement("SELECT like_counter FROM Music WHERE filename = '"+ listOfSongs.get(location) +"';");
						
						ResultSet result = update.executeQuery();
						result.next();
						Integer likeCount = result.getInt("like_counter");
						likeCount++;
						
						PreparedStatement updateLike = con.prepareStatement("UPDATE Music SET like_counter='"+likeCount+"' WHERE filename='" + listOfSongs.get(location) + "';");
						
						updateLike.executeUpdate();
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				}
			}
		});

		// allow user to message another user
		message.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				SwingUtilities.invokeLater( new Runnable() 
				{

					@Override
					public void run() {
						String message = JOptionPane.showInputDialog(null,
							    "Enter your message below.");
						
					}
				} );
				
				
			}
		});

		// display the name of the currently playing track.
		mediaView.mediaPlayerProperty().addListener(new ChangeListener<MediaPlayer>() {
			@Override
			public void changed(ObservableValue<? extends MediaPlayer> observableValue, MediaPlayer oldPlayer,
					MediaPlayer newPlayer) {
				try {
					setCurrentlyPlaying(newPlayer);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// start playing the first track.
		mediaView.setMediaPlayer(players.get(0));
		mediaView.getMediaPlayer().play();
		setCurrentlyPlaying(mediaView.getMediaPlayer());

		Button invisiblePause = new Button("Pause");
		invisiblePause.setVisible(false);
		play.prefHeightProperty().bind(invisiblePause.heightProperty());
		play.prefWidthProperty().bind(invisiblePause.widthProperty());

		// layout the scene.
		layout.setStyle("-fx-background-color: white; -fx-font-size: 20; -fx-padding: 20; -fx-alignment: center;");
		layout.getChildren().addAll(invisiblePause,
				VBoxBuilder.create().spacing(10)
						.alignment(Pos.CENTER).children(currentlyPlaying, mediaView, progress, HBoxBuilder.create()
								.spacing(10).alignment(Pos.CENTER).children(play, next, like, message).build())
				.build());
		progress.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(progress, Priority.ALWAYS);
		return new Scene(layout, 800, 600);

	}

	/**
	 * sets the currently playing label to the label of the new media player and
	 * updates the progress monitor.
	 * @throws Exception 
	 */
	private void setCurrentlyPlaying(final MediaPlayer newPlayer) throws Exception {
		progress.setProgress(0);
		progressChangeListener = new ChangeListener<Duration>() {
			@Override
			public void changed(ObservableValue<? extends Duration> observableValue, Duration oldValue,
					Duration newValue) {
				progress.setProgress(
						1.0 * newPlayer.getCurrentTime().toMillis() / newPlayer.getTotalDuration().toMillis());
			}
		};
		newPlayer.currentTimeProperty().addListener(progressChangeListener);

		String source = newPlayer.getMedia().getSource();
		source = source.substring(0, source.length() - ".mp4".length());
		source = source.substring(source.lastIndexOf("/") + 1).replaceAll("%20", " ");
		String title;
		String artist;
		Database database = new Database();
		try {

			Connection con = database.getConnection();
			PreparedStatement select = con.prepareStatement ("SELECT title, user_email FROM Music WHERE filename = '"+listOfSongs.get(location)+"';");
			ResultSet result = select.executeQuery();
			result.next();
			
			title = result.getString("title");
			String email = result.getString("user_email");
			
			select = con.prepareStatement("SELECT display_name FROM User WHERE email = '" + email + "';");
			result = select.executeQuery();
			result.next();
			artist = result.getString("display_name");
			currentlyPlaying.setText("Artist: " + artist + "\nSong: " + title);
		} catch (Exception error) {
			System.out.println("Song doesn't exist");
		}		
	}

	public void stopMusic() {
		mediaView.getMediaPlayer().pause();
	}

	/**
	 * @return a MediaPlayer for the given source which will report any errors
	 *         it encounters
	 */
	private MediaPlayer createPlayer(String aMediaSrc) {
		System.out.println("Creating player for: " + aMediaSrc);
		final MediaPlayer player = new MediaPlayer(new Media(aMediaSrc));
		player.setOnError(new Runnable() {
			@Override
			public void run() {
				System.out.println("Media error occurred: " + player.getError());
			}
		});
		return player;
	}
}