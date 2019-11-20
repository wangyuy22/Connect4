/* Name: Yuyang Wang
 * Pennkey: wangyuy
 * Recitation: 211
 * 
 * Execution: java Board
 * 
 * Description: hw09 - The board of a Connect 4 game
 */

public class Board {
    
    //Fields
    private Pieces[][] holes = new Pieces[6][7];
    private int player;
    private String playerColor;
    
    /*
     * Description: Constructor to create a Board that starts on player 1 whose
     *              designate color is yellow..
     * Input: None.
     * Output: None.
     */
    public Board() {
        player = 1;
        playerColor = "yellow";  
    }
    
    /*
     * Description: Drawing the top background of the game
     * Input: None.
     * Output: None, but with side effect of drawing.
     */
    public void drawTopBackground() {
        //Create Background for top
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.filledRectangle(0.5, 0.90, 0.5, 0.10);
    }
    
    /*
     * Description: Drawing the bottom background or the blue board of the game
     * Input: None.
     * Output: None, but with side effect of drawing.
     */
    public void drawBottomBackground() {
        PennDraw.setPenColor(PennDraw.BLUE);
        PennDraw.filledRectangle(0.5, 0.4, 0.5, 0.4);
    }
    
    /*
     * Description: Draws all of the "pieces" or chips that are in the 2d pieces
     *              array onto the board. If there are no pieces in a certain 
     *              spot in the array, a white piece is drawn.
     * Input: None.
     * Output: None, but with side effect of drawing.
     */
    public void drawPieces() {
        
        //Outer and inner loop to loop through 2d array
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                
                //If there is no piece, a white piece is drawn onto the board
                if (holes[row][col] == null || 
                    holes[row][col].getPieceColor().equals("white")) {
                    holes[row][col] = new Pieces("white");
                    PennDraw.setPenColor(PennDraw.WHITE);
                    
                    //If the color of the piece at that index is red
                } else if (holes[row][col].getPieceColor().equals("red")) {
                    PennDraw.setPenColor(PennDraw.RED);
                    
                    //If the color of the piece at that index is yellow
                } else if (holes[row][col].getPieceColor().equals("yellow")) {
                    PennDraw.setPenColor(PennDraw.YELLOW);
                }
                
                /*
                 * Draws the piece as a circle in the corresponding spot to its
                 * row and column
                 */
                PennDraw.filledCircle(0.08 + 0.14 * col, 0.07 + 0.13 * row,
                                      0.06);
            }
        }
    }
    
    /*
     * Description: Checks if either player has 4 pieces in a row, and hence,
     *              if the game is over.
     * Input: None.
     * Output: Boolean true or false as to whether the game is over.
     */
    public boolean isGameOver() {
        
        //Counter to see how many empty spaces there are in the board
        int emptySpaces = 0;
        
        //Outer and inner loop to loop through 2d array
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                
                //Checking for empty spaces in the board, as white is empty
                if (holes[row][col].getPieceColor().equals("white")) {
                    emptySpaces++;
                    
                    //If the space is occupied
                } else if (holes[row][col].getPieceColor().equals("white") == 
                           false) {
                    
                    //Checking for 4 in a row vertically
                    if (row < 3) {
                        if (holes[row][col].getPieceColor() == 
                            holes[row + 1][col].getPieceColor() && 
                            holes[row + 2][col].getPieceColor() == 
                            holes[row + 3][col].getPieceColor() &&
                            holes[row][col].getPieceColor() == 
                            holes[row + 2][col].getPieceColor()) {
                            return true;
                        }
                    }
                    
                    //Checking for 4 in a row horizontally
                    if (col < 4) {
                        if (holes[row][col].getPieceColor() == 
                            holes[row][col + 1].getPieceColor() && 
                            holes[row][col + 2].getPieceColor() == 
                            holes[row][col + 3].getPieceColor() &&
                            holes[row][col].getPieceColor() == 
                            holes[row][col + 2].getPieceColor()) {
                            return true;
                        }
                    }
                    
                    /*
                     * Checking for 4 in a row diagonally starting from the 
                     * lower left to the upper right
                     */
                    if (col < 4 && row < 3) {
                        
                        if (holes[row][col].getPieceColor() == 
                            holes[row + 1][col + 1].getPieceColor() && 
                            holes[row + 2][col + 2].getPieceColor() == 
                            holes[row + 3][col + 3].getPieceColor() &&
                            holes[row][col].getPieceColor() == 
                            holes[row + 2][col + 2].getPieceColor()) {
                            return true;
                        }
                    }
                    
                    /*
                     * Checking for 4 in a row diagonally starting from the 
                     * lower right to the upper left
                     */
                    if (col > 2 && row < 3) {
                        
                        if (holes[row][col].getPieceColor() == 
                            holes[row + 1][col - 1].getPieceColor() && 
                            holes[row + 2][col - 2].getPieceColor() == 
                            holes[row + 3][col - 3].getPieceColor() &&
                            holes[row][col].getPieceColor() == 
                            holes[row + 2][col - 2].getPieceColor()) {
                            return true;
                        }
                    }    
                }
            }   
        }
        
        //If no empty spaces, game is over by a draw
        if (emptySpaces == 0) {
            player = 3;
            return true;
        }
        
        /*I
         * f none of the players have won, or that a draw has occured, the game
         * is not over
         */
        return false;
    }
    
    /*
     * Description: Introduces the current player at the top of the game
     * Input: Integer that is the player number.
     * Output: None, but with the side effect of drawing.
     */
    public void introducePlayer(int playerNumber) {
        PennDraw.setFontBold();
        PennDraw.setFontSize(35);
        if (playerNumber == 1) {
            PennDraw.setPenColor(PennDraw.YELLOW);
            PennDraw.text(0.5, 0.93, "It is Yellow's Turn");
            
        } else if (playerNumber == 2) {
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.text(0.5, 0.93, "It is Red's Turn");
        }
    }
    
    /*
     * Description: Introduces the CPU at the top of the game
     * Input: Integer that is the player number.
     * Output: None, but with the side effect of drawing.
     */
    public void introduceComputer(int playerNumber) {
        PennDraw.setFontBold();
        PennDraw.setFontSize(35);
        if (playerNumber == 1) {
            PennDraw.setPenColor(PennDraw.YELLOW);
        } else if (playerNumber == 2) {
            PennDraw.setPenColor(PennDraw.RED);
        }
        PennDraw.text(0.5, 0.85, "CPU");  
    }
    
    /*
     * Description: Changes the player that is currently playing.
     * Input: None.
     * Output: None, but with the side effect of changing the player, changing
     *         the player color, and introducing the player through drawing.
     */
    public void changePlayer() {
        if (player == 1) {
            introducePlayer(2);
            playerColor = "red";
            player = 2;
        } else if (player == 2) {
            introducePlayer(1);
            playerColor = "yellow";
            player = 1;
        }
    }
    
    /*
     * Description: Getter method that gets the current player color.
     * Input: None.
     * Output: String that is the current player color.
     */
    public String getPlayerColor() {
        return playerColor;
    }
    
    /*
     * Description: Getter method that gets the current player number.
     * Input: None.
     * Output: Integer that is the current player number.
     */
    public int getPlayerNumber() {
        return player;
    }
    
    /*
     * Description: Adds a piece to the holes 2d array, and checks if a piece 
     *              is actually added.
     * Input: Double that is the x position of where the mouse clicks and the
     *        piece's color.
     * Output: Boolean of whether a piece has actually been added, as if the 
     *         mouse clicks directly in between two columns, no piece will be 
     *         added.
     */
    public boolean addPiece(double xPosition, String color) {
        
        //Temporary column number that doesn't exist
        int xColumn = -1;
        
        //Loop through every column
        for (int i = 0; i < 7; i++) {
            
            //Checking which column the input x position of the piece fits in
            if (0.02 + 0.14 * i <= xPosition && xPosition <= 0.14 + 0.14 * i) {
                
                //Checking if the column is full, where pieces can't be added
                if (colIsFull(i)) {
                    return false;
                    
                    //Else, the piece is added to that column
                } else {
                    xColumn = i;
                }
            }
        }
        
        //If a column has been chosen
        if (xColumn != -1) {
            
            /*
             * Looking for the first white space in the given color, where the
             * piece will be added
             */
            for (int row = 0; row < 6; row++) {
                if (holes[row][xColumn].getPieceColor().equals("white")) {
                    holes[row][xColumn] = new Pieces(color);
                    break;
                }
            }
            return true;
            
            /*
             * If column has not been set to an actual column, a piece has not been
             * added
             */
        } else {
            return false;
        }
    }  
    
    /*
     * Description: Checks if any given column is full.
     * Input: Integer corresponding to the column.
     * Output: Boolean of whether the column is full.
     */
    public boolean colIsFull(int column) {
        
        /*
         * Checks if the 6th row in each column is still occupied by a white 
         * piece, as if not, then the column is full
         */
        if (holes[5][column].getPieceColor().equals("white") == false) {
            return true;                    
        } else {
            return false;
        }
    }          
    
    /*
     * Description: Allows the CPU to randomly make a move.
     * Input: None.
     * Output: None, but side effect of adding a piece to the 2d array of the
     *         board and drawing the pieces and background.
     */
    public void automaticMove() {
        
        //Creating a random xPosition
        double xValue = Math.random();
        
        /*
         * Makes sure that a valid x position is given, as it recurses through
         * automaticMove until an x position that allows a piece to be added 
         * is given.
         */        
        if (addPiece(xValue, getPlayerColor()) == false) {
            automaticMove();
        }
        
        /*
         * Animates and loops through drawing in order to delay the move and
         * make it seem like the CPU is "thinking"
         */
        PennDraw.enableAnimation(15);
        for (int i = 0; i < 29; i++) {
            introducePlayer(getPlayerNumber());
            PennDraw.advance();
        }
        drawTopBackground();
        drawPieces();
        PennDraw.disableAnimation();
    }
}
