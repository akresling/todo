(ns todo.views.pages
  (:require [todo.views.layouts :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]))

(defn all_tasks [tasks]
    [:div {:class "all_tasks"}
     [:ul
      (map (fn [task] [:li [:p (:task task)]]) tasks)]])

(defn index [tasks]
  (layout/common "TODO" (all_tasks tasks)))
