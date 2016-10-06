(ns chalk.database-screens.view.country
  (:require [re-frame.core :refer [subscribe]]))

(defn country-view-screen []
  (let [country (subscribe [:selected-country-details])]
    (fn []
     [:div "This is a specific country view"
      [:pre (.stringify js/JSON (clj->js @country))]])))
