(ns robot-code-challenge.bearing
  (:gen-class)
  (:use robot-code-challenge.robot-maths))

(def canonical-bearings
  {0 "north" 
   90 "east"
   180 "south"
   270 "west"})

(defprotocol BearingProtocol
  (x-part [this])
  (y-part [this]))

(deftype Bearing [bearing]
  BearingProtocol
  (x-part [this]
    (sin bearing))

  (y-part [this]
    (cos bearing))

  (toString [this]
    (let [name (get canonical-bearings (mod bearing 360))]
      (if (= nil name)
        (format "%d\u00b0T" bearing)
        name))))

(defn create-bearing 
  ([] (new Bearing 0))
  ([bearing] (new Bearing bearing)))

