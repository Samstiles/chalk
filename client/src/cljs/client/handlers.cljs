(ns client.handlers
    (:require [re-frame.core :as re-frame :refer [register-handler debug]]
              [client.db :as db]
              [client.log :refer [l]]))

(register-handler
 :initialize-db
 debug
 (fn init-db-handler [_ _]
   db/default-db))

(register-handler
 :users-location-determined
 debug
 (fn users-loc-determined-handler [db [_ location]]
   (assoc db :location location)))

(register-handler
 :set-active-panel
 debug
 (fn set-active-panel-handler [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
