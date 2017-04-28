
import java.awt.geom.Point2D;

/**

 @author Jake Ira
 @author Charlie Laabs
 @author Noah Moss
 @author Nick Sosinski
 @author Ed VanDerJagt
 */
public class SnakeSegment extends Collidable
{

   private Snake.Direction direction;
   SnakeSegment(Point2D.Double spawnPoint, Snake.Direction inDir)
   {
      position = spawnPoint;
      direction = inDir;
   }

   public void moveForward()
   {
      //Left/Right math is reversed when dealing with drawing graphics
      switch (direction)
      {
         case RIGHT:
            position = new Point2D.Double(position.x + 1, position.y);
            break;
         case UP:
            position = new Point2D.Double(position.x, position.y - 1);
            break;
         case LEFT:
            position = new Point2D.Double(position.x - 1, position.y);
            break;
         case DOWN:
            position = new Point2D.Double(position.x, position.y + 1);
            break;
         default:
      }
   }

   public Point2D.Double getPosition()
   {
      return position;
   }

   public Snake.Direction getDirection()
   {
      return direction;
   }

   public void setDirection(Snake.Direction inDir)
   {
      direction = inDir;
   }

   public boolean isColliding()
   {
      return false;
   }

   // End Nick and Noah Area
}
