(ns vlko.todos.handler
  (:require
    [compojure.core :refer :all]
    [compojure.route :refer [not-found]]))

(defn greeting
  ([] (greeting "World"))
  ([greetee] (str "Hello, " greetee "!")))


(defroutes webapp
           (context "/hello" []
             (GET "/" [] (greeting))
             (GET "/:greetee" [greetee] (greeting greetee)))

           (GET "/json" [] "JSON comming soon")             ;TODO - implement json responses

           (not-found "Nothing here, mate!"))
