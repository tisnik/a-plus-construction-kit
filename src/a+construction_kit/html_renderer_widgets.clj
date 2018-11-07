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

(ns a+construction-kit.html-renderer-widgets
    "Module that contains common utility functions for the html-renderer and html-renderer help modules

    Author: Pavel Tisnovsky")


(require '[hiccup.core  :as hiccup])
(require '[hiccup.page  :as page])
(require '[hiccup.form  :as form])


(defn header
    "Renders part of HTML page - the header."
    [url-prefix & [options]]
    [:head
        [:title "A+ Construction Kit"]
        [:meta {:name "Author"    :content "Pavel Tisnovsky"}]
        [:meta {:name "Generator" :content "Clojure"}]
        [:meta {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
        (page/include-css (str url-prefix "bootstrap/bootstrap.min.css"))
        (page/include-css (str url-prefix "style.css"))
        (if (and options (:include-raphael options))
            (page/include-js  (str url-prefix "raphael/raphael.min.js")))
        (page/include-js  (str url-prefix "a_plus_construction_kit.js"))
    ] ; head
)


(defn navigation-bar
    "Renders whole navigation bar."
    [url-prefix]
    [:nav {:class "navbar navbar-inverse navbar-fixed-top" :role "navigation"} ; use navbar-default instead of navbar-inverse
        [:div {:class "container-fluid"}
            [:div {:class "row"}
                [:div {:class "col-md-7"}
                    [:div {:class "navbar-header"}
                        [:a {:href url-prefix :class "navbar-brand"} "A+ Construction Kit"]
                    ] ; ./navbar-header
                    [:div {:class "navbar-header"}
                        [:ul {:class "nav navbar-nav"}
                            ;[:li (tab-class :whitelist mode) [:a {:href (str url-prefix "whitelist")} "Whitelist"]]
                        ]
                    ]
                ] ; col-md-7 ends
                ;[:div {:class "col-md-3"}
                ;    (render-name-field user-name (remember-me-href url-prefix mode))
                ;]
                ;[:div {:class "col-md-2"}
                ;    [:div {:class "navbar-header"}
                ;        [:a {:href (users-href url-prefix mode) :class "navbar-brand"} "Users"]
                 ;   ] ; ./navbar-header
                ;] ; col ends
            ] ; row ends
        ] ; </div .container-fluid>
]); </nav>


(defn footer
    "Renders part of HTML page - the footer."
    []
    [:div "<br /><br />&copy; Pavel Tisnovsky, Red Hat"])


(defn back-button
    "Back button widget."
    []
    [:button {:class "btn btn-primary" :onclick "window.history.back()" :type "button" :style "width:12em"} "Back"])


(defn submit-button
    "Submit button widget."
    [text name value]
    [:button {:type "submit" :name name :value value :class "btn btn-success" :style "width:12em"} text])


(defn add-button
    "Add button widget."
    [language configuration drop-down-id]
    (let [onclick (str "onAddApplicationPart('" language "', '" configuration "', '" drop-down-id "')")]
        [:button {:type "button" :class "add_button" :style "width:7em" :onclick onclick} "Add"]))


(defn remove-button
    "Add button widget."
    [language configuration drop-down-id]
    (let [onclick (str "onRemoveApplicationPart('" language "', '" configuration "', '" drop-down-id "')")]
        [:button {:type "button" :class "remove_button" :style "width:7em" :onclick onclick} "Remove"]))


(defn disabled-submit-button
    "Disabled submit button widget."
    [text name value]
    [:button {:type "submit" :id name :name name :value value :class "btn btn-success" :style "width:12em" :disabled "disabled"} text])


(defn radio-button
    "Radio button widget."
    ([group checked value label]
        [:span (form/radio-button group checked value)
               " " label
               [:br]])
    ([group checked value label on-click]
        [:span (form/radio-button {:onclick on-click} group checked value)
               " " label
               [:br]]))

(defn help-button
    "Help button widget."
    [help-page-url]
    [:a {:href help-page-url} [:img {:src "icons/help.gif"}]])


(defn canvas
    []
    [:div {:id "canvas_container"}])

(defn drop-down
    [drop-down-id drop-down-values]
    (form/drop-down {:id drop-down-id :class "select"} drop-down-id drop-down-values))
