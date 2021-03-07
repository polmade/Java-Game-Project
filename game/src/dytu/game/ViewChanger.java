package dytu.game;

import city.cs.engine.DebugViewer;
import dytu.world.WorldBuilder;

import javax.swing.*;

public class ViewChanger {
    /*
    This class changes the view of the frame, and updates the world associated with the view
     */
    private WorldBuilder world;
    private static GameView view;
    //private StepListener sL = new StepListener(view, world.getHero(), world.getMonster(), world);

    public void setWorld(WorldBuilder world) {
        this.world = world;
    }

    public GameView viewSelect(int selection){
        switch(selection){
            case (0), (1), (2) -> {
                if (view == null){
                    view = new GameView(world, 500, 500);
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
    public void setFinished(boolean finished){
        view.setFinished(finished);
    }

    /*
    constructor methods, with different options for use of view changer
     */
    public ViewChanger(WorldBuilder world, GameView v){
        this.world = world;
        view = v;
    }
    public ViewChanger(WorldBuilder world){
        this.world = world;
    }

}
