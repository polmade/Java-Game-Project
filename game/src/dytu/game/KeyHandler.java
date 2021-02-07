package dytu.game;

import city.cs.engine.World;
import dytu.world.WorldBuilder;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//A class to allow keyboard interactions
public class KeyHandler implements KeyListener{
    private GameView view;
    private WorldBuilder world;
    protected Hero hero;

    //sets the view that the keyboard listener should be attached to, and provide world details to allow keyboard interactions
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
        if (key == 'x' || key == 'X'){
            world.getHero().setLookingForward(!(world.getHero().isLookingForward()));
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
            /*
            this code removes a body's image, then replaces the image with the correct directional image
            it then removes the fixture that is attached to the body, and subsequently replaces the removed fixture with a new fixture
             */
            world.getHero().removeAllImages();
            world.getHero().addImage(world.getHero().setImage("heroImageLeft"));
            world.getHero().getFixtureList().get(0).destroy();
            world.getHero().getFixtureList().add(0, world.getHero().setFixtureShape("heroShapeLeft"));
            world.velocityChangeHero(-6);
            //this.view.setCentre(new Vec2(hero.getPosition().x,(hero.getPosition().y)+8f));
        }
        if (key == KeyEvent.VK_UP){
            //jump the character
            world.jumpHero(15);
            world.getHero().setAngularVelocity(0.6f);
            world.getHero().setAngularVelocity(0f);
            //this.view.setCentre(new Vec2(hero.getPosition().x,(hero.getPosition().y)+8f));
        }
        if (key == KeyEvent.VK_RIGHT){
            System.out.println("Moving Right");
            /*
            this code removes a body's image, then replaces the image with the correct directional image
            it then removes the fixture that is attached to the body, and subsequently replaces the removed fixture with a new fixture
             */
            world.getHero().removeAllImages();
            world.getHero().addImage(world.getHero().setImage("heroImageRight"));
            world.getHero().getFixtureList().get(0).destroy();
            world.getHero().getFixtureList().add(0, world.getHero().setFixtureShape("heroShapeRight"));
            world.velocityChangeHero(6);
            //this.view.setCentre(new Vec2(hero.getPosition().x,(hero.getPosition().y)+8f));

        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        //System.out.println(e);
        // set var key to the code of the key pressed
        int key = e.getKeyCode();
        //check which directional key the user pressed, then stopped the character moving, and display text to the cl
        if (key == KeyEvent.VK_LEFT){
            System.out.println("Stopped Moving Left");
            world.velocityChangeHero(0);
        }
        if (key == KeyEvent.VK_UP){
            System.out.println("Jumped");
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
