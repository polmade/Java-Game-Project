package dytu.game;

import org.jbox2d.common.Vec2;
import city.cs.engine.*;
import city.cs.engine.StepListener;


import javax.swing.*;
import java.awt.*;

public class Build {
    int Level = 0;
    //Initialise a world and view
    private WorldBuilder theWorld;
    private GameView view;

    //constructor method to build a world
    public Build(){
        //make the world
        theWorld = new Level_One();
        //theWorld = new Level_One();



        //start the view
        view = new GameView(theWorld, 500, 500);
        view.addKeyListener(new KeyHandler(view, theWorld));
        //view.addStepListener(new stepListener(view, theWorld.getHero()));
        view.setFocusable(true);

        //add a stepListener to the world
        theWorld.addStepListener(new stepListener(view, theWorld.getHero()));


        //give the view a place to be
        final JFrame frame = new JFrame("The World");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);



        //start the world
        theWorld.start();
    }

    //where stuff happens
    public static void main(String[] args) {
        new Build();
    }

}