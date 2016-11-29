(ns chalk.database-screens.climb.view
  (:require [chalk.components.fact-blob :refer [fact-blob]]
            [chalk.components.steepness-blob :refer [steepness-blob]]
            [chalk.components.star-rating :refer [star-rating]]))

(defn climb-view []
  (fn []
    [:div {:id "components--climb-view"}
     [:div {:class "climb-details"}

      [:div {:class "climb-gallery"}
       [:div {:class "gallery-preview-img"}
        [:a {:href "#"}
         [:img {:src "/img/bg.jpg"}]
         [:div {:class "gallery-open"} "Climb gallery"]]]]


      [:div {:class "climb-information"}
       [:div {:class "quick-facts"}
        [fact-blob "5.11d" "Grade"]
        [fact-blob "Trad." "Style"]
        [fact-blob "22 m" "Height"]
        [steepness-blob "2"]
        [star-rating 4]]]]

       ;;[:div {:class "climb-text"}
        ;;[:h2 "Climb Name"]
        ;;[:div {:class "short-location"}]]]]

     [:div {:class "climb-map"}]
     [:div {:class "climb-comments"}]]))
