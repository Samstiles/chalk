(ns chalk.components.ajax-cover
  (:require [re-frame.core :as rf]
            [chalk.components.loading-cube :as lc]))

(defn ajax-cover []
  (fn []
    [:div {:id "components--ajax-cover"}
     [:div.loading-info
      [lc/loading-cube]
      [:h3 "Loading"]]]))
