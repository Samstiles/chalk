(ns client.log)

(defn l
  ([message] (.log js/console message))
  ([message & body] (.log js/console message body)))
