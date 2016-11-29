(ns chalk.database-screens.create-edit.region)

(defn region-create-edit-screen []
  (let [region {}]
    (fn []
      [:div {:class "region-create-screen"}
       [:h1 "region create screen"]])))
