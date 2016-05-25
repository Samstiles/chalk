(ns client.config
  (:require [devtools.core :as devtools]))

(def debug?
  ^boolean js/goog.DEBUG)
 
(when debug?
  (enable-console-print!)
  (devtools/install!))
