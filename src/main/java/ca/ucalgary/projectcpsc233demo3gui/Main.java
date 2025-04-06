package ca.ucalgary.projectcpsc233demo3gui;

import java.io.File;
/**
 * CPSC 233 W24 Project Menu.java
 * @author Christopher Umukoro, Kenneth Kapungu, Chido Chiradza
 * Tutorial:16
 * Date:1st March 2024
 * @email christopher.umukoro@ucalgary.ca, kenneth.kapungu@ucalgary.ca, chido.chiradza@ucalgary.ca
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Check if the number of command line arguments is more than two
        if(args.length > 2){
            // Print an error message if more than one argument is provided
            System.err.println("Expected one command line argument for filename to load from");
        }

        // Check if exactly one argument is provided
        if(args.length == 1){
            // Store the first command line argument as the filename
            String filename = args[0];
            // Create a File object based on the filename
            File file = new File(filename);

            // Check if the file does not exist or cannot be read
            if(!file.exists() || !file.canRead()){
                // Print an error message specifying the file cannot be loaded
                System.err.println("Can not load from the file " + filename);
                // Exit the program with status code 1 indicating an error
                System.exit(1);
            }

            // Start the menu loop with the file if it is valid
            Menu.menuLoop(file);
        } else {
            // Start the menu loop without any file if no arguments are provided
            Menu.menuLoop(null);
        }
    }
}
