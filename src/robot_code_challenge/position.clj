(ns robot-code-challenge.position
  (:gen-class)
  (:use robot-code-challenge.bearing)
  (:import [robot_code_challenge.bearing Bearing]))

(deftype Position [x y bearing])

(defn- make-position [x y bearing]
  (new Position x y (if (instance? Bearing bearing) 
                      bearing 
                      (create-bearing bearing))))
  

(defn create-position [& options]
  (let [opts (apply hash-map options)]
    (let [x (or (get opts :x) -1)
          y (or (get opts :y) -1)
          bearing (or (get opts :bearing) (create-bearing))
          table (get opts :table)]
      
      (if table
        (if (.check-bounds table x y)
          (make-position x y bearing)
          (get opts :old-position))
        (make-position x y bearing)))))
