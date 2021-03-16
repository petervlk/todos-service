(ns vlko.todos.handler
  (:require
    [compojure.core :refer :all]
    [compojure.route :refer [not-found]]))

(defn greeting
  ([] (greeting "World"))
  ([greetee] (str "Hello, " greetee "!")))


(defroutes webapp
           (GET "/" [] (greeting))
           (GET "/:greetee" [greetee] (greeting greetee))
           (not-found "Nothing here, mate!"))
