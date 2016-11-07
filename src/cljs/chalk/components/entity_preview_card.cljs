(ns chalk.components.entity-preview-card
  (:require [chalk.utils :as u]))

(defn entity-preview-card [entity]
  (let [{:keys [preview-image-url name id short-summary]} entity]
    (fn []
      [:div {:class "preview-card col s4"}
       [:div {:class "card"}
        [:div {:class "card-image preview-card-image"
               :style {:background-image (str "url('" preview-image-url "')")}}
         [:span {:class "card-title"} name]]
        [:div {:class "card-content"}
         [:p short-summary]]
        [:div {:class "card-action"}
         [:a {:href (u/build-entity-path entity)} (str "View " name)]]]])))
