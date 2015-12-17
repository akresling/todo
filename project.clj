(defproject todo "0.1.0-SNAPSHOT"
  :description "A small todo app to practice clojure"
  :url "http://github.com/akresling/todo"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main todo.core
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-defaults "0.1.2"]
                 [compojure "1.4.0"]
                 [hiccup "1.0.5"]])
