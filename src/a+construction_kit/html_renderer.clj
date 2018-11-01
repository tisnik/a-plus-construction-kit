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



(defn render-front-page
    "Render front page of this application."
    []
    (page/xhtml
        (widgets/header "/" {:include-calendar? true})
        [:body
            [:div {:class "container"}
                (widgets/navigation-bar "/")
                [:h3 "Vstup do systému"]
                (form/form-to {:name "inputForm"} [:get "/areals"]
                    [:span "Platnost od:"] "&nbsp;"
                    [:br]
                    [:br]
                    "&nbsp;"
                    [:a {:href "/help_valid_from_settings"} [:img {:src "icons/help.gif"}]]
                    [:br]
                    [:br]
                    [:button {:type "submit" :class "btn btn-success" :style "width:10em"} "Seznam areálů"]
                )
                [:div {:style "height: 10ex"}]
                (widgets/footer)
            ] ; </div class="container">
        ] ; </body>
))

