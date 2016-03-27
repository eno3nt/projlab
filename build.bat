@echo off
setlocal EnableDelayedExpansion
set javaFiles=
set jarIncludedFiles=
cd src/main/java/

FOR /R %%f IN (*.java) DO call :addJavaFile %%f
javac -g %javaFiles% 1>NUL

for /f "delims=" %%i in ('forfiles /m *.class /s /c "cmd.exe /c echo @relpath"') DO call :addJavaFile %%i

jar cfm stargate.jar ..\Manifest.mf %javaFiles% 1>NUL

move stargate.jar .. 1>NUL

cd ..\resources\

for /f "delims=" %%i in ('forfiles /m *.txt /s /c "cmd.exe /c echo @relpath"') DO call :includeInJar %%i
for /f "delims=" %%i in ('forfiles /m *.png /s /c "cmd.exe /c echo @relpath"') DO call :includeInJar %%i

jar uf ..\stargate.jar %jarIncludedFiles% 1>NUL
move ..\stargate.jar ..\..\..\ 1>NUL

goto :eof

:addJavaFile
echo Packaging %1
set javaFiles=%javaFiles% %1
goto :eof

:includeInJar
echo Packaging %1
set jarIncludedFiles=%jarIncludedFiles% %1
goto :eof