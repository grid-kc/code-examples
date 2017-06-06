Filebeat and Logstash
===

This is a convenient way to stand up a fully functioning
Filebeat-Logstash-Elasticsearch stack in order to test configurations for those
services.

Testing Protocol
---
1. Start up the log generator
```Shell
./log-generator.py
```
   You may need to add some code here in order to dummy the logs your service is
   generating.
2. Run docker-compose
```Shell
docker-compose up
```
