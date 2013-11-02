(ns robot-code-challenge.robot-maths-test
  (:use clojure.test
        robot-code-challenge.robot-maths))

(deftest degrees-to-radians-test
  (testing "It should return 0 radians for 0 degrees"
    (is (== (degrees-to-radians 0) 0)))
  (testing "It should return half pi for 90 degrees"
    (is (== (degrees-to-radians 90) (/ (. Math PI) 2))))
  (testing "It should return pi for 180 degrees"
    (is (== (degrees-to-radians 180) (. Math PI)))))

(deftest round-to-places-test
  (testing "It should return a whole number if 0 is passed as places"
    (is (== (round-to-places 0 1.1) 1)))
  (testing "It should return one decimal place if 1 is passed as places"
    (is (== (round-to-places 1 1.11) 1.1)))
  (testing "It should return five decimal places if 5 is passed as places"
    (is (== (round-to-places 5 1.111111) 1.11111)))
  (testing "It should round 5 up"
    (is (== (round-to-places 1 1.15) 1.2))))

(deftest test-sin
  (testing "It should return the correct value for cardinal directions"
    (is (== (sin 0) 0))
    (is (== (sin 90) 1))
    (is (== (sin 180) 0))
    (is (== (sin 270) -1))))

(deftest test-cos
  (testing "It should return the correct value for cardinal directions"
    (is (== (cos 0) 1))
    (is (== (cos 90) 0))
    (is (== (cos 180) -1))
    (is (== (cos 270) 0))))