@SET BACKUPPATH=%CLASSPATH%

@REM SETUP linked valumes here
@SET FULLDIR=./

@SET NEWPATH=%FULLDIR%/jinput.jar
@SET NEWPATH=%NEWPATH%;%FULLDIR%/jar/lwjgl.jar
@SET NEWPATH=%NEWPATH%;%FULLDIR%/jar/lwjgl_devil.jar
@SET NEWPATH=%NEWPATH%;%FULLDIR%/jar/lwjgl_fmod3.jar
@SET NEWPATH=%NEWPATH%;%FULLDIR%/jar/lwjgl_test.jar
@SET NEWPATH=%NEWPATH%;%FULLDIR%/jar/lwjgl_util.jar
@SET NEWPATH=%NEWPATH%;%FULLDIR%/jar/lwjgl_util_applet.jar


@SET CLASSPATH=%NEWPATH%;%CLASSPATH%;.
@echo Loading Game.....
@java -Djava.library.path="%FULLDIR%/win32" game.MainGameLoop 

@SET CLASSPATH=%BACKUPPATH%
@REM  -cp jar  -splash:images\splash.bmp -Djava.library.path="C:\java\311_LWJGL_Texture\win32"
@REM Documents and Settings/z/Desktop/work