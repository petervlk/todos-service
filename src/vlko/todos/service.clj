(ns vlko.todos.service
  (:require [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn app [_]
  {:status 200
   :body   "Hello, World!"})

; TODO -- move this to run configuration
(def server-port 3000)

(defonce
  ^:private
  ^{:doc
    "This exists so that if you run a socket REPL
    when you start the application,
    you can get at the running system easily."}
  server
  (atom nil))

(defn server-start []
  "Start web server."
  (when (nil? @server)
    (println "INFO: Starting server on port " server-port)
    (reset! server (run-jetty app {:port server-port :join? false}))))

(defn server-stop []
  "Stop web server."
  (when-not (nil? @server)
    (println "Shutting down server...")
    (.stop @server)
    (reset! server nil)))

(comment
  (server-start)
  (server-stop)
  @server
  *e)

(defn -main
  [& _]
  (server-start))
