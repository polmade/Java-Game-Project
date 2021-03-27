package dytu.game;

import dytu.overlay.OverlayGUI;
import dytu.world.WorldBuilder;

import java.awt.*;
import java.util.Scanner;

import javax.swing.*;

public class Build {
    //this is for later use, to implement a simple stage selection interface
    int level;

    public static final JFrame frame = new JFrame("The World");
    public static JDialog gui = new JDialog(frame, Dialog.ModalityType.MODELESS);

    public static boolean firstFrame = true;

    /*public static void makeNewFrame(){
        frame = new JFrame("The World");
    }*/

    //player name
    private static String playerName;

    //set player name

    public static void setPlayerName(String playerName) {
        Build.playerName = playerName;
    }

    public static String getPlayerName() {
        return playerName;
    }

    //Initialise a world and view
    private WorldBuilder theWorld;
    private static GameView view;


    //getters and setters for world and view
    public WorldBuilder getTheWorld(){
        return theWorld;
    }

    public void setTheWorld(WorldBuilder theWorld) {
        this.theWorld = theWorld;
    }

    public GameView getView() {
        return view;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    //inititalise GUI
    private static ControlForm controlForm = new ControlForm();
    //private static OverlayGUI oGUI = new OverlayGUI();

    //initialise level selector & view selector class
    private WorldChanger wc = new WorldChanger(theWorld/*, view*/);
    private ViewChanger vc = new ViewChanger(theWorld, view);



    //constructor method
    public Build(){
        //take input for choosing level
        Scanner scan = new Scanner(System.in);
        System.out.println("Select level:");
        level = scan.nextInt();
        theWorld = wc.levelSelect(level);
        vc.setWorld(theWorld);
        view = vc.viewSelect(level);
        controlForm.setViewAndWorld(this.theWorld, this.view);
        normalOp(this.view);
        //add a debug view, to allow for easy debugging
        //JFrame debugViewer = new DebugViewer(theWorld,500, 500);

        //start the world
        theWorld.start();
    }

    //allows for frame to have its view set
    public static void normalOp(GameView toView){
        if(!firstFrame){
            frame.remove(view);
        }
        frame.add(toView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setLocationByPlatform(true);
        //frame.setFocusable(false);
        frame.add(controlForm.getMainPanel(), BorderLayout.EAST);
        //view.requestFocusInWindow();
        frame.pack();
        frame.setVisible(true);
    }

    //create the ability to make a dialog, as a main menu
    public static void mainGUI(OverlayGUI comp){
        gui.add(comp.getMainPanel());
        gui.pack();
        gui.setVisible(true);
        view.requestFocusInWindow();

        //frame.add(gui.getContentPane());
        /*frame.setContentPane(comp.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);*/
    }


    //where stuff happens
    public static void main(String[] args) {
        new Build();
    }

}
