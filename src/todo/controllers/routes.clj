(ns todo.controllers.routes
  (:require [clojure.string :refer [trim]]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :as ring]
            [todo.models.tasks :as tasks]
            [todo.models.users :as users]
            [todo.views.layouts :as layout]
            [todo.views.pages :as pages]))

(defn index [username]
  (if (users/user_exists username)
    (pages/index username (tasks/get_task username))
    (layout/error_page "User does not exist")))

(defn add_task [username task]
  (if (users/user_exists username)
    (do
      (tasks/add_task {:task task :userid (users/get_id username) :done false})
      (index username))
    (layout/error_page "User does not exist")))

(defn add_user [username]
  (if-not (users/user_exists username)
    (do
      (users/add_user username)
      (index username))
    (layout/error_page "Username exists")))

(defroutes routes
           (GET "/" [] (pages/welcome))
           (GET "/addtask" [] (pages/add_task))
           (GET "/adduser" [] (pages/add_user))
           (POST "/tasks" {params :params} (let [username (trim (get params "username"))]
                                             (index username)))
           (POST "/new/task" {params :params} (let [username (trim (get params "username"))
                                                    task (trim (get params "task"))]
                                                (add_task username task)))
           (POST "/new/user" {params :params} (let [username (trim (get params "username"))]
                                                    (add_user username))))
