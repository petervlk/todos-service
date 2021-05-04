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

(def ds (jdbc/get-datasource db-spec))

(defn db-insert-item [k v]
  ;(jdbc/execute-one!
  ;  ds
  ;  ["INSERT INTO todo_simple_items (label, content) VALUES (?, ?)" k v]
  ;  {:return-keys true})
  (sql/insert! ds :todo_simple_items {:label k :content v}))

(defn db-delete-item [k]
  ;(jdbc/execute-one!
  ; ds
  ; ["DELETE FROM todo_simple_items WHERE label = ?" k])
  (sql/delete! ds :todo_simple_items {:label k}))

(defn db-show-simple-items []
  (sql/query ds ["SELECT * FROM todo_simple_items"]))

(comment
  (db-insert-item "123" "sfadfs")
  (db-delete-item "123")
  (db-show-simple-items)
  *e
  )
