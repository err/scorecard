(ns scorecard.core
  (:require [cheshire.core :as json]
            [clj-http.client :as http]
            [clojure.string :as str]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Fetching a scorecard

(def +base+ "https://github.com/")

(defn url
  [user]
  {:pre [(not (str/blank? user))]}
  (str +base+ user ".json"))

(defn fetch-activity
  "Issues HTTP GET request and parses response into EDN"
  [user]
  (-> (url user)
      http/get
      :body
      (json/parse-string true)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Computing event scores

(def +scoreboard+
  {"PushEvent" 	5
   "PullRequestReviewCommentEvent" 4
   "WatchEvent"  3
   "CreateEvent" 2})

(def +default-score+ 1)

(defn event-score
  "Provided a scoreboard (mapping of event-type => point-value),
   and an event, returns the point-value of the event.

   A default score may be specified for event types
   not present in the scoreboard (default is 1)."
  ([e]
   (event-score e +scoreboard+ +default-score+))
  ([e scoreboard]
   (event-score e scoreboard +default-score+))
  ([e scoreboard default]
   (get scoreboard (:type e) default)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API

(defn user-score
  "Compute GitHub user-score for user"
  ([user]
   (->> (fetch-activity user)
        (map event-score)
        (reduce +))))

;; Observed limitation of [EC]'s approach:
;; - Making (event-score) multi-arity cost precious time,
;;   and the argument order isn't amenable to currying
