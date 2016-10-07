(ns chalk.handlers
    (:require [re-frame.core :as re-frame :refer [reg-event-db debug trim-v]]
              [clojure.string :as str]
              [chalk.db :as db]
              [chalk.interceptors :as i :refer [debug-verbose]]))

(defn- db-route? [route]
  (str/includes? (name route) "-view-screen"))

(reg-event-db
 :initialize-db
 [debug-verbose trim-v]
 (fn  [_ _]
   db/default-db))

(reg-event-db
 :set-active-screen
 [debug-verbose trim-v]
 (fn [db [active-screen]]
   (assoc db :active-screen active-screen
             :in-db? (db-route? active-screen))))

(reg-event-db
  :update-selections
  [debug-verbose trim-v]
  (fn [db [selection selection-type]]
    (let [{:keys [country region location sublocation wall climb]} selection
          updated-selections {:country (if (= selection-type :country) (:id selection) (or country nil))
                              :region (if (= selection-type :region) (:id selection) (or region nil))
                              :location (if (= selection-type :location) (:id selection) (or location nil))
                              :sublocation (if (= selection-type :sublocation) (:id selection) (or sublocation nil))
                              :wall (if (= selection-type :wall) (:id selection) (or wall nil))
                              :climb (if (= selection-type :climb) (:id selection) (or climb nil))}]
      (.log js/console "UPDATED SELECTIONS" updated-selections)
      (assoc db :selections updated-selections))))
