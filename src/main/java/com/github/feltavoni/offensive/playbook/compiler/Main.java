package com.github.feltavoni.offensive.playbook.compiler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Main {

    public static void main(String args[]) throws IOException {

        // Execute Lexer...
        if (executeLexer(args[0])) {
            // Execute Parser...
            if (executeParser(args[0])) {
                executeSemanticAndImgGenerator(args[0], args[1]);
            }
        }
            
    }

    // Execute Lexer. It's necessary to redeclare the CharStrem, so the pointer does not affect future executions
    public static boolean executeLexer(String file) throws IOException {

        String file_name = file.substring(0, file.length() - 4);
        boolean ret = true;
        
        try (PrintWriter pw = new PrintWriter(new File(file_name + ".out"))) {
            
            CharStream cs = CharStreams.fromFileName(file);
            PlaybookGrammarLexer lex = new PlaybookGrammarLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lex);

            Token t = null;
            String tokenName;

            // While there's tokens to be read, read them...
            while ((t = lex.nextToken()).getType() != Token.EOF) {
                // Get token name
                tokenName = PlaybookGrammarLexer.VOCABULARY.getSymbolicName(t.getType());
                if (tokenName == null) {
                    // System.out.println("<" + PlaybookGrammarLexer.VOCABULARY.getDisplayName(t.getType()) + "," + t.getText() + ">");
                    continue;
                // If the token 'ERROR' has been detected, break execution...
                } else if (tokenName.equals("ERROR")) {
                    pw.println("Line " + t.getLine() + ": Non identified symbol \'"  + t.getText() + "\'\n");
                    ret = false;
                    return false;
                }
                // Printing read tokens.
                // System.out.println("<" + PlaybookGrammarLexer.VOCABULARY.getDisplayName(t.getType()) + "," + t.getText() + ">");
            }

            return ret;
        }
    }
    
    // Execute Lexer. It's again necessary to redeclare the CharStrem, so the pointer does not affect future executions (Sematic is next...)
    public static boolean executeParser(String file) throws IOException {
        
        String file_name = file.substring(0, file.length() - 4);
        
        try (PrintWriter pw = new PrintWriter(new File(file_name + ".out"))) {
            // CharStream Init, Lexer and it's Tokens
            CharStream cs = CharStreams.fromFileName(file);
            PlaybookGrammarLexer lex = new PlaybookGrammarLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lex);

            // Parser Init
            PlaybookGrammarParser parser = new PlaybookGrammarParser(tokens);

            // Remove default error listener and declares a new one (personalized)
            parser.removeErrorListeners();
            ErrorListener mcel = new ErrorListener(pw);
            parser.addErrorListener(mcel);

            try {
                // Call parser for execution, starting at 'playbook'
                parser.playbook();
                return true;
            } catch (Exception e) {
                // In case of excetion throwed by the parser, detects here and stops execution
                return false;
            }
        }
    }
    
    public static void executeSemanticAndImgGenerator(String file, String img_name) throws IOException {
        
        // Creating this file so output can be deleted.
        String file_name = file.substring(0, file.length() - 4);
        
        try (PrintWriter pw = new PrintWriter(new File(file_name + ".out"))) {
            CharStream cs = CharStreams.fromFileName(file);
            PlaybookGrammarLexer lexer = new PlaybookGrammarLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            PlaybookGrammarParser parser = new PlaybookGrammarParser(tokens);
            PlaybookGrammarParser.PlaybookContext tree = parser.playbook();
            PlaybookGrammarSemantic semantic = new PlaybookGrammarSemantic();
            semantic.visitPlaybook(tree);
            PlaybookGrammarSemanticUtils.semanticErrors.forEach((s) -> pw.println(s));

            if(PlaybookGrammarSemanticUtils.semanticErrors.isEmpty()) {
                ImgGenerator agc = new ImgGenerator(img_name);
                agc.visitPlaybook(tree);
            }
        }
    }
   
    
}
