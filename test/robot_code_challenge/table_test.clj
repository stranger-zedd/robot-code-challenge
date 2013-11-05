(ns robot-code-challenge.robot-test
  (:use clojure.test
        robot-code-challenge.table)
  (:import [robot_code_challenge.table Table]))

(deftest create-table-test
  (testing "with no arguments, x-bound should be 10"
    (is (== (.x-bound (create-table)) 10)))
  (testing "with no arguments, y-bound should be 10"
    (is (== (.y-bound (create-table)) 10)))
  (testing "with arguments, x-bound should be as passed"
    (is (== (.x-bound (create-table 1 2)) 1)))
  (testing "with arguments, y-bound should be as passed"
    (is (== (.y-bound (create-table 1 2)) 2))))

(deftest lower-x-bound-test
  (testing "It should return 0"
    (is (= 0 (.lower-x-bound (create-table))))))

(deftest lower-y-bound-test
  (testing "It should return 0"
    (is (= 0 (.lower-y-bound (create-table))))))  

(deftest check-bounds-test
  (let [table (create-table)]
    (testing "It should return true if x and y are within bounds"
      (is (= (.check-bounds table 1 1) true)))
    (testing "It should return false if x is below 0"
      (is (= (.check-bounds table -1 1) false)))
    (testing "It should return false if x is above x-bound"
      (is (= (.check-bounds table (+ (.x-bound table) 1) 1) false)))
    (testing "It should return false if y is below 0"
      (is (= (.check-bounds table 1 -1) false)))
    (testing "It should return false if y is above x-bound"
      (is (= (.check-bounds table (+ (.y-bound table) 1) 1) false)))))