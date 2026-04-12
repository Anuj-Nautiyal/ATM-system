javac -d out -cp "lib/*" atm/ui/*.java atm/database/*.java
xcopy /s /q /y atm\ui\icons out\atm\ui\icons\
java -cp "out;lib/*" atm.ui.Login