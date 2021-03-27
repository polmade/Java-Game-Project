package dytu.game;

import city.cs.engine.DebugViewer;
import dytu.world.WorldBuilder;

import javax.swing.*;

/**
 * Allows for the changing/setting of views
 * @author dylantuckey
 * @version 1
 * @since 2021
 * @see GameView
 */
public class ViewChanger {
    /*
    This class changes the view of the frame, and updates the world associated with the view
     */
    /**
     * The world passed to ViewChanger
     */
    private WorldBuilder world;
    /**
     * The view to be selected
     */
    private static GameView view;
    //private StepListener sL = new StepListener(view, world.getHero(), world.getMonster(), world);

    /**
     * Sets the world for the ViewChanger
     * @param world
     */
    public void setWorld(WorldBuilder world) {
        this.world = world;
    }

    /**
     * Changes the view
     * <p>
     *     Changes view, based on a given integer
     * </p>
     * @param selection
     * @return The view to be seen
     */
    public GameView viewSelect(int selection){
        switch(selection){
            case (0), (1), (2) -> {
                if (view == null){
                    view = new GameView(world, 500, 500, true);
                    view.updateBackground("data/background"+selection+".png");
                    view.addKeyListener(new KeyHandler(view, world));
                    view.addMouseListener(new MouseHandler(view, world));
                    //add a stepListener to the world
                    world.addStepListener(new StepListener(view, world.getHero(), world.getMonster(), world));
                    //view.addStepListener(new stepListener(view, world.getHero()));
                    view.setFocusable(true);
                    //JFrame debugViewer = new DebugViewer(world,500, 500);
                    return view;
                }else{
                    world.removeStepListener((city.cs.engine.StepListener) StepListener.getListenerCount().get(0));
                    StepListener.setListenerCount();
                    view.setWorld(world);
                    view.updateWorld(world);
                    world.getHero().setHealth();
                    MouseHandler mouseUpdate = (MouseHandler) view.getMouseListeners()[0];
                    mouseUpdate.updateWorldAndView(this.view, this.world);
                    KeyHandler keyUpdate = (KeyHandler) view.getKeyListeners()[0];
                    keyUpdate.updateWorldAndView(this.view, this.world);
                    //add a stepListener to the world
                    world.addStepListener(new StepListener(view, world.getHero(), world.getMonster(), world));
                    return view;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + selection);
        }
    }

    //set the state of the views finished boolean

    /**
     * Sets the boolean finished for the view
     * @param finished
     */
    public void setFinished(boolean finished){
        view.setFinished(finished);
    }

    /*
    constructor methods, with different options for use of view changer
     */

    /**
     * Constructor
     * <p>
     *     Takes both world and view
     * </p>
     * @param world
     * @param v
     */
    public ViewChanger(WorldBuilder world, GameView v){
        this.world = world;
        view = v;
    }

    /**
     * Constructor
     * <p>
     *     Takes only world
     * </p>
     * @param world
     */
    public ViewChanger(WorldBuilder world){
        this.world = world;
    }

}
