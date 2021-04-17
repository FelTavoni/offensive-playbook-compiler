grammar PlaybookGrammar;

// LEXER

// Playbook keywords.
KEYWORDS
    :   'Roster' | 'Yard Line' | 'Formation' | 'Play Type' | 'Positions' | 'Routes' | 'Main'
    ;

// Player's number.
NUMBER
    :   ('0'..'9')+
    ;

// Player's positions.
ROLE
    :   'OT' | 'OG' | 'C' | 'QB' | 'TE' | 'WR' | 'RB' | 'HB' | 'FB'
    ;

// Playbook main play
PLAYMAKE
    :   'Pass' | 'Run'
    ;

// Player's routes
ROUTE
    :   'Slant Left'   | 'Slant Right'  |
        'Flat Left'    | 'Flat Right'   |
        'Wheel Left'   | 'Wheel Right'  |
        'Angle Left'   | 'Angle Right'  |
        'Inside Left'  | 'Inside Right' |
        'Outside Left' | 'Outside Right'|
        'Comeback'     |
        'Curl'         |
        'Out'          | 'In'           | 'Dig' |
        'Corner'       |
        'Post'         |
        'Fly'          | 'Go'           |
        'Block'
    ;

// Available positions
POSITION
// WRs
    :   'Lined Far Left'    | 'Lined Far Right'    | 
        'Lined Middle Left' | 'Lined Middle Right' | 
        'Lined Close Left'  | 'Lined Close Right'  |
        'Idented Far Left'    | 'Idented Far Right'    | 
        'Idented Middle Left' | 'Idented Middle Right' | 
        'Idented Close Left'  | 'Idented Close Right'  |
// RBs
        'Left First' | 'Right First' |
        'Lined Second' | 'Left Second' | 'Right Second' |
        'Lined Third' | 'Left Third' | 'Right Third' |
// TEs
        'Lined Left' | 'Lined Right' |
        'Idented Left'  | 'Idented Right' |
// QBs
        'Idented' | 'Lined' |
// Linemen
        'Left' | 'Right' | 'Center'
    ;

// Players, position and routes identifiers.
IDENT
    :   ('a'..'z'|'A'..'Z')+
    ;

// Delimitators - Colons and parentheses - to be skipped...
DELIM	
    :   ( ':' | '(' | ')' ) {skip();}
    ;

// Whitespaces (also to be skipped...)
WS  
    :   ( ' ' | '\t' | '\r' | '\n') {skip();}
    ;

// If not any of above, it's an error.
ERROR
    :   .
    ;

// PARSER

playbook
    :   roster yard_line strategy
    ;

roster
    :   'Roster:' (player)*
    ;

player
    :   NUMBER ROLE (IDENT)+
    ;

yard_line
    :   'Yard Line:' NUMBER
    ;

strategy
    :   'Play Type:' PLAYMAKE 'Formation:' formation+ 'Positions:' (position)+ 'Routes:' (route)+
    ;

formation
    :   NUMBER ROLE
    ;

position
    :   NUMBER POSITION
    ;

route
    :   NUMBER ('(' main='Main' ')')? ROUTE
    ;
