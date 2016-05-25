(ns client.utils)

(defn json-to-clj
  [json]
  (js->clj json :keywordize-keys true))
