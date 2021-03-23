(ns vlko.todos.core
  (:require [next.jdbc :as jdbc]))

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


(comment
  (jdbc/execute! ds ["
    select * from todo_lists
  "])

  (jdbc/execute-one! ds ["
    select * from todo_lists
  "])

  (jdbc/execute! ds ["
    select l.*, i.id, i.label, i.completed from todo_lists l
    join todo_items i on l.id = i.list_id
    order by l.id, i.completed, i.id
  "])

  *e)

(defonce in-mem-db (atom {}))

(defn create-list [list-name]
  {:name  list-name
   :items {}})

(defn create-list-item [item-name]
  {:name  item-name
   :done? false})

(defn add-list
  "Add list to database."
  [db list-name]
  (swap! db assoc (gensym "l") (create-list list-name)))

(defn add-item [db list-name item-name item-value]
  (swap! db assoc-in [list-name item-name] item-value))

(comment
  (add-list in-mem-db :vlko2 {})
  (add-list in-mem-db :peter {})
  (add-item in-mem-db :peter :first {:value "prvy" :done? false})
  (add-item in-mem-db :peter :nd {:value "dos" :done? true})
  @in-mem-db
  (keyword 1)
  (gensym)
  (conj)
  )