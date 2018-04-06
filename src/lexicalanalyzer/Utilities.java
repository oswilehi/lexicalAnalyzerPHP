/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Oscar
 */
public class Utilities {
    public static String removeExtension (String word){
        char[] wordChar = word.toCharArray();
        String[] wordToChange = new String[word.length()];
        String wordWithoutExtension = "";
        for (int i = 0; i < word.length(); i++){
            wordToChange[i] = Character.toString(wordChar[i]);
            if (wordToChange[i].equals("."))
                break;
            else
                wordWithoutExtension = wordWithoutExtension + wordToChange[i];
        }    
       return wordWithoutExtension; 
    }
    public static void analyzeFile(JTextArea jTextArea2, JTextArea jTextArea1) throws FileNotFoundException, IOException{
        LinkedList<String> listOfErrors = new LinkedList<String>();
        File fichero = new File ("filePHP.txt");       
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(jTextArea2.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(phpFrameAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reader reader = new BufferedReader(new FileReader("filePHP.txt"));

        lexicalRules lexer = new lexicalRules(reader);
        String resultado="";
        while (true){
            Token token = lexer.yylex();
            
            if (token == null)
                break;
            
            
            switch (token){
                case WHITESPACE:
                    System.out.print(lexer.lexeme);
                    jTextArea1.append(lexer.lexeme);
                    break;                    
                case LOWER_CASE_WORDS:
                    System.out.print(lexer.lexeme.toLowerCase());
                    jTextArea1.append(lexer.lexeme.toLowerCase());
                    break;
                case CONSTANTS_DEFINE:
                    System.out.print(lexer.lexeme);
                    int firtSimpleComma = lexer.lexeme.indexOf("'");
                    int firtDoubleComma = lexer.lexeme.indexOf('"');
                    jTextArea1.append(Utilities.constanstDefine(firtSimpleComma, firtDoubleComma, lexer.lexeme));
                    break;    
                case SYMBOLS:
                    System.out.print(lexer.lexeme);
                    jTextArea1.append(lexer.lexeme);
                    break;
                case TYPES_VAL:
                    System.out.print(lexer.lexeme);
                    jTextArea1.append(lexer.lexeme);
                    break;
                case COMMENTS:
                    System.out.print(lexer.lexeme);
                    jTextArea1.append(lexer.lexeme);
                    break;
                case VAR:
                    System.out.print(lexer.lexeme);
                    jTextArea1.append(lexer.lexeme);
                    break;
                case TYPES_OP:
                    System.out.print(lexer.lexeme);
                    jTextArea1.append(lexer.lexeme);
                    break;
                case ID:
                    System.out.print(lexer.lexeme);
                    jTextArea1.append(lexer.lexeme);
                    break;   
                case UPPER_CASE_VAR_CONST:                    
                    System.out.print(lexer.lexeme);
                    jTextArea1.append(lexer.lexeme.toUpperCase());
                    break;
                case ACCESS_DB:
                    System.out.print(lexer.lexeme);
                    int firstComma = lexer.lexeme.indexOf("'");
                    int lastComma = lexer.lexeme.lastIndexOf("'");
                    int parenthesis = lexer.lexeme.indexOf("[");
                    
                    String variable="";
                    char[] lexeme = lexer.lexeme.toCharArray();                   
                    for (int i = 0; i < parenthesis; i++)
                        variable = variable + lexeme[i];

                    String dbAccessField = lexer.lexeme.substring(firstComma+1, lexer.lexeme.length()-2);
                    jTextArea1.append(variable + "['"+dbAccessField.toUpperCase()+"']");
                    break;
                case ERROR:
                    String[] error = lexer.lexeme.split(",");
                    String tokenThatFailed = error[1];
                    String line = error[0];
                    System.out.print("ERROR"  + lexer.lexeme);
                    listOfErrors.add("Error, no se reconocio el siguiente token " + tokenThatFailed + " en la linea " + line);
                    break;
                default:
                    resultado=resultado;
            }
        }
        if (!listOfErrors.isEmpty()){
            jTextArea1.setText("");
            while(!listOfErrors.isEmpty())
                jTextArea1.append(listOfErrors.removeFirst() + "\n");
        }
            
    }
    
    public static String constanstDefine(int simpleComma, int doubleComma, String word){
        int indexOfComma;
        String correctConstant = "";
        if (simpleComma == -1)
            indexOfComma = doubleComma+1;
        else
            indexOfComma = simpleComma+1;
        
        char[] wordArray = word.toCharArray();
        
        for(int i = 0; i < indexOfComma; i++)
            correctConstant = correctConstant + wordArray[i];
        
        int c = indexOfComma;
        while(!Character.toString(wordArray[c]).equals("'") && !Character.toString(wordArray[c]).equals('"')){
            correctConstant = correctConstant + Character.toString(wordArray[c]).toUpperCase();
            c++;
        }
        
        for(int i = c; i < wordArray.length; i++)
            correctConstant = correctConstant + wordArray[i];
               
        return correctConstant;
    }
}
