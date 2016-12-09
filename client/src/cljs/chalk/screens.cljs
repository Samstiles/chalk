(ns chalk.screens
  (:require [re-frame.core :as re-frame :refer [subscribe]]
            [chalk.components.breadcrumbs :refer [breadcrumbs]]
            [chalk.components.nav :refer [nav]]
            [chalk.components.ajax-cover :refer [ajax-cover]]
            [chalk.screens.home :refer [home-screen]]
            [chalk.screens.database.climb.view :refer [climb-view-screen]]
            [chalk.screens.authentication.signup :refer [signup-screen]]
            [chalk.screens.authentication.signin :refer [signin-screen]]))

(defmulti screens identity)

;; misc screens

(defmethod screens :climb-view [] [climb-view-screen])
(defmethod screens :home-screen [] [home-screen])
(defmethod screens :signup [] [signup-screen])
(defmethod screens :signin [] [signin-screen])
(defmethod screens :default [] [home-screen])

(defn show-screen
  [screen-name]
  [screens screen-name])

(defn main-screen []
  (let [active-screen (subscribe [:active-screen])
        in-db? (subscribe [:in-db?])
        ajax-happening? (subscribe [:ajax-happening?])]
    (fn []
      [:div {:id "app-root"}
       [nav]
       (when @in-db? [breadcrumbs])
       (when @ajax-happening? [ajax-cover])
       [show-screen @active-screen]])))
