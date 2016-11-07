(ns chalk.database-screens.view.region
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.entity-preview-card :refer [entity-preview-card]]
            [chalk.components.high-level-database-view :refer [high-level-database-view]]))

(defn region-view-screen []
  (let [region (subscribe [:selected-region-details])
        {:keys [short-summary name]} @region
        locations-by-region (subscribe [:locations-by-region (:id @region)])]
    (fn []
      [high-level-database-view @region @locations-by-region "Climbing Locations"])))
