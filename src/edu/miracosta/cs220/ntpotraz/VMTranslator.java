package edu.miracosta.cs220.ntpotraz;

import java.util.Scanner;

public class VMTranslator {
    private Parser parser;

    public static void main(String[] args) {
        String fileName;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        fileName = keyboard.nextLine();

	    Parser p = new Parser(fileName);
	    CodeWriter cw = new CodeWriter(fileName);



	    while(p.hasMoreCommands()) {
	        p.advance();
	        switch(p.commandType()) {
                case C_PUSH:
                case C_POP:
                    cw.writePushPop(p.commandType(), p.getArg1(), p.getArg2());
                    break;
                case C_ARITHMETIC:
                    cw.writeArithmetic(p.getArg1());
                    break;
                default:
                    break;
            }
        }
        cw.close();
    }
}
