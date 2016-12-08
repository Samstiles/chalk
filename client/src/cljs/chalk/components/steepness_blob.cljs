(ns chalk.components.steepness-blob)

(defn steepness-blob [steepness]
  (fn [steepness]
    [:div {:id "components--steepness-blob"}
      [:div {:class "round-part"} [:img {:src (str "/img/steepness-" steepness ".png")}]]
      [:div {:class "below-part"} "Steepness"]]))
