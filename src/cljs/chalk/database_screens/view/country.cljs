(ns chalk.database-screens.view.country
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.entity-preview-card :refer [entity-preview-card]]
            [chalk.components.high-level-database-view :refer [high-level-database-view]]))

(defn country-view-screen []
  (let [country (subscribe [:selected-country-details])
        {:keys [short-summary name]} @country
        regions-by-country (subscribe [:regions-by-country (:id @country)])]
    (fn []
      [high-level-database-view @country @regions-by-country])))
