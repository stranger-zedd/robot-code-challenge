(ns robot-code-challenge.run-candidate
  (:gen-class))

(defprotocol RunCandidate
  (run [this]))

;; This is hideous, but I honestly couldn't find a better way to do it. I tried.
(defn parse-and-run-command [string robot]
  (let [command-parts (re-seq #"\w+" (.toLowerCase string))]
    (case (first command-parts)
      "move" (.move robot)
      "left" (.turn-left robot)
      "right" (.turn-right robot)
      "place" (apply #(.place robot %1 %2 %3) (map #(Integer. (re-find #"\d+" %)) (rest command-parts)))
      "report" (.report-position robot))))

(deftype CLIRunCandidate []
  RunCandidate
  (run [this]
    ;; Do stuff
    ))

(deftype FileRunCandidate [file]
  RunCandidate
  (run [this]
    ;; Do stuff
    ))
  
(defn create-run-candidate [args]
  (if (and args (not (empty? args)))
    (new FileRunCandidate (first args))
    (new CLIRunCandidate)))