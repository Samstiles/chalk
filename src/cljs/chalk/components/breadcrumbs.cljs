(ns chalk.components.breadcrumbs
  (:require [chalk.utils :as u]
            [re-frame.core :refer [subscribe]]))

(defn breadcrumb [entity]
  (fn []
    [:a {:href (u/build-entity-path entity) :class "breadcrumb"} (:name entity)]))

(defn breadcrumbs []
 (let [selection-details (subscribe [:selection-details])]
   (fn []
    (let [{:keys [country region location sublocation wall climb]} @selection-details]
     [:div {:class "row db-breadcrumbs blue-grey lighten-1"}
       [:div {:class "nav-wrapper"}
        [:div {:class "col s12 db-breadcrumbs"}
         (when country [breadcrumb country])
         (when region [breadcrumb region])
         (when location [breadcrumb location])
         (when sublocation [breadcrumb sublocation])
         (when wall [breadcrumb wall])
         (when climb [breadcrumb climb])]]]))))
