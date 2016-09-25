echo "Going to solr bin folder"
cd ../solr-6.2.0/bin/

echo "Creating core rental"
./solr create -c rental

echo "Going back to Scripts folder"
cd ../../Scripts/