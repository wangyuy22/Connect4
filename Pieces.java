/* Name: Yuyang Wang
 * Pennkey: wangyuy
 * Recitation: 211
 * 
 * Execution: java Pieces
 * 
 * Description: hw09 - The pieces required in Connect 4
 */

public class Pieces {
    
    //Fields
    private String color;
    
    /*
     * Description: Constructor of a piece that is a given color that must be 
     *              either "yellow", "red", or "white", or else and error is 
     *              thrown.
     * Input: String that is a color.
     * Output: None.
     */
    public Pieces(String pieceColor) {
        
        //Changing the color of the piece
        color = pieceColor;
        if (color.equals("yellow")) {
            PennDraw.setPenColor(PennDraw.YELLOW);
        } else if (color.equals("red")) {
            PennDraw.setPenColor(PennDraw.RED);
        } else if (color.equals("white")) {
            PennDraw.setPenColor(PennDraw.WHITE);
        } else {
            throw new RuntimeException("Error: Invalid Color");
        }
    }
    
    /*
     * Description: Getter method that gets the color of a piece.
     * Input: None.
     * Output: String that is the color.
     */
    public String getPieceColor() {
        return color;
    }    
}

