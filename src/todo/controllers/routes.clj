(ns todo.controllers.routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.util.response :as ring]
            [todo.models.tasks :as tasks]
            [todo.models.users :as users]
            [todo.views.layouts :as layout]
            [todo.views.pages :as pages]))

(defn add_task [username task]
  (when (users/user_exists username)
    (tasks/add_task {:task task :userid (users/get_id username) :done false})
    (ring/redirect (str "/" username))))

(defn add_user [username]
  (if-not (users/user_exists username)
    (users/add_user username)
    (ring/redirect (str "/" username))))

(defn main [username]
  (if (users/user_exists username)
    (pages/index username (tasks/get_task username))
    (add_user username)))

(defroutes routes
           (GET "/" [] (pages/welcome))
           (GET "/:username" [username] (main username))
           (GET "/addtask" [] (pages/add_task_page))
           (POST "/tasks" {params :params} (let [username (get params "username")]
                                             (main username)))
           (POST "/add/task" {params :params} (let [username (get params "username")
                                                    task (get params "task")]
                                                (add_task username task)))
           (POST "/add/user" {params :params} (let [username (get params "username")]
                                                    (add_user username))))
