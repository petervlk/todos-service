(ns vlko.todos.handler-test
  (:require [clojure.test :refer :all]
            [vlko.todos.handler :as sut]
            [ring.mock.request :as mock]))

(defn response-matcher [http-method path]
  (select-keys
    (sut/app (mock/request http-method path))
    [:status :body]))

(deftest hello-request-handling
  (testing "path: '/hello'"
    (is (=
          {:status 200 :body "Hello, World!"}
          (response-matcher :get "/hello"))))

  (testing "path: '/hello/:name'"
    (is (=
          {:status 200 :body "Hello, Vlko!"}
          (response-matcher :get "/hello/Vlko")))))

(deftest json-request-handling
  (testing "path: '/json'"
    (is (=
          {:status 200 :body "JSON comming soon"}
          (response-matcher :get "/json")))))

(deftest undefined-request-handling
  (testing "path: unhandled"
    (is (=
          {:status 404, :body "Nothing here, mate!"}
          (response-matcher :get "/unhandled/path")))))
