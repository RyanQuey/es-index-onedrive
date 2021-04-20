# now that es is running, get enterprise-search too
filename=enterprise-search-7.12.0.deb
if test -f $filename; then
  echo "downloaded already"
else
  curl -LO https://artifacts.elastic.co/downloads/enterprise-search/$filename
fi

sudo dpkg -i ./$filename
