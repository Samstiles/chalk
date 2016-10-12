(ns chalk.components.sublocation-preview-card
  (:require [chalk.utils :as u]))

(defn sublocation-preview-card [sublocation]
  (let [{:keys [preview-image-url name id short-summary]} sublocation]
    (fn []
      [:div {:class "sublocation-preview-card col s6"}
       [:div {:class "card"}
        [:div {:class "card-image sublocation-preview-card-image"}
         [:img {:src preview-image-url}]
         [:span {:class "card-title"} name]]
        [:div {:class "card-content"}
         [:p short-summary]]
        [:div {:class "card-action"}
         [:a {:href (u/build-entity-path sublocation)} (str "View " name)]]]])))
