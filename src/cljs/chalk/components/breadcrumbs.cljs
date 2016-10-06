(ns chalk.components.breadcrumbs
  (:require [chalk.utils :as u]
            [re-frame.core :refer [subscribe]]))

(defn breadcrumb [entity entity-type]
  (fn []
    [:a {:href (u/build-entity-path entity entity-type) :class "breadcrumb"} (:name entity)]))

(defn breadcrumbs []
 (let [selection-details (subscribe [:selection-details])]
   (fn []
    (let [{:keys [country region location sublocation wall climb]} @selection-details
          _ (.log js/console ">>>>>>>>>" @selection-details)]
     [:div {:class "row"}
      [:nav {:class "blue-grey lighten-1"}
       [:div {:class "nav-wrapper"}
        [:div {:class "col s12"}
         (when country [breadcrumb country :country])
         (when region [breadcrumb region :region])
         (when location [breadcrumb location :location])
         (when sublocation [breadcrumb sublocation :sublocation])
         (when wall [breadcrumb wall :wall])
         (when climb [breadcrumb climb :climb])]]]]))))
