(ns chalk.database-screens.view.wall
  (:require [re-frame.core :refer [subscribe]]
            [chalk.logs :refer [i]]))

(defn wall-view-screen []
  (let [wall (subscribe [:selected-wall-details])
        climbs (subscribe [:climbs-by-wall (:id @wall)])
        {:keys [name short-summary]} @wall]
    (fn []
     [:div {:class "entity-view z-depth-1 wall-view"}
      [:div {:class "entity-summary"}
       [:h3 name]
       [:hr]
       [:p short-summary]]
      [:div {:class "climb-table"}
       [:table {:class "bordered highlight"}
        [:thead {:class "climb-table-head"}
         [:tr
          [:th "Climb"]
          [:th "Grade"]
          [:th "Rating"]
          [:th "Actions"]]]
        [:tbody
         (for [climb @climbs]
           (let [{:keys [id name grade rating]} climb]
             ^{:key id}
             [:tr {:class "climb-table-row"}
              [:td name]
              [:td grade]
              [:td rating]
              [:td [:a {:href "#" :class "waves-effect waves-light btn"} "View"]]]))]]]])))
