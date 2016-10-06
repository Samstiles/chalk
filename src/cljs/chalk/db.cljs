(ns chalk.db
  (:require [chalk.utils :as u]))

(def default-db
  {:app-name (u/determine-app-name)
   :active-screen :home-screen
   :selections {:country nil
                :region nil
                :location nil
                :sublocation nil
                :wall nil
                :climb nil}
   :database {:countries {1 {:name "Canada" :id 1}
                          2 {:name "USA" :id 2}}
              :regions {1 {:name "New Brunswick" :id 1 :country 1 :mapLat 46.4245 :mapLong -66.2234 :mapZoom 7}
                        2 {:name "West Virginia" :id 2 :country 2}}
              :locations {1 {:name "Welsford" :id 1 :country 1 :region :1 :mapLat 45.4538 :mapLong -66.3462 :mapZoom 14}
                          2 {:name "New River Gorge" :id 2 :country 2 :region 2}}
              :sublocations {1 {:name "L-Shape" :id 1 :country 1 :region 1 :location 1}
                             2 {:name "Muir Valley" :id 2 :country 2 :region 2 :location 2}}
              :walls {1 {:name "Joe's Garage" :id 1 :country 1 :region 1 :location :1 :sublocation 1}
                      2 {:name "Orange Oswald" :id 2 :country 2 :region 2 :location :2 :sublocation 2}}
              :climbs {1 {:name "It's a way of life" :id 1 :country 1 :region 1 :location 1 :sublocation 1 :wall 1}
                       2 {:name "Pumpernickle" :id 2 :country 2 :region 2 :location 2 :sublocation 2 :wall 2}}}})
