echo "Going to Source/solandra directory"
cd ../Source/solandra/

echo "Running maven build"
mvn clean package

echo "Going back to Scripts Folder"
cd ../../Scripts/