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


(ns a+construction-kit.config
    "Configuration loader and parser.

    Author: Pavel Tisnovsky")


(require '[clj-utils.utils :as utils])
(require '[config-loader.config-loader :as config-loader])


(defn full-prefix
    "Construct full prefix for all REST API endpoints (including API version)."
    [configuration]
    (str (-> configuration :api :prefix) "/" (-> configuration :api :version)))


(defn update-configuration
    "Update all selected items in the configuration structure.
     Other items are stored with string values."
    [configuration]
    (-> configuration
        (update-in [:config        :verbose]          utils/parse-boolean)
        (update-in [:config        :pretty-print]     utils/parse-boolean)))


(defn load-configuration-from-ini
    "Load configuration from the provided INI file and perform conversions
     on selected items from strings to numeric or Boolean values."
    [ini-file-name]
    (-> (config-loader/load-configuration-file ini-file-name)
        update-configuration))


(defn pretty-print?
    "Read the pretty-print settings (it is used mainly for JSON output etc.)"
    [request]
    (-> request :configuration :config :pretty-print))


(defn verbose?
    "Read the verbose settings (it is used for all outputs that does not went into logs)"
    [request]
    (-> request :configuration :config :verbose))
