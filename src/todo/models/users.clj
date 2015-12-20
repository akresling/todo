(ns todo.models.users
  (:require [clojure.java.jdbc :as sql]))

(def db (or (System/getenv "DATABASE_URL")
            "postgresql://localhost:5432/tasks"))

(def users_table_keys [:username :userid])

(defn user_exists [username]
  (< 0 (:count
    (sql/query db [(str "SELECT count(*) "
                        "FROM users "
                        "WHERE username = ?") username]
               :result-set-fn first))))

(defn get_id [username]
  (:userid (sql/query db [(str "SELECT userid "
                 "FROM users "
                 "WHERE username = ?") username] :result-set-fn first)))

(defn add_user [username]
  (when-not (user_exists username)
  (sql/insert! db :users {:username username})))
