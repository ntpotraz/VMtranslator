package edu.miracosta.cs220.ntpotraz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeWriter {
    private BufferedWriter outputFile;
    private String file;

    public CodeWriter(String fileName) {
        file = fileName.substring(0, fileName.indexOf("."));

        String asmName = fileName;
        asmName = asmName.substring(0, asmName.indexOf("."));
        asmName = asmName + ".asm";

        try {
            outputFile = new BufferedWriter(new FileWriter(asmName));
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void close() {
        try {
            outputFile.close();
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void writeArithmetic(String command) {

        try {
            switch (command) {
                case "add":
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("M=D+M");
                    outputFile.newLine();
                    outputFile.write("AD=A+1");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("M=D");
                    outputFile.newLine();
                    break;
                case "sub":

                    break;
                case "neg":

                    break;
                case "eq":
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("D=M");
                    outputFile.newLine();
                    outputFile.write("A=A-1");
                    outputFile.newLine();
                    outputFile.write("D=D-M");
                    outputFile.newLine();
                    outputFile.write("@EQUAL");
                    outputFile.newLine();
                    outputFile.write("D;JEQ");
                    outputFile.newLine();
                    outputFile.write("A=D");
                    outputFile.newLine();
                    outputFile.write("M=FALSE");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("M=D+1");
                    outputFile.newLine();
                    outputFile.write("@EQUALEND");
                    outputFile.newLine();
                    outputFile.write("0;JMP");
                    outputFile.newLine();
                    outputFile.write("(EQUAL)");
                    outputFile.newLine();
                    outputFile.write("A=D");
                    outputFile.newLine();
                    outputFile.write("M=TRUE");
                    outputFile.newLine();
                    outputFile.write("@SP");
                    outputFile.newLine();
                    outputFile.write("M=D+1");
                    outputFile.newLine();
                    outputFile.write("(EQUALEND)");
                    outputFile.newLine();
                    break;
                case "gt":

                    break;
                case "lt":

                    break;
                case "and":

                    break;
                case "or":

                    break;
                case "not":

                    break;

                default:
                    break;
            }
            outputFile.write("//----------------");
            outputFile.newLine();
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void writePushPop(CommandType commandType, String segment, int index) {
        try {
            switch (commandType) {
                case C_POP:

                    switch (segment) {
                        case "local":
                            String address = "@" + index;
                            outputFile.write(address);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@LCL");
                            outputFile.newLine();
                            outputFile.write("A=M+D");
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=A-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();

                            break;
                        case "argument":

                            break;
                        case "this":

                            break;
                        case "that":

                            break;
                        case "temp":

                            break;
                        case "pointer":

                            break;
                        case "static":
                            String fileName = "@" + file + "." + index;
                            outputFile.write(fileName);
                            outputFile.newLine();
                            outputFile.write("D=A");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            outputFile.write("@SP");
                            outputFile.newLine();
                            outputFile.write("AM=A-1");
                            outputFile.newLine();
                            outputFile.write("D=M");
                            outputFile.newLine();
                            outputFile.write("@temp");
                            outputFile.newLine();
                            outputFile.write("A=M");
                            outputFile.newLine();
                            outputFile.write("M=D");
                            outputFile.newLine();
                            break;

                    }
                    break;
                case C_PUSH:

                    switch (segment) {
                        case "constant":
                            String address = "@" + index;
                            if (index == 0 || index == 1 || index == -1) {
                                String binaryIndex = "M=" + index;
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("A=M");
                                outputFile.newLine();
                                outputFile.write(binaryIndex);
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("M=M+1");
                                outputFile.newLine();
                            } else {
                                outputFile.write(address);
                                outputFile.newLine();
                                outputFile.write("D=A");
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.write("A=M");
                                outputFile.newLine();
                                outputFile.write("M=D");
                                outputFile.newLine();
                                outputFile.write("@SP");
                                outputFile.newLine();
                                outputFile.write("M=M+1");
                                outputFile.newLine();
                            }
                            break;
                        case "static":

                            break;
                        case "pointer":

                            break;
                        case "this":

                            break;
                        case "that":

                            break;
                        case "local":

                            break;
                        case "argument":

                            break;
                        case "temp":

                            break;
                        default:
                            break;

                    }

                    break;
                default:
                    break;
            }
            outputFile.write("//----------------");
            outputFile.newLine();
        } catch(IOException e) {
            e.getMessage();
        }
    }

}
