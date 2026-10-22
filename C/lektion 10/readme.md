# Template för wokwi-projekt

## Viktiga komponenter
- wokwi.toml
- diagram.json
- .vscode/c_cpp_properties.json
- .vscode/settings.json
- makefile


### wokwi.toml
```bash
[wokwi]
version = 1
firmware = "main.hex"
elf = "main.elf"
```
För att wokwi ska veta vilka filer som ska laddas in och simuleras måste vi peka på dem med hjälp av configureringsfilen wokwi.toml! Se alltid till att firmware pekar, har rätt filväg, till din *.hex-fil, likaså samma för elf att den pekar på *.elf-filen.

### diagram.json
Denna fil innehåller komponenterna i ditt projekt och kan ritas upp på wokwi:s hemsida och kopieras till en fil vars namn måste vara diagram.json för att kunna simuleras. Går att högerklicka och öppna med en text-editor och kopiera över om man behöver göra ändringar.

### .vscode/c_cpp_properties.json
Den här filen används för att underlätta skrivande av kod och linter-hjälp då den specificerar vilken katalog vi syftar på när vi kör include och annat. Utan den så kommer projektet fortfarande kunna kompileras men vi får massa errors i vår kod som säger att vi inte kan hitta paketen osv.

### .vscode/settings.json
Samma som ovan typ

### makefile
Vi behöver en annorlunda makefile nu så vi specificerar vad som ska skapas och vart, samt andra viktiga konfigureringar så som klockhastighet och mcu o.s.v.


## What to be done?
1. klona projektet
2. installera wokwi extension i vs-code
3. modifier eventuellt c_cpp_properites.json utifall ni har lagt avr någon annanstans
4. kör makefile 
5. klicka på diagram.json och sedan play-knappen
6. Då bör simuleringen börja och de olika led-lamporna bör blinka lite knasigt (då fungerar det) 

Allternativ är att kompilera på datorn och ladda upp elf/hex-filen till wokwi och köra simuleringen i webbläsaren