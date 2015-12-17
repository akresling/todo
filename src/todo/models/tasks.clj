(ns todo.models.tasks
    (:require [clojure.java.jdbc :as sql]))

(def db (or (System/getenv "DATABASE_URL")
            "postgresql://localhost:5432/tasks"))

(def tasks_table_keys [:task :userid :done])

(defn get_task [user]
  (into [] (sql/query db [(str "SELECT username, task, done FROM tasks t "
                       "JOIN users u ON t.userid = u.userid "
                       "WHERE u.username = ?") user])))

(defn add_task [task]
  (sql/insert! db :tasks (select-keys task tasks_table_keys)))
