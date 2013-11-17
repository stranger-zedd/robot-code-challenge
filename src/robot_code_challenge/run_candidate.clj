(ns robot-code-challenge.run-candidate
  (:gen-class)
  (:use robot-code-challenge.robot
        clojure.java.io))

(defprotocol RunCandidate
  (run [this]))

;; This is hideous, but I honestly couldn't find a better way to do it. I tried.
;; Input must be passed in lower case.
(defn parse-and-run-command [string robot]
  (let [command-parts (re-seq #"\w+" string)]
    (case (first command-parts)
      "move" (.move robot)
      "left" (.turn-left robot)
      "right" (.turn-right robot)
      "clean" (if (empty? (.clean-tile robot))
                (println "Game Over!"))
      "place" (apply #(.place robot %1 %2 %3) (map #(Integer. (re-find #"\d+" %)) (rest command-parts)))
      "report" (println (.report-position robot))
      (println "INVALID COMMAND"))))

;; Just reads from stdin until it recieves 'end' in any case
(deftype CLIRunCandidate []
  RunCandidate
  (run [this]
    (let [robot (create-robot)]
      (loop [input (.toLowerCase (read-line))]
        (when-not (= "end" input)
          (parse-and-run-command input robot)
          (recur (.toLowerCase (read-line))))))))

;; Reads from file until EOF and executes any commands in it
(deftype FileRunCandidate [file]
  RunCandidate
  (run [this]
    (let [robot (create-robot)]
      (with-open [rdr (reader file)]
        (doseq [line (line-seq rdr)]
          (parse-and-run-command (.toLowerCase line) robot))))))
  
(defn create-run-candidate [args]
  (if (and args (not (empty? args)))
    (new FileRunCandidate (first args)))
    (new CLIRunCandidate)))