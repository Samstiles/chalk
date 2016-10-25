(ns chalk.database-screens.view.region
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.entity-preview-card :refer [entity-preview-card]]))

(defn region-view-screen []
  (let [region (subscribe [:selected-region-details])
        {:keys [short-summary name]} @region
        locations-by-region (subscribe [:locations-by-region (:id @region)])]
    (fn []
      [:div {:class "entity-view z-depth-1"}
       [:div {:class "entity-summary"}
        [:h1 name]
        [:hr]
        [:p short-summary]
        [:hr]
        [:h3 "Climbing Locations"]]
       [:div {:class "entity-child-list"}
         [:div {:class "row"}
           (for [location @locations-by-region]
             ^{:key (:id location)}
             [entity-preview-card location])]]])))
