(ns chalk.components.region-preview-card
  (:require [chalk.utils :as u]))

(defn region-preview-card [region]
  (let [{:keys [preview-image-url name id short-summary]} region]
    (fn []
      [:div {:class "region-preview-card col s6"}
       [:div {:class "card"}
        [:div {:class "card-image region-preview-card-image"}
         [:img {:src preview-image-url}]
         [:span {:class "card-title"} name]]
        [:div {:class "card-content"}
         [:p short-summary]]
        [:div {:class "card-action"}
         [:a {:href (u/build-entity-path region)} (str "View " name " Climbing Locations")]]]])))
