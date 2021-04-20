docker pull docker.elastic.co/enterprise-search/enterprise-search:7.12.0

es_pass=$1

if [ -z "$es_pass" ]
then
  echo "pass is empty, send in as first arg"
else
  docker run -p 3002:3002 \
    -e elasticsearch.host='http://host.docker.internal:9200' \
    -e elasticsearch.username=elastic \
    -e elasticsearch.password=$es_pass \
    -e allow_es_settings_modification=true \
    -e secret_management.encryption_keys='[4a2cd3f81d39bf28738c10db0ca782095ffac07279561809eecc722e0c20eb09]' \
    --name enterprise_search \
  docker.elastic.co/enterprise-search/enterprise-search:7.12.0
fi

