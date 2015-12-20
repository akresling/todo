(ns todo.views.layouts
  (:require [hiccup.page :as h]))

(defn common [title & body]
  (h/html5
    [:head
     [:title title]]
    [:body
     [:div {:id "header" :class "container"}
      [:h2 {:class "container"} "Welcome"]]
     [:div {:class "container content"} body]]))

(defn error_page [err_msg]
  (common "Error"
          [:div {:class "error_message"}
           [:p err_msg]]))

(defn four_o_four []
  (common "Page Not Found"
          [:div {:id "four-oh-four"}
           "The page you are looking for could not be found"]))
