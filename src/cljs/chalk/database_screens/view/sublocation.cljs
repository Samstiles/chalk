(ns chalk.database-screens.view.sublocation
  (:require [re-frame.core :refer [subscribe]]
            [chalk.components.high-level-database-view :refer [high-level-database-view]]))

(defn sublocation-view-screen []
  (let [sublocation (subscribe [:selected-sublocation-details])
        {:keys [short-summary name]} @sublocation
        walls-by-sublocation (subscribe [:walls-by-sublocation (:id @sublocation)])]
    (fn []
      [high-level-database-view @sublocation @walls-by-sublocation])))
