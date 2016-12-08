(ns chalk.screens.authentication.signup)

(defn signup-screen []
  (fn []
    [:div {:id "components--signup"}
     [:div.signup-form
      [:h1 "Sign Up"]
      [:div.form-input.half-input
       [:div.first-half-input-inner
        [:input {:type "text" :placeholder "First Name"}]]]
      [:div.form-input.half-input
       [:div.second-half-input-inner
        [:input {:type "text" :placeholder "Last Name"}]]]
      [:div.form-input
       [:input {:type "text" :placeholder "Email"}]]
      [:div.form-input
       [:input {:type "password" :placeholder "Password"}]]
      [:div.signup-divider]
      [:div.signup-button
       [:button {:type "button"} "Let's climb!"]]]]))
