(ns chalk.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame :refer [reg-sub]]))

(reg-sub
 :app-name
 (fn [db]
   (:app-name db)))

(reg-sub
 :active-screen
 (fn [db _]
   (:active-screen db)))

(reg-sub
 :in-db?
 (fn [db _]
   (:in-db? db)))

(reg-sub
  :selection-ids
  (fn [db _]
    (:selections db)))

(reg-sub
  :selection-details
  (fn [db _]
    (let [selections (:selections db)
          country (first (filter #(= (:id %) (int (:country selections))) (vals (get-in db [:database :countries]))))
          region (first (filter #(= (:id %) (int (:region selections))) (vals (get-in db [:database :regions]))))
          location (first (filter #(= (:id %) (int (:location selections))) (vals (get-in db [:database :locations]))))
          sublocation (first (filter #(= (:id %) (int (:sublocation selections))) (vals (get-in db [:database :sublocations]))))
          wall (first (filter #(= (:id %) (int (:wall selections))) (vals (get-in db [:database :walls]))))
          climb (first (filter #(= (:id %) (int (:climb selections))) (vals (get-in db [:database :climbs]))))]
     {:country country :region region :location location :sublocation sublocation :wall wall :climb climb})))

(reg-sub
  :country-list
  (fn [db _]
    (get-in db [:database :countries])))

(reg-sub
  :region-list
  (fn [db _]
    (get-in db [:database :regions])))

(reg-sub
  :regions-by-country
  (fn [db [_ country-id]]
    (let [regions (vals (get-in db [:database :regions]))]
      (filter #(= country-id (:country %))) regions)))

(reg-sub
  :location-list
  (fn [db _]
    (get-in db [:database :locations])))

(reg-sub
  :locations-by-region
  (fn [db [_ region-id]]
    (let [locations (vals (get-in db [:database :locations]))]
      (filter #(= region-id (:region %))) locations)))

(reg-sub
  :sublocation-list
  (fn [db _]
    (get-in db [:database :sublocations])))

(reg-sub
  :wall-list
  (fn [db _]
    (get-in db [:database :walls])))

(reg-sub
  :climb-list
  (fn [db _]
    (get-in db [:database :climbs])))

(reg-sub
  :selected-country-details
  (fn [db _]
    (let [selected-country-id (int (get-in db [:selections :country]))
          all-countries (vals (get-in db [:database :countries]))
          selected-country-details (first (filter #(= selected-country-id (:id %)) all-countries))]
      selected-country-details)))

(reg-sub
  :selected-region-details
  (fn [db _]
    (let [selected-region-id (int (get-in db [:selections :region]))
          all-regions (vals (get-in db [:database :regions]))
          selected-region-details (first (filter #(= selected-region-id (:id %)) all-regions))]
      selected-region-details)))

(reg-sub
  :selected-location-details
  (fn [db _]
    (let [selected-location-id (int (get-in db [:selections :location]))
          all-locations (vals (get-in db [:database :locations]))
          selected-location-details (first (filter #(= selected-location-id (:id %)) all-locations))]
      selected-location-details)))

(reg-sub
  :selected-sublocation-details
  (fn [db _]
    (let [selected-sublocation-id (int (get-in db [:selections :sublocation]))
          all-sublocations (vals (get-in db [:database :sublocations]))
          selected-sublocation-details (first (filter #(= selected-sublocation-id (:id %)) all-sublocations))]
      selected-sublocation-details)))

(reg-sub
  :selected-wall-details
  (fn [db _]
    (let [selected-wall-id (int (get-in db [:selections :wall]))
          all-walls (vals (get-in db [:database :walls]))
          selected-wall-details (first (filter #(= selected-wall-id (:id %)) all-walls))]
      selected-wall-details)))

(reg-sub
  :selected-climb-details
  (fn [db _]
    (let [selected-climb-id (int (get-in db [:selections :climb]))
          all-climbs (vals (get-in db [:database :climbs]))
          selected-climb-details (first (filter #(= selected-climb-id (:id %)) all-climbs))]
      selected-climb-details)))
