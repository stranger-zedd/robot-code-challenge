(ns robot-code-challenge.table
  (:gen-class)
  (:use clojure.java.io))

(def table-size-file
  "table_size.txt")

(defn get-default-table-size []
  (if (.exists (as-file table-size-file))
    (let [data (slurp table-size-file)
          split (re-seq #"\d+" data)]
      [(Integer. (first split)) (Integer. (second split))])
    [10 10]))

(def dirt-positions-file
  "dirt_positions.txt")

(defn get-dirt-positions []
  (if (.exists (as-file dirt-positions-file))
    (mapv (fn [[pos-a pos-b]]
            [(Integer. pos-a) (Integer. pos-b)])
          (map #(re-seq #"\d+" %) (line-seq (reader dirt-positions-file))))
    []))

(defprotocol TableProtocol
  (lower-x-bound [this])
  (lower-y-bound [this])
  (x-bound [this])
  (y-bound [this])
  (dirt-piles [this])
  (clean [this x y])
  (clear? [this x y])
  (check-bounds [this x y]))

;; The table is valid on both axes between 0 and the corresponding bound
(deftype Table [upper-bounds ^{:volatile-mutable true} dirt-piles]
  TableProtocol
  (lower-x-bound [this]
    0)

  (lower-y-bound [this]
    0)

  (x-bound [this]
    (first upper-bounds))

  (y-bound [this]
    (last upper-bounds))

  (clean [this x y]
    (set! dirt-piles (vec (filter #(not (= % [x y])) dirt-piles))))

  (clear? [this x y]
    (and (.check-bounds this x y)
         (not (some #{[x y]} dirt-piles))))

  (check-bounds [this x y]
    (and (>= x (.lower-x-bound this)) (<= x (.x-bound this))
         (>= y (.lower-y-bound this)) (<= y (.y-bound this)))))

(defn create-table
  ([] (new Table (get-default-table-size) (get-dirt-positions)))
  ([x-bound y-bound] (new Table [x-bound y-bound] (get-dirt-positions)))
  ([x-bound y-bound dirt-piles] (new Table [x-bound y-bound] dirt-piles)))
