(ns vlko.todos.persistence)

(defonce ^{:private true} db (atom {}))

(defn items [] @db)

(defn add-item! [k v]
  (swap! db assoc k v))

(defn remove-item! [k]
  (swap! db dissoc k))

(comment
  (items)
  (add-item! :vlko "was here")
  (remove-item! "")
  *e
  )