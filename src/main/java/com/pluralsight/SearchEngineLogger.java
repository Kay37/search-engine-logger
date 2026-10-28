package com.pluralsight;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class SearchEngineLogger {

    public static void main(String[] args) throws InterruptedException {

        //? What are the requirements for the TODOs below?
        //? Scanner
        //? LocalDateTime
        //? LocalDateTime.now()
        //? DateTimeFormatter.ofPattern("")
        //? .format(formatter)
        //? A loop for the actions menu
        //? equalsIgnoreCase for X or x to exit
        //? A static method for logging the actions of the user
        //? BufferedWriter & FileWriter

        //? For the logger - the user's input must be captured, stored in a String variable, and then sent to
        //? a FileWriter wrapped in a BufferedWriter that stores that String in a logs.txt file
        //? The user input String must be put into the logs.txt file AFTER the LocalDateTime.now() is formatted
        //? in yyyy-MM-dd hh:mm:ss format and written to the logs.txt with EVERY line.

        //TODO: Create a method to log the actions of the user. Write each user action to a file named logs.txt
        //* Actions include:
        //* a. Launching the application
        //* b. Searching for a term
        //* c. Exiting the application

        //* Entries in the logs.txt file should follow this format:
        // 2026-04-22 12:42:20 launch
        // 2026-04-22 12:42:45 search : How to use ChatGPT
        // 2026-04-22 12:43:51 search : How to forge a camp knife
        // 2026-04-22 12:45:32 exit

        //TODO: Prompt the user for a search term that they wish to search.
        Scanner userActionRequest =  new Scanner(System.in);
        String logFile = "logs.txt";
        BufferedWriter eLogger = new BufferedWriter(new FileWriter(logFile));

        //* create a variable to keep the actions menu looping
        boolean menuRunning = true;

        //* loop the actions menu
        while (menuRunning) {
            String userAction = displayActionsMenu(userActionRequest);
            switch (userAction) {
                case "a":
                    System.out.println("Please enter a search term: ");
                    String userActionLogged = userActionRequest.next();
                    System.out.println("Searching...");
                    Thread.sleep(1000);
                    System.out.println("...");
                    Thread.sleep(1000);
                    System.out.println("...");
                    Thread.sleep(1000);
                    System.out.println("...");
                    Thread.sleep(1000);
                    System.out.println("Beep boop! Search failed! Returning to actions menu. ");
                    Thread.sleep(1000);
                    Thread.sleep(1000);
                    break;
                case "b":
                    System.out.println("Powering down... beep... boop... zzz. Goodbye!");
                    Thread.sleep(1000);
                    menuRunning = false;
                    break;
            }
        }

    }


    public static String displayActionsMenu(Scanner userActionRequest, BufferedWriter eLogger) {
        //? ask the user what action they want to run
        System.out.println("""
         _____________________________________
        |  ( )  ( )  ( )        SEARCH-O-TRON |
        |_____________________________________|
        |                                     |
        |   Beep boop! Your trusty search     |
        |   robot is awake and ready.         |
        |   Available requests:               |
        |a. Enter a search term               |
        |b. Enter X or x to exit the app      |
        |_____________________________________|
        \n
        """);
        //? store their action
        String userActionChoice = userActionRequest.nextLine();
        eLogger.write()
        return userActionChoice.toLowerCase();

    }

//!    public static String userLogger (String) {
//!
//!    }

}
