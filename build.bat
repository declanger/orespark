setlocal

echo Generating data files...
call gradlew runDataGen

echo Deleting old mod jars...
del /Q "c:\Users\kachd\curseforge\minecraft\Instances\Orespark\mods\orespark-*.jar"

echo Building the mod...
call gradlew build

echo Build Complete

endlocal