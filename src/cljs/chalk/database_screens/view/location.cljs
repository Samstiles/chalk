(ns chalk.database-screens.view.location
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.entity-preview-card :refer [entity-preview-card]]
            [chalk.components.high-level-database-view :refer [high-level-database-view]]))

(defn location-view-screen []
  (let [location (subscribe [:selected-location-details])
        {:keys [short-summary name]} @location
        sublocations-by-location (subscribe [:sublocations-by-location (:id @location)])]
    (fn []
      [high-level-database-view @location @sublocations-by-location])))
