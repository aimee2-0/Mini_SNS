@echo off

REM Run from: c:\tomcat10\webapps\Guys_Into_Code (or double-click this .bat in that folder)
set "BASEDIR=%~dp0"
cd /d "%BASEDIR%"


REM Use the Oracle JDBC JAR inside this app's WEB-INF\lib (ojdbc7.jar)
set "OJDBC=%BASEDIR%WEB-INF\lib\ojdbc7.jar"
if not exist "%OJDBC%" (
    echo ERROR: Oracle JDBC JAR not found at %OJDBC%
    echo Please add ojdbc7.jar or ojdbc11.jar to WEB-INF\lib and run again.
    pause
    exit /b 1
)

set "CLASSES=%BASEDIR%WEB-INF\classes"
set "SRC_DB=%BASEDIR%WEB-INF\src\db\DBAccess.java"
set "SRC_LOADER=%BASEDIR%WEB-INF\src\testdata\LoadTestImages.java"
set "SERVLET_API=c:\tomcat10\lib\servlet-api.jar"

if not exist "%CLASSES%" mkdir "%CLASSES%"

if not exist "%SRC_LOADER%" (
    echo ERROR: File not found: %SRC_LOADER%
    echo Make sure you run this batch from the Guys_Into_Code folder.
    pause
    exit /b 1
)

echo Compiling...
javac -encoding UTF-8 -d "%CLASSES%" "%SRC_DB%"
if errorlevel 1 (
    echo Compile failed: DBAccess
    pause
    exit /b 1
)


REM Compile image/servlet classes used by the web app (so profile images work)
if not exist "%SERVLET_API%" (
    echo ERROR: servlet-api.jar not found at %SERVLET_API%
    echo Please adjust SERVLET_API path in this .bat file.
    pause
    exit /b 1
)

javac -encoding UTF-8 -cp "%CLASSES%;%SERVLET_API%" -d "%CLASSES%" ^
    "%BASEDIR%WEB-INF\src\image\UserImage.java" ^
    "%BASEDIR%WEB-INF\src\image\UserImageServlet.java" ^
    "%BASEDIR%WEB-INF\src\image\QAPostImage.java" ^
    "%BASEDIR%WEB-INF\src\image\QAPostImageServlet.java" ^
    "%BASEDIR%WEB-INF\src\image\SNSPostImage.java" ^
    "%BASEDIR%WEB-INF\src\image\SNSPostImageServlet.java" ^
    "%BASEDIR%WEB-INF\src\prof\ProfileImageServlet.java" ^
    "%BASEDIR%WEB-INF\src\sns\SNSview.java" ^
    "%BASEDIR%WEB-INF\src\qa\QAview.java" ^
    "%BASEDIR%WEB-INF\src\prof\UserActivity.java"
if errorlevel 1 (
    echo Compile failed: image/prof servlets
    pause
    exit /b 1
)

javac -encoding UTF-8 -cp "%CLASSES%" -d "%CLASSES%" "%SRC_LOADER%"
if errorlevel 1 (
    echo Compile failed: LoadTestImages
    pause
    exit /b 1
)

echo Running image loader...
java -cp "%CLASSES%;%OJDBC%" testdata.LoadTestImages

echo.
pause