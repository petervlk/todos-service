(ns vlko.todos.persistence
  (:require [next.jdbc :as jdbc]
            [next.jdbc.sql :as sql]))

(def db-spec
  {
   :dbtype   "postgresql"
   :host     "localhost"
   :port     5000
   :dbname   "todos"
   :user     "postgres"
   :password "postgres"
   })

(def data-source (jdbc/get-datasource db-spec))

(defn db-insert-item [ds k v]
  ;(jdbc/execute-one!
  ;  ds
  ;  ["INSERT INTO todo_simple_items (label, content) VALUES (?, ?)" k v]
  ;  {:return-keys true})
  (sql/insert! ds :todo_simple_items {:label k :content v}))

(defn db-delete-item [ds k]
  ;(jdbc/execute-one!
  ; ds
  ; ["DELETE FROM todo_simple_items WHERE label = ?" k])
  (sql/delete! ds :todo_simple_items {:label k}))

(defn db-show-simple-items [ds]
  (sql/query ds ["SELECT * FROM todo_simple_items"]))

(comment
  (db-insert-item data-source "123" "sfadfs")
  (db-delete-item data-source "123")
  (db-show-simple-items data-source)
  *e
  )
