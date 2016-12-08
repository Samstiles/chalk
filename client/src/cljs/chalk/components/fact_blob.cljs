(ns chalk.components.fact-blob)

(defn fact-blob [fact fact-key]
  (fn [fact fact-key]
    [:div {:id "components--fact-blob"}
      [:div {:class "round-part"} fact]
      [:div {:class "below-part"} fact-key]]))
