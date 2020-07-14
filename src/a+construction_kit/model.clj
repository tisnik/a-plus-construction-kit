;
;  (C) Copyright 2018, 2020  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns a+construction-kit.model)

(def app-types
    [{:label "Microservice"
      :name  "microservice"}
     {:label "Web application"
      :name  "web-application"}
     {:label "OpenShift CRON job"
      :name  "openshift-cron-job"}
     {:label "Desktop app with GUI"
      :name  "desktop"}
     {:label "CLI tool"
      :name  "cli-tool"}])

(def app-parts
    {"web-application" ["Back end" "Front end"]})

(def app-languages
    {"microservice"       ["Java" "Python" "JavaScript" "TypeScript" "Go"]
     "web-application" {"Back end"
                          ["Java" "Python" "JavaScript" "TypeScript" "Go"]
                        "Front end"
                          ["JavaScript" "TypeScript"]}
     "openshift-cron-job" ["Java" "Python" "JavaScript" "TypeScript" "Go" "BASH"]
     "desktop"            ["Java" "Python" "C" "C++" "Go"]
     "cli-tool"           ["Java" "Python" "C" "C++" "Go" "BASH"]})

(def configurations
    {"microservice" ["Web service framework"
                     "Data storage"
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
                         "CI"]
     "Libraries"        ["Logging" "Networking" "Image manipulation" "Plotting"]
})

;
(def config-values
    {"Python" {"Web service framework"      ["Flask" "web.py" "Bottle"]
               "Relational database"        ["DB/2" "Oracle RDBMS" "PostgreSQL" "MariaDB/MySQL" "SQLite" "ODBC"]
               "Key-value database"         ["Dynamo", "Oracle NoSQL Database" "memcached" "Redis" "Berkeley DB"]
               "Document-oriented database" ["Caché" "CouchDB" "MongoDB"]
               "Graph database"             ["JanusGraph" "Neo4j" "Oracle Spatial and Graph"]
               "Other NoSQL database"       ["BaseX" "eXist" "Sedna"]
               "Message queuing service"    ["Microsoft Azure Service Bus" "Oracle Messaging Cloud Service" "Amazon Simple Queue Service" "IronMQ" "StormMQ" "AnypointMQ"]
               "Information system"         ["SAP" "WordPress" "Joomla" "Drupal" "Google Maps" "Yahoo Maps"]
               "CI"                         ["Jenkins" "Buildbot" "Travis CI"]
               "Logging"                    ["logging" "logging2" "fastlog"]     
               "Image manipulation"         ["Pillow"]
               "Networking"                 ["asyncoro" "Gevent" "TwistedMatrix" "RPyC" "PyRO"]
               "Plotting"                   ["gnuplot.py" "Matplotlib"]
    }})                                                                          
