package edu.miracosta.cs220.ntpotraz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    private String arg1;
    private int arg2;
    private Scanner inputFile;
    private String currentLine;

    /**
     * Constructor for the parser. reads through a vm file in preparation for writing assembly
     * @param fileName The name of the vm file
     */
    public Parser(String fileName) {
        try {
            if(!fileName.contains(".vm")) {
                throw new FileNotFoundException();
            }
            inputFile = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.err.println("File Does not Exist!");
        }
    }

    /**
     * Method for checking to see if the vm file has more commands
     * @return True if there is another line in the file, false if not
     */
    public boolean hasMoreCommands() {
        return inputFile.hasNextLine();
    }

    /**
     * Accessor method for the current line that the parser is looking at
     * @return The current line
     */
    public String getCurrentLine() {
        return currentLine;
    }

    /**
     * Checking the current line for the type of command that the line is
     * @return The type of command that the current line is
     */
    public CommandType commandType() {
        //If the current line is empty and the method is called, returns that there is no command
        if(currentLine == null)
            return CommandType.C_NOCOMMAND;

        //If the current command line contains 'pop' then it returns that it is a pop command
        if(currentLine.contains("pop"))
            return CommandType.C_POP;
        //If the current command line contains 'push' then it returns that it is a push command
        else if(currentLine.contains("push"))
            return  CommandType.C_PUSH;
        //If the current command line does not contain a space, then it returns that it is an arithmetic command
        else if(!currentLine.contains(" "))
            return CommandType.C_ARITHMETIC;
        //Returns no command if no other command has been return yet
        return CommandType.C_NOCOMMAND;
    }

    /**
     * Advances to the next line in the file
     */
    public void advance() {
        //if hasMoreCommands returns false, method just returns
        if(!hasMoreCommands()) {
            System.out.println("End of the line!");
            return;
        }
        //Sets the currentLine to the next line on the file
        currentLine = inputFile.nextLine();
        //System.out.println(currentLine);

        //If current line is empty or starts with a '/', then it calls the advance() method again since those are
        //blank lines or comments
        if(currentLine.equals("") || currentLine.charAt(0) == '/') {
            advance();
            return;
        }

        //Checks to see if the current line contains a comment and removes it from currentLine
        if(currentLine.contains("//")) {
            int index = currentLine.indexOf("//");
            currentLine = currentLine.substring(0, index);
        }

        //Setting arg1 and arg2 for push and pop commands
        if(commandType() == CommandType.C_POP || commandType() == CommandType.C_PUSH)
           setPushPop();

        //Sets arg1 for the arithmetic command
        if(commandType() == CommandType.C_ARITHMETIC) {
            arg1 = currentLine;

            //Testing stuff
            /*
            System.out.println("---------------------\nARITH COMMAND!");
            System.out.println("arg1: " + arg1);
            System.out.println("vvvvvvvvvvvvvvvvvvvvv");
            */
        }
    }

    //Helper method for setting the arg1 and arg2
    private void setPushPop() {
        //Gets rid of the 'pop ' from the string
        String tempArg = currentLine.substring(currentLine.indexOf(" ")+1);
        arg1 = tempArg.substring(0, tempArg.indexOf(" "));

        //Gets rid of arg1 from the remainder of the string and sets the number to arg2
        tempArg = tempArg.substring(tempArg.indexOf(" ")+1);
        arg2 = Integer.parseInt(tempArg);

        //Testing stuff
        /*
        if(commandType() == CommandType.C_POP)
            System.out.println("---------------------\nPOP COMMAND!");
        else
            System.out.println("---------------------\nPUSH COMMAND!");
        System.out.println("arg1: " + arg1);
        System.out.println("arg2: " + arg2);
        System.out.println("vvvvvvvvvvvvvvvvvvvvv");
         */
    }

    /**
     * Accessor method for arg1
     * @return the value of arg1
     */
    public String getArg1() {
        return arg1;
    }

    /**
     * Accessor method for arg2
     * @return the value of arg2
     */
    public int getArg2() {
        return arg2;
    }
}
