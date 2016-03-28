@echo off
REM Reset build environment.

REM JAR file.
del .\stargate.jar 2>NUL

REM Class file-ok.
forfiles /m "*.class" /s /c "cmd.exe /c del @path 2>NUL" 2>NUL
