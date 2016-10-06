(ns chalk.screens
    (:require [re-frame.core :as re-frame :refer [subscribe]]
              [chalk.components.breadcrumbs :refer [breadcrumbs]]
              [chalk.components.nav :refer [nav]]
              [chalk.misc-screens.home :refer [home-screen]]
              [chalk.database-screens.create-edit.country :refer [country-create-edit-screen]]
              [chalk.database-screens.create-edit.region :refer [region-create-edit-screen]]
              [chalk.database-screens.create-edit.location :refer [location-create-edit-screen]]
              [chalk.database-screens.create-edit.sublocation :refer [sublocation-create-edit-screen]]
              [chalk.database-screens.create-edit.wall :refer [wall-create-edit-screen]]
              [chalk.database-screens.create-edit.climb :refer [climb-create-edit-screen]]
              [chalk.database-screens.view.country :refer [country-view-screen]]
              [chalk.database-screens.view.region :refer [region-view-screen]]
              [chalk.database-screens.view.location :refer [location-view-screen]]
              [chalk.database-screens.view.sublocation :refer [sublocation-view-screen]]
              [chalk.database-screens.view.wall :refer [wall-view-screen]]
              [chalk.database-screens.view.climb :refer [climb-view-screen]]))

(defmulti screens identity)

;; misc screens

(defmethod screens :home-screen [] [home-screen])

;; database views

(defmethod screens :country-view-screen [] [country-view-screen])
(defmethod screens :region-view-screen [] [region-view-screen])
(defmethod screens :location-view-screen [] [location-view-screen])
(defmethod screens :sublocation-view-screen [] [sublocation-view-screen])
(defmethod screens :wall-view-screen [] [wall-view-screen])
(defmethod screens :climb-view-screen [] [climb-view-screen])

(defmethod screens :country-create-edit-screen [] [country-create-edit-screen])
(defmethod screens :region-create-edit-screen [] [region-create-edit-screen])
(defmethod screens :location-create-edit-screen [] [location-create-edit-screen])
(defmethod screens :sublocation-create-edit-screen [] [sublocation-create-edit-screen])
(defmethod screens :wall-create-edit-screen [] [wall-create-edit-screen])
(defmethod screens :climb-create-edit-screen [] [climb-create-edit-screen])

(defmethod screens :default [] [home-screen])

(defn show-screen
  [screen-name]
  [screens screen-name])

(defn main-screen []
  (let [active-screen (subscribe [:active-screen])]
   (fn []
    [:div {:id "app-root"}
     [nav]
     [:div {:class "app-container container"}
      [breadcrumbs]
      [show-screen @active-screen]]])))
