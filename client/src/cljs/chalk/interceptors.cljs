(ns chalk.interceptors
  (:require [re-frame.interceptor :refer [->interceptor get-effect get-coeffect]]
            [re-frame.loggers :refer [console]]
            [clojure.data :as data]))

(def debug-verbose
  (->interceptor
    :id :debug-verbose
    :before (fn debug-before
              [context]
              (console :log "\n\n________________________________________________________________________\n\n\nHandling re-frame event:" (get-coeffect context :event))
              context)
    :after  (fn debug-after
              [context]
              (let [event (get-coeffect context :event)
                    orig-db (get-coeffect context :db)
                    new-db (get-effect   context :db ::not-found)]
                (if (= new-db ::not-found)
                  (console :log "No :db changes caused by:" event)
                  (do (console :group "\nDB changes from this event:")
                      (console :log "Before:" orig-db)
                      (console :log "After :" new-db)
                      (console :groupEnd)))
                context))))
