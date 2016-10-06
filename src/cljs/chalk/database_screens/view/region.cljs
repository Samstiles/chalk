(ns chalk.database-screens.view.region
  (:require [re-frame.core :refer [subscribe]]))

(defn location-app-table-row [location]
  (let []
    (fn []
     [:div {:class "app-table-tr valign-wrapper"}
      [:div {:class "col s2 app-table-cell"} (:name location)]
      [:div {:class "col s2 app-table-cell"} "TODO"]
      [:div {:class "col s2 app-table-cell"} "TODO"]
      [:div {:class "col s2 app-table-cell"} "TODO"]
      [:div {:class "col s2 app-table-cell"} "TODO"]
      [:div {:class "col s2 app-table-cell"} "TODO"]])))

(defn region-view-screen []
  (let [region (subscribe [:selected-region-details])
        {:keys [name id]} @region
        locations (subscribe [:location-list])]
    (fn []
     [:div {:class "province-view"}
      [:div {:class "row"}
       [:div {:class "col s12"}
        [:div {:class "mapHeader"}
         [:h2 {:class "z-depth-1"} name]
         [:div {:id "provinceMap"}]]]]
      [:div {:class "row"}
       [:div {:class "app-table"}
        [:div {:class "app-table-head"}
         [:div {:class "col s2 app-table-column-head"} "Location Name"]
         [:div {:class "col s2 app-table-column-head"} "Total Crags"]
         [:div {:class "col s2 app-table-column-head"} "Total Climbs"]
         [:div {:class "col s2 app-table-column-head"} "Primary Style"]
         [:div {:class "col s2 app-table-column-head"} "Average Grade"]
         [:div {:class "col s2 app-table-column-head"} "Average Rating"]]
        [:div {:class "app-table-body"}
         (for [location (vals @locations)]
          ^{:key (:id location)}
          [location-app-table-row location])]]]])))
