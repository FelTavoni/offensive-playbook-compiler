package com.github.feltavoni.offensive.playbook.compiler;

import java.util.Arrays;
import java.util.List;

/*
    Semantic Analyser:
    - Checks if the yard line is between 0 - 100;
    - Checks if players jersey's are unique and fullfilled;
    - Checks if the declared players in 'positions' and 'routes' are corresponding;
*/

public class PlaybookGrammarSemantic extends PlaybookGrammarBaseVisitor<Void> {
 
    HashMapPlayers roster;
    // This array contains the number of roles in the described formation
    int[] formation = new int[9];
    // Main routes control, only 1 is possible!
    int main = 0;
    
    public enum Roles {
        OT,
	OG,
        C,
        TE,
        WR,
        QB,
        RB,
        HB,
        FB
    }
    
    // This function access the effective array position of each role
    public static int getRoleIndex(String role) {
        int ret = 0;
        switch (role) {
            case "OT":
                ret = Roles.OT.ordinal();
                break;
            case "OG":
                ret = Roles.OG.ordinal();
                break;
            case "C":
                ret = Roles.C.ordinal();
                break;
            case "TE":
                ret = Roles.TE.ordinal();
                break;
            case "WR":
                ret = Roles.WR.ordinal();
                break;
            case "QB":
                ret = Roles.QB.ordinal();
                break;
            case "RB":
                ret = Roles.RB.ordinal();
                break;
            case "HB":
                ret = Roles.HB.ordinal();
                break;
            case "FB":
                ret = Roles.FB.ordinal();
                break;
        }
        return ret;
    }
    
    // Declaring the hash for players caracteristics.
    @Override 
    public Void visitPlaybook(PlaybookGrammarParser.PlaybookContext ctx) { 
        roster = new HashMapPlayers();
        return super.visitPlaybook(ctx); 
    }
    
    // Checks if the snap line is between 0 and 100.
    @Override 
    public Void visitYard_line(PlaybookGrammarParser.Yard_lineContext ctx) { 
        if ((Integer.parseInt(ctx.NUMBER().getText()) > 100) || (Integer.parseInt(ctx.NUMBER().getText()) > 100)) {
            PlaybookGrammarSemanticUtils.addSemanticError(ctx.NUMBER().getSymbol(), "The snap must happen between 0 and 100 yards.");
        }
        return super.visitYard_line(ctx); 
    }
    
    // When reading roster, insert players in the hash
    @Override 
    public Void visitPlayer(PlaybookGrammarParser.PlayerContext ctx) { 
        // NUMBER POSITION IDENT+
        String name = null;
        for (int i = 0; i < ctx.IDENT().size(); i++){
            if (i == 0) {
                name = ctx.IDENT(0).getText() + ' ';
            } else {
                name += ctx.IDENT(i).getText() + ' ';
            }
        }
        // Removing space at the end
        name = name.substring(0, name.length() - 1);
        // Check if player's jersey is already in use
        if ( !roster.checkPlayerExists(ctx.NUMBER().getText()) ) {
            roster.setPlayer(ctx.NUMBER().getText(), ctx.ROLE().getText(), name);
        } else {
            PlaybookGrammarSemanticUtils.addSemanticError(ctx.NUMBER().getSymbol(), "Jersey num " + ctx.NUMBER().getText() + " already in use.");
        }
        return super.visitPlayer(ctx); 
    }
    
    // Check following:
    //  - Playmake type
    //  - Quantity of linemans
    //  - Matching formation with players num and positions
    //  - Matching players and routes
    @Override 
    public Void visitStrategy(PlaybookGrammarParser.StrategyContext ctx) { 
        
        // Update the global formation array with user's description
        for (int i = 0; i < ctx.formation().size(); i++) {
            String num = ctx.formation(i).NUMBER().getText();
            String role = ctx.formation(i).ROLE().getText();
            formation[getRoleIndex(role)] = Integer.parseInt(num);
        }
        
        // Check if theres's 11 players
        int sum = Arrays.stream(formation).sum();
        if ((ctx.position().size() != 11) || (sum != 11)) {
            PlaybookGrammarSemanticUtils.addSemanticError(ctx.getStart(), "Only 11 players are possible in football.");
        }
        // Check if there's 2 OT, 2 OG and 2 Linemen
        if ( ( formation[0] != 2 ) && ( formation[1] != 2 ) && ( formation[2] != 2 ) ) {
            PlaybookGrammarSemanticUtils.addSemanticError(ctx.getStart(), "Missing or exceeded linemen.");
        }
        
        // Next, we check the position of each player and their routes
        
        // If the chosen play is pass...
        if (ctx.PLAYMAKE().getText().equals("Pass")) {
            // First, we check the players positioning, if they all match the informed formation.
            checkPositions(ctx.position());
            // QB cannot run...but needs a position
            formation[getRoleIndex("QB")] = 0;
            // Remove lineman (they block)
            formation[0] = 0; formation[1] = 0; formation[2] = 0;
            // Second, we check the routes, same way as the positions.
            checkRoutes(ctx.route());
        // Else if it's a run play, just QB/RB/FB/HB can run, so only 1 route.
        } else {
            checkPositions(ctx.position());
            // Only one route
            if (ctx.route().size() > 1) {
                PlaybookGrammarSemanticUtils.addSemanticError(ctx.route(0).getStart(), "Only one player can run.");
            } else {
                if (ctx.route(0).main != null) {
                    // So if there's only one route, add this player
                    roster.setPlayerRoute(ctx.route(0).NUMBER().getText(), ctx.route(0).ROUTE().getText());
                } else {
                    PlaybookGrammarSemanticUtils.addSemanticError(ctx.route(0).getStart(), "No main route declared.");
                }
            }
        }
        
        return super.visitStrategy(ctx); 
    }
    
    // Check following
    // - If player exists in roster;
    // - If there's still open-spots for his role;
    // - If there's a player occupying same position as another player while in same role;
    // - Check if the position informed is equal to it's role.
    public void checkPositions(List<PlaybookGrammarParser.PositionContext> ctx) {
        // Variables
        int[] formationAux = new int[9];
        System.arraycopy(formation, 0, formationAux, 0, 9);    // A copy of the gloobal array, so we can manipulate it without losing it's values.
        String num = null;                                     // Player number
        String pos = null;                                     // Player role
        
        for (PlaybookGrammarParser.PositionContext player_pos : ctx) {
            num = player_pos.NUMBER().getText();
            pos = player_pos.POSITION().getText();
            // Check if jersey is occupied (player previously declared)
            if ( roster.checkPlayerExists(num) ) {
                // If player role was declared of formation and there's still available open spots
                if ( formationAux[getRoleIndex(roster.getRole(num))] > 0 ) {
                    // Checks if player position is characteristic of his role.
                    if ( checkPosition(roster.getRole(num), pos) ) {
                        // Two players of same role cannot occuppie a same position (Newton...)
                        // Exception for OT and OG...their position is fixed...
                        if ( roster.getRole(num).equals("OT") || roster.getRole(num).equals("OG") || roster.isPositionUnique(roster.getRole(num), pos) ) {
                            roster.setPlayerPosition(num, pos);
                            formationAux[getRoleIndex(roster.getRole(num))]--;
                        } else {
                            PlaybookGrammarSemanticUtils.addSemanticError(player_pos.NUMBER().getSymbol(), "There's already a player on the same position as " + roster.getName(num) + '.');
                        }
                    } else {
                        PlaybookGrammarSemanticUtils.addSemanticError(player_pos.NUMBER().getSymbol(), roster.getName(num) + ", " + roster.getRole(num) + ", can't play " + pos + '.');
                    }
                } else {
                    PlaybookGrammarSemanticUtils.addSemanticError(player_pos.NUMBER().getSymbol(), "No more available options for " + roster.getRole(num) + '.');
                }
            } else {
                PlaybookGrammarSemanticUtils.addSemanticError(player_pos.NUMBER().getSymbol(), "Jersey num " + num + " does not exist.");
            }
        }
        
    }
    
    // Check following
    // - If player exists in roster
    // - If there's still open-spots for his role
    // - If player wasn't positioned
    public void checkRoutes(List<PlaybookGrammarParser.RouteContext> ctx) {
        // Variables
        int[] formationAux = new int[9];
        System.arraycopy(formation, 0, formationAux, 0, 9);   // A copy of the gloobal array, so we can manipulate it without losing it's values.
        String num = null;                                     // Player number
        String route = null;                                   // Player route
        
        for (PlaybookGrammarParser.RouteContext player_route : ctx) {
            num = player_route.NUMBER().getText();
            route = player_route.ROUTE().getText();
            // Check if jersey is occupied (player previously declared)
            if ( roster.checkPlayerExists(num) ) {
                // If player role was declared of formation and there's still available open spots
                if ( formationAux[getRoleIndex(roster.getRole(num))] > 0 ) {
                    // Every player, to run a route, must be positioned
                    if ( roster.getPosition(num) != null ) {
                        // Priority option on a play
                        if (player_route.main != null) {
                            // Only one main is possible per playboook
                            if (main == 0) {
                                main++;
                                roster.setPlayerRoute(num, route);
                                formationAux[getRoleIndex(roster.getRole(num))]--;
                            } else {
                                PlaybookGrammarSemanticUtils.addSemanticError(player_route.NUMBER().getSymbol(), "Only one \'main\' route is possible.");
                            }
                        // If there's no main, then just add the player.
                        } else {
                            roster.setPlayerRoute(num, route);
                            formationAux[getRoleIndex(roster.getRole(num))]--;
                        }
                    } else {
                        PlaybookGrammarSemanticUtils.addSemanticError(player_route.NUMBER().getSymbol(), roster.getName(num) + " wasn't positioned.");
                    }
                } else {
                    PlaybookGrammarSemanticUtils.addSemanticError(player_route.NUMBER().getSymbol(), "No more available options for " + roster.getRole(num) + '.');
                }
            } else {
                PlaybookGrammarSemanticUtils.addSemanticError(player_route.NUMBER().getSymbol(), "Jersey num " + num + " does not exist.");
            }
        }
        
        // Check if the playbook has a main route
        if (main == 0) {
            PlaybookGrammarSemanticUtils.addSemanticError(ctx.get(0).getStart(), "No main route declared.");
        }
    }
    
    /*
        CHECKING POSITIONS OF EACH ROLE
    */
    
    public boolean checkPosition(String role, String pos) {
        boolean ret = false;
        switch (role) {
            case "OT":
            case "OG":
                ret = checkOTOGPosition(pos);
                break;
            case "C":
                ret = checkCPosition(pos);
                break;
            case "QB":
                ret = checkQBPosition(pos);
                break;
            case "TE":
                ret = checkTEPosition(pos);
                break;
            case "WR":
                ret = checkWRPosition(pos);
                break;
            case "RB":
            case "FB": 
            case "HB":
                ret = checkRBHBFBPosition(pos);
                break;
        }
        return ret;
    }
    
    public boolean checkCPosition(String pos) {
        boolean ret = false;
        switch (pos) {
            case "Center":
                ret = true;
                break;
        }
        return ret;
    }
    
    public boolean checkOTOGPosition(String pos) {
        boolean ret = false;
        switch (pos) {
            case "Left":
                ret = true;
                break;
            case "Right":
                ret = true;
                break;
        }
        return ret;
    }
    
    public boolean checkQBPosition(String pos) {
        boolean ret = false;
        switch (pos) {
            case "Idented":
                ret = true;
                break;
            case "Lined":
                ret = true;
                break;
        }
        return ret;
    }
    
    // Checks if the TEs position is valid.
    public boolean checkTEPosition(String pos) {
        boolean ret = false;
        switch (pos) {
            case "Lined Left":
                ret = true;
                break;
            case "Lined Right":
                ret = true;
                break;
            case "Idented Left":
                ret = true;
                break;
            case "Idented Right":
                ret = true;
                break;
        }
        return ret;
    }
    
    // Checks if the WRs position is valid.
    public boolean checkWRPosition(String pos) {
        boolean ret = false;
        switch (pos) {
            case "Lined Far Left":
                ret = true;
                break;
            case "Lined Far Right":
                ret = true;
                break;
            case "Lined Middle Left":
                ret = true;
                break;
            case "Lined Middle Right":
                ret = true;
                break;
            case "Lined Close Left":
                ret = true;
                break;
            case "Lined Close Right":
                ret = true;
                break;
            case "Idented Far Left":
                ret = true;
                break;
            case "Idented Far Right":
                ret = true;
                break;
            case "Idented Middle Left":
                ret = true;
                break;
            case "Idented Middle Right":
                ret = true;
                break;
            case "Idented Close Left":
                ret = true;
                break;
            case "Idented Close Right":
                ret = true;
                break;
        }
        return ret;
    }
        
    // Checks if the RB/HB/FBs position is valid.
    public boolean checkRBHBFBPosition(String pos) {
        boolean ret = false;
        switch (pos) {
            case "Lined First":
                ret = true;
                break;
            case "Left First":
                ret = true;
                break;
            case "Right First":
                ret = true;
                break;
            case "Lined Second":
                ret = true;
                break;
            case "Left Second":
                ret = true;
                break;
            case "Right Second":
                ret = true;
                break;
            case "Lined Third":
                ret = true;
                break;
            case "Left Third":
                ret = true;
                break;
            case "Right Third":
                ret = true;
                break;
        }
        return ret;
    }
}
