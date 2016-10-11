(ns chalk.database-screens.view.region
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.location-preview-card :refer [location-preview-card]]))

(defn region-view-screen []
  (let [region (subscribe [:selected-region-details])
        {:keys [short-summary]} @region
        locations-by-region (subscribe [:locations-by-region (:id @region)])]
    (fn []
      [:div {:class "region-view z-depth-1"}
       [:div {:class "region-summary"}
        [:h1 "Canada"]
        [:hr]
        [:h3 "Summary"]
        [:p short-summary]
        [:hr]
        [:h3 "Climbing Locations"]]
       [:div {:class "region-child-list"}
         [:div {:class "row"}
           (for [location @locations-by-region]
             ^{:key (:id location)}
             [location-preview-card location])]]])))
