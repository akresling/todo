(ns todo.controllers.routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [todo.models.tasks :as tasks]
            [todo.views.layouts :as layout]
            [todo.views.pages :as pages]))

(defn index [name]
  (pages/index (tasks/get_task name)))

(defroutes routes
           (GET "/" [] (layout/common "Welcome" "Go to a name url"))
           (POST "/" [name] (index name)))
