(ns todo.controllers.routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [todo.models.tasks :as tasks]
            [todo.views.layouts :as layout]
            [todo.views.pages :as pages]))

(defn index [username]
  (pages/index username (tasks/get_task username)))

(defroutes routes
           (GET "/" [] (pages/welcome))
           (POST "/tasks" {params :params} (let [username (get params "username")]
                                             (index username))))
