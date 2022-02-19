@echo off

:: import the .cer file into Chrome as a Trusted Root Certificate
keytool -v -export -file ..\classmaster.cer -keystore ..\classmaster.p12 -alias classmaster