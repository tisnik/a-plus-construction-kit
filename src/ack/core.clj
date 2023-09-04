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

(ns a+construction-kit.core
  "Core module containing the -main function and the startup code.

    Author: Pavel Tisnovsky"
  (:gen-class))


(require '[ring.adapter.jetty      :as jetty])
(require '[ring.middleware.params  :as http-params])
(require '[ring.middleware.cookies :as cookies])
(require '[ring.middleware.session :as session])

(require '[clojure.tools.cli       :as cli])
(require '[clojure.tools.logging   :as log])

(require '[clj-middleware.middleware  :as middleware])

(require '[a+construction-kit.config  :as config])
(require '[a+construction-kit.server  :as server])


(def cli-options
  "Definitions of all command line options currenty supported."
  ;; an option with a required argument
  [["-c" "--print-config" "print configuration and exit" :id :print-config]
   ["-h" "--help" "show this help" :id :help]])


; we need to load the configuration in advance so the 'app' could use it
(def configuration
  "Configuration structure that is loaded automatically during the startup."
  (config/load-configuration-from-ini "config.ini"))


(def app
  "Definition of a Ring-based application behaviour."
  (-> server/handler            ; handle all events
      (middleware/inject-configuration configuration) ; inject configuration
                                                      ; structure into the
                                                      ; parameter
      session/wrap-session
      cookies/wrap-cookies      ; we need to work with cookies
      http-params/wrap-params)) ; and to process request parameters, of course


(defn start-server
  "Start the HTTP server on the specified port.
     The port is specified as string."
  [port]
  (log/info "Starting the server at the port: " port)
  (jetty/run-jetty app {:port (read-string port)}))


(defn show-help
  "Display brief help on the standard output."
  [all-options]
  (println "Usage:")
  (println (:summary all-options)))


(defn show-configuration
  "Show the configuration loaded from the standard INI file."
  [configuration]
  (clojure.pprint/pprint configuration))


(defn -main
  "Entry point to the A+ Construction Kit service."
  [& args]
  (let [all-options (cli/parse-opts args cli-options)
        options     (all-options :options)
        port        (-> configuration
                        :service
                        :port)]
    ; perform the selected operation according to CLI options
    (cond (:help options) (show-help all-options)
          (:print-config options) (show-configuration configuration)
          :else (start-server port))))
