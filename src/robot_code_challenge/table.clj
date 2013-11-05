(ns robot-code-challenge.table
  (:gen-class))

;; The table is valid on both axes between 0 and the corresponding bound
(deftype Table [x-bound y-bound])

(defn create-table
  ([] (new Table 10 10))
  ([x-bound y-bound] (new Table x-bound y-bound)))