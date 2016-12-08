(ns chalk.components.nav
  (:require [chalk.components.button-link :refer [button-link]]
            [chalk.utils :as u]))

(defn nav []
  (fn []
    [:nav {:id "components--nav"}
     [:div {:class "nav-inner"}
      [:div {:class "logo"}
       [:a {:href "#" :class "logo-text"} (u/determine-app-name)]]
      [:div {:class "auth"}
       [button-link "#/signup" "primary-1" "Sign Up"]
       [button-link "#/signin" "primary-2" "Login"]]
      [:div {:class "links"}
       [:a {:href "#"} "Home"]
       [:a {:href "#/countries/1/regions/1/locations/1/sublocations/1/walls/1/climbs/1"} "Route Database"]
       [:a {:href "http://forums.climbeasterncanada.com" :target "_blank"} "Forums"]]]]))
