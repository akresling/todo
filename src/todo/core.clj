(ns todo.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [todo.views.pages :as page]
            [todo.views.layouts :as layout]
            [todo.controllers.routes :as controller]
            [ring.adapter.jetty :as ring]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.params :refer [wrap-params]]))


(defroutes routes
           controller/routes
           (route/resources "/")
           (route/not-found (layout/four_o_four)))

(def app (wrap-params routes))

(defn -main []
  (ring/run-jetty app {:port 8080 :join? false}))

