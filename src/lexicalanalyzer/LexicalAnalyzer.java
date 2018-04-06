/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalanalyzer;

import java.io.File;

/**
 *
 * @author Oscar
 */
public class LexicalAnalyzer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String jflexString = "/lexicalAnalyzerPHP/../src/lexicalanalyzer/lexicalRules.jflex";
        File tempFile = new File(jflexString);
        
        generateJavaFile(jflexString);
        tempFile.delete();
        phpFrameAnalyzer analyzerWindow = new phpFrameAnalyzer();
        analyzerWindow.setVisible(true);
    }
    
    public static void generateJavaFile(String path){
        File file = new File(path);
        jflex.Main.generate(file);
    }
}
