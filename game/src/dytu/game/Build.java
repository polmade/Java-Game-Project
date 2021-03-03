package dytu.game;

import dytu.world.WorldBuilder;

import java.awt.*;
import java.util.Scanner;

import javax.swing.*;

public class Build {
    //this is for later use, to implement a simple stage selection interface
    int level;



    //Initialise a world and view
    private WorldBuilder theWorld;
    private GameView view;



    //inititalise GUI
    private ControlForm controlForm = new ControlForm();

    /*
    public void levelSelect(int selection){
        switch(selection){
            case (0) -> {
                if (theWorld != null){
                    theWorld.getCovidList().clear();
                    theWorld.stop();
                    theWorld = new Level_One();
                } else{
                    theWorld = new Level_One();
                } if (this.view == null){
                    view = new GameView(theWorld, 500, 500);
                    view.addKeyListener(new KeyHandler(view, theWorld));
                    view.addMouseListener(new MouseHandler(view, theWorld));
                    //view.addStepListener(new stepListener(view, theWorld.getHero()));
                    view.setFocusable(true);
                    JFrame debugViewer = new DebugViewer(theWorld,500, 500);
                }else{
                    view.setWorld(theWorld);
                    MouseHandler mouseUpdate = (MouseHandler) view.getMouseListeners()[0];
                    mouseUpdate.updateWorldAndView(this.view, this.theWorld);
                    KeyHandler keyUpdate = (KeyHandler) view.getKeyListeners()[0];
                    keyUpdate.updateWorldAndView(this.view, this.theWorld);
                }
                theWorld.start();
            }
            case (1) -> {
                if (theWorld != null){
                    theWorld.getCovidList().clear();
                    theWorld.stop();
                    theWorld = new Level_Two();
                }else{
                    theWorld = new Level_Two();
                } if (this.view == null){
                    view = new GameView(theWorld, 500, 500);
                    view.addKeyListener(new KeyHandler(view, theWorld));
                    view.addMouseListener(new MouseHandler(view, theWorld));
                    //view.addStepListener(new stepListener(view, theWorld.getHero()));
                    view.setFocusable(true);
                }else{
                    view.setWorld(theWorld);
                    MouseHandler mouseUpdate = (MouseHandler) view.getMouseListeners()[0];
                    mouseUpdate.updateWorldAndView(this.view, this.theWorld);
                    KeyHandler keyUpdate = (KeyHandler) view.getKeyListeners()[0];
                    keyUpdate.updateWorldAndView(this.view, this.theWorld);
                }
                theWorld.start();

            }
        }
    }

     */

    //initialise level selector & view selector class
    private WorldChanger wc = new WorldChanger(theWorld/*, view*/);
    private ViewChanger vc = new ViewChanger(theWorld, view);



    //constructor method
    public Build(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Select level:");
        level = scan.nextInt();
        theWorld = wc.levelSelect(level);
        vc.setWorld(theWorld);
        view = vc.viewSelect(level);

        controlForm.setViewAndWorld(this.theWorld, this.view);

        /*theWorld = new Level_One();

        //start the view
        view = new GameView(theWorld, 500, 500);
        view.addKeyListener(new KeyHandler(view, theWorld));
        view.addMouseListener(new MouseHandler(view, theWorld));
        //view.addStepListener(new stepListener(view, theWorld.getHero()));
        view.setFocusable(true);



         */


        //give the view a place to be
        final JFrame frame = new JFrame("The World");
        frame.add(this.view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        //frame.setFocusable(false);

        frame.add(controlForm.getMainPanel(), BorderLayout.EAST);
        //view.requestFocusInWindow();
        frame.pack();
        frame.setVisible(true);


        //add a debug view, to allow for easy debugging
        //JFrame debugViewer = new DebugViewer(theWorld,500, 500);



        //start the world
        theWorld.start();



    }

    //where stuff happens
    public static void main(String[] args) {
        new Build();
    }

}
