@echo off
setlocal EnableDelayedExpansion
set mainClass=ballmerpeak.stargate.gui.GameWindow
set javaFiles=
set classFiles=
set jarIncludedFiles=
cd src/main/java/
echo "%~1"
if "%~1" neq "" set mainClass=%~1
echo Generating manifest for entry point: %mainClass%
echo Main-Class: %mainClass% > ..\Manifest.mf
echo( >> ..\Manifest.mf

FOR /R %%f IN (*.java) DO call :addJavaFile %%f
javac -g %javaFiles% 1>NUL

for /f "delims=" %%i in ('forfiles /m "*.class" /s /c "cmd.exe /c echo @relpath"') DO call :addClassFile %%i

jar cfm stargate.jar ..\Manifest.mf %classFiles% 1>NUL

move stargate.jar .. 1>NUL

cd ..\resources\

for /f "delims=" %%i in ('forfiles /m "*.txt" /s /c "cmd.exe /c echo @relpath"') DO call :includeInJar %%i
for /f "delims=" %%i in ('forfiles /m "*.png" /s /c "cmd.exe /c echo @relpath"') DO call :includeInJar %%i

jar uf ..\stargate.jar %jarIncludedFiles% 1>NUL
move ..\stargate.jar ..\..\..\ 1>NUL

echo Included manifest:
echo ------------------
type ..\Manifest.mf
echo --End of Manifest-

goto :eof

:addJavaFile
echo Compiling %1
set javaFiles=%javaFiles% %1
goto :eof

:addClassFile
echo Packaging class %1
set classFiles=%classFiles% %1
goto :eof

:includeInJar
echo Packaging resource %1
set jarIncludedFiles=%jarIncludedFiles% %1
goto :eof