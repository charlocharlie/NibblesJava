/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.sound.sampled.LineEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**

 @author User
 */
public class AudioEffectPlayerTest
{

   public AudioEffectPlayerTest()
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
   }

   @After
   public void tearDown()
   {
   }

   /**
    Test of playSound method, of class AudioEffectPlayer.
    */
   @Test
   public void testPlaySound() throws InterruptedException
   {
      System.out.println("AudioEffectPlayer:playSound");
      String soundFile = "theme-slow.wav";
      AudioEffectPlayer instance = new AudioEffectPlayer();
      StackTraceElement element = Thread.currentThread().getStackTrace()[0];
      System.out.println(element);
      System.out.println("Verify " + soundFile + " is playing.");
      instance.playSound(soundFile);
      Thread.sleep(1000);

   }

   /**
    Test of update method, of class AudioEffectPlayer.
    */
   @Test
   public void testUpdate() throws InterruptedException
   {
      System.out.println("AudioEffectPlayer:update");
      String soundFile1 = "theme-fast.wav";
      String soundFile2 = "crash.wav";
      AudioEffectPlayer instance = new AudioEffectPlayer();
      StackTraceElement element = new Exception().getStackTrace()[0];
      System.out.println(element);
      System.out.println("Verify one sound plays after the other.");
      Thread.sleep(1000);
      instance.playSound(soundFile1);
      instance.playSound(soundFile2);
      Thread.sleep(1250);
   }

}
