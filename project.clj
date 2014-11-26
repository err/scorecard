(defproject scorecard "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [hiccup    "1.0.2"]
                 [cheshire  "5.3.1"]
                 [compojure "1.1.6"]
                 [clj-http  "0.9.1"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler scorecard.handler/app
         :nrepl {:start? true}}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]
                        [javax.servlet/servlet-api "2.5"]]}})
