(ns chalk.utils)

(defn build-entity-path [entity entity-kw]
  (let [{:keys [country region location sublocation wall climb]} entity]
    (str "#"

     (when (or (= entity-kw :country) country)
      (str "/countries/" (if (= entity-kw :country) (:id entity) (:country entity))))

     (when (or (= entity-kw :region) region)
      (str "/regions/" (if (= entity-kw :region) (:id entity) country)))

     (when (or (= entity-kw :location) location)
      (str "/locations/" (if (= entity-kw :location) (:id entity) country)))

     (when (or (= entity-kw :sublocation) sublocation)
      (str "/sublocations/" (if (= entity-kw :sublocation) (:id entity) country)))

     (when (or (= entity-kw :wall) wall)
      (str "/walls/" (if (= entity-kw :wall) (:id entity) country)))

     (when (or (= entity-kw :climb) climb)
      (str "/climbs/" (if (= entity-kw :climb) (:id entity) country))))))

(defn determine-app-name []
  (let [url (-> js/window .-location .-href)
        cec? (> (.indexOf url "climbeasterncanada.com") -1)]
   (if cec?
     "CEC" "Chalk")))
