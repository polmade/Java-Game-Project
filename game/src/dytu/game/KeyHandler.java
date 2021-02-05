package dytu.game;

import dytu.world.WorldBuilder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//A class to allow keyboard interactions
public class KeyHandler implements KeyListener{
    private GameView view;
    private WorldBuilder world;

    //sets the view that the keyboard listener should be attached to, and provide world details to allow keyboard interactions
    //this may have to changed later on, depending on whether extends works with this, but as yet, I don't know
    //from initial testing extends does work.
    public KeyHandler(GameView view, WorldBuilder world){
        this.view = view;
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent e){
        // set var key to the code of the key pressed
        char key = e.getKeyChar();

        //check for key type to display hero health
        if (key == 'h' || key == 'H'){
            System.out.println(world.getHero().getHealth());
        }

    }

    //This method will be the most used, and allows you to move bodies
    @Override
    public void keyPressed(KeyEvent e){
        //System.out.println(e);
        // set var key to the code of the key pressed
        int key = e.getKeyCode();
        //check which directional key the user pressed, then do something
        if (key == KeyEvent.VK_LEFT){
            System.out.println("Moving Left");
            world.velocityChangeHero(-4);
        }
        if (key == KeyEvent.VK_UP){
            System.out.println("No Movement Allowed");
        }
        if (key == KeyEvent.VK_RIGHT){
            System.out.println("Moving Right");
            world.velocityChangeHero(4);
        }
        if (key == KeyEvent.VK_DOWN){
            System.out.println("No Movement Allowed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        //System.out.println(e);
        // set var key to the code of the key pressed
        int key = e.getKeyCode();
        //check which directional key the user pressed, then do something
        if (key == KeyEvent.VK_LEFT){
            System.out.println("Stopped Moving Left");
            world.velocityChangeHero(0);
        }
        if (key == KeyEvent.VK_UP){
            //System.out.println("No Movement Allowed");
        }
        if (key == KeyEvent.VK_RIGHT){
            System.out.println("Stopped Moving Right");
            world.velocityChangeHero(0);
        }
        if (key == KeyEvent.VK_DOWN){
            //System.out.println("No Movement Allowed");
        }
    }
}
