(ns robot-code-challenge.robot
  (:gen-class))

(defprotocol RobotProtocol
  "Defines the protocol for the Robot class"
  (x [this])
  (y [this])
  (bearing [this])
  (place [this x y bearing]))

(deftype Robot [^{:volatile-mutable true} _x 
                ^{:volatile-mutable true} -y
                ^{:volatile-mutable true} -bearing]
  RobotProtocol
  (x [this]
    _x)

  (y [this]
    -y)

  (bearing [this]
    -bearing)

  (place [this new-x y bearing]
    (set! _x new-x)
    (set! -y y)
    (set! -bearing bearing)
    this))

(defn create-robot []
  (new Robot 0 0 0))

(defn place [x y bearing]
  (list x y bearing))
