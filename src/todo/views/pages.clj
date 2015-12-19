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

(defn index [username tasks]
  (layout/common "TODO" (all_tasks username tasks)))

(defn task_form []
  [:div {:class "task_form"}
   (form/form-to [:post "/tasks"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "username" "Enter a username")
                 (form/text-area "username")
                 (form/submit-button "Tasks"))])

(defn welcome []
  (layout/common "Welcome"
                 [:div {:class "container"}
                  [:div {:class "welcome_heading"}
                   [:h3 "Enter a username"]]
                  (task_form)]))

