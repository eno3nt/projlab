@echo off
setlocal EnableDelayedExpansion

del .\stargate.jar 2>NUL
del src\main\Manifest.mf 2>NUL
forfiles /m "*.class" /s /c "cmd.exe /c del @path 2>NUL" 2>NUL
