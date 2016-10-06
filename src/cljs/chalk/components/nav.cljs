(ns chalk.components.nav
  (:require [re-frame.core :refer [subscribe]]))

(defn nav []
  (let [app-name (subscribe [:app-name])]
    (fn []
      [:div {:class "navbar-fixed"}
       [:nav {:class "blue-grey darken-2"}
        [:div {:class "nav-wrapper"}
         [:a {:href "#" :class "logo"} @app-name]
         [:ul {:class "right"}
          [:li
           [:a {:href "#" :class "waves-effect waves-light"} "News"]]
          [:li {:class "active"}
           [:a {:href "#" :class "waves-effect waves-light"} "Database"]]
          [:li
           [:a {:href "#" :class "waves-effect waves-light"} "Forums"]]
          [:li
           [:a {:href "#" :class "waves-effect waves-light btn"} "Log In"]]
          [:li
           [:a {:href "#" :class "waves-effect waves-light btn"} "Sign Up"]]]]]])))
