(ns robot-code-challenge.run-candidate
  (:gen-class))

(defprotocol RunCandidate
  (run [this]))

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