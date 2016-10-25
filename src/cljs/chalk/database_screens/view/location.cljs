(ns chalk.database-screens.view.location
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.entity-preview-card :refer [entity-preview-card]]))

(defn location-view-screen []
  (let [location (subscribe [:selected-location-details])
        {:keys [short-summary name]} @location
        sublocations-by-location (subscribe [:sublocations-by-location (:id @location)])]
    (fn []
      [:div {:class "entity-view z-depth-1"}
       [:div {:class "entity-summary"}
        [:h1 name]
        [:hr]
        [:p short-summary]
        [:hr]
        [:h3 (str name " Crags")]]
       [:div {:class "entity-child-list"}
         [:div {:class "row"}
           (for [sublocation @sublocations-by-location]
             ^{:key (:id sublocation)}
             [entity-preview-card sublocation])]]])))
