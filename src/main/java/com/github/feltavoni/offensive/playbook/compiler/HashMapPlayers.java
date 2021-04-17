package com.github.feltavoni.offensive.playbook.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapPlayers {
    
    class Player {
        String number;          // Player number. Each one must be unique.
        String role;            // Player role. QBs, WRs, RBs etc.
        String name;            // Player's name. Just for especify errors. Could be removed...
        String route;           // Player's route
        String position;        // Player's positioning in playbook
        boolean main;

        // Player's constructor
        private Player(String number, String role, String name) {
            this.number = number;
            this.role = role;
            this.name = name;
            this.route = null;
            this.position = null;
            this.main = false;
        }
    }
    
    // Declare the hash map roster, with all players...
    private final Map<String, Player> roster;
    
    public HashMapPlayers() {
        this.roster = new HashMap<>();
    }
    
    // Add player to roster
    public void setPlayer(String number, String role, String name) {
        roster.put(number, new Player(number, role, name));
    }
    
    public void setPlayerRoute(String number, String route) {
        this.roster.get(number).route = route;
    }
    
    public void setPlayerPosition(String number, String position) {
        this.roster.get(number).position = position;
    }
    
    public void setMain(String number) {
        this.roster.get(number).main = true;
    }
    
    public boolean checkPlayerExists(String number) {
        return roster.containsKey(number);
    }
    
    public String getRole(String number) {
        return roster.get(number).role;
    }
    
    public String getName(String number) {
        return roster.get(number).name;
    }
    
    public String getRoute(String number) {
        return roster.get(number).route;
    }
    
    public String getPosition(String number) {
        return roster.get(number).position;
    }
    
    public boolean getMain(String number) {
        return roster.get(number).main;
    }
    
    public boolean isPositionUnique(String role, String pos) {
        for (String key: roster.keySet()) {
            if ( roster.get(key).role.equals(role) ) {
                if ( (roster.get(key).position != null) && (roster.get(key).position.equals(pos)) ) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Set<String> getHashMap() {
        return roster.keySet();
    }
}
