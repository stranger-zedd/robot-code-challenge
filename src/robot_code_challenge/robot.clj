(ns robot-code-challenge.robot
  (:gen-class)
  (:use robot-code-challenge.bearing
        robot-code-challenge.table
        robot-code-challenge.position))
                                
(defprotocol RobotProtocol
  (x [this])
  (y [this])
  (bearing [this])
  (move [this])
  (clean-tile [this])
  (turn-right [this])
  (turn-left [this])
  (place [this x y bearing])
  (report-position [this]))

(deftype Robot [^{:volatile-mutable true} position table]
  RobotProtocol
  (x [this]
    (.x position))

  (y [this]
    (.y position))

  (bearing [this]
    (.bearing position))

  (move [this]
    (let [x (.x this) 
          y (.y this)]
      (set! position (create-position :x (int (+ x (.x-part (.bearing this))))
                                      :y (int (+ y (.y-part (.bearing this))))
                                      :bearing (.bearing this) :table table :old-position position))))

  (turn-right [this]      
    (let [x (.x this) 
          y (.y this)]
      (set! position (create-position :x x :y y 
                                      :bearing (mod (+ (.bearing (.bearing this)) 90) 360)))))

  (turn-left [this]
    (let [x (.x this) 
          y (.y this)]
      (set! position (create-position :x x :y y 
                                      :bearing (mod (- (.bearing (.bearing this)) 90) 360)))))

  (place [this x y bearing]
    (set! position (create-position :x x :y y 
                                    :bearing bearing 
                                    :table table 
                                    :old-position position))    
    this)

  (clean-tile [this]
    (let [x (int (+ (.x this) (.x-part (.bearing this))))
          y (int (+ (.y this) (.y-part (.bearing this))))]
      (.clean table x y)))

  (report-position [this]
    (if (.check-bounds table (.x this) (.y this))
      (format "%d, %d: %s", (int (.x this)) (int (.y this)) (.bearing this))
      "INVALID PLACEMENT")))

(defn create-robot [& options]
  (let [opts (apply hash-map options)]
    (let [x (or (get opts :x) -1)
          y (or (get opts :y) -1)
          bearing (or (get opts :bearing) (create-bearing))
          table (or (get opts :table) (create-table))]

      (new Robot (create-position :x x :y y :bearing bearing) table))))
