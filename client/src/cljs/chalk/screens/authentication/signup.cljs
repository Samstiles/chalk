(ns chalk.screens.authentication.signup
  (:require [chalk.components.loading-cube :as lc]
            [reagent.core :as r]
            [re-frame.core :as rf]))

(defn submit-signup [{:keys [email password first last] :as form}]
  (rf/dispatch [:POST-users form]))

(defn signup-screen []
  (let [password (r/atom "")
        email (r/atom "")
        first-name (r/atom "")
        last-name (r/atom "")
        ajax-happening? (rf/subscribe [:ajax-happening?])]
    (fn []
      [:div {:id "components--signup"}
       [:div.signup-form
        [:h1 "Sign Up"]
        [:div.form-input.half-input
         [:div.first-half-input-inner
          [:input {:type "text"
                   :placeholder "First Name"
                   :on-change #(reset! first-name (-> % .-target .-value))
                   :value @first-name}]]]
        [:div.form-input.half-input
         [:div.second-half-input-inner
          [:input {:type "text"
                   :placeholder "Last Name"
                   :on-change #(reset! last-name (-> % .-target .-value))
                   :value @last-name}]]]
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
                   :on-click #(submit-signup {:email @email
                                              :password @password
                                              :first-name @first-name
                                              :last-name @last-name})}
          "Let's climb!"]]]])))
