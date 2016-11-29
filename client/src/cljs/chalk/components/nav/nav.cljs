(ns chalk.components.nav
  (:require [chalk.components.button-link :refer [button-link]]))

(defn nav []
  (fn []
    [:nav {:id "components--nav"}
      [:div {:class "nav-inner"}
        [:div {:class "logo"}
          [:a {:href "#" :class "logo-text"} "Chalk"]]
        [:div {:class "auth"}
          [button-link "#" "primary-1" "Sign Up"]
          [button-link "#" "primary-2" "Login"]]
        [:div {:class "links"}
          [:a {:class "active" :href "#"} "Home"]
          [:a {:href "#"} "Route Database"]
          [:a {:href "#"} "Forums"]]]]))
