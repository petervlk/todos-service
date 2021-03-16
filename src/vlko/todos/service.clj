(ns vlko.todos.service
  (:require [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn app [req]
  {:status 200
   :body   "Hello, World!"})

(defn -main
  [& args]
  (run-jetty #'app {:port 3000})
  )
