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