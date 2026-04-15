javac -d out -cp "Library/*" atm/ui/*.java atm/database/*.java 
xcopy /s /q /y atm\ui\icons out\atm\ui\icons\

java -cp "out;Library/*" atm.ui.MainFrame