@echo off
title SGF Demo - Inicializando entorno limpio

echo ================================
echo  Limpiando procesos previos...
echo ================================

taskkill /F /IM java.exe >nul 2>&1
taskkill /F /IM javaw.exe >nul 2>&1

timeout /t 2 >nul

echo ================================
echo  Verificando puerto 8000...
echo ================================

netstat -ano | findstr :8000 >nul
if %errorlevel%==0 (
    echo [ERROR] El puerto 8000 sigue ocupado.
    echo Cerralo manualmente antes de continuar.
    pause
    exit /b
)

echo ================================
echo  Levantando servidor central...
echo ================================

start "Servidor Central" cmd /k java -jar servidor-central-1.0-SNAPSHOT.jar

echo Esperando inicializacion del servidor...
timeout /t 3 >nul

echo ================================
echo  Levantando operadores...
echo ================================

start "" javaw -jar panel-operador-1.0-SNAPSHOT.jar 1
start "" javaw -jar panel-operador-1.0-SNAPSHOT.jar 2

echo ================================
echo  Levantando monitor...
echo ================================

start "" javaw -jar monitor-visualizacion-1.0-SNAPSHOT.jar

echo ================================
echo  Levantando terminales...
echo ================================

start "" javaw -jar terminal-registro-1.0-SNAPSHOT.jar
start "" javaw -jar terminal-registro-1.0-SNAPSHOT.jar
start "" javaw -jar terminal-registro-1.0-SNAPSHOT.jar

echo ================================
echo  SGF listo para demo
echo ================================

pause