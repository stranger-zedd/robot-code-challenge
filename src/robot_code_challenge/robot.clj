(ns robot-code-challenge.robot
  (:gen-class)
  (:use robot-code-challenge.bearing))

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
                ^{:volatile-mutable true} -bearing]
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


(defn create-robot 
  ([] (new Robot 0 0 (create-bearing)))
  ([x y bearing] (new Robot x y (create-bearing bearing))))