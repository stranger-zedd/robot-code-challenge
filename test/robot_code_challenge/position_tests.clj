(ns robot-code-challenge.position-test
  (:use clojure.test
        robot-code-challenge.position
        robot-code-challenge.bearing
        robot-code-challenge.table)
  (:import [robot_code_challenge.position Position]))

(deftest create-position-test
  (testing "Without parameters"
    (let [position (create-position)]
      (testing "x should be -1"
        (is (= (.x position) -1)))
      (testing "y should be -1"
        (is (= (.y position) -1)))
      (testing "bearing should be 0"
        (is (= (.bearing (.bearing position)) 0)))))
  (testing "With parameters"
    (testing "without a table"
      (testing "x should set x"
        (is (= 1 (.x (create-position :x 1))))
      (testing "y should set y")
        (is (= 1 (.y (create-position :y 1))))
      (testing "bearing should set bearing"
        (let [bearing (create-bearing 10)]
          (is (= bearing (.bearing (create-position :bearing bearing))))))
      (testing "bearing should set bearing as a number"
        (is (= 10 (.bearing (.bearing (create-position :bearing 10))))))))
    (testing "with a table"
      (testing "it should return nil if x is out of bounds"
        (is (= nil (create-position :x -1 :table (create-table)))))
      (testing "it should return nil if y is out of bounds"
        (is (= nil (create-position :y -1 :table (create-table)))))
      (testing "it should return an object if x and y are within bounds"
        (is (not (= nil (create-position :y 1 :x 1 :table (create-table)))))))))
      
