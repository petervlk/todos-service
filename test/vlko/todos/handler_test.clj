(ns vlko.todos.handler-test
  (:require [clojure.test :refer :all]
            [vlko.todos.handler :as sut]
            [ring.mock.request :as mock]))

(defn response-matcher [http-method path]
  (select-keys
    (sut/webapp (mock/request http-method path))
    [:status :body]))

(deftest request-handler-test
  (testing "path: '/'"
    (is (=
          {:status 200 :body "Hello, World!"}
          (response-matcher :get "/"))))

  (testing "path: '/:name'"
    (is (=
          {:status 200 :body "Hello, Vlko!"}
          (response-matcher :get "/Vlko"))))

  (testing "path: unhandled"
    (is (=
          {:status 404, :body "Nothing here, mate!"}
          (response-matcher :get "/unhandled/path"))))
  )
