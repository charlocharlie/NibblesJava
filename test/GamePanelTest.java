/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */

import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**

 @author Noah Moss
 */
public class GamePanelTest
{

   private static final int CELL_SIZE = 15;
   private static final int WIDTH = 80;
   private static final int HEIGHT = 50;
   private static final int MARGIN_SPACING = 20;
   private static final int TITLE_BAR_HEIGHT = 25;
   private JFrame window;

   private GamePanel instance;
   public GamePanelTest()
   {
   }

   @BeforeClass
   public static void setUpClass()
   {
   }

   @AfterClass
   public static void tearDownClass()
   {
   }

   @Before
   public void setUp()
   {
      window = new JFrame();
      window.setTitle("Nibbles - .min.jHawks V2");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setVisible(true);
      window.setSize(CELL_SIZE * WIDTH + MARGIN_SPACING,
            CELL_SIZE * HEIGHT + MARGIN_SPACING + TITLE_BAR_HEIGHT);
      instance = new GamePanel(WIDTH, HEIGHT, CELL_SIZE);
      window.add(instance);
   }

   @After
   public void tearDown()
   {
   }

   /**
    Test of getContents method, of class GamePanel.
    */
   @Test
   public void testGetContents()
   {
      System.out.println("getContents");
      for (int i = 0; i < GamePanel.CellContents.values().length - 1; i++)
      {
         for (int column = 0; column < WIDTH - 1; column++)
            for (int row = 0; row < HEIGHT - 1; row++)
               instance.setContents(column, row, GamePanel.CellContents.values()[i]);
         for (int column = 0; column < WIDTH - 1; column++)
            for (int row = 0; row < HEIGHT - 1; row++)
               assertTrue(GamePanel.CellContents.values()[i] == instance.getContents(column, row));
      }
   }

   /**
    Test of showPause method, of class GamePanel.
    */
   @Test
   public void testShowPause() throws InterruptedException
   {
      System.out.println("showPause");
      instance.showPause(true);
      System.out.println("Verify \"Pause\" screen is showing.");
      Thread.sleep(2500);
      instance.showPause(false);
      System.out.println("Verify \"Pause\" screen is not showing.");
      Thread.sleep(2500);
   }

   /**
    Test of paintComponent method, of class GamePanel.
    */
   @Test
   public void testPaintComponent() throws InterruptedException
   {
      System.out.println("paintComponent");
      System.out.println("If you can see the \"Pause\" screen, it works...");
      instance.showPause(true);
      Thread.sleep(2500);
   }

   /**
    * Test of setContents method, of class GamePanel.
    */
   @Test
   public void testSetContents()
   {
      System.out.println("setContents");
      int column = 0;
      int row = 0;
      GamePanel.CellContents contents = null;
      GamePanel instance = null;
      instance.setContents(column, row, contents);
      // TODO review the generated test code and remove the default call to fail.
      fail("The test case is a prototype.");
   }

}
