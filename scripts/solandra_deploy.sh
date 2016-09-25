echo "Going to Source/solandra directory"
cd ../Source/solandra/

echo "Copy solandra.war to tomcat webapps folder"
cp target/solandra.war ../../apache-tomcat-8.0.15/webapps/

echo "Going back to Scripts Folder"
cd ../../Scripts/