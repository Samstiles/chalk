(ns chalk.database-screens.create-edit.wall)

(defn wall-create-edit-screen []
  (let [wall {}]
    (fn []
      [:div {:class "wall-create-screen"}
       [:h1 "wall create screen"]])))
