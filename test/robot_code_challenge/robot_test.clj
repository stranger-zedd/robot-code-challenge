(ns robot-code-challenge.robot-test
  (:use clojure.test
        robot-code-challenge.robot
        robot-code-challenge.bearing)
  (:import [robot_code_challenge.robot Robot]))

(deftest accessor-test
  (let [robot (new Robot 1 2 3 4)]
    (testing "x should return the value of x"
      (is (= (.x robot) 1)))
    (testing "y should return the value of y"
      (is (= (.y robot) 2)))
    ;; Bearing is the wrong type here, but we aren't testing the Bearing class.
    (testing "bearing should return the value of bearing"
      (is (= (.bearing robot) 3)))))

(deftest create-robot-test
  (testing "Without arguments"
    (let [robot (create-robot)]
      (testing "x should return a default value"
        (is (= (.x robot) 0)))
      (testing "y should return a default value"
        (is (= (.y robot) 0)))
      (testing "bearing should return a default value"
        (is (= (.bearing (.bearing robot)) 0)))
      (testing "table should return a default value"
        (is (= (.x-bound (.table robot)) 10))
        (is (= (.y-bound (.table robot)) 10)))))
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
    (.place robot 10 20 30)
    (testing "It should correctly assign x"
      (is (= (.x robot) 10)))
    (testing "It should correctly assign y"
      (is (= (.y robot) 20)))
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
      (let [robot (create-robot :x 0 :y 0 :bearing 180)]
        (.move robot)
        (testing "y should equal negative one"
          (is (== (.y robot) -1)))
        (testing "x should equal zero"
          (is (== (.x robot) 0)))))
    (testing "For 270 degrees"
      (let [robot (create-robot :x 0 :y 0 :bearing 270)]
        (.move robot)
        (testing "y should equal zero"
          (is (== (.y robot) 0)))
        (testing "x should equal negative one"
          (is (== (.x robot) -1)))))))

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

(deftest report-position-test
  (let [robot (new Robot 1 2 (create-bearing) (create-table))]
    (testing "It should correctly report x, y and facing"
      (is (= (.report-position robot) "1, 2: north")))))
