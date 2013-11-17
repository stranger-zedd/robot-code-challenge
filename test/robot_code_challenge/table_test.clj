(ns robot-code-challenge.robot-test
  (:use clojure.test
        robot-code-challenge.table)
  (:import [robot_code_challenge.table Table]))

(deftest create-table-test
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

(deftest clean-test
  (let [dirt-piles [[1 2] [3 4]]]
    (testing "It should return the dirt-piles vector"
      (is (= dirt-piles (.clean (create-table 10 10 dirt-piles) 1 1))))
    (testing "It should remove dirt from the passed tile"
      (is (= [[3 4]] (.clean (create-table 10 10 dirt-piles) 1 2))))))

(deftest clear?-test
  (let [table (create-table 10 10 [[1 2]])]
    (testing "It should return true if the tile is on the board and is clear"
      (is (= true (.clear? table 1 1))))
    (testing "It should return false if the tile has dirt on it"
      (is (= false (.clear? table 1 2))))))

(deftest check-bounds-test
  (let [table (create-table 10 10 [])]
    (testing "It should return true if x and y are within bounds"
      (is (= (.check-bounds table 1 1) true)))
    (testing "It should return false if x is below 0"
      (is (= (.check-bounds table -1 1) false)))
    (testing "It should return false if x is above x-bound"
      (is (= (.check-bounds table (+ (.x-bound table) 1) 1) false)))
    (testing "It should return false if y is below 0"
      (is (= (.check-bounds table 1 -1) false)))
    (testing "It should return false if y is above y-bound"
      (is (= (.check-bounds table 1 (+ (.y-bound table) 1)) false)))))