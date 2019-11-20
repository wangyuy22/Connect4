/* Name: Yuyang Wang
 * Pennkey: wangyuy
 * Recitation: 211
 * 
 * Execution: java GameInterface
 * 
 * Description: hw09 - The interface of the game
 */

public class GameInterface {
    
    public GameInterface() {
        
        //Creates new board that draws the initial interface
        Board game = new Board();
        drawInterface();
        
        while (true) {
            
            //If the user clicks the box that says "One Player Mode"
            if (PennDraw.mousePressed() && PennDraw.mouseX() < 0.45 &&
                PennDraw.mouseX() > 0.05 && PennDraw.mouseY() < 0.4 && 
                PennDraw.mouseY() > 0.2) {
                
                //Begins the game by drawing backgounds and introducting player
                game.drawTopBackground();
                game.drawBottomBackground();
                game.drawPieces();
                game.introducePlayer(1);
                
                //50% chance of the CPU starting the game
                double starting = Math.random();
                
                if (starting < 0.5) {
                    game.introduceComputer(game.getPlayerNumber());
                    game.automaticMove();
                    game.changePlayer();
                }
                
                //While the game is not over
                while (game.isGameOver() == false) {
                    
                    //Variable for mouse location
                    double mouseXLocation = PennDraw.mouseX();
                    
                    /*
                     * If moust is pressed and a piece is added, the new board
                     * is drawn
                     */
                    if (PennDraw.mousePressed()) {
                        if (game.addPiece(mouseXLocation, 
                                          game.getPlayerColor()) == true) {
                            game.drawPieces();
                            game.drawTopBackground();
                            
                            /*
                             * If game is not over, the CPU is introduced and
                             * makes a move
                             */
                            if (game.isGameOver() == false) {
                                game.drawTopBackground();
                                game.changePlayer();
                                game.introduceComputer(game.getPlayerNumber());
                                game.automaticMove();
                                
                                /*
                                 * After the CPU moves, if game is not over,
                                 * switches player back to user
                                 */
                                if (game.isGameOver() == false) {
                                    game.changePlayer();
                                    
                                    /*
                                     * If game is over, game over message 
                                     * appears and returns back to initial 
                                     * interface
                                     */
                                } else {
                                    gameOverText(4);
                                    PennDraw.clear();
                                    gameRestart();
                                }
                                
                                /*
                                 * If game is over, game over message appears 
                                 * and returns back to initial interface
                                 */    
                            } else {
                                gameOverText(game.getPlayerNumber());
                                PennDraw.clear();
                                gameRestart();
                            }
                            
                        }
                    }
                }
                
                //If the user clicks the box that says "One Player Mode"
            } else if (PennDraw.mousePressed() && PennDraw.mouseX() < 0.95 &&
                       PennDraw.mouseX() > 0.55 && PennDraw.mouseY() < 0.4 && 
                       PennDraw.mouseY() > 0.2) {
                
                //Begins the game by drawing backgounds and introducting player
                game.drawTopBackground();
                game.drawBottomBackground();
                game.drawPieces();
                game.introducePlayer(1);
                
                //While the game is not over
                while (game.isGameOver() == false) {
                    
                    //Variable for mouse location
                    double mouseXLocation = PennDraw.mouseX();
                    
                    /*
                     * If moust is pressed and a piece is added, the new board
                     * is drawn
                     */
                    if (PennDraw.mousePressed()) {
                        if (game.addPiece(mouseXLocation,
                                          game.getPlayerColor()) == true) {
                            game.drawPieces();
                            game.drawTopBackground();
                            
                            //If game is not over, the player changes
                            if (game.isGameOver() == false) {
                                game.changePlayer();
                                
                                /*
                                 * If game is over, game over message appears 
                                 * and returns back to initial interface
                                 */
                            } else {
                                gameOverText(game.getPlayerNumber());
                                PennDraw.clear();
                                gameRestart();
                            }
                        }            
                    }           
                } 
            }
        }   
    }
    
/*
     * Description: Draws the initial menu interface.
     * Input: None.
     * Output: None, but side effect of drawing the interface.
     */ 
    public void drawInterface() {
        
        //Background
        PennDraw.setPenColor(0, 112, 232);
        PennDraw.filledSquare(0.5, 0.5, 0.5);
        
        //Text Connect 4
        PennDraw.setPenColor(249, 241, 0);
        PennDraw.setFontBold();
        PennDraw.setFontSize(70);
        PennDraw.text(0.5, 0.8, "Connect 4!");
        
        //One Player
        PennDraw.setPenColor(PennDraw.GRAY);
        PennDraw.filledRectangle(0.25, 0.3, 0.2, 0.1);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(0.25, 0.3, 0.18, 0.08);
        PennDraw.setPenColor(0, 5, 155);
        PennDraw.setFontSize(23);
        PennDraw.text(0.25, 0.33, "One Player");
        PennDraw.text(0.25, 0.27, "Mode");
        
        //Two Player
        PennDraw.setPenColor(PennDraw.GRAY);
        PennDraw.filledRectangle(0.75, 0.3, 0.2, 0.1);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(0.75, 0.3, 0.18, 0.08);
        PennDraw.setPenColor(0, 5, 155);
        PennDraw.setFontSize(23);
        PennDraw.text(0.75, 0.33, "Two Player");
        PennDraw.text(0.75, 0.27, "Mode");
    }
    
    /*
     * Description: Allows the CPU to randomly make a move.
     * Input: None.
     * Output: None, but side effect of adding a piece to the 2d array of the
     *         board and drawing the pieces and background.
     */
    public void gameOverText(int playerNumber) {
        PennDraw.enableAnimation(10);
        for (int i = 0; i < 29; i++) {
            PennDraw.setFontBold();
            PennDraw.setFontSize(35);
            if (playerNumber == 1) {
                PennDraw.setPenColor(PennDraw.YELLOW);
                PennDraw.text(0.5, 0.93, "Yellow is the Winner!");
                
            } else if (playerNumber == 2) {
                PennDraw.setPenColor(PennDraw.RED);
                PennDraw.text(0.5, 0.93, "Red is the Winner!");
            } else if (playerNumber == 3) {
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.text(0.5, 0.93, "Draw - No One Wins"); 
            } else if (playerNumber == 4) {
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.text(0.5, 0.93, "CPU Wins"); 
            }
            PennDraw.advance();
        }
        PennDraw.disableAnimation();
    }
    
    /*
     * Description: Restarts the entire game by returning to the original 
     *              interface.
     * Input: None.
     * Output: None.
     */
    public void gameRestart() {
        GameInterface newGame = new GameInterface();
    }
}




