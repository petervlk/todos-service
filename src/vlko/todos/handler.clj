(ns vlko.todos.handler
  (:require
    [compojure.core :refer :all]
    [compojure.route :refer [not-found]]
    [ring.middleware.json :refer [wrap-json-response]]
    [ring.util.response :refer [response]]))

(defn json-rng-int-handler [_]
  (response {:random-int (rand-int 100)}))

(comment
  (json-rng-int-handler))

(defroutes webapp
           (wrap-json-response (GET "/" req (json-rng-int-handler req)))
           (not-found "Nothing here, mate!"))
