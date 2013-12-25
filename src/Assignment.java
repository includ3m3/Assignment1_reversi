import javax.swing.JOptionPane;


public class Assignment {
	private static Seed[][] gameBoard = new Seed[8][8];
    private static Seed player1 = Seed.BLACK;
    private static Seed player2 = Seed.WHITE;
    private static Seed currentPlayer = player1;
    private static int delrow=0;
	private static int delcol=0;

    public Assignment() {
        NewGame();
    }

    private void NewGame() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	gameBoard[i][j] = Seed.EMPTY;
            }
        }
        placeDisk(1, 7);
        nextTurn();
        placeDisk(1, 1);
        nextTurn();
        placeDisk(7, 1);
        nextTurn();
        placeDisk(7, 7);
        nextTurn();
        
    }

    public static void placeDisk(int row, int col) {

        if (currentPlayer == Seed.BLACK) {
        	gameBoard[row][col] = Seed.BLACK;
        } else if (currentPlayer == Seed.WHITE) {
        	gameBoard[row][col] = Seed.WHITE;
        }
    }

    public Seed[][] getBoard() {
        return gameBoard;
    }
    public static Seed setBoard(int i, int j, Seed x){
    	return gameBoard[i][j] = x;
    }
    

    public static void nextTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Seed getCurrentPlayer() {
        return currentPlayer;
    }

    public static boolean checksurroundsame(int row,int col){
    	boolean result = false;
    	for (int chkRow = -1; chkRow < 2; chkRow++) {
            for (int chkCol = -1; chkCol < 2; chkCol++) {
                if (chkRow == 0 && chkCol == 0) {               //skip origin (current position)
                    continue;
                }
                int xRow = row + chkRow;
                int xCol = col + chkCol;
                
                if (xRow >= 0 && xRow <= 7 && xCol >= 0 && xCol <= 7) {
                	if(gameBoard[xRow][xCol] == currentPlayer){
                		result = true;
                	}
                }
            }
    	}
    	return result;
    }
    public static void checksurrounddifferent(int row,int col){
    	
    	for (int chkRow = -1; chkRow < 2; chkRow++) {
            for (int chkCol = -1; chkCol < 2; chkCol++) {
                if (chkRow == 0 && chkCol == 0) {               //skip origin (current position)
                    continue;
                }
                int xRow = row + chkRow;
                int xCol = col + chkCol;
                
                if (xRow > 0 && xRow <= 7 && xCol > 0 && xCol <= 7) {
                	if(gameBoard[xRow][xCol] != currentPlayer && gameBoard[xRow][xCol]!=Seed.EMPTY){
                		gameBoard[xRow][xCol]=currentPlayer;
                	}
                }
            }
    	}
    }
    public static void showlegal(int srcRow,int srcCol){
    	for(int i=-2;i<=2;i++){
    		for(int j=-2;j<=2;j++){
    			if (i == 0 && j == 0) {               //skip origin (current position)
                    continue;
                }
    			if((i==2 && j>=-2) || (i== -2 && j>=-2) || (j==2 && i>=-2) || (j==-2 && i>=-2) ){
    				int xRow = srcRow + i;
    				int xCol = srcCol + j;
    				//System.out.println(xRow+","+xCol);
    				if(withinBounds(xRow,xCol)&& gameBoard[xRow][xCol]==Seed.EMPTY){
    					gameBoard[xRow][xCol] = Seed.LEGAL;
    				}
    			}
    		}
    	}
    }
    public static void move(int row, int col){
    	if(gameBoard[row][col]==Seed.EMPTY){
    		deleteLegal();
    		if(checksurroundsame(row, col)){
				if(withinBounds(row,col)){
					placeDisk(row, col);
					checksurrounddifferent(row, col);
					nextTurn();
				}
			}else{
				JOptionPane.showMessageDialog(null, "NO_NEARBY_FRIENDLY_PIECE", "Warning",JOptionPane.WARNING_MESSAGE);
			}
    	}else if (gameBoard[row][col]==Seed.LEGAL){
    		//System.out.println(delrow+","+delcol);
    		gameBoard[delrow][delcol] = Seed.EMPTY;
    		placeDisk(row,col);
    		deleteLegal();
    		checksurrounddifferent(row, col);
    		nextTurn();        	
    	}
    	else if (gameBoard[row][col]!=Seed.EMPTY){
    		deleteLegal();
    		delrow = row;
    		delcol = col;
    		if(gameBoard[row][col] == currentPlayer){
        		showlegal(row,col);
        		
        	}else{
        		JOptionPane.showMessageDialog(null, "NOT_CURRENT_PLAYER_TURN", "Warning",JOptionPane.WARNING_MESSAGE);
        	}
        	
    	}
    }
    public static void deleteLegal(){
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[i][j] == Seed.LEGAL) {
                    setBoard(i, j, Seed.EMPTY);
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
