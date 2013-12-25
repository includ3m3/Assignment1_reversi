import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class Assignment1GUI {
	private static ImageIcon picture;
	private JFrame frame;
	private static JTextField player1, player2;
	private JPanel topPanel, CenterPanel, topPanel_sub1;
	private static JLabel playerName1, playerName2, Score1, Score2;
	private static JLabel ScoreLabel1, ScoreLabel2;
	private static JLabel info;
    private static Assignment b = new Assignment();
    private JPanel slotsPanel = new JPanel(new GridLayout(8, 8));
    private static JPanel[][] panelBoard = new JPanel[8][8];
    
    private static int wCount = 0;
    private static int bCount = 0;
	public Assignment1GUI(){
		frame=new JFrame("Assignment 1");
		frame.setResizable(false);
		Image ico = Toolkit.getDefaultToolkit().getImage("ico.jpg");
		//Image img = Toolkit.getDefaultToolkit().getImage("boot1.jpg");
		//frame.getContentPane().setBackground(Color.YELLOW);
		frame.setIconImage(ico);

		frame.setBounds(200, 200, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
	


		//--start of topPanel
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel_sub1 = new JPanel();
		
		JPanel sub1_sub1 = new JPanel();
		JPanel sub1_sub2 = new JPanel();
		JPanel sub1_sub3 = new JPanel();
		JPanel sub1_sub4 = new JPanel();

		topPanel_sub1.setLayout(new GridLayout(2,2));
		playerName1 = new JLabel("Player 1 (Black)");
		player1 = new JTextField(10);
		player1.setText("One");
		sub1_sub1.add(playerName1);
		sub1_sub1.add(player1);

		ScoreLabel1 = new JLabel("Score  : ");
		Score1 = new JLabel("0");
		sub1_sub2.add(ScoreLabel1);
		sub1_sub2.add(Score1);

		playerName2 = new JLabel("Player 2 (White)");
		player2 = new JTextField(10);
		player2.setText("Two");
		sub1_sub3.add(playerName2);
		sub1_sub3.add(player2);

		ScoreLabel2 = new JLabel("Score  : ");
		Score2 = new JLabel("0");
		sub1_sub4.add(ScoreLabel2);
		sub1_sub4.add(Score2);

		topPanel_sub1.add(sub1_sub1);
		topPanel_sub1.add(sub1_sub2);
		topPanel_sub1.add(sub1_sub3);
		topPanel_sub1.add(sub1_sub4);
		topPanel_sub1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//end of topPanel
		info = new JLabel("info panel");
		info.setPreferredSize(new Dimension(2, 50));
		info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		info.setFont(new Font("Verdana", Font.BOLD, 16));
		info.setForeground(Color.RED);
		info.setVerticalAlignment(SwingConstants.CENTER);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(info,BorderLayout.SOUTH);

        topPanel.setBorder(BorderFactory.createTitledBorder("Info"));
		topPanel_sub1.setPreferredSize(new Dimension(500,100));
		topPanel.add(topPanel_sub1);
		
		topPanel.setPreferredSize(new Dimension(2,170));
		// End of topPanel
		// Menu
		JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        JMenu gameMenu = new JMenu("Menu");
        gameMenu.setBackground(Color.LIGHT_GRAY);
        menuBar.add(gameMenu);
        
        JMenuItem newGameItem = new JMenuItem("Start new game");
        JMenuItem nextPlayer = new JMenuItem("Next Player");
        JMenuItem exit = new JMenuItem("Exit");
        gameMenu.add(newGameItem);
        gameMenu.add(nextPlayer);
        gameMenu.add(exit);
        newGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				start();	
				
			}
		});
        nextPlayer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				b.nextTurn();
				updateStatus();
			}
        	
        });
        exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
        	
        });

        // End of Menu
        
        frame.setJMenuBar(menuBar);
        frame.add(topPanel, BorderLayout.NORTH);
		frame.add(CreateDisplay());	
		
		frame.setVisible(true);
		
	}


	private JPanel CreateDisplay() {
		int counter_row = 0;
		int counter_col = 0;
        
		CenterPanel = new JPanel();
		TitledBorder border = new TitledBorder("Display");
		CenterPanel.setBorder(border);
		for(int i=0;i<8;i++){
			for (int j=0;j<8;j++){
				Border black = BorderFactory.createLineBorder(Color.black);
                final JPanel slots = new JPanel();
                slots.setBorder(black);
                slots.setBackground(new Color(0, 100, 0));
                final int row = i;
                final int col = j;
                slots.addMouseListener(new MouseListener(){
					@Override
					public void mouseClicked(MouseEvent e) {
						b.move(row, col);
						
                        update();
                        updateStatus();
                       
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						slots.setBackground(new Color(34, 139, 34));
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						slots.setBackground(new Color(0, 100, 0));
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
                	
                });
                slotsPanel.add(slots);
                panelBoard[i][j] = slots;
				if(i==0 && j<7 && j>0){
					JLabel label = new JLabel(Integer.toString(counter_row));
					panelBoard[i][j].add(label);
					counter_row++;
				}else if(i==0 && j==7){
					JLabel label = new JLabel(Integer.toString(counter_row));
					panelBoard[i][j].add(label);
				}else if (j == 0 && i < 8 && i>0) {
					JLabel label = new JLabel(Integer.toString(counter_col));
					panelBoard[i][j].add(label);
					counter_col++;
				}
				
			}
		}
		
        slotsPanel.setPreferredSize(new Dimension(700, 250));
        CenterPanel.setBackground(Color.LIGHT_GRAY);           
        CenterPanel.add(slotsPanel, BorderLayout.CENTER);
        
        return CenterPanel;
    }
	public void updateStatus() {
        wCount = 0;
        bCount = 0;
        if (b.getCurrentPlayer() == Seed.BLACK) {
            info.setText("Black Turn");
        } else {
            info.setText("White Turn");
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.getBoard()[i][j] == Seed.WHITE) {
                    wCount++;
                }
                if (b.getBoard()[i][j] == Seed.BLACK) {
                    bCount++;
                }
            }
        }
        Score1.setText("" + bCount);
        Score2.setText("" + wCount);
        ScoreLabel1.setText(player1.getText()+"'s Score : ");
        ScoreLabel2.setText(player2.getText()+"'s Score : ");
        chkWin();

        
    }
	
	public void setPicture(Seed player, int row, int col) {

        if (player==Seed.BLACK) {
            picture = createImageIcon("Picture1.png");
            JLabel picLabel = new JLabel((picture));
            picLabel.setPreferredSize(new Dimension(30,20));
            panelBoard[row][col].removeAll();
            panelBoard[row][col].add(picLabel);

        } else if (player==Seed.WHITE) {
            picture = createImageIcon("Picture2.png");
            JLabel picLabel = new JLabel((picture));
            picLabel.setPreferredSize(new Dimension(30,20));
            panelBoard[row][col].removeAll();
            panelBoard[row][col].add(picLabel);

        }else if(player == Seed.LEGAL){
        	picture = createImageIcon("legalSlot.png");
            JLabel picLabel = new JLabel((picture));
            picLabel.setPreferredSize(new Dimension(30,20));
            panelBoard[row][col].removeAll();
            panelBoard[row][col].add(picLabel);
        }
        else {
            panelBoard[row][col].removeAll();
            panelBoard[row][col].setBackground(new Color(0, 100, 0));
        }
    }
	private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find the file: " + path);
            return null;
        }
    }
	public void start() {
        b = new Assignment();
        update();
        slotsPanel.updateUI();      
        updateStatus();

    }
	
	public void update() {
		int counter_row = 0;
		int counter_col = 0;
        for (int i = 0; i < b.getBoard().length; i++) {
            for (int j = 0; j < b.getBoard()[i].length; j++) {
                panelBoard[i][j].removeAll();
                if (b.getBoard()[i][j] == Seed.BLACK) {
                    setPicture(Seed.BLACK, i, j);
                }
                if (b.getBoard()[i][j] == Seed.WHITE) {
                    setPicture(Seed.WHITE, i, j);
                }
                if (b.getBoard()[i][j] == Seed.EMPTY) {
                    setPicture(Seed.EMPTY, i, j);
                }
                if (b.getBoard()[i][j] == Seed.LEGAL) {
                    setPicture(Seed.LEGAL, i, j);
                }
                if(i==0 && j<7 && j>0){
					JLabel label = new JLabel(Integer.toString(counter_row));
					panelBoard[i][j].add(label);
					counter_row++;
				}else if(i==0 && j==7){
					JLabel label = new JLabel(Integer.toString(counter_row));
					panelBoard[i][j].add(label);
				}else if (j == 0 && i < 8 && i>0) {
					JLabel label = new JLabel(Integer.toString(counter_col));
					panelBoard[i][j].add(label);
					counter_col++;
				}
                
            }
        }
        slotsPanel.updateUI();
    }
	public static void chkWin(){
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (wCount + bCount == 49) {
                	if (wCount > bCount) {
                        info.setText(" Player2 (White) has won the game");
                    } else if (wCount < bCount) {
                    	info.setText("Player1 (Black) has won the game");
                    } else {
                    	info.setText("It is a draw...");
                    }
                }
            }
        }
    }
	
	public static boolean withinBounds(int Y, int X) {
        if (Y < 1 || Y > 7 || X < 1 || X > 7) {
            return false;
        }
        return true;
    }
	
	
}
/*
 * I declare that the above codes were truly written by me and I didn't copy from other sources. I also did not let others copy my codes
 * 
 *  Student ID : 101211
 *  Class :
 *  Name :Kaung Htet Aung
 */
	
