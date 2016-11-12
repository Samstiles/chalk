(ns chalk.database-screens.view.climb
  (:require [re-frame.core :refer [subscribe]]))

(defn climb-view-screen []
  (let [climb (subscribe [:selected-climb-details])
        {:keys [name short-summary]} @climb]
    (fn []
      [:div {:class "entity-view z-depth-1 climb-view"}
       [:div {:class "entity-summary"}
        [:div {:class "entity-text"}
         [:h3 name]
         [:hr]
         [:p short-summary]]
        [:div {:class "entity-images"}]]])))
