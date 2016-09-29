(ns chalk.views
    (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
              [chalk.utils :as u]))

;; Util components

(defn entity-list
  [entities entity-name entity-kw down-name up-name]
  (fn []
   [:div (str "This is the " entity-name " list")
    [:ul {:class (str entity-name "-list")}
     (for [entity entities]
       ^{:key (:id entity)} [:a {:class "app-button"
                                 :on-click #(dispatch [:update-selections entity entity-kw])
                                 :href (u/build-entity-path entity entity-kw)}
                                (:name entity)])]
    (when up-name [:a {:class "app-button"
                       :href (str "#/" up-name)}
                      "▲ Up a level (to " up-name ")"])
    (when down-name [:a {:class "app-button"
                         :href (str "#/" down-name)}
                        "▼ Down a level (to " down-name ")"])]))

(defn breadcrumb [entity entity-type]
  (fn []
    [:div {:class "breadcrumb"}
     [:a {:href (u/build-entity-path entity entity-type)} (:name entity)]
     (when (not (= :climb entity-type)) [:span "->"])]))

(defn breadcrumbs []
 (let [selection-details (subscribe [:selection-details])]
   (fn []
    (let [{:keys [country region location sublocation wall climb]} @selection-details]
     [:div {:class "breadcrumbs"}
      (when country [breadcrumb country :country])
      (when region [breadcrumb region :region])
      (when location [breadcrumb location :location])
      (when sublocation [breadcrumb sublocation :sublocation])
      (when wall [breadcrumb wall :wall])
      (when climb [breadcrumb climb :climb])]))))

(defn header []
  (let [app-name (subscribe [:name])]
    (fn []
      [:div {:class "header"}
       [:h1 @app-name]
       [:a {:class "app-button" :href "#/"} "Home"]
       [breadcrumbs]])))

;; List screens

(defn country-list-screen []
  (let [countries (subscribe [:country-list])]
    (fn []
     [entity-list (vals @countries) "countries" :country "regions" nil])))

(defn region-list-screen []
  (let [regions (subscribe [:region-list])]
    (fn []
     [entity-list (vals @regions) "regions" :region "locations" "countries"])))

(defn location-list-screen []
  (let [locations (subscribe [:location-list])]
    (fn []
     [entity-list (vals @locations) "locations" :location "sublocations" "regions"])))

(defn sublocation-list-screen []
  (let [sublocations (subscribe [:sublocation-list])]
    (fn []
     [entity-list (vals @sublocations) "sublocations" :sublocation "walls" "locations"])))

(defn wall-list-screen []
  (let [walls (subscribe [:wall-list])]
    (fn []
     [entity-list (vals @walls) "walls" :wall "climbs" "sublocations"])))

(defn climb-list-screen []
  (let [climbs (subscribe [:climb-list])]
    (fn []
     [entity-list (vals @climbs) "climbs" :climb nil "walls"])))

;; View screens

(defn country-view-screen []
  (let [country (subscribe [:selected-country-details])]
    (fn []
     [:div "This is a specific country view"
      [:pre (.stringify js/JSON (clj->js @country))]])))

(defn region-view-screen []
  (let [region (subscribe [:selected-region-details])]
    (fn []
     [:div "This is a specific region view"
      [:pre (.stringify js/JSON (clj->js @region))]])))

(defn location-view-screen []
  (let [location (subscribe [:selected-location-details])]
    (fn []
     [:div "This is a specific location view"
      [:pre (.stringify js/JSON (clj->js @location))]])))

(defn sublocation-view-screen []
  (let [sublocation (subscribe [:selected-sublocation-details])]
    (fn []
     [:div "This is a specific sublocation view"
      [:pre (.stringify js/JSON (clj->js @sublocation))]])))

(defn wall-view-screen []
  (let [wall (subscribe [:selected-wall-details])]
    (fn []
     [:div "This is a specific wall view"
      [:pre (.stringify js/JSON (clj->js @wall))]])))

(defn climb-view-screen []
  (let [climb (subscribe [:selected-climb-details])]
    (fn []
     [:div "This is a specific climb view"
      [:pre (.stringify js/JSON (clj->js @climb))]])))

;; home

(defn home-screen []
  (fn []
    [:div {:id "home-screen"}
     [:h1 "Home"]
     [:button {:on-click #(dispatch [:update-selections {:country 1}])} "click me"]
     [:a {:class "app-button" :href "#/countries"} "Countries"]
     [:a {:class "app-button" :href "#/regions"} "Regions"]
     [:a {:class "app-button" :href "#/locations"} "Locations"]
     [:a {:class "app-button" :href "#/sublocations"} "Sublocations"]
     [:a {:class "app-button" :href "#/walls"} "Walls"]
     [:a {:class "app-button" :href "#/climbs"} "Climbs"]]))

;; boilerplate

(defmulti screens identity)
(defmethod screens :country-list-screen [] [country-list-screen])
(defmethod screens :region-list-screen [] [region-list-screen])
(defmethod screens :location-list-screen [] [location-list-screen])
(defmethod screens :sublocation-list-screen [] [sublocation-list-screen])
(defmethod screens :wall-list-screen [] [wall-list-screen])
(defmethod screens :climb-list-screen [] [climb-list-screen])
(defmethod screens :country-view-screen [] [country-view-screen])
(defmethod screens :region-view-screen [] [region-view-screen])
(defmethod screens :location-view-screen [] [location-view-screen])
(defmethod screens :sublocation-view-screen [] [sublocation-view-screen])
(defmethod screens :wall-view-screen [] [wall-view-screen])
(defmethod screens :climb-view-screen [] [climb-view-screen])
(defmethod screens :default [] [home-screen])

(defn show-screen
  [screen-name]
  [screens screen-name])

(defn main-screen []
  (let [active-screen (subscribe [:active-screen])]
   (fn []
    [:div {:id "app"}
     [header]
     [:hr]
     [show-screen @active-screen]])))
