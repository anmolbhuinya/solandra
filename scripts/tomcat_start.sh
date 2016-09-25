ehco "Going to tomcat bin folder"
cd ../apache-tomcat-8.0.15/bin/

echo "Starting tomcat"
./startup.sh

echo "Our webapp solandra started at port 8080. Base URL: http://localhost:8080/solandra/"

echo "Going back to Scripts Folder"
cd ../../Scripts/