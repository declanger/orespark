setlocal

echo Generating data files...
call gradlew runDataGen

echo Running the mod...
call gradlew runClient

echo Build Complete

endlocal