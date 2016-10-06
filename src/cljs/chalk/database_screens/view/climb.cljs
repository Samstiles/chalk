(ns chalk.database-screens.view.climb
  (:require [re-frame.core :refer [subscribe]]))

(defn climb-view-screen []
  (let [climb (subscribe [:selected-climb-details])]
    (fn []
     [:div "This is a specific climb view"
      [:pre (.stringify js/JSON (clj->js @climb))]])))
