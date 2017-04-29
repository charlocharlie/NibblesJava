
import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**

 @author Jake Ira
 @author Charlie Laabs
 @author Noah Moss
 @author Nick Sosinski
 @author Ed VanDerJagt
 */
public class GamePanel extends JPanel
{

   private static final Font displayFont = new Font(Font.MONOSPACED, Font.PLAIN, 14);
   private static final int MARGIN_SIZE = 10;
   private final CellContents gameBoard[][];
   private final int charWidth;
   private boolean displayPause = false;
   private Level level;
   private static int charHeight;
   private static int width;
   private static int height;

   private int xOffset;
   private int yOffset;

   private GameManager manager;

   public enum CellContents
   {
      EMPTY, WALL, FOOD, SNAKE, SNAKEHEAD
   };

   public GamePanel(int boardWidth, int boardHeight, int inCharWidth, GameManager inManager)
   {
      manager = inManager;
      height = boardHeight;
      width = boardWidth;
      charWidth = inCharWidth;
      charHeight = 2 * charWidth;
      gameBoard = new CellContents[boardWidth][boardHeight];
      xOffset = MARGIN_SIZE;
      yOffset = MARGIN_SIZE + charHeight;
      for (int column = 0; column < gameBoard.length; column++)
         for (int row = 0; row < gameBoard[column].length; row++)
            gameBoard[column][row] = CellContents.EMPTY;
      setPreferredSize(
            new Dimension(charWidth * boardWidth + 2 * MARGIN_SIZE,
                  charWidth * boardHeight + 2 * MARGIN_SIZE + charHeight));
   }

   public CellContents getContents(int column, int row)
   {
      try
      {
         return gameBoard[column][row];
      }
      catch (IndexOutOfBoundsException e)
      {
         System.err.println("Out of bounds exception:\n" + e.toString());
         System.err.println(Integer.toString(column));
         System.err.println(Integer.toString(row));
         return CellContents.EMPTY;
      }
   }

   public void setContents(double column, double row, CellContents contents)
   {
      try
      {
         gameBoard[(int) column][(int) row] = contents;
      }
      catch (IndexOutOfBoundsException e)
      {
         System.err.println("Out of bounds exception:\n" + e.toString());
      }
   }

   private void updateFood()
   {
      Food food = manager.getFood();
      Point2D.Double foodPosition = food.getPosition();
      setContents(foodPosition.x, foodPosition.y, GamePanel.CellContents.FOOD);
   }

   private void updateSnakes()
   {
      Snake[] snakes = manager.getSnakes();
      for (int i = 0; i < snakes.length; i++)
      {
         Point2D.Double headLocation = snakes[i].getHeadLocation();
         setContents(headLocation.x, headLocation.y, GamePanel.CellContents.SNAKEHEAD);
         java.util.List<SnakeSegment> segments = snakes[i].getSnakeSegments();
         for (int j = 0; j < segments.size(); j++)
         {
            Point2D.Double segmentPosition = segments.get(j).getPosition();
            setContents(segmentPosition.x, segmentPosition.y, GamePanel.CellContents.SNAKE);
         }
      }
   }

   public void showPause(boolean pause)
   {
      displayPause = pause;
   }

   @Override
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      flushGameBoard();
      updateSnakes();
      updateFood();
      paintInformationLine(g2);
      drawGameBoard(g2);
      if (displayPause)
         paintPauseScreen(g2);
   }

   private void drawGameBoard(Graphics2D g)
   {
      for (int column = 0; column < gameBoard.length; column++)
         for (int row = 0; row < gameBoard[column].length; row++)
         {
            g.setColor(getContentColor(getContents(column, row)));
            int xPos = xOffset + column * charWidth;
            int yPos = yOffset + row * charWidth;
            g.fillRect(xPos, yPos, charWidth, charWidth);
         }
   }

   private void flushGameBoard()
   {
      for (int column = 0; column < gameBoard.length; column++)
         for (int row = 0; row < gameBoard[column].length; row++)
            gameBoard[column][row] = CellContents.EMPTY;
   }

   private void paintPauseScreen(Graphics2D g)
   {
      g.setColor(Color.white);
      int xPos = xOffset + 23 * charWidth;
      int yPos = yOffset + 9 * charHeight;
      int gWidth = 33 * charWidth;
      int gHeight = 3 * charHeight;
      g.fillRect(xPos, yPos, gWidth, gHeight);
      g.setColor(Color.red);
      xPos = xOffset + 24 * charWidth;
      yPos = yOffset + (int) (9.5 * charHeight);
      gWidth = 31 * charWidth;
      gHeight = 2 * charHeight;
      g.fillRect(xPos, yPos, gWidth, gHeight);
      g.setColor(Color.white);
      g.setFont(displayFont);
      xPos = xOffset + 26 * charWidth;
      yPos = yOffset + (int) (10.75 * charHeight);
      g.drawString("Game Paused ... Push Space", xPos, yPos);
   }

   private void paintInformationLine(Graphics2D g)
   {
      g.setColor(Color.blue);
      g.fillRect(MARGIN_SIZE, MARGIN_SIZE, charWidth * width, charHeight);
      g.setColor(Color.white);
      int yPos = yOffset;
      String sammyString = "SAMMY-->  Lives: "
            + Integer.toString(manager.getPlayerLives(GameManager.playerEnum.PLAYER_ONE))
            + "     "
            + Integer.toString(manager.getPlayerScore(GameManager.playerEnum.PLAYER_ONE));
      int xPos = xOffset + 48 * charWidth;
      g.setFont(displayFont);
      g.drawString(sammyString, xPos, yPos);
      if (manager.getNumberOfPlayers() == 2)
      {
         String jakeString = Integer.toString(manager.getPlayerScore(GameManager.playerEnum.PLAYER_TWO))
               + "  Lives: "
               + Integer.toString(manager.getPlayerLives(GameManager.playerEnum.PLAYER_TWO))
               + "  <--JAKE";
         xPos = xOffset + charWidth;
         g.setFont(displayFont);
         g.drawString(jakeString, xPos, yPos);
      }
   }

   private Color getContentColor(CellContents content)
   {
      switch (content)
      {
         case WALL:
            return Color.red;
         case FOOD:
            return Color.white;
         case SNAKE:
            return Color.orange;
         case SNAKEHEAD:
            return Color.yellow;
         default:
         case EMPTY:
            return Color.blue;
      }
   }

}
