ECHO OFF
ECHO ----------
ECHO Test Begin
ECHO ----------

ECHO Testing CurlFlat.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\CurlFlat.txt tests\in\CurlFlat.png

ECHO Testing HailMary.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\HailMary.txt tests\in\HailMary.png

ECHO Testing QuickSlant.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\QuickSlant.txt tests\in\QuickSlant.png

ECHO Testing Shotgun4WR.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Shotgun4WR.txt tests\in\Shotgun4WR.png

ECHO Testing OutsideRun.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\OutsideRun.txt tests\in\OutsideRun.png

ECHO Testing Error_MissingRosterTE.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Error_MissingRosterTE.txt tests\in\Error_MissingRosterTE.png

ECHO Testing Error_NoMainRoute.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Error_NoMainRoute.txt tests\in\Error_NoMainRoute.png

ECHO Testing Error_PlayersWithSamePosition.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Error_PlayersWithSamePosition.txt tests\in\Error_PlayersWithSamePosition.png

ECHO Testing Error_WrongYardLine.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Error_WrongYardLine.txt tests\in\Error_WrongYardLine.png

ECHO Testing Error_WRNotPositioned.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Error_WRNotPositioned.txt tests\in\Error_WRNotPositioned.png

ECHO Testing Error_WRWithNoRoute.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Error_WRWithNoRoute.txt tests\in\Error_WRWithNoRoute.png

ECHO Testing Error_RunWith2Routes.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Error_RunWith2Routes.txt tests\in\Error_RunWith2Routes.png

ECHO Testing Error_UndefinedRoute.txt
START /WAIT java -jar target\offensive-playbook-compiler-1.0-SNAPSHOT-jar-with-dependencies.jar tests\in\Error_UndefinedRoute.txt tests\in\Error_UndefinedRoute.png

ECHO -------------------
ECHO Checking Results...
ECHO -------------------

if exist "tests\in\CurlFlat.png" ( ECHO "CurlFlat Success" ) else ( ECHO "CurlFlat Failed" ) 
if exist "tests\in\HailMary.png" ( ECHO "HailMary Success" ) else ( ECHO "HailMary Failed" ) 
if exist "tests\in\QuickSlant.png" ( ECHO "QuickSlant Success" ) else ( ECHO "QuickSlant Failed" ) 
if exist "tests\in\Shotgun4WR.png" ( ECHO "Shotgun4WR Success" ) else ( ECHO "Shotgun4WR Failed" ) 
if exist "tests\in\OutsideRun.png" ( ECHO "OutsideRun Success" ) else ( ECHO "OutsideRun Failed" ) 

FC /A "tests\in\Error_MissingRosterTE.out" "tests\out\MissingRosterTE.out" > nul
if errorlevel 1 ( ECHO "Error_MissingRosterTE Failed" ) else ( ECHO "Error_MissingRosterTE Success" )
FC "tests\in\Error_NoMainRoute.out" "tests\out\NoMainRoute.out" > nul
if errorlevel 1 ( ECHO "Error_NoMainRoute Failed" ) else ( ECHO "Error_NoMainRoute Success" )
FC "tests\in\Error_PlayersWithSamePosition.out" "tests\out\PlayersWithSamePosition.out" > nul
if errorlevel 1 ( ECHO "Error_PlayersWithSamePosition Failed" ) else ( ECHO "Error_PlayersWithSamePosition Success" )
FC "tests\in\Error_WrongYardLine.out" "tests\out\WrongYardLine.out" > nul
if errorlevel 1 ( ECHO "Error_WrongYardLine Failed" ) else ( ECHO "Error_WrongYardLine Success" )
FC "tests\in\Error_WRNotPositioned.out" "tests\out\WRNotPositioned.out" > nul
if errorlevel 1 ( ECHO "Error_WRNotPositioned Failed" ) else ( ECHO "Error_WRNotPositioned Success" )
FC "tests\in\Error_WRWithNoRoute.out" "tests\out\WRWithNoRoute.out" > nul
if errorlevel 1 ( ECHO "Error_WRWithNoRoute Failed" ) else ( ECHO "Error_WRWithNoRoute Success" )
FC "tests\in\Error_RunWith2Routes.out" "tests\out\RunWith2Routes.out" > nul
if errorlevel 1 ( ECHO "Error_RunWith2Routes Failed" ) else ( ECHO "Error_RunWith2Routes Success" )
FC "tests\in\Error_UndefinedRoute.out" "tests\out\UndefinedRoute.out" > nul
if errorlevel 1 ( ECHO "Error_UndefinedRoute Failed" ) else ( ECHO "Error_UndefinedRoute Success" )

ECHO -------------
ECHO Test Finished
ECHO -------------

ECHO PAUSE