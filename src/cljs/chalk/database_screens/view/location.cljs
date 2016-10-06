(ns chalk.database-screens.view.location
  (:require [re-frame.core :refer [subscribe]]))

(defn location-view-screen []
  (let [location (subscribe [:selected-location-details])]
    (fn []
     [:div "This is a specific location view"
      [:pre (.stringify js/JSON (clj->js @location))]])))
