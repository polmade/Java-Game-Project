package dytu.game;

import org.jbox2d.common.Vec2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Saves & reads state of player
 * @author dylantuckey
 * @version 1
 * @since 2021
 */
public class StateSaver{
    private String[] lineContent;
    private String charName;
    private int charScore;
    private boolean charLook;
    private Vec2 charCoords;
    public static Vec2 staticCharCoords;
    private int charLevel;
    private final String DEFAULT_SAVE_LOCATION = "./Data/State.txt";
    private String saveLocation = DEFAULT_SAVE_LOCATION;

    /**
     * Sets the save location to a non-default save location
     * @param saveLocation
     */
    public void setSaveLocation(String saveLocation) {
        if(saveLocation.length() !=0){
            this.saveLocation = saveLocation;
            System.out.println("set save location");
        }else{
            this.saveLocation = DEFAULT_SAVE_LOCATION;
        }
    }

    //private WorldChanger wC = new WorldChanger(Build.getTheWorld());

    //getters for those relevant to character spawning

    /**
     * Get the player name
     * @return The player's name
     */
    public String getCharName() {
        return charName;
    }

    /**
     * Get the player score
     * @return The player's score
     */
    public int getCharScore() {
        return charScore;
    }

    /**
     * @return The player direction
     */
    public boolean isCharLook() {
        return charLook;
    }

    /**
     * Get the player coordinates
     * @return The player's coordinates
     */
    public Vec2 getCharCoords() {
        return charCoords;
    }

    /**
     * Get the player level
     * @return The player's level
     */
    public int getCharLevel(){
        return charLevel;
    }

    /**
     * Constructor
     * <p>
     *     Save state to file
     *     Takes multiple inputs
     * </p>
     * @param name
     * @param score
     * @param looking
     * @param coords
     * @param level
     */
    public StateSaver(String name, int score, boolean looking, Vec2 coords, int level){
     try{
         FileWriter fW = new FileWriter(DEFAULT_SAVE_LOCATION, true);
         BufferedWriter bW = new BufferedWriter(fW);
         String toWrite = name+","+score+","+looking+","+coords+","+level+"\n";
         bW.write(toWrite);
         //System.out.println(toWrite);
         bW.close();
     } catch (Exception e) {
         e.printStackTrace();
     }
    }

    /**
     * Empty constructor
     */
    public StateSaver(){
    }

    /**
     * State saving method
     * <p>
     *     This is separate from the full constructor, to allow for usage in occasion where the moment of instantiation may not be able to provide full details of the parameters needed
     *     This allows greater flexibility in state saving
     * </p>
     * @param name
     * @param score
     * @param looking
     * @param coords
     * @param level
     */
    public void saveStateSep(String name, int score, boolean looking, Vec2 coords, int level){
        try{
            FileWriter fW = new FileWriter(saveLocation, true);
            System.out.println("writing to "+saveLocation+" now");
            BufferedWriter bW = new BufferedWriter(fW);
            String toWrite = name+","+score+","+looking+","+coords+","+level+"\n";
            bW.write(toWrite);
            //System.out.println(toWrite);
            bW.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to reload from a saved state
     * <p>
     *     Reads all lines from given file
     *     Takes user input to determine which save needs to be loaded
     * </p>
     * @return Player save selection
     */
    public String nameState(){
        try{
            //FileReader fR = new FileReader("./Data/State.txt");
            //BufferedReader bR = Files.newBufferedReader(Path.of("./Data/State.txt"));
            //String line = bR.readAllLines();
            List<String> lines = Files.readAllLines(Path.of(saveLocation));
            for(String line: lines){
                lineContent = line.split(",");
                System.out.println(lineContent[0]);
            }
            Scanner scanner = new Scanner(System.in);
            String selection = scanner.next();
            System.out.println(selection);
            for(String line: lines){
                lineContent = line.split(",");
                //System.out.println(lineContent[0]);
                if(selection.equals(lineContent[0])){
                    charName = lineContent[0];
                    //System.out.println("set name");
                    charScore = Integer.parseInt(lineContent[1]);
                    charLook = Boolean.parseBoolean(lineContent[2]);
                    //charCoords = lineContent[3];
                    // for these next two lines, the \\ is a regex escape
                    float coords1 = Float.parseFloat(lineContent[3].replaceAll("\\(", ""));
                    float coords2 = Float.parseFloat(lineContent[4].replaceAll("\\)", ""));
                    charCoords = new Vec2(coords1, coords2);
                    staticCharCoords = charCoords;
                    charLevel = Integer.parseInt(lineContent[5]);
                    System.out.println(charLevel);
                }
            }
            return selection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
