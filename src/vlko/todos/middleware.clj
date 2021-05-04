(ns vlko.todos.middleware)

(def db-middleware
  {:name    ::db-middleware
   :compile (fn [{:keys [db]} _]
                (fn [handler]
                    (fn [req]
                        (handler (assoc req :db db)))))})
