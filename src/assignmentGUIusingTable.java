import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class assignmentGUIusingTable {
	private JFrame frame;
	private static JTextField player1, player2;
	private JPanel topPanel, CenterPanel, BottomPanel, topPanel_sub1;
	private static JLabel playerName1, playerName2, Score1, Score2;
	private static JLabel ScoreLabel1, ScoreLabel2;
	private JLabel info;
	private static JTable table;
	private Assignment assignment;
	private Seed currentplayer=Seed.BLACK;
	private ImageIcon picture;
	static int BlackScore = 0;
	static int WhiteScore = 0;

	public assignmentGUIusingTable(){
		
		frame=new JFrame("Assignment 1");
		frame.setResizable(false);
		Image ico = Toolkit.getDefaultToolkit().getImage("ico.jpg");
		//Image img = Toolkit.getDefaultToolkit().getImage("boot1.jpg");
		frame.getContentPane().setBackground(Color.YELLOW);
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
		JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.LIGHT_GRAY);
        JMenu gameMenu = new JMenu("Menu");
        gameMenu.setBackground(Color.LIGHT_GRAY);
        menuBar.add(gameMenu);

        JMenuItem newGameItem = new JMenuItem("Start new game");
        gameMenu.add(newGameItem);
		// Start of CenterPanel
		CenterPanel = new JPanel();
		CenterPanel.setLayout(new BorderLayout());
		table= new JTable(8, 8) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredSize(new Dimension(0,0));
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		drawTable(8,8,Seed.EMPTY);

		int height = table.getRowHeight();
		table.setRowHeight(height+10);
		table.setIntercellSpacing(new Dimension(10,1));
		
		CenterPanel.setBorder(BorderFactory.createTitledBorder("Display"));
		CenterPanel.add(table,BorderLayout.CENTER);
		// -- End of CenterPanel
		// -- Start of Bottom Panel

		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(CenterPanel,BorderLayout.CENTER);
		//frame.add(BottomPanel,BorderLayout.SOUTH);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
		newGameItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BlackScore=0;
				WhiteScore=0;
				
				start();	
				
			}
		});
		table.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Point p = arg0.getPoint();
	            int row = table.rowAtPoint(p); 
	            int column = table.columnAtPoint(p);
	            int realrow =table.convertRowIndexToModel(row);
	            int realcol =table.convertColumnIndexToModel(column);
				System.out.println(table.convertRowIndexToModel(row-1));
				System.out.println(table.convertColumnIndexToModel(column-1));
				info.setText(currentplayer.toString());
				/*
				if (table.getValueAt(realrow,realcol)==Seed.EMPTY) {
                    if (doFlip(realrow,realcol, false)) {
                        doFlip(realrow,realcol, true);
                        placeDisk(realrow,realcol);
                        nextTurn(); 
                        info.setText(currentplayer.toString());
                    } else if (!doFlip(realrow,realcol, false)) {
                        System.out.println("Invalid Move. Please Try Again !");
                    }

                }
				*/
				if (table.getValueAt(realrow,realcol)==Seed.EMPTY){
					if(check(realrow,realcol,currentplayer)){
						placeDisk(realrow,realcol);
						setPicture(currentplayer,realrow,realcol);
						nextTurn();
						info.setText(currentplayer.toString());
						
					}
				}
				/*	
				if(check(table.convertRowIndexToModel(row-1), table.convertColumnIndexToModel(column-1), Seed.BLACK)){
					System.out.println("hahah");
				}else{
					System.out.println("false");
				}
				*/
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
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


	}
	public void start() {
		drawTable(8, 8, Seed.EMPTY);
		table.setValueAt(Seed.WHITE, 1, 1);
		table.setValueAt(Seed.WHITE, 7, 7);
		table.setValueAt(Seed.BLACK, 1, 7);
		table.setValueAt(Seed.BLACK, 7, 1);
		ScoreLabel1.setText(player1.getText() + "'s score  : ");
		ScoreLabel2.setText(player2.getText() + "'s score  : ");
		scoreCount();
	}
	public void drawTable(int row, int col, Seed x) {
		int counter_row = 0;
		int counter_col = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0 && j < col - 1) {
					table.setValueAt(counter_row, i, j + 1);
					counter_row++;
				} else if (i == 0 && j < col) {
					table.setValueAt(counter_row - 1, i, j);
				} else if (j == 0 && i < row) {
					table.setValueAt(counter_col, i, j);
					counter_col++;
				} else {
					table.setValueAt(x, i, j);
				}
			}
		}

	}
	
	
	private static void scoreCount() {
		for (int i = 0; i < table.getRowCount(); i++) {
			for (int j = 0; j < table.getColumnCount(); j++) {
				if (table.getValueAt(i, j) == Seed.WHITE) {
					WhiteScore++;
				}
				if (table.getValueAt(i, j) == Seed.BLACK) {
					BlackScore++;
				}
			}
		}
		Score1.setText(Integer.toString(BlackScore));
		Score2.setText(Integer.toString(WhiteScore));

	}
	public static boolean check(int row, int col, Seed player) {
		boolean result = false;
		if (table.getValueAt(row, col + 1) == player
				|| table.getValueAt(row, col - 1) == player
				|| table.getValueAt(row - 1, col) == player
				|| table.getValueAt(row + 1, col) == player
				|| table.getValueAt(row - 1, col + 1) == player
				|| table.getValueAt(row + 1, col - 1) == player
				|| table.getValueAt(row - 1, col - 1) == player
				|| table.getValueAt(row + 1, col + 1) == player) {
			result = true;

		}
		return result;
	}
	public void nextTurn() {
        this.currentplayer = (this.currentplayer == Seed.BLACK ? Seed.WHITE : Seed.BLACK);
    }
	public boolean checkGame(int row, int col) {
		boolean result = false;
		if (row >= table.getRowCount() || row < 0
				|| col >= table.getColumnCount() || col < 0) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (table.getValueAt(i, j) != Seed.EMPTY) {
					result = true;
				}
			}
		}

		return result;

	}
	public boolean doFlip(int currentRow, int currentCol, boolean flippable) {
        boolean isValid = false;
        for (int chkRow = -1; chkRow < 2; chkRow++) {
            for (int chkCol = -1; chkCol < 2; chkCol++) {
                if (chkRow == 0 && chkCol == 0) {               
                    continue;
                }

                int xRow = currentRow + chkRow;
                int xCol = currentCol + chkCol;

                if (xRow >= 0 && xRow <= 7 && xCol >= 0 && xCol <= 7) {

                    
                    if ((table.getValueAt(xRow, xCol)) == (currentplayer == Seed.BLACK ? Seed.WHITE : Seed.BLACK)) {   
                        for (int range = 0; range < 8; range++) {

                            int nRow = currentRow + range * chkRow;
                            int nCol = currentCol + range * chkCol;
                            if (nRow < 0 || nRow > 7 || nCol < 0 || nCol > 7) {
                                continue;
                            }

                            

                            if (table.getValueAt(xRow, xCol) ==currentplayer) {
                                if (flippable) {
                                    for (int flipDistance = 1; flipDistance < range; flipDistance++) {  
                                        int finalRow = currentRow + flipDistance * chkRow;
                                        int finalCol = currentCol + flipDistance * chkCol;

                                        table.setValueAt(currentplayer,finalRow, finalCol);  
                                    }
                                }
                                isValid = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return isValid;
    }
	
	
	public void placeDisk(int row, int col) {

        if (currentplayer == Seed.BLACK) {
            table.setValueAt(Seed.BLACK,row, col);
        } else if (currentplayer== Seed.WHITE) {
        	table.setValueAt(Seed.WHITE,row, col);
        }
    }
	public void setPicture(Seed player, int row, int col) {

        if (player==Seed.BLACK) {
            picture = createImageIcon("Picture1.png");
            JLabel picLabel = new JLabel((picture));
            table.setValueAt("",row, col);
            table.setValueAt(picLabel, row, col);

        } else if (player==Seed.WHITE) {
            picture = createImageIcon("Picture2.png");
            JLabel picLabel = new JLabel((picture));
            table.setValueAt("",row, col);
            table.setValueAt(picLabel, row, col);

        } else {
        	table.setValueAt("",row, col);
           table.setValueAt("", row, col);
        }
    }
	protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find the file: " + path);
            return null;
        }
    }
}
