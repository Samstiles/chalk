(ns chalk.handlers
  (:require [re-frame.core :refer [dispatch reg-event-db reg-event-fx debug trim-v]]
            [clojure.string :as str]
            [chalk.db :as db]
            [chalk.toast :as t]
            [ajax.core :as ajax]
            [clojure.set :refer [rename-keys]]
            [camel-snake-kebab.core :refer [->camelCaseString]]
            [chalk.interceptors :refer [debug-verbose]]))

(defn- db-route? [route]
  (or (str/includes? (name route) "-create")
      (str/includes? (name route) "-edit")
      (str/includes? (name route) "-view")))

(reg-event-db
 :initialize-db
 [trim-v]
 (fn  [_ _]
   db/default-db))

(reg-event-db
 :set-active-screen
 [trim-v]
 (fn [db [active-screen]]
   (assoc db :active-screen active-screen
          :in-db? (db-route? active-screen))))

(reg-event-db
 :update-selections
 [trim-v]
 (fn [db [selection selection-type]]
   (let [{:keys [country region location sublocation wall climb]} selection
         updated-selections {:country (if (= selection-type :country) (:id selection) (or country nil))
                             :region (if (= selection-type :region) (:id selection) (or region nil))
                             :location (if (= selection-type :location) (:id selection) (or location nil))
                             :sublocation (if (= selection-type :sublocation) (:id selection) (or sublocation nil))
                             :wall (if (= selection-type :wall) (:id selection) (or wall nil))
                             :climb (if (= selection-type :climb) (:id selection) (or climb nil))}]
     (.log js/console "UPDATED SELECTIONS" updated-selections)
     (assoc db :selections updated-selections))))

(reg-event-db
 :ajax-happening?
 [trim-v]
 (fn [db [ajax-happening?]]
   (assoc db :ajax-happening? ajax-happening?)))

(reg-event-fx
 :POST-users-login
 [trim-v]
 (fn [{:keys [db]} [login-form]]
   {:db db
    :dispatch [:ajax-happening? true]
    :http-xhrio {:method :post
                 :uri "http://localhost:3450/users/login"
                 :params login-form
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :timeout 5000
                 :on-success [:POST-users-login/success]
                 :on-failure [:POST-users-login/failure]}}))

(reg-event-fx
 :POST-users-login/success
 [trim-v]
 (fn [{:keys [db]} [result]]
   (t/toast :success "Logged in.")
   {:dispatch [:ajax-happening? false]
    :db db}))

(reg-event-fx
 :POST-users-login/failure
 [trim-v]
 (fn [{:keys [db]} [result]]
   (let [errors (reduce (fn [acc err]
                          (conj acc [:error (:message err)]))
                        '()
                        (vals (-> result :response :errors)))]
     (t/toast-n errors)
     {:dispatch [:ajax-happening? false]
      :db db})))


(reg-event-fx
 :POST-users
 [trim-v]
 (fn [{:keys [db]} [signup-form]]
   {:db db
    :dispatch [:ajax-happening? true]
    :http-xhrio {:method :post
                 :uri "http://localhost:3450/users"
                 :params (rename-keys signup-form {:first-name :firstName :last-name :lastName})
                 :format (ajax/json-request-format)
                 :response-format (ajax/json-response-format {:keywords? true})
                 :timeout 5000
                 :on-success [:POST-users/success]
                 :on-failure [:POST-users/failure]}}))

(reg-event-fx
 :POST-users/success
 [trim-v]
 (fn [{:keys [db]} [result]]
   (t/toast :success "Thanks for signing up!")
   {:dispatch [:ajax-happening? false]
    :db db}))

(reg-event-fx
 :POST-users/failure
 [trim-v]
 (fn [{:keys [db]} [result]]
   (let [errors (reduce (fn [acc err]
                          (conj acc [:error (:message err)]))
                        '()
                        (vals (-> result :response :errors)))]
     (t/toast-n errors)
     {:dispatch [:ajax-happening? false]
      :db db})))
