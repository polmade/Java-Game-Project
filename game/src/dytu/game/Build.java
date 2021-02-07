package dytu.game;

import city.cs.engine.DebugViewer;
import dytu.world.Level_One;
import dytu.world.WorldBuilder;


import javax.swing.*;

public class Build {
    //this is for later use, to implement a simple stage selection interface
    int Level = 0;


    //Initialise a world and view
    private WorldBuilder theWorld;
    private GameView view;


    //constructor method
    public Build(){
        //make the world
        theWorld = new Level_One();



        //start the view
        view = new GameView(theWorld, 500, 500);
        view.addKeyListener(new KeyHandler(view, theWorld));
        view.addMouseListener(new MouseHandler(view, theWorld));
        //view.addStepListener(new stepListener(view, theWorld.getHero()));
        view.setFocusable(true);

        //add a stepListener to the world
        theWorld.addStepListener(new StepListener(view, theWorld.getHero(), theWorld.getMonster(), theWorld));

        //give the view a place to be
        final JFrame frame = new JFrame("The World");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);

        //add a debug view, to allow for easy debugging
        JFrame debugViewer = new DebugViewer(theWorld,500, 500);



        //start the world
        theWorld.start();
    }

    //where stuff happens
    public static void main(String[] args) {
        new Build();
    }

}
