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

(defproject a+construction-kit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
    :url "http://example.com/FIXME"
    :license {:name "Eclipse Public License"
              :url "http://www.eclipse.org/legal/epl-v10.html"}
    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.clojure/tools.cli "0.3.1"]
                   [clojure-ini "0.0.1"]
                   [ring/ring-core "1.3.2"]
                   [ring/ring-jetty-adapter "1.3.2"]
                   [org.clojure/data.json "0.2.5"]
                   [hiccup "1.0.4"]
                   [org.clojure/tools.logging "0.3.1"]
                   [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                      javax.jms/jms
                                                      com.sun.jmdk/jmxtools
                                                      com.sun.jmx/jmxri]]
                   [org.slf4j/slf4j-log4j12 "1.6.6"]
                   [org.clojars.tisnik/clj-utils "0.3.0-SNAPSHOT"]
                   [org.clojars.tisnik/clj-middleware "0.1.0-SNAPSHOT"]
                   [org.clojars.tisnik/clj-config-loader "0.1.0-SNAPSHOT"]
                   [org.clojars.tisnik/clj-fileutils "0.4.0-SNAPSHOT"]
                   [org.clojars.tisnik/clj-http-utils "0.1.0-SNAPSHOT"]]
    :dev-dependencies [[lein-ring "0.8.10"]]
    :main ^:skip-aot a+construction-kit.core
    :plugins [[lein-ring "0.8.10"]
              [codox "0.8.11"]
              [test2junit "1.1.0"]
              [lein-cloverage "1.0.6"]]
    :ring {:handler a+construction-kit.core/app}
    :target-path "target/%s"
    :profiles {:uberjar {:aot :all}})
