(ns robot-code-challenge.run-candidate-test
  (:use clojure.test 
        robot-code-challenge.run-candidate)
  (:import robot_code_challenge.run_candidate.CLIRunCandidate
           robot_code_challenge.run_candidate.FileRunCandidate))

(deftest create-run-candidate-test
  (testing "It should return a CLIRunCandidate if passed nil"
    (is (instance? CLIRunCandidate (create-run-candidate nil))))
  (testing "It should return a CLIRunCandidate if passed an empty sequence"
    (is (instance? CLIRunCandidate (create-run-candidate []))))
  (testing "It should return a FileRunCandidate if there are things in the array"
    (let [rc (create-run-candidate ['pie])]
      (is (instance? FileRunCandidate rc))
      (testing "The file should be the first thing in the array"
        (is (= 'pie (.file rc)))))))