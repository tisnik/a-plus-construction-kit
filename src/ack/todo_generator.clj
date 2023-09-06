;
;  (C) Copyright 2023  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns ack.todo-generator
  "Module that generates all the TODO tasks based on user configuration.
  
  Author: Pavel Tisnovsky")


(require '[clojure.pprint :as pprint])
(require '[clojure.walk   :as walk])
(require '[clojure.string :as string])


(def project-stub
  {
    :Go     {:title         "Project stub will be prepared"
             :description   "Project structure will be generated based on Go programming language"
             :list-of-files ["go.mod" "go.sum" "project.go" "license.txt"]
             :command       "go build project"
             :download      nil}
    :Python {:title         "Project stub will be prepared"
             :description   "Project structure will be generated based on Python programming language"
             :list-of-files ["setup.py" "requirements.in" "requirements.txt" "project.py" "license.txt"]
             :command       "python project.py\npip install -m requirements.txt"
             :download      nil}
    })


(def deployment-type
  {
   :openshift {:title         "Project will de deployed on OpenShift"
               :description   "Project will be deployed on OpenShift"
               :list-of-files ["deployment.yaml"]
               :command       "oc something"
               :download      nil}
   :aws       {:title         "Project will de deployed on Amazon Web Services"
               :description   "Project will be deployed on Amazon Web Services"
               :list-of-files ["deployment.yaml"]
               :command       "wa something"
               :download      nil}
   :azure     {:title         "Project will de deployed on Microsoft Azure"
               :description   "Project will be deployed on Microsoft Azure"
               :list-of-files ["deployment.yaml"]
               :command       "wa something"
               :download      nil}
   })


(def add-web-service-framework
  {:Go
    {
     :Gorilla-Toolkit {:title         "Gorilla Toolkit dependency"
                       :description   "Gorilla Toolkit package will be added into the project"
                       :list-of-files ["gorilla_interface.go" "go.mod" "go.sum"]
                       :command       "go get github.com/gorilla\ngo mod tidy"
                       :download      nil}
     }
  :Python
   {
    :Flask            {:title         "Flask dependency"
                       :description   "Flask framework will be added into the project"
                       :list-of-files ["flask.py" "requirements.in" "requirements.txt"]
                       :command       "pip install -i requirements.txt"
                       :download      nil}
   }})


(def add-logging-library
  {:Go
    {
     :log             {:title         "Standard log library dependency"
                       :description   "Standard log library does not need any further step"
                       :list-of-files nil
                       :command       nil
                       :download      nil}
     :zerolog         {:title         "Zerolog library dependency"
                       :description   "Zerolog library will be added into the project"
                       :list-of-files ["go.mod" "go.sum"]
                       :command       "go get github.com/zerolog\ngo mod tidy"
                       :download      nil}
     }
    :Python
     {
     :logging         {:title         "Logging library dependency"
                       :description   "Logging library will be added into the project as dependency"
                       :list-of-files ["logging.py" "requirements.in" "requirements.txt"]
                       :command       "pip install -i requirements.txt"
                       :download      nil}
      }})


(def add-streaming-platform
  {:Go
    {
     :Apache-Kafka  {:title         "Interface to Apache Kafka"
                     :description   "Interface to Apache Kafka will be added as a dependency. Configuration will be changed as well."
                     :list-of-files ["kafka.go" "go.mod" "go.sum" "config.toml" "server.properties" "zookeeper.properties"]
                     :command       "go get github.com/sarama\ngo mod tidy\ncd kafka\nbin/kafka-server-start.sh config/server.properties\nbin/zookeeper-server-start.sh config/zookeeper.properties"
                     :download      nil}
     :JetStream     {:title         "Interface to JetStream"
                     :description   "Interface to JetStream will be added as a dependency. Configuration will be changed as well."
                     :list-of-files ["nats.cfg"]
                     :command       "nats redis.cfg"
                     :download      nil}
     :Redis-streams {:title         "Interface to Redis Streams"
                     :description   "Interface to Redis Streams will be added as a dependency. Configuration will be changed as well."
                     :list-of-files ["redis.go" "go.mod" "go.sum" "config.toml" "redis.cfg"]
                     :command       "go get github.com/redis\ngo mod tidy\nredis-server redis.cfg"
                     :download      nil}
     }
   :Python
    {
     :Apache-Kafka  {:title         "Interface to Apache Kafka"
                     :description   "Interface to Apache Kafka will be added as a dependency. Configuration will be changed as well."
                     :list-of-files ["kafka.py" "setup.py" "requierements.txt" "server.properties" "zookeeper.properties"]
                     :command       "pip install -i requirements.txt\ncd kafka\nbin/kafka-server-start.sh config/server.properties\nbin/zookeeper-server-start.sh config/zookeeper.properties"
                     :download      nil}
     :JetStream     {:title         "Interface to JetStream"
                     :description   "Interface to JetStream will be added as a dependency. Configuration will be changed as well."
                     :list-of-files ["nats.py" "setup.py" "requierements.txt" "nats.cfg"]
                     :command       "pip install -i requirements.txt\nnats redis.cfg"
                     :download      nil}
     :Redis-streams {:title         "Interface to Redis Streams"
                     :description   "Interface to Redis Streams will be added as a dependency. Configuration will be changed as well."
                     :list-of-files ["redis.py" "setup.py" "requierements.txt"]
                     :command       "pip install -i requirements.txt\nredis-server redis.cfg"
                     :download      nil}
     }
    })


(defn not-nil
  [x]
  (or x ""))

(defn get-item
  [input item]
  (if item
    (-> input item not-nil (string/replace " " "-") keyword)))


(defn todo-item
  [input item dict]
  ((get-item input item) dict))


(defn todo-item-language
  [input item dict]
  (todo-item input item ((keyword (:primary-language input)) dict)))


(defn generate-model
  [params]
  (let [transformed (walk/keywordize-keys params)]
    (into {}
          (for [[k v] transformed]
            [k (if (or (= v "(please choose)") (= v "")) nil v)]))))


(defn generate-todo-list
  [model]
  [
   (todo-item model          :primary-language      project-stub)
   (todo-item-language model :web_service_framework add-web-service-framework)
   (todo-item-language model :streaming_platform    add-streaming-platform)
   (todo-item-language model :logging               add-logging-library)
   (todo-item model          :deployment-type       deployment-type)
  ])


(defn generate-warnings
  [model]
  [
   {:title "foo"
    :description "bar"}
   {:title "baz"
    :description "bar"}
   ])


(comment

(require '[clojure.pprint :as pprint])
(require '[clojure.walk   :as walk])
(require '[clojure.string :as string])

(pprint/pprint (generate-todo-list params))

(pprint/pprint (:Go project-stub))

(pprint/pprint (:Python project-stub))

(def params
  {"primary-language"           "Go"
   "back-end-language"          ""
   "front-end-language"         ""
   "deployment-type"            "aws"
   "web_service_framework"      "Gorilla Toolkit"
   "logging"                    "log"
   "alerting"                   "(please choose)"
   "ci"                         "Jenkins"
   "image_manipulation"         "(please choose)"
   "message_queuing_service"    "(please choose)"
   "streaming_platform"         "JetStream"
   "monitoring"                 "(please choose)"
   "app-type"                   "microservice"
   "finish"                     "finish"
   "plotting"                   "(please choose)"
   "networking"                 "apr"
   "information_system"         "WordPress"
   "other_nosql_database"       "(please choose)"
   "relational_database"        "PostgreSQL"
   "graph_database"             "JanusGraph"
   "document-oriented_database" "(please choose)"
   "key-value_database"         "(please choose)"}
) ; params

) ; comment
