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

(ns a+construction-kit.http-utils
    "Module that contains several HTTP-related utility functions.")


(require '[ring.util.response    :as http-response])
(require '[clojure.tools.logging :as log])


(defn return-file
    "Create HTTP response containing content read from specified file.
     Special value nil / HTTP response 404 is returned in case of any I/O error."
    [^String prefix file-name content-type]
    (let [file (new java.io.File prefix file-name)]
        (log/info "Returning file " (.getAbsolutePath file))
        (if (.exists file)
            (-> (http-response/response file)
                (http-response/content-type content-type))
            (log/error "return-file(): can not access file: " (.getName file)))))


(defn cache-control-headers
    "Update the response to contains all cache-control headers."
    [response]
    (-> response
        (assoc-in [:headers "Cache-Control"] ["must-revalidate" "no-cache" "no-store"])
        (assoc-in [:headers "Expires"] "0")
        (assoc-in [:headers "Pragma"] "no-cache")))


(defn png-response
    "Update the response with the content type valid for PNG images."
    [image-data]
    (-> image-data
        (http-response/response)
        (http-response/content-type "image/png")
        cache-control-headers))


(defn gif-response
    "Update the response with the content type valid for GIF images."
    [image-data]
    (-> image-data
        (http-response/response)
        (http-response/content-type "image/gif")
        cache-control-headers))
