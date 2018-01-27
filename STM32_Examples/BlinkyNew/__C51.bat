@echo off
::This file was created automatically by CrossIDE to compile with C51.
C:
cd "\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\"
"C:\CrossIDE\FlashLoader\BIN\STMFlashLoader.exe" --use-stdout  "C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.c"
if not exist hex2mif.exe goto done
if exist main.ihx hex2mif main.ihx
if exist main.hex hex2mif main.hex
:done
echo done
echo Crosside_Action Set_Hex_File C:\Users\Candice\OneDrive\Pictures\Other\ELEC Year 2\ELEC291 Course Content\Project 2\STM32_Examples\BlinkyNew\main.hex
