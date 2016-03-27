@echo off
setlocal EnableDelayedExpansion
set mainClass=ballmerpeak.stargate.gui.GameWindow
set javaFiles=
set classFiles=
set jarIncludedFiles=
cd src/main/java/

if "%~1" neq "" set mainClass=%~1
echo Entry point is set to %mainClass%

FOR /R %%f IN (*.java) DO call :addJavaFile %%f
javac -g %javaFiles% 1>NUL

for /f "delims=" %%i in ('forfiles /m "*.class" /s /c "cmd.exe /c echo @relpath"') DO call :addClassFile %%i

jar cfe stargate.jar %mainClass% %classFiles% 1>NUL

move stargate.jar .. 1>NUL
if exist ..\resources\ (call :packageResources)

move ..\stargate.jar ..\..\..\ 1>NUL

goto :eof

:packageResources
cd ..\resources\
	
for /f "delims=" %%i in ('forfiles /m "*.txt" /s /c "cmd.exe /c echo @relpath"') DO call :includeInJar %%i
for /f "delims=" %%i in ('forfiles /m "*.png" /s /c "cmd.exe /c echo @relpath"') DO call :includeInJar %%i

if "%jarIncludedFiles% neq "" jar uf ..\stargate.jar %jarIncludedFiles% 1>NUL

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