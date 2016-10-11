(ns chalk.db
  (:require [chalk.utils :as u]))

(def default-db
  {:app-name (u/determine-app-name)
   :in-db? false
   :active-screen :home-screen
   :selections {:country nil
                :region nil
                :location nil
                :sublocation nil
                :wall nil
                :climb nil}
   :database {:countries {1 {:name "Canada"
                             :entity-type :country
                             :short-summary "Canada is a North American country stretching from the U.S. in the south to the Arctic Circle in the north. Major cities include massive Toronto, west coast film centre Vancouver, French-speaking Montréal and Québec City, and capital city Ottawa. Canada's vast swaths of wilderness include lake-filled Banff National Park in the Rocky Mountains. It's also home to Niagara Falls, a famous group of massive waterfalls."
                             :id 1}}
              :regions {1 {:name "New Brunswick"
                           :id 1
                           :entity-type :region
                           :preview-image-url "img/nb.jpg"
                           :short-summary "New Brunswick is one of eastern Canada's Maritime provinces. It encompasses rivers, pine forest, mountains and the Bay of Fundy, known for extreme tides and whale-watching."
                           :country 1}
                        2 {:name "Nova Scotia"
                           :entity-type :region
                           :preview-image-url "img/ns.jpg"
                           :short-summary "Nova Scotia is one of eastern Canada's Maritime provinces on the Atlantic. Consisting of a peninsula and offshore islands, it's home to puffins and seals, and popular for water sports like kayaking."
                           :id 2
                           :country 1}}
              :locations {1 {:name "Welsford"
                             :id 1
                             :entity-type :location
                             :short-summary "Welsford is New Brunswicks premier climbing destination with hundreds of routes across a multitude of climbing disciplines."
                             :preview-image-url "img/welsford.jpg"
                             :country 1
                             :region 1}
                          2 {:name "West Dover"
                             :id 2
                             :entity-type :location
                             :short-summary "West Dover is a part of Nova Scotia with lots of Granite, yo. The climbing is primarily bouldering with a few sparse trad routes."
                             :preview-image-url "img/westdover.jpg"
                             :country 1
                             :region 2}}
              :sublocations {1 {:name "L-Shape"
                                :id 1
                                :entity-type :sublocation
                                :country 1
                                :region 1
                                :location 1}
                             2 {:name "Dover Island"
                                :id 2
                                :entity-type :sublocation
                                :country 1
                                :region 2
                                :location 2}}
              :walls {1 {:name "Joe's Garage"
                         :id 1
                         :entity-type :wall
                         :country 1
                         :region 1
                         :location 1
                         :sublocation 1}
                      2 {:name "Warm-Up Area"
                         :id 2
                         :entity-type :wall
                         :country 1
                         :region 2
                         :location 2
                         :sublocation 2}}
              :climbs {1 {:name "It's a way of life"
                          :id 1
                          :entity-type :climb
                          :country 1
                          :region 1
                          :location 1
                          :sublocation 1
                          :wall 1}
                       2 {:name "The Coffin"
                          :id 2
                          :entity-type :climb
                          :country 1
                          :region 2
                          :location 2
                          :sublocation 2
                          :wall 2}}}})
