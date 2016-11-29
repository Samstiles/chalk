(ns chalk.components.nav
  (:require [re-frame.core :refer [subscribe]]))

(defn nav []
  (let [app-name (subscribe [:app-name])
        active-screen (subscribe [:active-screen])
        in-db? (subscribe [:in-db?])]
    (fn []
      [:div {:class "navbar-fixed"}
       [:nav {:class "blue-grey darken-2"}
        [:div {:class "nav-wrapper"}
         [:a {:href "#" :class "logo"} @app-name]
         [:ul {:class "right"}
          [:li {:class (when (= :home-screen @active-screen) "active")}
           [:a {:href "#" :class "waves-effect waves-light"} "Home"]]
          [:li {:class (when @in-db? "active")}
           [:a {:href "#/countries/1/regions/1/locations/1/sublocations/1/walls/1/climbs/1" :class "waves-effect waves-light"} "Route Database"]]
          [:li
           [:a {:href "#" :class "waves-effect waves-light"} "Forums"]]
          [:li
           [:a {:href "#" :class "waves-effect waves-light btn"} "Log In"]]
          [:li
           [:a {:href "#" :class "waves-effect waves-light btn"} "Sign Up"]]]]]])))
