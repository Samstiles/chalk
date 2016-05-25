(ns client.ajax
  (:require [ajax.core :as ajax :refer [GET POST PUT]]
            [re-frame.core :refer [dispatch]]
            [client.log :refer [l]]
            [client.utils :as utils]))

;;;;;;;;;;;;;;;;;;;;;;;; Generic Fns ;;;;;;;;;;;;;;;;;;;;;;;;

(defn ajax
  [action url opts]
  (let [updated-opts (assoc opts :keywords? true :response-format :json)]
    (action url updated-opts)))

;;;;;;;;;;;;;;;;;;;;;;;; Middleware ;;;;;;;;;;;;;;;;;;;;;;;;

(defn js-to-clj-middleware
  [response]
  (js->clj response :keywordize-keys true))

;;;;;;;;;;;;;;;;;;;;; Generic Handlers ;;;;;;;;;;;;;;;;;;;;;

(defn handler
  [response handler-fn]
  (let [middlewared-response (-> response js-to-clj-middleware)]
    (handler-fn middlewared-response)))

(defn error-handler
  []
  nil)

;;;;;;;;;;;; Misc (not-entity-specific) ajax fns ;;;;;;;;;;;;

(defn get-users-location-by-ip
  []
  (ajax GET "http://ipinfo.io"
    {:handler #(handler % (fn [response] (dispatch [:users-location-determined response])))}))
