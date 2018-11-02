;
;  (C) Copyright 2018  Pavel Tisnovsky
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
    {"microservice"       ["Java" "Python" "JavaScript" "TypeScript"]
     "web-application" {"Back end"
                          ["Java" "Python" "JavaScript" "TypeScript"]
                        "Front end"
                          ["JavaScript" "TypeScript"]}
     "openshift-cron-job" ["Java" "Python" "JavaScript" "TypeScript" "BASH"]
     "desktop"            ["Java" "Python" "C" "C++"]
     "cli-tool"           ["Java" "Python" "C" "C++" "BASH"]})
