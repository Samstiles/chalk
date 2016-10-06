(ns chalk.database-screens.create-edit.sublocation)

(defn sublocation-create-edit-screen []
  (let [sublocation {}]
    (fn []
      [:div {:class "sublocation-create-screen"}
       [:h1 "sublocation create screen"]])))
