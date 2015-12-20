(ns todo.views.pages
  (:require [todo.views.layouts :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn all_tasks [username tasks]
  [:div {:class "all_tasks"}
   [:h1 (str username ", You have things todo")]
   [:ul
    (map (fn [task] [:li [:p (:task task)]]) tasks)]])

(defn get_tasks []
  [:div {:class "get_tasks"}
   (form/form-to [:post "/tasks"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "username" "Enter a username")
                 (form/text-area "username")
                 (form/submit-button "Tasks"))])

(defn add_task []
  [:div {:class "add_task"}
   (form/form-to [:post "/add/task"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "username" "Enter the user")
                 (form/text-area "username")
                 (form/label "task" "What do you need to do?")
                 (form/text-area "task")
                 (form/submit-button "Add Task"))])

(defn add_task_page []
  (layout/common "Add Task"
                 [:div {:class "container"}
                  (add_task)]))

(defn welcome []
  (layout/common "Welcome"
                 [:div {:class "container"}
                  [:div {:class "welcome_heading"}
                   [:h3 "Enter a username"]]
                  (get_tasks)]))

(defn index [username tasks]
  (layout/common "TODO" (all_tasks username tasks) (add_task)))
