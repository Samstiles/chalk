(ns chalk.database-screens.view.wall
  (:require [re-frame.core :refer [subscribe]]))

(defn wall-view-screen []
  (let [wall (subscribe [:selected-wall-details])]
    (fn []
     [:div "This is a specific wall view"
      [:pre (.stringify js/JSON (clj->js @wall))]])))
