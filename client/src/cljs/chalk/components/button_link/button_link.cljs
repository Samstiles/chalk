(ns chalk.components.button-link)

(defn button-link [href color text]
  (fn [href color text]
    [:a {:href href
         :id "components--button-link"
         :class color} text]))
