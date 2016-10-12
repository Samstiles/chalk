(ns chalk.database-screens.view.country
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.region-preview-card :refer [region-preview-card]]))

(defn country-view-screen []
  (let [country (subscribe [:selected-country-details])
        {:keys [short-summary name]} @country
        regions-by-country (subscribe [:regions-by-country (:id @country)])]
    (fn []
      [:div {:class "country-view z-depth-1"}
       [:div {:class "country-summary"}
        [:h1 name]
        [:hr]
        [:p short-summary]
        [:hr]
        [:h3 "Provinces"]]
       [:div {:class "country-child-list"}
         [:div {:class "row"}
           (for [region @regions-by-country]
             ^{:key (:id region)}
             [region-preview-card region])]]])))
