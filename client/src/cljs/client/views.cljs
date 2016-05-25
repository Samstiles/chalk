(ns client.views
    (:require [re-frame.core :as re-frame]
              [client.log :refer [l]]))

(defn home-panel []
  (let [name (re-frame/subscribe [:name])
        location (re-frame/subscribe [:location])]
    (fn home-panel-render-fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [:div [:a {:href "#/about"} "go to About Page"]]
       (when (not (empty? @location))
         [:div (str "Location... Country: " (:country @location) ", Region: " (:region @location))])])))

(defn about-panel []
  (fn about-panel-render-fn []
    [:div "This is the About Page."
     [:div [:a {:href "#/"} "go to Home Page"]]]))

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn main-panel-render-fn []
      [show-panel @active-panel])))
