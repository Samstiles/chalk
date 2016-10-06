(ns chalk.database-screens.create-edit.country)

(defn country-create-edit-screen []
  (let [country {}]
    (fn []
      [:div {:class "country-create-screen"}
       [:h1 "country create screen"]])))
