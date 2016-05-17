(ns prototype.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [prototype.handlers]
              [prototype.subs]
              [prototype.views :as views]
              [prototype.config :as config]))

(when config/debug?
  (println "dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
