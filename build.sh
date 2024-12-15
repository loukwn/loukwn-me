# Delete unneeded files
echo "Files deleted:"
find . -maxdepth 1 -type f
find . -maxdepth 1 -type f -delete

# Create CNAME
touch CNAME && echo "loukwn.me" > CNAME

# Move Web stuff to root folder
mv web/* .
rm -rf web

# Build Compose
cd compose
./gradlew wasmJsBrowserDistribution
cd ..
mkdir compose2
mv compose/composeApp/build/dist/wasmJs/productionExecutable/* compose2/
rm -rf compose
mv compose2/ compose

