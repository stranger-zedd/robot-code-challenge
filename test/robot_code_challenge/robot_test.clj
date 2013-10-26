(ns robot-code-challenge.robot-test
  (:use clojure.test
        robot-code-challenge.robot)
  (:import [robot_code_challenge Robot]))

(deftest accessor-test
  (let [robot (new Robot 1 2 3)]
    (testing "x should return the value of x"
      (is (= (.x robot) 1)))
    (testing "y should return the value of y"
      (is (= (.y robot) 2)))
    (testing "bearing should return the value of bearing"
      (is (= (.bearing robot) 3)))))

(deftest create-robot-test
  (let [robot (create-robot)]
    (testing "x should return a default value"
      (is (= (.x robot) 0)))
    (testing "y should return a default value"
      (is (= (.y robot) 0)))
    (testing "bearing should return a default value"
      (is (= (.bearing robot) 0)))))

(deftest place-test
  (let [robot (create-robot)]
    (.place robot 10 20 30)
    (testing "It should correctly assign x"
      (is (= (.x robot) 10)))
    (testing "It should correctly assign y"
      (is (= (.y robot) 20)))
    (testing "It should correctly assign bearing"
      (is (= (.bearing robot) 30)))))
