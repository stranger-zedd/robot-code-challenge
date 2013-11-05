(ns robot-code-challenge.robot
  (:gen-class)
  (:use robot-code-challenge.bearing
        robot-code-challenge.table)
  (:import [robot_code_challenge.bearing Bearing]))

(defprotocol RobotProtocol
  (x [this])
  (y [this])
  (bearing [this])
  (move [this])
  (turn-right [this])
  (turn-left [this])
  (place [this x y bearing])
  (report-position [this]))

(deftype Robot [^{:volatile-mutable true} -x 
                ^{:volatile-mutable true} -y
                ^{:volatile-mutable true} -bearing
                table]
  RobotProtocol
  (x [this]
    -x)

  (y [this]
    -y)

  (bearing [this]
    -bearing)

  (move [this]
    (set! -x (+ -x (.x-part -bearing)))
    (set! -y (+ -y (.y-part -bearing))))    

  (turn-right [this]
    (set! -bearing (create-bearing (mod (+ (.bearing -bearing) 90) 360))))

  (turn-left [this]
    (set! -bearing (create-bearing (mod (- (.bearing -bearing) 90) 360))))

  (place [this x y bearing]
    (set! -x x)
    (set! -y y)
    (set! -bearing (create-bearing bearing))
    this)
  
  (report-position [this]
    (format "%d, %d: %s", -x, -y, -bearing)))


(defn create-robot [& options]
  (let [opts (apply hash-map options)]
    (let [x (or (get opts :x) -1)
          y (or (get opts :y) -1)
          bearing (or (get opts :bearing) (create-bearing))
          table (or (get opts :table) (create-table))]

      ;; This looks kinda ugly; it's making a robot, and if a bearing hasn't been
      ;; passed in, it tries to make one out of whatever HAS been passed in.
      (new Robot x y (if (instance? Bearing bearing) 
                       bearing 
                       (create-bearing bearing)) table))))
