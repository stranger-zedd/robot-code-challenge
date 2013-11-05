(ns robot-code-challenge.table
  (:gen-class))

(defprotocol TableProtocol
  (lower-x-bound [this])
  (lower-y-bound [this])
  (check-bounds [this x y]))

;; The table is valid on both axes between 0 and the corresponding bound
(deftype Table [x-bound y-bound]
  TableProtocol
  (lower-x-bound [this]
    0)
  (lower-y-bound [this]
    0)
  (check-bounds [this x y]
    (and (>= x (.lower-x-bound this)) (<= x (.x-bound this))
         (>= y (.lower-y-bound this)) (<= y (.y-bound this)))))

(defn create-table
  ([] (new Table 10 10))
  ([x-bound y-bound] (new Table x-bound y-bound)))