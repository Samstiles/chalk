(ns chalk.components.location-preview-card
  (:require [chalk.utils :as u]))

(defn location-preview-card [location]
  (let [{:keys [preview-image-url name id short-summary]} location]
    (fn []
      [:div {:class "location-preview-card col s6"}
       [:div {:class "card"}
        [:div {:class "card-image location-preview-card-image"}
         [:img {:src preview-image-url}]
         [:span {:class "card-title"} name]]
        [:div {:class "card-content"}
         [:p short-summary]]
        [:div {:class "card-action"}
         [:a {:href (u/build-entity-path location)} (str "View the " name " climbing area")]]]])))
