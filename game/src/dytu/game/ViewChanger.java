package dytu.game;

import city.cs.engine.DebugViewer;
import dytu.world.WorldBuilder;

import javax.swing.*;

public class ViewChanger {
    private WorldBuilder world;
    private static GameView view;
    //private StepListener sL = new StepListener(view, world.getHero(), world.getMonster(), world);

    public void setWorld(WorldBuilder world) {
        this.world = world;
    }

    public GameView viewSelect(int selection){
        switch(selection){
            case (0), (1) -> {
                if (view == null){
                    view = new GameView(world, 500, 500);
                    view.addKeyListener(new KeyHandler(view, world));
                    view.addMouseListener(new MouseHandler(view, world));
                    //add a stepListener to the world
                    world.addStepListener(new StepListener(view, world.getHero(), world.getMonster(), world));
                    //view.addStepListener(new stepListener(view, world.getHero()));
                    view.setFocusable(true);
                    JFrame debugViewer = new DebugViewer(world,500, 500);
                    return view;
                }else{
                    world.removeStepListener((city.cs.engine.StepListener) StepListener.getListenerCount().get(0));
                    StepListener.setListenerCount();
                    view.setWorld(world);
                    view.updateWorld(world);
                    world.getHero().setHealth();
                    //JFrame debugViewer = new DebugViewer(world,500, 500);
                    /*final JFrame frame = new JFrame("The World");
                    frame.add(this.view);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);
                    frame.setLocationByPlatform(true);
                    frame.pack();
                    frame.setVisible(true);
                     */
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


    public ViewChanger(WorldBuilder world, GameView v){
        this.world = world;
        view = v;
    }
    public ViewChanger(WorldBuilder world){
        this.world = world;
    }

}
