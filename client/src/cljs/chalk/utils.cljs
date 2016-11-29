(ns chalk.utils)

(defn build-entity-path [entity]
  (let [{:keys [country region location sublocation wall climb entity-type]} entity]
    (str "#"

     (when (or (= entity-type :country) country)
      (str "/countries/" (if (= entity-type :country) (:id entity) (:country entity))))

     (when (or (= entity-type :region) region)
      (str "/regions/" (if (= entity-type :region) (:id entity) country)))

     (when (or (= entity-type :location) location)
      (str "/locations/" (if (= entity-type :location) (:id entity) country)))

     (when (or (= entity-type :sublocation) sublocation)
      (str "/sublocations/" (if (= entity-type :sublocation) (:id entity) country)))

     (when (or (= entity-type :wall) wall)
      (str "/walls/" (if (= entity-type :wall) (:id entity) country)))

     (when (or (= entity-type :climb) climb)
      (str "/climbs/" (if (= entity-type :climb) (:id entity) country))))))

(defn determine-app-name []
  (let [url (-> js/window .-location .-href)
        cec? (> (.indexOf url "climbeasterncanada.com") -1)]
   (if cec?
     "Climb Eastern Canada" "Climb Eastern Canada")))
