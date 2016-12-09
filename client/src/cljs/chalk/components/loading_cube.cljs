(ns chalk.components.loading-cube)

(defn loading-cube []
  (fn []
    [:div {:id "components--loading-cube"}
     [:div.sk-folding-cube
      [:div.sk-cube1.sk-cube]
      [:div.sk-cube2.sk-cube]
      [:div.sk-cube4.sk-cube]
      [:div.sk-cube3.sk-cube]]]))
