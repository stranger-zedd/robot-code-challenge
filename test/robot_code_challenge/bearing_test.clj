(ns robot-code-challenge.bearing-test
  (:use clojure.test
        robot-code-challenge.bearing)
  (:import [robot_code_challenge.bearing Bearing]))

(deftest y-part-test
  (testing "y-part should return 1 when bearing is 0"
    (is (== (.y-part (new Bearing 0)) 1.0)))
  (testing "y-part should return -1 when bearing is 180"
    (is (== (.y-part (new Bearing 180)) -1.0)))
  (testing "y-part should return 0 when bearing is 90"
    (is (== (.y-part (new Bearing 90)) 0.0)))
  (testing "y-part should return 0 when bearing is 270"
    (is (== (.y-part (new Bearing 270)) 0.0))))

(deftest x-part-test
  (testing "x-part should return 0 when bearing is 0"
    (is (== (.x-part (new Bearing 0)) 0.0)))
  (testing "x-part should return 0 when bearing is 180"
    (is (== (.x-part (new Bearing 180)) 0.0)))
  (testing "x-part should return 1 when bearing is 90"
    (is (== (.x-part (new Bearing 90)) 1.0)))
  (testing "x-part should return -1 when bearing is 270"
    (is (== (.x-part (new Bearing 270)) -1.0))))

(deftest to-string-test
  (testing "0 degrees should be 'north'"
    (is (= (.toString (new Bearing 0)) "north")))
  (testing "90 degrees should be 'east'"
    (is (= (.toString (new Bearing 90)) "east")))
  (testing "180 degrees should be 'south'"
    (is (= (.toString (new Bearing 180)) "south")))
  (testing "270 degrees should be 'west'"
    (is (= (.toString (new Bearing 270)) "west")))
  (testing "a strange number of degrees should output in correct format"
    (is (= (.toString (new Bearing 1)) "1\u00b0T"))))
