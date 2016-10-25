(ns chalk.database-screens.view.country
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.entity-preview-card :refer [entity-preview-card]]))

(defn country-view-screen []
  (let [country (subscribe [:selected-country-details])
        {:keys [short-summary name]} @country
        regions-by-country (subscribe [:regions-by-country (:id @country)])]
    (fn []
      [:div {:class "entity-view z-depth-1"}
       [:div {:class "entity-summary"}
        [:h1 name]
        [:hr]
        [:p short-summary]
        [:hr]
        [:h3 "Provinces"]]
       [:div {:class "entity-child-list"}
         [:div {:class "row"}
           (for [region @regions-by-country]
             ^{:key (:id region)}
             [entity-preview-card region])]]])))
