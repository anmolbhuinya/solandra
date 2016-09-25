echo "Going inside solr server"
cd ../solr-6.2.0/server/solr/

echo "Creating folder rental inside solr server"
mkdir rental

echo "Copying conf folder containing schema.xml, solrconfig.xml and other files from Source folder"
cp -r ../../../Source/solandra/conf .

echo "Going back to Scripts Folder"
cd ../../../Scripts/
