(ns chalk.database-screens.climb.view
  (:require [chalk.components.fact-blob :refer [fact-blob]]
            [chalk.components.steepness-blob :refer [steepness-blob]]
            [chalk.components.star-rating :refer [star-rating]]))

(defn climb-view []
  (fn []
    [:div {:id "components--climb-view"}
     [:div {:class "climb-details"}

      [:div {:class "climb-gallery"}
       [:div {:class "gallery-preview-img"}
        [:a {:href "#"}
         [:img {:src "/img/bg.jpg"}]
         [:div {:class "gallery-open"} "Climb gallery"]]]]

      [:div {:class "climb-information"}
       [:div {:class "quick-facts"}
        [fact-blob "5.11d" "Grade"]
        [fact-blob "Sport" "Style"]
        [fact-blob "22 m" "Height"]
        [steepness-blob "2"]
        [star-rating 4]]]

      [:div {:class "climb-text"}
       [:h2 {:class "climb-name"} "It's A Way Of Life"]
       [:h3 {:class "climb-quick-location"} "FMA: A. Morgan " " | " " FFA: L. Belyea"]
       [:p {:class "climb-description"} "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales, neque et accumsan consectetur, ex enim condimentum neque, ut faucibus tellus purus at orci. Maecenas a tortor ut ex porta iaculis. Aliquam ut iaculis mauris. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."]
       [:p {:class "climb-history"} "Duis a posuere arcu, id ultrices velit. Sed sit amet erat sed magna malesuada maximus molestie sit amet augue. Ut in dui sit amet nibh feugiat congue ac quis sem. Proin accumsan lacus luctus tempus."]
       [:p {:class "climb-location"} "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur sodales, neque et accumsan consectetur, ex enim condimentum neque, ut faucibus tellus purus at orci. Maecenas a tortor ut ex porta iaculis. Aliquam ut iaculis mauris. Cum sociis natoque penatibus et magnis dis parturient."]

       [:div {:class "climb-text-separator"}]

       [:div {:class "climb-data"}
        [:ul
         [:li [:span "Moves: "] "Bouldery + Technical"]
         [:li [:span "Anchors: "] "Rap Rings"]
         [:li [:span "Stays Dry: "] "Never"]
         [:li [:span "Database views: "] "7,388"]
         [:li [:span "Database entry by: "] "Sam Stiles"]
         [:li [:span "Added to database: "] "Jan 27, 2016"]
         [:li [:span "Last edited: "] "Mar 05, 2016"]]]]]

     #_[:div {:class "climb-map"}]
     [:div {:class "climb-comments"}
      [:ul
       [:li {:class "comment"}]]]]))
