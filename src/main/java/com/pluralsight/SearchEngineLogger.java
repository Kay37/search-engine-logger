package com.pluralsight;

//! For user input and reading what the user types
import java.util.Scanner;
//! Holds a date and time
import java.time.LocalDateTime;
//! Describes how a date should be formatted / look
import java.time.format.DateTimeFormatter;
//! BufferedWriter wraps the FileWriter
import java.io.BufferedWriter;
//! Writes to a file.
import java.io.FileWriter;

public class SearchEngineLogger {

    //! We have throws InterruptedException because of Thread.sleep
    public static void main(String[] args) throws InterruptedException {

        //! My planning notes
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
        //? in yyyy-MM-dd HH:mm:ss format and written to the logs.txt with EVERY line.

        //!This SearchEngineLogger opens a log file, writes launch to the log at the beginning, loops:
        //! A menu, reading a choice, and logs the choice whether it's a search or an exit.
        //! It then closes the file. Each line in the log has a date and timestamp + the action.
        //! The method userLogger builds every line in the log file so the format is always the same

        //! Create a method to log the actions of the user. Write each user action to a file named logs.txt
        //* Actions include:
        //* a. Launching the application
        //* b. Searching for a term
        //* c. Exiting the application

        //* Entries in the logs.txt file should follow this format:
        // 2026-04-22 12:42:20 launch
        // 2026-04-22 12:42:45 search : How to use ChatGPT
        // 2026-04-22 12:43:51 search : How to forge a camp knife
        // 2026-04-22 12:45:32 exit

        //! Prompt the user for a search term that they wish to search. Created a Scanner to allow user input
        Scanner userActionRequest = new Scanner(System.in);
        //! Created variable for what the logs.txt file should be called for easier use.
        String logFile = "logs.txt";

        //! Try and catch for the FileWriter and BufferedWriter because file work can fail. If there's a full disk, or
        //! a locked file, or something like that then the FileWriter can fail and so we have to catch that error
        //! if it happens from the main code we're *trying*.

        try {
            BufferedWriter eLogger = new BufferedWriter(new FileWriter(logFile, true));

            //* create a variable to keep the actions menu looping
            boolean menuRunning = true;
            userLogger(eLogger, "Menu launch");

            //* loop the actions menu
            while (menuRunning) {
                String userAction = displayActionsMenu(userActionRequest);
                switch (userAction) {
                    case "a":
                        //! Print a message showing confirm of them selecting A or a
                        System.out.println("Please enter a search term: ");
                        //! Store their search result
                        String userActionLogged = userActionRequest.nextLine();
                        //! call the userLogger method to write the user's search result to the log file
                        userLogger(eLogger, "User search : " +  userActionLogged);
                        //! UX/UI
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
                    case "x":
                        //! call the userLogger method to write the menu exiting to the log.
                        userLogger(eLogger, "Menu exit");
                        //! Print a message showing confirm of them selecting X or x
                        System.out.println("Powering down... beep... boop... zzz. Goodbye!");
                        Thread.sleep(1000);
                        //! end our loop condition (menuRunning)
                        menuRunning = false;
                        break;
                }
            }
            eLogger.close();

        } catch (Exception e) {
            System.out.println("Log file could not be created.");
            return;
        }
    }

    //! Nice little method to display the action menu instead of it bloating the main method.
    //! public static so we can call it from main, String so it gives back a piece of text (the menu)
    //! provided this method with our Scanner because it needs the Scanner to store input type and name
    public static String displayActionsMenu(Scanner userActionRequest) {
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
                |x. Enter X or x to exit the app      |
                |_____________________________________|
                \n
                """);
        //? store their action

        //! Reads the user's choice and stores it
        String userActionChoice = userActionRequest.nextLine();
        //!Return to send the user's choice back to main method (in lower case so they can enter A or a or X or x)
        return userActionChoice.toLowerCase();

    }

    //! Heart of this code. public static void because it does a job and returns nothing. It takes what file to write
    //! to (eLogger has the logFile String) and what happened / what to write (String logLine)
    public static void userLogger(BufferedWriter eLogger, String logLine) {
        try {
            //!Getting the exact date and time for start of log - must precisely show actions / app start and exit
            //! down to the second for proper logging.
            LocalDateTime currentDateTime = LocalDateTime.now();
            //! formatter for our exact date and time in our desired String format / print
            DateTimeFormatter logFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //! Using our formatter from our previous line and applying it to the currentDateTime.
            String formattedDateTime = logFormatter.format(currentDateTime);
            //! Writes timestamp (formattedDateTime) + a space for readability + the actual action / start / exit.
            eLogger.write(formattedDateTime + " " + logLine + "\n");
            //! catch if the write fails - prints message instead of crashing.
        } catch (Exception e) {
            System.out.println("There was an error. Please try again.");
        }
    }

}
