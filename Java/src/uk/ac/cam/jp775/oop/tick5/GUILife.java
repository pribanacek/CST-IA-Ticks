package uk.ac.cam.jp775.oop.tick5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUILife extends JFrame implements ListSelectionListener {

	private ArrayList<World> mCachedWorlds = new ArrayList<World>();
	private World mWorld;
	private PatternStore mStore;
	private GamePanel mGamePanel;
	private JButton mPlayButton;
	private Timer mTimer;
	private boolean mPlaying;

	public GUILife(PatternStore ps) {
		super("Game of Life");
		mStore = ps;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1024, 768);

		add(createPatternsPanel(), BorderLayout.WEST);
		add(createControlPanel(), BorderLayout.SOUTH);
		add(createGamePanel(), BorderLayout.CENTER);

		mGamePanel.display(mWorld);
	}

	private World copyWorld(boolean useCloning) {
		if (!useCloning) {
			if (mWorld instanceof PackedWorld) {
				return new PackedWorld((PackedWorld) mWorld);
			}
			return new ArrayWorld((ArrayWorld) mWorld);
		}
		return mWorld.clone();
	}

	private void moveForward() {
		if (mWorld == null)
			System.out.println("Please select a pattern to play (l to list):");
		else {
			if (mWorld.getGenerationCount() < mCachedWorlds.size() - 1) {
				mWorld = mCachedWorlds.get(mWorld.getGenerationCount() + 1);
			} else {
				mWorld = copyWorld(true);
				mWorld.nextGeneration();
				mCachedWorlds.add(mWorld);
			}
			mGamePanel.display(mWorld);
		}
	}

	private void moveBack() {
		if (mWorld == null) {
			System.out.println("Please select a pattern to play (l to list):");
		} else {
			if (mWorld.getGenerationCount() > 0) {
				mWorld = mCachedWorlds.get(mWorld.getGenerationCount() - 1);
			}
			mGamePanel.display(mWorld);
		}
		mPlaying = true;
		runOrPause();
	}

	private void addBorder(JComponent component, String title) {
		Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border tb = BorderFactory.createTitledBorder(etch, title);
		component.setBorder(tb);
	}

	private JPanel createGamePanel() {
		mGamePanel = new GamePanel();
		addBorder(mGamePanel, "Game Panel");
		return mGamePanel;
	}

	private JPanel createPatternsPanel() {
		JPanel patt = new JPanel();
		addBorder(patt, "Patterns");
		patt.setLayout(new BorderLayout());

		JList<Object> list = new JList<Object>(mStore.getPatternsNameSorted().toArray());
		list.addListSelectionListener(this);

		JScrollPane pane = new JScrollPane(list);
		patt.add(pane);

		return patt;
	}

	private JPanel createControlPanel() {
		JPanel ctrl = new JPanel();
		addBorder(ctrl, "Controls");
		ctrl.setLayout(new GridLayout(1, 3));

		mPlayButton = new JButton("Play");
		mPlayButton.addActionListener(e -> runOrPause());
		JButton bBack = new JButton("< Back");
		bBack.addActionListener(e -> moveBack());
		JButton bForward = new JButton("Forward >");
		bForward.addActionListener(e -> {
			moveForward();
			mPlaying = true;
			runOrPause();
		});

		ctrl.add(bBack);
		ctrl.add(mPlayButton);
		ctrl.add(bForward);
		return ctrl;
	}

	public static void main(String[] args) {
		PatternStore ps;
		try {
			ps = new PatternStore("http://www.cl.cam.ac.uk/teaching/1617/OOProg/ticks/life.txt");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		GUILife gui = new GUILife(ps);
		gui.setVisible(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<Pattern> list = (JList<Pattern>) e.getSource();
		Pattern p = list.getSelectedValue();

		try {
			if (p.getHeight() * p.getWidth() <= 64) {
				mWorld = new PackedWorld(p);
			} else {
				mWorld = new ArrayWorld(p);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("This should never happen");
		}

		mCachedWorlds.clear();
		mCachedWorlds.add(mWorld);
		mGamePanel.display(mWorld);
		mPlaying = true;
		runOrPause();
	}

	private void runOrPause() {
		if (mPlaying) {
			if (mTimer == null) {
				mTimer = new Timer(true);
			}
			mTimer.cancel();
			mPlaying = false;
			mPlayButton.setText("Play");
		} else {
			mPlaying = true;
			mPlayButton.setText("Stop");
			mTimer = new Timer(true);
			mTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					moveForward();
				}
			}, 0, 500);
		}
	}

}