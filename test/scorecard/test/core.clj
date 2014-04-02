(ns scorecard.test.core
  (:use clojure.test
        scorecard.core))

(deftest fetch-user-scorecard
  (testing "User scorecard is parsed correctly"
    (is (= #{:actor_attributes
             :payload
             :created_at
             :public
             :url
             :type
             :repository
             :actor}
           
           (-> (fetch-activity "err")
               (first)
               (keys)
               (set))))))

(deftest compute-event-score
  (testing "known event score is correct"
    (is (= 5 (event-score {:type "PushEvent"}))))

  (testing "unknown event score is correct"
    (is (= 1 (event-score {:type nil})))))

(deftest compute-user-score
  (testing "event scores summed correctly"
    (with-redefs-fn {#'fetch-activity (fn [_] [{:type "PushEvent"} {:type nil}])}
      #(is (= 6 (user-score "some-name"))))))

