package com.github.feltavoni.offensive.playbook.compiler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class ImgGenerator extends PlaybookGrammarBaseVisitor<Void> {
    
    HashMapPlayers roster;      // Contains all info of all players
    int x = 0;
    int y = 280;
    String img_name;
    
    public ImgGenerator(String name) {
        this.img_name = name;
    }
    
    @Override 
    public Void visitPlaybook(PlaybookGrammarParser.PlaybookContext ctx) { 
        roster = new HashMapPlayers();
        return super.visitPlaybook(ctx); 
    }
    
    // Get the yard line where to the snap will take part...
    @Override 
    public Void visitYard_line(PlaybookGrammarParser.Yard_lineContext ctx) { 
        x = (int) (9.5 * Integer.parseInt(ctx.NUMBER().getText()) + 125) - (player_size/2);
        return super.visitYard_line(ctx); 
    }
    
    // Add all player to the hash 
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
        }
        return super.visitPlayer(ctx); 
    }
    
    @Override 
    public Void visitStrategy(PlaybookGrammarParser.StrategyContext ctx) { 
        
        ctx.position().forEach((player_pos) -> {
            roster.setPlayerPosition(player_pos.NUMBER().getText(), player_pos.POSITION().getText());
        });
        
        for (PlaybookGrammarParser.RouteContext player_route : ctx.route()) {
            if (player_route.main != null) {
                roster.setMain(player_route.NUMBER().getText());
            }
            roster.setPlayerRoute(player_route.NUMBER().getText(), player_route.ROUTE().getText());
        }
        
        try {
            // After all data has been processed, start drawing image
            createImage(roster, x, y);
        } catch (IOException ex) {
            Logger.getLogger(ImgGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return super.visitStrategy(ctx); 
    }
    
    /*
        DRAWING IMAGE
    */
    
    int player_size = 30;
    int player_spacing = 8;
    int arrow_size = 8;
    int qbX = 0;
    int qbY = 0;
    
    public void createImage(HashMapPlayers roster, int x, int y) throws IOException {
        
        // Importing
        String imagePath = "img\\AmericanFootballField.png";
        BufferedImage myPicture = ImageIO.read(new File(imagePath));
        
        // Creating Graphics
        Graphics2D g = (Graphics2D) myPicture.getGraphics();
        g.setStroke(new BasicStroke(2));
        Font fnormalText = new Font("Times New Roman", Font.BOLD, 13);
        g.setFont(fnormalText);
        g.setColor(Color.BLACK);
        
        // First, search and draw QB...
        for (String key: roster.getHashMap()) {
            if ( roster.getRole(key).equals("QB") ) {
                if ( roster.getPosition(key) != null) {
                    drawQB(g, x, y, key, roster.getRoute(key), roster.getPosition(key), roster.getMain(key));
                }
            }
        }
        
        // After that, draw the rest of the players
        for (String key: roster.getHashMap()) {
            if ( roster.getPosition(key) != null) {
                if ( roster.getRoute(key) != null ) {
                    drawPlayer(g, x, y, roster.getRole(key), key, roster.getRoute(key), roster.getPosition(key), roster.getMain(key));
                } else {
                    drawPlayer(g, x, y, roster.getRole(key), key, "Block", roster.getPosition(key), roster.getMain(key));
                }
            }
        }
        
        try {
            if (ImageIO.write(myPicture, "png", new File(img_name)))
            {
                System.out.println("Succesfully saved");
            }
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    
    /*
        DRAWING ROUTES
    */
    
    public void drawRoute(Graphics2D g, String route, int x, int y) {
        switch (route) {
            case "Slant Right":
                drawSlantRight(g, x, y);
                break;
            case "Slant Left":
                drawSlantLeft(g, x, y);
                break;
            case "Flat Right":
                drawFlatRight(g, x, y);
                break;
            case "Flat Left":
                drawFlatLeft(g, x, y);
                break;
            case "Wheel Right":
                drawWheelRight(g, x, y);
                break;
            case "Wheel Left":
                drawWheelLeft(g, x, y);
                break;
            case "Angle Right":
                drawAngleRight(g, x, y);
                break;
            case "Angle Left":
                drawAngleLeft(g, x, y);
                break;
            case "Inside Right":
                drawInsideRight(g, x, y);
                break;
            case "Inside Left":
                drawInsideLeft(g, x, y);
                break;
            case "Outside Right":
                drawOutsideRight(g, x, y);
                break;
            case "Outside Left":
                drawOutsideLeft(g, x, y);
                break;
            case "Comeback":
                drawComeback(g, x, y);
                break;
            case "Curl":
                drawCurl(g, x, y);
                break;
            case "Out":
                drawOut(g, x, y);
                break;
            case "In":
            case "Dig":
                drawIn_Dig(g, x, y);
                break;
            case "Corner":
                drawCorner(g, x, y);
                break;
            case "Post":
                drawPost(g, x, y);
                break;
            case "Fly":
            case "Go":
                drawFly_Go(g, x, y);
                break;
            case "Block":
                drawBlock(g, x, y);
                break;
            default:
                break;
        }
    }
    
    // Draw Slant Right Route
    public void drawSlantRight(Graphics2D g, int x, int y) {
        x = x + player_size; y = y + (player_size/2);                   // Start route line in the front os the circle
        g.drawLine(x, y, x+40, y);                                      // Draw
        x = x + 40;                                                     // Update new cursor position
        drawArrowLine(g, x, y, x+40, y+80, arrow_size, arrow_size);    // Turn and add a arrow at the end
    }

    // Draw Slant Left Route
    public void drawSlantLeft(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+40, y);
        x = x + 40;
        drawArrowLine(g, x, y, x+40, y-80, arrow_size, arrow_size);
    }

    // Draw Flat In Right
    public void drawFlatRight(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+50, y);
        x = x + 50;
        drawArrowLine(g, x, y, x, y+80, arrow_size, arrow_size);
    }

    // Draw Flat In Left
    public void drawFlatLeft(Graphics2D g, int x, int y) { 
        x = x + player_size; y = y + (player_size/2); 
        g.drawLine(x, y, x+50, y);
        x = x + 50;
        drawArrowLine(g, x, y, x, y-80, arrow_size, arrow_size);
    }

    // Draw Comeback
    public void drawComeback(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+60, y);
        x = x + 60;
        drawArrowLine(g, x, y, x-20, y-20, arrow_size, arrow_size);
    }
    
    // Draw Curl
    public void drawCurl(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+60, y);
        x = x + 60;
        drawArrowLine(g, x, y, x-20, y+20, arrow_size, arrow_size);
    }

    // Draw Wheel Left
    public void drawWheelLeft(Graphics2D g, int x, int y) {  
        x = x + (player_size/2);
        g.drawLine(x, y, x+30, y-70);
        x = x + 30; y = y - 70;
        drawArrowLine(g, x, y, x+100, y, arrow_size, arrow_size);
    }

    // Draw Wheel Right
    public void drawWheelRight(Graphics2D g, int x, int y) {  
        x = x + (player_size/2); y = y + player_size;
        g.drawLine(x, y, x+30, y+70);
        x = x + 30; y = y + 70;
        drawArrowLine(g, x, y, x+100, y, arrow_size, arrow_size);
    }
    
    // Draw Angle Left
    public void drawAngleLeft(Graphics2D g, int x, int y) {  
        x = x + (player_size/2);
        g.drawLine(x, y, x+50, y-50);
        x = x + 50; y = y - 50;
        drawArrowLine(g, x, y, x+50, y+50, arrow_size, arrow_size);
    }

    // Draw Angle Right
    public void drawAngleRight(Graphics2D g, int x, int y) {  
        x = x + (player_size/2); y = y + player_size;
        g.drawLine(x, y, x+50, y+50);
        x = x + 50; y = y + 50;
        drawArrowLine(g, x, y, x+50, y-50, arrow_size, arrow_size);
    }
    
    // Draw Inside Left
    public void drawInsideLeft(Graphics2D g, int x, int y) {  
        x = x + (player_size/2);
        drawArrowLine(g, x, y, x+50, y-20, arrow_size, arrow_size);
    }

    // Draw Inside Right
    public void drawInsideRight(Graphics2D g, int x, int y) {  
        x = x + (player_size/2); y = y + player_size;
        drawArrowLine(g, x, y, x+50, y+20, arrow_size, arrow_size);
    }
    
    // Draw Outside Left
    public void drawOutsideLeft(Graphics2D g, int x, int y) {  
        x = x + (player_size/2);
        drawArrowLine(g, x, y, x+20, y-50, arrow_size, arrow_size);
    }

    // Draw Outside Right
    public void drawOutsideRight(Graphics2D g, int x, int y) {
        x = x + (player_size/2); y = y + player_size;
        drawArrowLine(g, x, y, x+20, y+50, arrow_size, arrow_size);
    }
    
    // Draw Out
    public void drawOut(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+60, y);
        x = x + 60;
        drawArrowLine(g, x, y, x, y-80, arrow_size, arrow_size);
    }
    
    // Draw In/Dig
    public void drawIn_Dig(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+60, y);
        x = x + 60;
        drawArrowLine(g, x, y, x, y+80, arrow_size, arrow_size);
    }

    // Draw Corner
    public void drawCorner(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+60, y);
        x = x + 60;
        drawArrowLine(g, x, y, x+40, y-20, arrow_size, arrow_size);
    }
    
    // Draw Post
    public void drawPost(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+60, y);
        x = x + 60;
        drawArrowLine(g, x, y, x+40, y+20, arrow_size, arrow_size);
    }
    
    // Draw Fly/Go
    public void drawFly_Go(Graphics2D g, int x, int y) {  
        x = x + player_size; y = y + (player_size/2);
        drawArrowLine(g, x, y, x+100, y, arrow_size, arrow_size);
    }

    // Draw Block
    public void drawBlock(Graphics2D g, int x, int y) { 
        x = x + player_size; y = y + (player_size/2);
        g.drawLine(x, y, x+10, y);
        x = x + 10;
        g.drawLine(x, y-5, x, y+5);
    }
    
    // Solution proposed by RubenLaguna - StackOverflow
    public void drawArrowLine(Graphics2D g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
    
    /*
        DRAWING PLAYERS
    */
    
    public void drawPlayer(Graphics2D g, int x, int y, String role, String num, String route, String pos, boolean main) {
        switch (role) {
            case "OT":
                drawOT(g, x, y, num, route, pos);
                break;
            case "OG":
                drawOG(g, x, y, num, route, pos);
                break;
            case "C":
                drawC(g, x, y, num, route, pos);
                break;
            case "QB":
                drawQB(g, x, y, num, route, pos, main);
                break;
            case "TE":
                drawTE(g, x, y, num, route, pos, main);
                break;
            case "WR":
                drawWR(g, x, y, num, route, pos, main);
                break;
            case "RB":
            case "HB":
            case "FB":
                drawRB_HB_FB(g, num, route, pos, main);
                break;
            default:
                break;
        }
    }
    
    // Draw the player circle
    public void drawPlayerCircle(Graphics2D g, int x, int y, String num) {
        g.setColor(Color.BLACK);                                        // All players circle's black.
        g.drawOval(x, y, player_size, player_size);                     // A circle with size (player_size x player_size).
        g.drawString(num, x+5, y+((2*player_size)/3));                  // Write the player's number inside.
    }
    
    // Draw Offensive Tackle
    public void drawOT(Graphics2D g, int x, int y, String num, String route, String pos) {
        switch (pos) {
            case "Left":
                y = y - 2*player_spacing - 2*player_size;                       // The space between the linemen.
                break;
            case "Right":
                y = y + 2*player_spacing + 2*player_size;                       // The space between the linemen.
                break;
        }
        drawPlayerCircle(g, x, y, num);
        drawRoute(g, "Block", x, y);
    }
    
    // Draw Offensive Tackle
    public void drawOG(Graphics2D g, int x, int y, String num, String route, String pos) {
        switch (pos) {
            case "Left":
                y = y - player_spacing - player_size;                       // The space between the linemen.
                break;
            case "Right":
                y = y + player_spacing + player_size;                       // The space between the linemen.
                break;
        }
        drawPlayerCircle(g, x, y, num);
        drawRoute(g, "Block", x, y);
    }
    
    // Center
    public void drawC(Graphics2D g, int x, int y, String num, String route, String pos) {
        drawPlayerCircle(g, x, y, num);
        drawRoute(g, "Block", x, y);
    }
   
    // Tight-End
    public void drawTE(Graphics2D g, int x, int y, String num, String route, String pos, boolean main) {
        switch (pos) {
            case "Lined Left":
                y = y - 3*player_spacing - 3*player_size;     // Above the RT.
                break;
            case "Lined Right":
                y = y + 3*player_spacing + 3*player_size;     // Below the right OT.
                break;
            case "Idented Left":
                x = x - 8;                                    // Idented.
                y = y - 3*player_spacing - 3*player_size;     // Above the RT.
                break;
            case "Idented Right":
                x = x - 8;                                    // Idented.
                y = y + 3*player_spacing + 3*player_size;     // Below the right OT.
                break;
            default:
                break;
        }
        drawPlayerCircle(g, x, y, num);
        if (main) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.YELLOW);
        }
        drawRoute(g, route, x, y);
    }
    
    // Quarter-Back
    public void drawQB(Graphics2D g, int x, int y, String num, String route, String pos, boolean main) {
        if (pos.equals("Lined")) {
            x = x - player_spacing - player_size;         // Behind the C.
            qbX = x; qbY = y;
        } else if (pos.equals("Idented")) {
            x = x - 30 - player_size;                     // 5 yards space.
            qbX = x; qbY = y;
        }
        drawPlayerCircle(g, x, y, num);
        if ((route != null) && (!route.equals("Block"))) {
            g.setColor(Color.RED);
            drawRoute(g, route, x, y);
        }
    }
    
    // Wide-Receivers
    public void drawWR(Graphics2D g, int x, int y, String num, String route, String pos, boolean main) {
        switch (pos) {
            case "Lined Far Left":
                y = y - 3*player_spacing - 3*player_size - 90;         // LG + LT + 100...
                break;
            case "Lined Middle Left":
                y = y - 3*player_spacing - 3*player_size - 60;
                break;
            case "Lined Close Left":
                y = y - 3*player_spacing - 3*player_size - 30;               // Avoiding TE...
                break;
            case "Idented Far Left":
                x = x - player_size - player_spacing;
                y = y - 3*player_spacing - 3*player_size - 90;
                break;
            case "Idented Middle Left":
                x = x - 8;
                y = y - 3*player_spacing - 3*player_size - 60;
                break;
            case "Idented Close Left":
                x = x - 8;
                y = y - 3*player_spacing - 3*player_size - 30;
                break;
            case "Lined Far Right":
                y = y + 3*player_spacing + 3*player_size + 90;
                break;
            case "Lined Middle Right":
                y = y + 3*player_spacing + 3*player_size + 60;
                break;
            case "Lined Close Right":
                y = y + 3*player_spacing + 3*player_size + 30;
                break;
            case "Idented Far Right":
                x = x - 8;
                y = y + 3*player_spacing + 3*player_size + 90;
                break; 
            case "Idented Middle Right":
                x = x - 8;
                y = y + 3*player_spacing + 3*player_size + 60;
                break; 
            case "Idented Close Right":
                x = x - 8;
                y = y + 3*player_spacing + 3*player_size + 30;
                break; 
            default:
                break;
        }
        drawPlayerCircle(g, x, y, num);
        if (main) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.YELLOW);
        }
        drawRoute(g, route, x, y);
    }
    
    // Draw Running-Back / Half-Back / Full-Back based on QB position
    public void drawRB_HB_FB(Graphics2D g, String num, String route, String position, boolean main) {
        int x = 0, y = 0;
        switch (position) {
            case "Left First":
                x = qbX;
                y = qbY - player_spacing - player_size;
                break;
            case "Right First":
                x = qbX;
                y = qbY + player_spacing + player_size;
                break; 
            case "Lined Second":
                x = qbX - player_spacing - player_size;
                y = qbY;
                break;
            case "Left Second":
                x = qbX - player_spacing - player_size;
                y = qbY - player_spacing - player_size;
                break;
            case "Right Second":
                x = qbX - player_spacing - player_size;
                y = qbY + player_spacing + player_size;
                break; 
            case "Lined Third":
                x = qbX - 2*player_spacing - 2*player_size;
                y = qbY;
                break;
            case "Left Third":
                x = qbX - 2*player_spacing - 2*player_size;
                y = qbY - player_spacing - player_size;
                break;
            case "Right Third":
                x = qbX - 2*player_spacing - player_size;
                y = qbY + player_spacing + player_size;
                break; 
            default:
                break;
        }
        drawPlayerCircle(g, x, y, num);
        if (main) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.YELLOW);
        }
        drawRoute(g, route, x, y);
    }
    
}
