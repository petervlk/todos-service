(ns vlko.todos.service
  (:require [vlko.todos.system :as system])
  (:gen-class))

(defn -main
  [& _]
  (system/start))
