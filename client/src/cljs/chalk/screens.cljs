(ns chalk.screens
    (:require [re-frame.core :as re-frame :refer [subscribe]]
              [chalk.components.breadcrumbs :refer [breadcrumbs]]
              [chalk.components.nav :refer [nav]]
              [chalk.misc-screens.home :refer [home-screen]]
              [chalk.database-screens.climb.view :refer [climb-view]]))

(defmulti screens identity)

;; misc screens

(defmethod screens :climb-view [] [climb-view])
(defmethod screens :home-screen [] [home-screen])
(defmethod screens :default [] [home-screen])

(defn show-screen
  [screen-name]
  [screens screen-name])

(defn main-screen []
  (let [active-screen (subscribe [:active-screen])
        in-db? (subscribe [:in-db?])]
   (fn []
    [:div {:id "app-root"}
     [nav]
     (when @in-db? [breadcrumbs])
     [show-screen @active-screen]])))
