(ns vlko.todos.service-test
  (:require [clojure.test :refer :all]
            [vlko.todos.service :as sut]
            [ring.mock.request :as mock]))

(deftest app-test
  (testing "test request handler"
    (is (=
          {:status 200 :body "Hello, World!"}
          (sut/app (mock/request :get "/"))))))