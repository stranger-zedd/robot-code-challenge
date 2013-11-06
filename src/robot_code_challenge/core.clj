(ns robot-code-challenge.core
  (:use robot-code-challenge.run-candidate))

(defn -main [& args]
  (let [rc (create-run-candidate args)]
    (println rc)))