import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class Main extends JFrame{

	private JFrame frmFlappyBird;
	private JPanel panelPlayer;
	private JPanel GamePanel;
	private JPanel panelBarrelBottom;
	private JPanel panelBarrelTop;
	private JLabel lblScore;
	private int Score = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmFlappyBird.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	Thread tgravity = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			Gravity();
		}
	});
	
	Thread tdetection = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			Detect();
			Score();
			
		}
	});
	
	Thread tmapgenerator = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			Mapgenerator();
		}
	});
	
	private void initialize() {
		
		frmFlappyBird = new JFrame();
		frmFlappyBird.setBounds(100, 100, 455, 478);
		frmFlappyBird.setResizable(false);
		frmFlappyBird.setFocusable(false);
		frmFlappyBird.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFlappyBird.setTitle("Flappy Bird");
		frmFlappyBird.getContentPane().setLayout(null);
		
		JPanel GamePanel = new JPanel();
		GamePanel.setBounds(0, 0, 450, 450);
		frmFlappyBird.getContentPane().add(GamePanel);
		GamePanel.setLayout(null);
		GamePanel.setFocusable(false);
		GamePanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				Playerjump();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				
			}
		});
		
		JPanel panelPlayer = new JPanel();
		panelPlayer.setBorder(new LineBorder(Color.BLACK, 2));
		panelPlayer.setBounds(200, 200, 50, 50);
		panelPlayer.setBackground(Color.RED);
		panelPlayer.setFocusable(false);
		GamePanel.add(panelPlayer);
		panelPlayer.setLayout(null);
		
		JPanel panelBarrelBottom = new JPanel();
		panelBarrelBottom.setBounds(400, 0, 50, 1);
		panelBarrelBottom.setBackground(Color.BLUE);
		panelBarrelBottom.setBorder(new LineBorder(Color.BLACK, 4));
		GamePanel.add(panelBarrelBottom);
		panelBarrelBottom.setLayout(null);
		
		JPanel panelBarrelTop = new JPanel();
		panelBarrelTop.setBounds(400, 0, 50, 1);
		panelBarrelTop.setBackground(Color.BLUE);
		panelBarrelTop.setBorder(new LineBorder(Color.BLACK, 4));
		GamePanel.add(panelBarrelTop);
		panelBarrelTop.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 11, 10, 10);
		panelPlayer.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(30, 11, 10, 10);
		panelPlayer.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(10, 32, 30, 10);
		panelPlayer.add(panel_2);
		
		JLabel lblScore = new JLabel("Score: ");
		lblScore.setBounds(1, 1, 79, 14);
		GamePanel.add(lblScore);
		
		this.panelPlayer = panelPlayer;
		this.panelBarrelBottom = panelBarrelBottom;
		this.panelBarrelTop = panelBarrelTop;
		this.GamePanel = GamePanel;
		this.lblScore = lblScore;
		
		tmapgenerator.start();
		tgravity.start();
		tdetection.start();
		
		
	}
	
	private void Playerjump() {
		
		if (panelPlayer.getY()-40 >= 0) {
			
			panelPlayer.setBounds(200,panelPlayer.getY()-40, 50, 50);
			
		} else {}
		
		
	}
	
	private void Gravity() {
		
		try {
			
			
			while(true) {
				
				if (panelPlayer.getY()<401) {
					
					Thread.sleep(10);
//					System.out.println(playerPanel.getY());
					Scoreposition();
					panelPlayer.setBounds(200, panelPlayer.getY()+1, 50, 50);
					
				} else {
					
					panelPlayer.setBounds(200, 400, 50, 50);
					
				}
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	private void Detect() {
		
		try {
			
			while(true) {
				
				while (
						panelPlayer.getX() > panelBarrelBottom.getX() && panelPlayer.getX() <= panelBarrelBottom.getX() + panelBarrelBottom.getWidth() ||
						panelPlayer.getX() + panelPlayer.getWidth() >= panelBarrelBottom.getX() && panelPlayer.getX() + panelPlayer.getWidth() <= panelBarrelBottom.getX() + panelBarrelBottom.getWidth() 
						) {
					
					if (panelPlayer.getY() >= panelBarrelBottom.getY() || panelPlayer.getY() <= panelBarrelTop.getY() + panelBarrelTop.getHeight() ||
						panelPlayer.getY() + panelPlayer.getWidth() >= panelBarrelBottom.getY() || panelPlayer.getY() + panelPlayer.getWidth() <= panelBarrelTop.getY() + panelBarrelTop.getHeight()
							) {
						
						panelBarrelBottom.setBackground(Color.RED);
						panelBarrelTop.setBackground(Color.RED);
						
					} else {
						
						
					}
					
					
				}
				
				panelBarrelBottom.setBackground(Color.BLUE);
				panelBarrelTop.setBackground(Color.BLUE);
				
			}
			
		} catch (Exception e) {

		}

	}	
	
	private void Colorchange() {
		
		double f = (double) Math.random()*100;
		
		if (f < 0.1) {
			
			panelPlayer.setBackground(Color.BLUE);
			
		} else if (f > 0.1 && f < 0.2) {
			
			panelPlayer.setBackground(Color.CYAN);
			
		} else if (f > 0.2 && f < 0.3) {
			
			panelPlayer.setBackground(Color.GREEN);
			
		} else if (f > 0.3 && f < 0.4) {
			
			panelPlayer.setBackground(Color.MAGENTA);
			
		} else if (f > 0.4 && f < 0.5) {
			
			panelPlayer.setBackground(Color.ORANGE);
			
		} else if (f > 0.5 && f < 0.6) {
			
			panelPlayer.setBackground(Color.PINK);
			
		} else if (f > 0.6 && f < 0.7) {
			
			panelPlayer.setBackground(Color.RED);
			
		} else if (f > 0.7 && f < 0.8) {
			
			panelPlayer.setBackground(Color.WHITE);
			
		} else if (f > 0.8 && f < 0.9) {
			
			panelPlayer.setBackground(Color.YELLOW);
			
		} else if (f > 0.9 && f <= 1) {
			
			panelPlayer.setBackground(Color.LIGHT_GRAY);
			
		}
	}
	
	private void Mapgenerator() {
		
			Random random = new Random();
			int f = random.nextInt(GamePanel.getHeight() - 100);
			int y = GamePanel.getHeight();
		
		try {
			
			while(true) {
				
				if (panelBarrelBottom.getX() > 0) {

					panelBarrelBottom.setBounds(panelBarrelBottom.getX()-1, y - f, panelBarrelBottom.getWidth(), f);
					panelBarrelTop.setBounds(panelBarrelBottom.getX(), 0, panelBarrelBottom.getWidth(), panelBarrelBottom.getY() - 120);
					Thread.sleep(7);
					
				} else if (panelBarrelBottom.getX() == 0){
					
					f = random.nextInt(GamePanel.getHeight() - 100);
					panelBarrelBottom.setBounds(400 + panelBarrelBottom.getWidth(), y - f, panelBarrelBottom.getWidth(), f);
					
				} else {}
				
			}
			
		} catch (Exception e) {
			
		}
	}
	
	private void Scoreposition() {
		
		lblScore.setBounds(panelPlayer.getX(), panelPlayer.getY() - lblScore.getHeight() - 5, lblScore.getWidth(), lblScore.getHeight());
		
	}
	
	private void Score() {
		
		if (panelPlayer.getX() == panelBarrelBottom.getX()) {
		
			Score = Score+1;
			lblScore.setText("Score: " +Score);
			
		} else {
			
		}
		
	}
}
