(ns vlko.todos.persistence
  (:require [next.jdbc.sql :as sql]))

(defn db-insert-item [ds k v]
  (sql/insert! ds :todo_simple_items {:label k :content v}))

(defn db-delete-item [ds k]
  (sql/delete! ds :todo_simple_items {:label k}))

(defn db-show-simple-items [ds]
  (sql/query ds ["SELECT * FROM todo_simple_items"]))
