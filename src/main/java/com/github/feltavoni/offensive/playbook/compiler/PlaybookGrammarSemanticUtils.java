package com.github.feltavoni.offensive.playbook.compiler;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class PlaybookGrammarSemanticUtils {
    public static List<String> semanticErrors = new ArrayList<>();
    
    public static void addSemanticError(Token t, String message) {
        int line = t.getLine();
        semanticErrors.add(String.format("Error line %d - %s", line, message));
    }
    
}
