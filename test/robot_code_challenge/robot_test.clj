(ns robot-code-challenge.robot-test
  (:use clojure.test
        robot-code-challenge.robot
        robot-code-challenge.table
        robot-code-challenge.bearing
        robot-code-challenge.position)
  (:import [robot_code_challenge.robot Robot]))

(deftest accessor-test
  (let [robot (new Robot (create-position :x 1 :y 2 :bearing 3) 4)]
    (testing "x should return the value of x"
      (is (= (.x robot) 1)))
    (testing "y should return the value of y"
      (is (= (.y robot) 2)))
    (testing "bearing should return the value of bearing"
      (is (= (.bearing (.bearing robot)) 3)))))

(deftest create-robot-test
  (testing "Without arguments"
    (let [robot (create-robot)]
      (testing "x should return an invalid value"
        (is (= (.x robot) -1)))
      (testing "y should return an invalid value"
        (is (= (.y robot) -1)))
      (testing "bearing should return a default value"
        (is (= (.bearing (.bearing robot)) 0)))))
  (testing "With arguments"
    (testing "x should return as specified"
      (is (= (.x (create-robot :x 1)) 1)))
    (testing "y should return as specified"
      (is (= (.y (create-robot :y 1)) 1)))
    (testing "bearing should return a specified bearing"
      (let [bearing (create-bearing 10)]
        (is (= (.bearing (create-robot :bearing bearing)) bearing))))
    (testing "bearing should return a specified bearing value"
      (is (= (.bearing (.bearing (create-robot :bearing 10))) 10)))
    (testing "table should return as specified"
      (let [table (create-table 12 12)]
        (is (= (.table (create-robot :table table)) table))))))

(deftest place-test
  (let [robot (create-robot)]
    (.place robot 9 10 30)
    (testing "It should correctly assign x"
      (is (= (.x robot) 9)))
    (testing "It should correctly assign y"
      (is (= (.y robot) 10)))
    (testing "It should correctly assign bearing"
      (is (= (.bearing (.bearing robot)) 30)))))

(deftest move-test
   (testing "It should correctly move in the right direction"
    (testing "For 0 degrees"
      (let [robot (create-robot :x 0 :y 0 :bearing 0)]
        (.move robot)
        (testing "y should equal one"
          (is (== (.y robot) 1)))
        (testing "x should equal zero"
          (is (== (.x robot) 0)))))
    (testing "For 90 degrees"
      (let [robot (create-robot :x 0 :y 0 :bearing 90)]
        (.move robot)
        (testing "y should equal zero"
          (is (== (.y robot) 0)))
        (testing "x should equal one"
          (is (== (.x robot) 1)))))
    (testing "For 180 degrees"
      (let [robot (create-robot :x 0 :y 1 :bearing 180)]
        (.move robot)
        (testing "y should equal zero"
          (is (== (.y robot) 0)))
        (testing "x should equal zero"
          (is (== (.x robot) 0)))))
    (testing "For 270 degrees"
      (let [robot (create-robot :x 1 :y 0 :bearing 270)]
        (.move robot)
        (testing "y should equal zero"
          (is (== (.y robot) 0)))
        (testing "x should equal zero"
          (is (== (.x robot) 0)))))))

(deftest invalid-move-test
  (testing "It shouldn't be able to move into the negatives"
    (let [robot (create-robot :x 0 :y 0 :bearing 270)]
      (is (== 0 (.x (move robot)))))
    (let [robot (create-robot :x 0 :y 0 :bearing 180)]
      (is (== 0 (.y (move robot))))))
  (testing "It shoudln't be able to move off the board"
    (let [robot (create-robot :x 10 :y 10 :bearing 0)]
      (is (== 10 (.x (move robot)))))
    (let [robot (create-robot :x 10 :y 10 :bearing 90)]
      (is (== 10 (.y (move robot)))))))

(deftest invalid-place-test
  (testing "It shouldn't be able to be placed into the negatives"
    (let [robot (create-robot :x 0 :y 0)]
      (is (== 0 (.x (.place robot -1 0 90))))))
  (testing "It shouldn't be able to be placed off the board"
    (let [robot (create-robot :x 0 :y 0 :table (create-table 10 10 []))]
      (is (== 0 (.x (.place robot 11 0 90)))))))

(deftest turn-right-test
  (let [robot (create-robot)]
    (testing "after calling right, the robot should be facing east"
      (.turn-right robot)
      (is (= (.bearing (.bearing robot)) 90)))))

(deftest turn-left-test
  (let [robot (create-robot)]
    (testing "after calling left, the robot should be facing west"
      (.turn-left robot)
      (is (= (.bearing (.bearing robot)) 270)))))

(deftest clean-tile-test
  (let [robot (create-robot :x 1 :y 1 :bearing 0 :table (create-table 10 10 [[2 1] [1 2]]))]
    (testing "it should clear the tile in front of the robot"
      (is (= [[2 1]] (.clean-tile robot))))))

(deftest report-position-test
  (testing "It should correctly report x, y and facing"
    (let [robot (create-robot :x 1 :y 2)]
      (is (= (.report-position robot) "1, 2: north"))))
  (testing "It should report when the position is invalid"
    (let [robot (create-robot)]
      (is (= "INVALID PLACEMENT" (.report-position robot))))))
          