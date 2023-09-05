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
             :list-of-files ["setup.py" "requirements.in" "project.py" "license.txt"]
             :command       "python project.py"
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
  {
   :Gorilla-Toolkit {:title         "Gorilla Toolkit dependency"
                     :description   "Gorilla Toolkit package will be added into the project"
                     :list-of-files ["gorilla_interface.go" "go.mod" "go.sum"]
                     :command       "go get github.com/gorilla"
                     :download      nil}
   })


(def add-logging-library
  {
   :log             {:title         "Standard log library dependency"
                     :description   "Standard log library does not need any further step"
                     :list-of-files nil
                     :command       nil
                     :download      nil}
   })


(defn get-item
  [input item]
  (-> input item (string/replace " " "-") keyword))


(defn todo-item
  [input item dict]
  ((get-item input item) dict))


(defn generate-todo-list
  [params]
  (let [input (walk/keywordize-keys params)]
  [
   (todo-item input :primary-language      project-stub)
   (todo-item input :web_service_framework add-web-service-framework)
   (todo-item input :logging               add-logging-library)
   (todo-item input :deployment-type       deployment-type)
  ]))



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
