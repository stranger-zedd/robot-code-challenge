(ns robot-code-challenge.robot-maths
  (:gen-class)
  (:use clojure.math.numeric-tower)
  (:require [clojure.algo.generic.math-functions :as math]))

;; Clojure doesn't define trig functions which work for degrees
(defn degrees-to-radians [degrees]
  (/ (* degrees (. Math PI)) 180))

;; Clojure also doesn't define a 'round-to-decimal-places' function
(defn round-to-places [places number]
  (let [factor (expt 10 places)]
    (double (/ (round (* factor number)) factor))))

(defn sin [degrees]
  (round-to-places 10 (math/sin (degrees-to-radians degrees))))

(defn cos [degrees]
  (round-to-places 10 (math/cos (degrees-to-radians degrees))))
