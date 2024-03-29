;
;  (C) Copyright 2018, 2020, 2023  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns ack.model)

(def deployment-types
    [{:label "OpenShift"
      :name  "openshift"}
     {:label "Amazon Web Services"
      :name  "aws"}
     {:label "Microsoft Azure"
      :name  "azure"}])

(def app-types
    [{:label "Microservice"
      :name  "microservice"}
     {:label "Web application"
      :name  "web-application"}
     {:label "CRON job"
      :name  "cron-job"}
     {:label "Desktop app with GUI"
      :name  "desktop"}
     {:label "CLI tool"
      :name  "cli-tool"}])

(def app-parts
  {"web-application" ["Back end" "Front end"]})

(def app-languages
  {"microservice"       ["Java" "C#" "Python" "JavaScript" "TypeScript" "Go" "Rust"]
   "web-application" {"Back end"
                        ["Java" "C#" "Python" "JavaScript" "TypeScript" "Go" "Rust"]
                      "Front end"
                        ["JavaScript" "TypeScript"]}
   "cron-job"           ["Java" "Python" "JavaScript" "TypeScript" "Go" "BASH"]
   "desktop"            ["Java" "C#" "Python" "C" "C++" "Go"]
   "cli-tool"           ["Java" "C#" "Python" "C" "C++" "Go" "BASH"]})

(def configurations
  {"microservice" ["Web service framework"
                   "Data storage"
                   "Streaming platform"
                   "Message queuing service"
                   "Other interfaces"
                   "Libraries"]})

(def subgroups
  {"Data storage"     ["Relational database"
                       "Key-value database"
                       "Document-oriented database"
                       "Graph database"
                       "Other NoSQL database"]
   "Other interfaces" ["Information system"
                       "CI"
                       "Monitoring"
                       "Alerting"]
   "Libraries"        ["Logging" "Networking" "Image manipulation" "Plotting"]
})

;
(def config-values
  {"Python" {"Web service framework"      ["Flask" "web.py" "Bottle"]
             "Relational database"        ["DB/2" "Oracle RDBMS" "PostgreSQL" "AWS RDS" "MariaDB/MySQL" "SQLite" "ODBC"]
             "Key-value database"         ["Dynamo", "Oracle NoSQL Database" "memcached" "Redis" "Berkeley DB"]
             "Document-oriented database" ["Caché" "CouchDB" "MongoDB"]
             "Graph database"             ["JanusGraph" "Neo4j" "Oracle Spatial and Graph"]
             "Other NoSQL database"       ["BaseX" "eXist" "Sedna"]
             "Streaming platform"         ["Apache Kafka" "JetStream" "Redis streams"]
             "Message queuing service"    ["Microsoft Azure Service Bus" "Oracle Messaging Cloud Service" "Amazon Simple Queue Service" "IronMQ" "StormMQ" "AnypointMQ"]
             "Information system"         ["SAP" "WordPress" "Joomla" "Drupal" "Google Maps" "Yahoo Maps"]
             "CI"                         ["Jenkins" "Buildbot" "Travis CI"]
             "Monitoring"                 ["Grafana"]
             "Alerting"                   ["Grafana Alerts"]
             "Logging"                    ["logging" "logging2" "fastlog"]     
             "Image manipulation"         ["Pillow" "PIL"]
             "Networking"                 ["asyncoro" "Gevent" "TwistedMatrix" "RPyC" "PyRO"]
             "Plotting"                   ["gnuplot.py" "Matplotlib"]
  }
   "Go"     {"Web service framework"      ["Gorilla Toolkit" "Gin" "Beego" "Iris" "Echo" "Fiber"]
             "Relational database"        ["DB/2" "Oracle RDBMS" "PostgreSQL" "AWS RDS" "MariaDB/MySQL" "SQLite" "ODBC"]
             "Key-value database"         ["Dynamo", "Oracle NoSQL Database" "memcached" "Redis" "Berkeley DB"]
             "Document-oriented database" ["Caché" "CouchDB" "MongoDB"]
             "Graph database"             ["JanusGraph" "Neo4j" "Oracle Spatial and Graph"]
             "Other NoSQL database"       ["BaseX" "eXist" "Sedna"]
             "Streaming platform"         ["Apache Kafka" "JetStream" "Redis streams"]
             "Message queuing service"    ["Microsoft Azure Service Bus" "Oracle Messaging Cloud Service" "Amazon Simple Queue Service" "IronMQ" "StormMQ" "AnypointMQ"]
             "Information system"         ["SAP" "WordPress" "Joomla" "Drupal" "Google Maps" "Yahoo Maps"]
             "CI"                         ["Jenkins" "Buildbot" "Travis CI"]
             "Monitoring"                 ["Grafana"]
             "Alerting"                   ["Grafana Alerts"]
             "Logging"                    ["logrus" "go-kit" "log" "zap" "zerolog"]     
             "Image manipulation"         ["img" "mergi" "picfit"]
             "Networking"                 ["apr" "fullproxy" "cidranger" "event" "gaio" "gmqt"]
             "Plotting"                   ["chart" "gonum" "gograph" "gohistogram"]
  }
})                                                                          
