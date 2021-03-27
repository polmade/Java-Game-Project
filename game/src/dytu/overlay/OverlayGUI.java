package dytu.overlay;

import dytu.game.*;
import dytu.world.WorldBuilder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * A main menu
 * @author dylantuckey
 * @version 1
 * @since 2021
 * @see WriteFile
 * @see WorldChanger
 * @see StateSaver
 * @see ViewChanger
 */
public class OverlayGUI {
    private JButton loadSave;
    private JPanel mainPanel;
    private JButton saveState;
    private JButton chooseLevel;
    private JTextField saveLocation;
    private JSlider soundLevel;
    private static int soundLevelInt;
    private JLabel soundName;
    private JButton highScores;
    private static StateSaver sS = new StateSaver();
    private static WorldBuilder world;
    private static GameView view;
    private boolean beenUsed = false;
    private boolean looking;
    private WriteFile wf = new WriteFile();
    private String[] scores;

    private ViewChanger vc = new ViewChanger((WorldBuilder) world, view);
    private WorldChanger wc = new WorldChanger((WorldBuilder) world);



    {
        soundLevel.setMajorTickSpacing(10);
        soundLevel.setMinorTickSpacing(2);
        soundLevel.setPaintTicks(true);
    }

    /**
     * Gets the sound level
     * @return The sound volume
     */
    public static int getSoundLevelInt(){
        return soundLevelInt;
    }

    /**
     * Sets the view and world for this
     * @param w
     * @param v
     */
    public static void setViewAndWorld(WorldBuilder w, GameView v){
        world = w;
        view = v;
        //levelNo = world.getLevelNo();
    }

    /**
     * Gets the main panel
     * @return the panel to be added to the dialog
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Constructor
     * <p>
     *     Defines the interactions between the game and the buttons/inputs of the GUI
     * </p>
     */
    public OverlayGUI(){
        loadSave.setFocusable(false);
        saveState.setFocusable(false);
        chooseLevel.setFocusable(false);
        highScores.setFocusable(false);
        soundLevel.setValue((int) (0.15 * 100));
        //allows the player to select from a saved play, and then sets them in that save
        loadSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sS.nameState();
                wc.setLooking(sS.isCharLook());
                WorldChanger.reload = true;
                world = wc.levelSelect(sS.getCharLevel());
                vc.setWorld(world);
                view = vc.viewSelect(sS.getCharLevel());
                Build.firstFrame = false;
                Build.normalOp(view);
                WorldChanger.reload = false;

            }
        });
        //saves the players current state, including name, level, direction of player, location within game
        saveState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(world.getHero().getImage() == "rightImage"){
                    looking = true;
                }else{
                    looking = false;
                }
                //System.out.println(saveLocation.getText().length());
                if(saveLocation.getText().length() != 0 && saveLocation.getText().equals("Define a location to save to (defaults if null, must contain full path)") != true){
                    sS.setSaveLocation(saveLocation.getText());
                }else{
                    sS.setSaveLocation("");
                }


                sS.saveStateSep(Build.getPlayerName(), world.getHero().getPoints(), looking, world.getHero().getPosition(), world.getLevelNo()-1);
            }
        });
        //allows the player to select a different level
        chooseLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Select Level: \n- 1 \n- 2 \n- 3");
                Scanner scan = new Scanner(System.in);
                int level = scan.nextInt() - 1;
                scan.close();
                world = wc.levelSelect(level);
                vc.setWorld(world);
                view = vc.viewSelect(level);
            }
        });
        //allows the user to change the music volume
        soundLevel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(soundLevel.getValueIsAdjusting() != true){
                    soundLevelInt = soundLevel.getValue();
                    System.out.println(soundLevelInt);
                    WorldBuilder.stopLevelSound();
                    WorldBuilder.playLevelSound(soundLevelInt);
                }
            }
        });
        //allows the user to see previous scores of players that have completed the game
        highScores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scores = wf.readFile();
                for(int i=0; i<scores.length; i++){
                    System.out.println(scores[i]);
                }

            }
        });



    }
}
