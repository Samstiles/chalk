(ns chalk.screens.authentication.signin
  (:require [re-frame.core :as rf]
            [reagent.core :as r]))

(defn submit-login [login-form]
  (rf/dispatch [:POST-users-login login-form]))

(defn signin-screen []
  (let [email (r/atom "")
        password (r/atom "")
        ajax-happening? (rf/subscribe [:ajax-happening?])]
    (fn []
      [:div {:id "components--signin"}
       [:div.signup-form
        [:h1 "Climb Eastern Canada"]
        [:div.inner-signup-form
         [:div.signup-divider]
         [:div.form-input
          [:input {:type "text"
                   :placeholder "Email"
                   :on-change #(reset! email (-> % .-target .-value))
                   :value @email}]]
         [:div.form-input
          [:input {:type "password"
                   :placeholder "Password"
                   :on-change #(reset! password (-> % .-target .-value))
                   :value @password}]]
         [:div.signup-divider]
         [:div.signup-button
          [:button {:type "button"
                    :disabled @ajax-happening?
                    :on-click #(submit-login {:email @email
                                              :password @password})} "Sign In"]]
         [:div.signup-button.newuser-button
          [:a {:href "#/signup" :type "button"} "New User?"]]]]])))
