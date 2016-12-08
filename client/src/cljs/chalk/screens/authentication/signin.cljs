(ns chalk.screens.authentication.signin)

(defn signin-screen []
  (fn []
    [:div {:id "components--signin"}
     [:div.signup-form
      [:h1 "Climb Eastern Canada"]
      [:div.inner-signup-form
       [:div.signup-divider]
       [:div.form-input
        [:input {:type "text" :placeholder "Email"}]]
       [:div.form-input
        [:input {:type "password" :placeholder "Password"}]]
       [:div.signup-divider]
       [:div.signup-button
        [:button {:type "button"} "Sign In"]]
       [:div.signup-button.newuser-button
        [:a {:href "#/signup" :type "button"} "New User?"]]]]]))
