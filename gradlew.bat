@rem Gradle startup script for Windows.
@rem Minimal stand-in — see README for wrapper-jar note.

@echo off
set DIR=%~dp0
java -Xmx64m -Xms64m -classpath "%DIR%gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*
