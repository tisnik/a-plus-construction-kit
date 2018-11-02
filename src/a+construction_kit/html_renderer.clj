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

(ns a+construction-kit.html-renderer
    "Module that contains functions used to render HTML pages sent back to the browser.

    Author: Pavel Tisnovsky")


(require '[hiccup.core  :as hiccup])
(require '[hiccup.page  :as page])
(require '[hiccup.form  :as form])

(require '[a+construction-kit.html-renderer-widgets :as widgets])



(defn render-select-app-page
    "Render front page of this application."
    [app-types]
    (page/xhtml
        (widgets/header "/" {:include-calendar? true})
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

(defn render-select-language-page
    [app-type app-type-label app-parts app-languages]
    (page/xhtml
        (widgets/header "/" {:include-calendar? true})
        [:body
            [:div {:class "container"}
                (widgets/navigation-bar "/")
                [:h3 "Language selection for " app-type-label]
                [:br]
                (form/form-to {:name "inputForm"} [:get "/configure-modules"]
                    [:input {:type "hidden" :name "app-type" :id "app-type" :value app-type}]
                    (if app-parts
                        (for [part app-parts]
                            [:span
                                [:h4 part]
                                (for [language (get app-languages part)]
                                    (widgets/radio-button (str part "-language") false language language (str "language_part_selected('" part "'," (count app-parts) ")")))
                                [:br]])
                        (for [language app-languages]
                            (widgets/radio-button "primary-language" false language language "enable_next_button()")))
                        [:br]
                        (widgets/disabled-submit-button "Next" "next" "next"))
                [:div {:style "height: 10ex"}]
                (widgets/footer)
            ] ; </div class="container">
        ] ; </body>
))

