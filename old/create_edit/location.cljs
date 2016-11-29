(ns chalk.database-screens.create-edit.location)

(defn location-create-edit-screen []
  (let [location {}]
    (fn []
      [:div {:class "location-create-screen"}
       [:h1 "location create screen"]])))
