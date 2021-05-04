(ns vlko.todos.handler
  (:require
    [compojure.core :refer :all]
    [compojure.route :refer [not-found]]
    [ring.middleware.json :refer [wrap-json-response
                                  wrap-json-body
                                  wrap-json-params]]
    [ring.util.response :refer [response]]
    [vlko.todos.persistence :as db]))

(defn handler-item-add [{label :label content :item-content}]
  (response (db/db-insert-item label content)))

(defn handler-item-remove [{label :label}]
  (response (db/db-delete-item label)))

(defn handler-items-show [_]
  (response (db/db-show-simple-items)))

(defroutes webapp
           (-> (context "/api" []
                 (GET "/" req (handler-items-show req))
                 (POST "/" {data :body} (handler-item-add data))
                 (DELETE "/" {data :body} (handler-item-remove data)))
               (wrap-json-body {:keywords? true})
               (wrap-json-response))
           (not-found "Nothing here, mate!"))
