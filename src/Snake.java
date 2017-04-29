
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**

 @author Jake Ira
 @author Charlie Laabs
 @author Noah Moss
 @author Nick Sosinski
 @author Ed VanDerJagt
 */
public class Snake
{

   // Nick and Noah Area
   private static final int MAX_SNAKE_LENGTH = 1000;
   private static final int GROW_FACTOR = 4;
   private boolean alive;
   private ArrayList<SnakeSegment> body = new ArrayList();
   private int lives = 5;
   private int score = 0;
   private int newSegments;

   private final Point2D.Double initialSpawn;
   private final Direction initialDirection;
   private Direction directionLastMoved;

   public enum Direction
   {
      RIGHT, UP, LEFT, DOWN
   };

   public Snake(Point2D.Double spawnPoint, Direction dir)
   {
      initialDirection = dir;
      initialSpawn = spawnPoint;
      respawn();
   }

   public void respawn()
   {
      newSegments = 0;
      body.clear();
      body.add(new SnakeHead(initialSpawn, initialDirection));
      addSegment();
   }

   private void addSegment()
   {
      body.add(new SnakeBody(body.get(body.size() - 1)));
      newSegments++;
   }

   private void growSnake(int growValue)
   {
      if (body.size() < MAX_SNAKE_LENGTH - 30)
         for (int i = 0; i < growValue * GROW_FACTOR; i++)
            addSegment();
   }

   public void iterateForward()
   {
      directionLastMoved = body.get(0).getDirection();
      for (int i = 0; i < body.size() - newSegments; i++)
         body.get(i).moveForward();
      for (int i = body.size() - newSegments - 1; i > 1; i--)
         body.get(i).setDirection(body.get(i - 1).getDirection());
      body.get(1).setDirection(body.get(0).getDirection());
      if (newSegments > 0)
         newSegments--;
   }

   public Point2D.Double getHeadLocation()
   {
      return body.get(0).getPosition();
   }

   public List<SnakeSegment> getSnakeSegments()
   {
      return body.subList(1, body.size());
   }

   public boolean checkCollison(Collidable c)
   {
      return body.get(0).collided(c);
   }

   public void setDirection(Direction inDir)
   {
      body.get(0).setDirection(inDir);
   }

   public Direction getDirectionLastMoved()
   {
      return directionLastMoved;
   }

   public void die()
   {
      lives--;
      try
      {
         InputStream inputStream = new FileInputStream("./resources/crash.wav");
         AudioStream audioStream = new AudioStream(inputStream);
         AudioPlayer.player.start(audioStream);
      }
      catch (IOException e)
      {
         System.err.println("File not found.");
      }
      System.out.println("Player died");
   }

   public void eat(Food food)
   {
      int foodValue = food.getValue();
      growSnake(foodValue);
      score += 100 * foodValue;
      try
      {
         InputStream inputStream = new FileInputStream("./resources/get-food-2.wav");
         AudioStream audioStream = new AudioStream(inputStream);
         AudioPlayer.player.start(audioStream);
      }
      catch (IOException e)
      {
         System.err.println("File not found.");
      }
      System.out.println("Player ate");
   }

   public int getScore()
   {
      return score;
   }

   public int getLives()
   {
      return lives;
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null)
         return false;
      if (o.getClass() != Snake.class)
         return false;
      Snake snake = (Snake) o;
      if (snake.body.size() != body.size())
         return false;
      for (int i = 0; i < body.size() - 1; i++)
         if (!body.get(i).getPosition().equals(snake.body.get(i).getPosition())
               || body.get(i).getDirection() != snake.body.get(i).getDirection())
            return false;
      return newSegments == snake.newSegments
            && score == snake.score
            && lives == snake.lives;
   }

   // End Nick and Noah Area
}
