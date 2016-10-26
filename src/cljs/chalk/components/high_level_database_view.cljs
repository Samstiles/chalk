(ns chalk.components.high-level-database-view
  (:require [chalk.components.entity-preview-card :refer [entity-preview-card]]))

(defn high-level-database-view [entity children]
  (let [{:keys [name short-summary]} entity]
    (fn []
      [:div {:class "entity-view z-depth-1"}
       [:div {:class "entity-summary"}
        [:h1 name]
        [:hr]
        [:p short-summary]
        [:hr]
        [:h3 "Children"]]
       [:div {:class "entity-child-list"}
         [:div {:class "row"}
           (for [child children]
             ^{:key (:id child)}
             [entity-preview-card child])]]])))
