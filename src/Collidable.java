
import java.awt.geom.Point2D;

/**
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates and open the template
 in the editor.
 */
/**

 @author Nick
 */
public class Collidable
{

   protected Point2D.Double position;

   public Collidable(Point2D.Double inPos)
   {
      position = inPos;
   }

   public boolean collided(Collidable c)
   {
      if (c == null)
         return false;
      if (this == c)
         return false;
      return position.equals(c.position);
   }

   @Override
   public boolean equals(Object o)
   {
      if (o == null)
         return false;
      if (o.getClass() != Collidable.class)
         return false;
      Collidable collidable = (Collidable) o;
      return position.equals(collidable.position);
   }
}