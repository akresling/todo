(ns todo.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [todo.views.pages :as page]
            [todo.views.layouts :as layout]
            [todo.controllers.routes :refer [routes]]
            [ring.adapter.jetty :as ring]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.params :refer [wrap-params]]))

(defn start [port]
  (ring/run-jetty #'routes {:port port
                            :join? false}))

(defn -main []
  (start 8080))

