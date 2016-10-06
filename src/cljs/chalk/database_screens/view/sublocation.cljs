(ns chalk.database-screens.view.sublocation
  (:require [re-frame.core :refer [subscribe]]))

(defn sublocation-view-screen []
  (let [sublocation (subscribe [:selected-sublocation-details])]
    (fn []
     [:div "This is a specific sublocation view"
      [:pre (.stringify js/JSON (clj->js @sublocation))]])))
