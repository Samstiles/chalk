(ns chalk.components.breadcrumbs
  (:require [chalk.utils :as u]
            [re-frame.core :refer [subscribe]]))

(defn breadcrumb [entity]
  (fn []
    [:a {:id "components--breadcrumb" :href (u/build-entity-path entity) :class "breadcrumb"} (:name entity)
     [:div {:class "caret"}]]))

(defn breadcrumbs []
 (let [selection-details (subscribe [:selection-details])]
   (fn []
    (let [{:keys [country region location sublocation wall climb]} @selection-details]
     [:div {:id "components--breadcrumbs"}
      [:div {:class "breadcrumbs-inner"}
       (when country [breadcrumb country])
       (when region [breadcrumb region])
       (when location [breadcrumb location])
       (when sublocation [breadcrumb sublocation])
       (when wall [breadcrumb wall])
       (when climb [breadcrumb climb])]]))))
