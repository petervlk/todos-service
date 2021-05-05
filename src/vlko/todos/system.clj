(ns vlko.todos.system
  (:require [integrant.core :as ig]
            [next.jdbc :as jdbc]
            [ring.adapter.jetty :as jetty]
            [vlko.todos.handler :as handler]))

(def system-config
  {:todos/jetty   {:handler (ig/ref :todos/handler)
                   :port    3000}
   :todos/handler {:db (ig/ref :todos/db)}
   :todos/db      {:db-spec {:dbtype   "postgresql"
                             :host     "localhost"
                             :port     5000
                             :dbname   "todos"
                             :user     "postgres"
                             :password "postgres"}}})

(defmethod ig/init-key :todos/jetty [_ {:keys [handler port]}]
  (println "Starting server on port " port)
  (jetty/run-jetty handler {:port port :join? false}))

(defmethod ig/init-key :todos/handler [_ {:keys [db]}]
  (handler/create-app db))

(defmethod ig/init-key :todos/db [_ {:keys [db-spec]}]
  (jdbc/get-datasource db-spec))

(defmethod ig/halt-key! :todos/jetty [_ jetty]
  (.stop jetty))

(defn start []
  (ig/init system-config))
