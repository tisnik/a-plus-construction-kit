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

(ns ack.html-renderer
  "Module that contains functions used to render HTML pages sent back to the browser.

    Author: Pavel Tisnovsky")


(require '[clojure.string :as str])
(require '[clojure.pprint :as pprint])

(require '[hiccup.page :as page])
(require '[hiccup.form :as form])

(require '[clojure.data.json :as json])


(require '[ack.html-renderer-widgets :as widgets])



(defn render-index-page
  "Render index page."
  []
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "A+ Construction Kit"]
          [:div {:style "height: 10ex"}]
          (form/form-to
                {:name "inputForm1"}
                [:get "/select-app-type"]
                   (widgets/submit-button "Create new application" "app-type" "app-type")
                [:br])
          [:br]
          (form/form-to
                {:name "inputForm2"}
                [:get "/documentation"]
                   (widgets/submit-button "Documentation" "about" "about")
                [:br])
          widgets/footer
     ] ; </div class="container">
    ] ; </body>
  ))

(defn render-documentation-page
  "Render documentation page."
  []
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "Documentation"]
          [:ol
           [:li [:a {:href "/about.html"} "About"]]
           [:li [:a {:href "/motivation.html"} "Motivation"]]
           [:li [:a {:href "/solution.html"} "Solution"]]
           [:li [:a {:href "/references.html"} "References"]]
           [:li [:a {:href "/why.html"} "Why the name 'A+ Construction Kit' was choosed?"]]
          ]
          [:br]
          widgets/back-button
          widgets/tall-vertical-separator
          widgets/footer
     ] ; </div class="container">
    ] ; </body>
  ))

(defn render-select-app-page
  "Render front page of this application with the selection of application type."
  [app-types]
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "Please choose application type"]
          (form/form-to
                {:name "inputForm"}
                [:get "/select-deployment"]
                [:br]
                (for [app-type app-types]
                  [:div
                   (widgets/submit-button (:label app-type) "app-type" (:name app-type))
                   [:br]
                   [:br]]))
          widgets/back-button
          widgets/tall-vertical-separator
          widgets/footer
        ] ; </div class="container">
    ] ; </body>
  ))


(defn deployment-icon
    [deployment-type]
    (str "icons/" (:name deployment-type) ".png"))


(defn render-select-deployment-page
  [app-type app-type-label deployment-types]
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "Please choose deployment type for: " app-type-label]
          (form/form-to
                {:name "inputForm"}
                [:get "/select-language"]
                [:br]
                [:input {:type "hidden" :name "app-type" :id "app-type" :value app-type}]
                (for [deployment-type deployment-types]
                      [:span
                          [:a {:href (str "javascript:onSelectDeploymentTypeIcon('" (:name deployment-type) "')")}
                              [:img {:src (deployment-icon deployment-type) :style "margin-right:10px"}]]
                          (widgets/radio-button "deployment-type" false (:name deployment-type) (:label deployment-type) "enableNextButton()")
                          [:br]
                      ])
                [:br]
                (widgets/disabled-submit-button "Next" "next" "next")
                [:br]
                [:br]
          )
          widgets/back-button
          widgets/tall-vertical-separator
          widgets/footer
        ] ; </div class="container">
    ] ; </body>
  ))

(defn render-select-app-page
  "Render front page of this application with the selection of application type."
  [app-types]
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "Please choose application type"]
          (form/form-to
                {:name "inputForm"}
                [:get "/select-deployment"]
                [:br]
                (for [app-type app-types]
                  [:div
                   (widgets/submit-button (:label app-type) "app-type" (:name app-type))
                   [:br]
                   [:br]]))
          widgets/back-button
          widgets/tall-vertical-separator
          widgets/footer
        ] ; </div class="container">
    ] ; </body>
  ))

(defn render-desktop-app-page
  []
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body {:style "padding-top:50px"}
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "Desktop application"]
          [:br]
          [:div {:class "alert alert-danger" :role "alert"}
             "Sorry: this application type is not (yet) supported by A+ Construction Kit"
          ]
          widgets/back-button
          widgets/tall-vertical-separator
          widgets/footer
            ] ; </div class="container">
        ] ; </body>
))

(defn render-cli-app-page
  []
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body {:style "padding-top:50px"}
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "CLI Tool"]
          [:br]
          [:div {:class "alert alert-danger" :role "alert"}
             "Sorry: this application type is not (yet) supported by A+ Construction Kit"
          ]
          widgets/back-button
          [:div {:style "height: 10ex"}]
          widgets/footer
            ] ; </div class="container">
        ] ; </body>
))

(defn render-app-type
  [app-type]
  (.toLowerCase app-type))

(defn language-icon
    [language]
    (str "languages/64x64/" (str/replace language #"#" "sharp") ".png"))

(defn render-select-language-page
  "Render the page with the selection of language or languages to use for the app."
  [app-type app-type-label deployment-type app-parts app-languages next-page]
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body {:style "padding-top:50px"}
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "Language selection for " (render-app-type app-type-label)]
                [:br]
                (form/form-to {:name "inputForm"} [:get next-page]
                    [:input {:type "hidden" :name "app-type" :id "app-type" :value app-type}]
                    [:input {:type "hidden" :name "deployment-type" :id "deployment-type" :value deployment-type}]
                    (if app-parts
                        [:div {:class "container"}
                        [:div {:class "row"}
                        (for [part app-parts]
                            [:div {:class "col-lg-2" :style "width:300px"}
                                [:h4 part]
                                (for [language (get app-languages part)]
                                    [:span
                                        [:a {:href (str "javascript:onSelectLanguagePartIcon('" part "', '" language "', "(count app-parts)")")}
                                           [:img {:src (language-icon language) :style "margin-right:10px"}]]
                                        (widgets/radio-button (str part "-language") false language language (str "languagePartSelected('" part "'," (count app-parts) ")"))
                                        [:br]
                                    ])
                                [:br]])
                        ]]
                        (for [language app-languages]
                            [:span
                                [:a {:href (str "javascript:onSelectPrimaryLanguageIcon('" language "')")}
                                   [:img {:src (language-icon language) :style "margin-right:10px"}]]
                                (widgets/radio-button "primary-language" false language language "enableNextButton()")
                                [:br]
                            ]))
                        [:br]
                        (widgets/disabled-submit-button "Next" "next" "next"))
                        [:br]
                        widgets/back-button
                widgets/tall-vertical-separator
                widgets/footer
            ] ; </div class="container">
        ] ; </body>
))


(defn render-languages
  [languages]
  (println languages)
  (if (get languages "primary-language")
    (get languages "primary-language")
    (str (get languages "Front end-language")
         " and "
         (get languages "Back end-language"))))


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
                              drop-down-values (cons "(please choose)" (get cfg-values group))]
                          [:tr [:td {:style "padding-right:2em;"} group]
                           [:td (widgets/drop-down drop-down-id drop-down-values)]
                           [:td (widgets/add-button    language configuration drop-down-id)]
                           [:td (widgets/remove-button language configuration drop-down-id)]]))
              [:tr [:td {:colspan 4} "&nbsp;"]]
             ]
            (let [drop-down-id     (configuration->id configuration)
                  drop-down-values (cons "(please choose)" (get cfg-values configuration))]
               [:tr [:th {:style "padding-right:2em;"} [:h4 configuration]]
                [:td (widgets/drop-down drop-down-id drop-down-values)]
                [:td (widgets/add-button    language configuration drop-down-id)]
                [:td (widgets/remove-button language configuration drop-down-id)]])))
    ]))


(defn render-application-config
  [primary-language configuration subgroups config-values])

(defn render-cli-tool-config
  [primary-language configuration subgroups config-values])

(defn render-cron-job-config
  [primary-language configuration subgroups config-values])

(defn render-desktop-config
  [primary-language configuration subgroups config-values])

(defn render-configure-modules-form
  [app-type languages configurations subgroups config-values]
    (condp = app-type
        "microservice"
        (render-microservice-config (get languages "primary-language") configurations subgroups config-values)
        "web-application"
        (render-application-config (get languages "primary-language") configurations subgroups config-values)
        "desktop"
        (render-desktop-config (get languages "primary-language") configurations subgroups config-values)
        "cli-tool"
        (render-cli-tool-config (get languages "primary-language") configurations subgroups config-values)
        "cron-job"
        (render-cron-job-config (get languages "primary-language") configurations subgroups config-values)
    ))


(defn render-configure-modules-page
  [app-type app-type-label deployment-type languages configurations subgroups config-values]
  (page/xhtml
    (widgets/header "/" {:include-raphael true})
    [:body {:style "padding-top:50px"}
     [:div {:class "container",
            :style "width:90%"}
          (widgets/navigation-bar "/")
          [:h3 "Modules for " (render-app-type app-type-label) " written in " (render-languages languages)]
          [:br]
          (form/form-to
                {:name "service_configuration"}
                [:get "/finish"]
                [:div {:class "row"}
                [:input {:type "hidden" :name "app-type" :id "app-type" :value app-type}]
                [:input {:type "hidden" :name "deployment-type" :id "deployment-type" :value deployment-type}]
                [:input {:type "hidden" :name "primary-language" :id "primary-language" :value (get languages "primary-language")}]
                [:input {:type "hidden" :name "front-end-language" :id "front-end-language" :value (get languages "front-end-language")}]
                [:input {:type "hidden" :name "back-end-language" :id "back-end-language" :value (get languages "back-end-language")}]
                [:div {:class "column"}
                     widgets/canvas]
                [:div {:class "column"}
                     (render-configure-modules-form app-type
                                                    languages
                                                    configurations
                                                    subgroups
                                                    config-values)]
                ]
                [:div {:style "height: 5ex"}]
                (widgets/submit-button "Finish" "finish" "finish")
                [:span "&nbsp;"]
                widgets/back-button
                [:br])
          widgets/footer
          (let [langs-json (json/write-str languages)]
                [:script (str "window.onload = function() {
                              createPaper(640, 640);
                              drawAppSchema(paper, '" app-type "', " langs-json ");
                          }")]
            )
        ] ; </div class="container">
    ] ; </body>
  ))

(defn render-finish-construction-page
  "Render 'finish construction' page."
  [params model todo-list]
  (page/xhtml
    (widgets/header "/" {:include-raphael false})
    [:body
     [:div {:class "container"}
          (widgets/navigation-bar "/")
          [:h3 "Finish application construction"]
          [:div "The following tasks need to be performed in order to deploy application to selected cloud provider"]
          (for [todo-item todo-list]
            [:span
              widgets/short-vertical-separator
              [:div {:style "height: 2ex"}]
              [:h4  (:title todo-item)]
              [:div (:description todo-item)]
              (if (:list-of-files todo-item)
                [:span "List of added or changed files"
                 [:ol
                  (for [file (:list-of-files todo-item)]
                    [:li [:code file]])
                  ]
                 ]
                )
              (if (:command todo-item)
                [:span "Command(s) to be executed:"
                [:pre [:code (:command todo-item)]]])
              (if (:download todo-item)
                [:div [:a {:href (:download todo-item)} "Download available support files"]])
            ])
          widgets/tall-vertical-separator
           [:h3 "Model"]
           [:div [:pre
                  (with-out-str (pprint/pprint model))
           ]]
          widgets/tall-vertical-separator
          widgets/back-button
          [:br]
          widgets/footer
     ] ; </div class="container">
    ] ; </body>
  ))

