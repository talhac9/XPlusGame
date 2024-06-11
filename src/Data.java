/*
CS2210 Assignment 2 X+ Game
Talha Chaudhry
tchaud@uwo.ca
251390028
October 17, 2023
 */

public class Data {

    private String config; // Unique config to be stored in Hash Dictionary
    private int score;

    // Constructor method that creates a Data object with given parameters
    public Data(String config, int score) {
        this.config = config;
        this.score = score;
    }
    // Getters
    public String getConfiguration() {
        return this.config;
    }

    public int getScore() {
        return this.score;
    }
}
