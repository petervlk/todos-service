(ns vlko.todos.handler
  (:require
    [muuntaja.core :as m]
    [reitit.ring :as ring]
    [reitit.ring.middleware.exception :as exception]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [vlko.todos.middleware :as mw]
    [vlko.todos.persistence :as db]))

(defn response-ok [body]
  {:status 200 :body body})

(defn handler-ok [_]
  (response-ok "ok"))

(defn handler-show-todos [{data-source :db}]
  (response-ok (db/db-show-simple-items data-source)))

(defn handler-add-todo [{data-source :db {:keys [label item-content]} :body-params}]
  (response-ok (db/db-insert-item data-source label item-content)))

(defn handler-remove-todo [{data-source :db {:keys [label]} :body-params}]
  (response-ok (db/db-delete-item data-source label)))

(def routes
  [["/api"
    {:name   ::todos-api
     :get    {:handler handler-show-todos}
     :post   {:handler handler-add-todo}
     :delete {:handler handler-remove-todo}}]
   ["/health-check"
    {:name ::ping
     :get  {:handler handler-ok}}]])

(defn create-app [db]
  (ring/ring-handler
    (ring/router routes
                 {:data {:db         db
                         :muuntaja   m/instance
                         :middleware [muuntaja/format-negotiate-middleware
                                      muuntaja/format-response-middleware
                                      exception/exception-middleware
                                      muuntaja/format-request-middleware
                                      mw/db-middleware]}})
    (ring/create-default-handler
      {:not-found          (constantly {:status 404, :body "Nothing here"})
       :method-not-allowed (constantly {:status 405, :body "Not allowed"})
       :not-acceptable     (constantly {:status 406, :body "Not acceptable"})})))
