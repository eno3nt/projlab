@echo off
REM Compile and package files into a JAR.

REM Variable declarations.
set mainClass=NOTSET
set javaFiles=
set classFiles=
set jarIncludedFiles=
cd src/main/java/

REM Did we receive the entry point parameter?
if "%~1" neq "" set mainClass=%~1
if %mainClass%==NOTSET GOTO ERROR_noEntryPoint
echo Entry point is set to "%mainClass%"

REM Look for and compile Java source files.
FOR /R %%f IN (*.java) DO call :addJavaFile %%f
javac -g %javaFiles% 1>NUL

goto :end

for /f "delims=" %%i in ('forfiles /m "*.class" /s /c "cmd.exe /c echo @relpath"') DO call :addClassFile %%i

REM We are not building JARs in the current release,
REM but if needed, just comment the line below.
goto :end


REM Build JAR.
jar cfe stargate.jar %mainClass% %classFiles% 1>NUL

move stargate.jar .. 1>NUL

REM If there are additional resources in the src/main/ folder, package them in the JAR.
if exist ..\resources\ (call :packageResources)

REM Move JAR to project root.
move ..\stargate.jar ..\..\..\ 1>NUL

goto :end

:packageResources
cd ..\resources\

REM Resources can be in PNG (Graphical assets) or TXT (Map files) format. 
for /f "delims=" %%i in ('forfiles /m "*.txt" /s /c "cmd.exe /c echo @relpath"') DO call :includeInJar %%i
for /f "delims=" %%i in ('forfiles /m "*.png" /s /c "cmd.exe /c echo @relpath"') DO call :includeInJar %%i

REM Update package.
if "%jarIncludedFiles% neq "" jar uf ..\stargate.jar %jarIncludedFiles% 1>NUL
goto :eof
:ERROR_noEntryPoint
echo Entry point not set
echo If you are trying to build the skeleton, please run "build-skeleton.bat"
goto :end
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
:end
cd ..\..\..\