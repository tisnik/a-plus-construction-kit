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

(ns a+construction-kit.html-renderer
    "Module that contains functions used to render HTML pages sent back to the browser.

    Author: Pavel Tisnovsky")


(require '[hiccup.page  :as page])
(require '[hiccup.form  :as form])

(require '[clojure.data.json :as json])


(require '[a+construction-kit.html-renderer-widgets :as widgets])



(defn render-select-app-page
    "Render front page of this application with the selection of application type."
    [app-types]
    (page/xhtml
        (widgets/header "/" {:include-raphael false})
        [:body
            [:div {:class "container"}
                (widgets/navigation-bar "/")
                [:h3 "Please choose application type"]
                (form/form-to {:name "inputForm"} [:get "/select-language"]
                    [:br]
                    (for [app-type app-types]
                        [:div
                            (widgets/submit-button (:label app-type) "app-type" (:name app-type))
                            [:br]
                            [:br]])
                )
                [:div {:style "height: 10ex"}]
                (widgets/footer)
            ] ; </div class="container">
        ] ; </body>
))

(defn render-app-type
    [app-type]
    (.toLowerCase app-type))

(defn language-icon
    [language]
    (str "languages/64x64/" language ".png"))

(defn render-select-language-page
    "Render the page with the selection of language or languages to use for the app."
    [app-type app-type-label app-parts app-languages]
    (page/xhtml
        (widgets/header "/" {:include-raphael false})
        [:body
            [:div {:class "container"}
                (widgets/navigation-bar "/")
                [:h3 "Language selection for " (render-app-type app-type-label)]
                [:br]
                (form/form-to {:name "inputForm"} [:get "/configure-modules"]
                    [:input {:type "hidden" :name "app-type" :id "app-type" :value app-type}]
                    (if app-parts
                        (for [part app-parts]
                            [:span
                                [:h4 part]
                                (for [language (get app-languages part)]
                                    [:span
                                        [:a {:href (str "javascript:onSelectLanguagePartIcon('" part "', '" language "', "(count app-parts)")")} [:img {:src (language-icon language) :style "margin-right:10px"}]]
                                        (widgets/radio-button (str part "-language") false language language (str "languagePartSelected('" part "'," (count app-parts) ")"))
                                    ])
                                [:br]])
                        (for [language app-languages]
                            [:span
                                [:a {:href (str "javascript:onSelectPrimaryLanguageIcon('" language "')")} [:img {:src (language-icon language) :style "margin-right:10px"}]]
                                (widgets/radio-button "primary-language" false language language "enableNextButton()")
                            ]))
                        [:br]
                        (widgets/disabled-submit-button "Next" "next" "next"))
                        [:br]
                        (widgets/back-button)
                [:div {:style "height: 10ex"}]
                (widgets/footer)
            ] ; </div class="container">
        ] ; </body>
))


(defn render-languages
    [languages]
    (println languages)
    (if (get languages "primary-language")
        (get languages "primary-language")
        (str (get languages "Front end-language") " and " (get languages "Back end-language"))))


(defn configuration->id
    [value]
    (clojure.string/lower-case (clojure.string/replace value '\space \_)))


(defn render-microservice-config
    [language configurations subgroups config-values]
    (let [cfg-values (get config-values language)]
        [:table
            (for [configuration configurations]
                (if (get subgroups configuration)
                    [:tgroup
                        [:tr [:th [:h4 configuration]]]
                        (for [group (get subgroups configuration)]
                            (let [drop-down-id     (configuration->id group)
                                  drop-down-values (get cfg-values group)]
                                [:tr [:td {:style "padding-right:2em;"} group]
                                     [:td (widgets/drop-down drop-down-id drop-down-values)]
                                     [:td (widgets/add-button    language configuration drop-down-id)]
                                     [:td (widgets/remove-button language configuration drop-down-id)]]))
                         [:tr [:td {:colspan 4} "&nbsp;"]]
                    ]
                (let [drop-down-id     (configuration->id configuration)
                      drop-down-values (get cfg-values configuration)]
                    [:tr [:th {:style "padding-right:2em;"} [:h4 configuration]]
                         [:td (widgets/drop-down drop-down-id drop-down-values)]
                         [:td (widgets/add-button    language configuration drop-down-id)]
                         [:td (widgets/remove-button language configuration drop-down-id)]])))
        ]))


(defn render-application-config
    [primary-language configuration subgroups config-values]
    )

(defn render-cli-tool-config
    [primary-language configuration subgroups config-values]
    )

(defn render-openshift-cron-job-config
    [primary-language configuration subgroups config-values]
    )

(defn render-desktop-config
    [primary-language configuration subgroups config-values]
    )

(defn render-configure-modules-form
    [app-type languages configurations subgroups config-values]
    (form/form-to {:name "inputForm"} [:post "/generate-source"]
    (condp = app-type
        "microservice"
        (render-microservice-config (get languages "primary-language") configurations subgroups config-values)
        "web-application"
        (render-application-config (get languages "primary-language") configurations subgroups config-values)
        "desktop"
        (render-desktop-config (get languages "primary-language") configurations subgroups config-values)
        "cli-tool"
        (render-cli-tool-config (get languages "primary-language") configurations subgroups config-values)
        "openshift-cron-job"
        (render-openshift-cron-job-config (get languages "primary-language") configurations subgroups config-values)
    )))


(defn render-configure-modules-page
    [app-type app-type-label languages configurations subgroups config-values]
    (page/xhtml
        (widgets/header "/" {:include-raphael true})
        [:body
            [:div {:class "container" :style "width:90%"}
                (widgets/navigation-bar "/")
                [:h3 "Modules for " (render-app-type app-type-label) " written in " (render-languages languages)]
                [:br]
                [:div {:class "row"}
                    [:div {:class "column"}
                        (widgets/canvas)]
                    [:div {:class "column"}
                        (render-configure-modules-form app-type languages configurations subgroups config-values)]
                ]
                [:div {:style "height: 5ex"}]
                (widgets/footer)
                (let [langs-json (json/write-str languages)]
                    [:script (str "window.onload = function() {
                                  createPaper(640, 640);
                                  drawAppSchema(paper, '" app-type "', " langs-json ");
                              }")]
                )
            ] ; </div class="container">
        ] ; </body>
))

