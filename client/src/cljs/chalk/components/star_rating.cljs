(ns chalk.components.star-rating)

(defn star-rating [rating]
  (fn [rating]
    [:div {:id "components--star-rating"}
     [:div {:class "above-part"}
      [:div {:class "star star-1" :style {:opacity (if (< rating 1) 0.4 1)}}]
      [:div {:class "star star-2" :style {:opacity (if (< rating 2) 0.4 1)}}]
      [:div {:class "star star-3" :style {:opacity (if (< rating 3) 0.4 1)}}]
      [:div {:class "star star-4" :style {:opacity (if (< rating 4) 0.4 1)}}]
      [:div {:class "star star-5" :style {:opacity (if (< rating 5) 0.4 1)}}]]
     [:div {:class "below-part"} "Quality Consensus"]]))
