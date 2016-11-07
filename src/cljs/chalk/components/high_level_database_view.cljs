(ns chalk.components.high-level-database-view
  (:require [chalk.components.entity-preview-card :refer [entity-preview-card]]))

(defn high-level-database-view [entity children entity-children-name]
  (let [{:keys [name short-summary]} entity]
    (fn []
      [:div {:class "entity-view z-depth-1"}
       [:div {:class "entity-summary"}
        [:h3 name]
        [:hr]
        [:p short-summary]
        [:hr]
        [:h3 entity-children-name]]
       [:div {:class "entity-child-list"}
         [:div {:class "row"}
           (for [child children]
             ^{:key (:id child)}
             [entity-preview-card child])]]])))
