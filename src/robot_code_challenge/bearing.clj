(ns robot-code-challenge.bearing
  (:gen-class)
  (:require [clojure.algo.generic.math-functions :as math]))

;; Clojure doesn't define trig functions which work for degrees
(defn degrees-to-radians [degrees]
  (/ (* degrees (. Math PI)) 180))

;; Because of conversion errors from degrees to radians, I never get zeroes from
;; the trig functions. This is used to correct that error. 
(defmacro approximate-zero [threshold form]
  `(let [num# ~form]
     (if (or (and (> num# 0) (< num# ~threshold)) (and (< num# 0) (> num# (- ~threshold))))
       0.0
       num#)))

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
    (approximate-zero 0.01 (math/sin (degrees-to-radians bearing))))

  (y-part [this]
    (approximate-zero 0.01 (math/cos (degrees-to-radians bearing))))

  ;; TODO: make this show canonical bearings even when outside the initial 360 
  ;; degrees, i.e -90 degrees is still west, 450 degrees still east.
  (toString [this]
    (let [name (get canonical-bearings bearing)]
      (if (= nil name)
        (format "%d\u00b0T" bearing)
        name))))

(defn create-bearing 
  ([] (new Bearing 0))
  ([bearing] (new Bearing bearing)))

