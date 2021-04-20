(ns rest-tutorial.core
  (:require [org.httpkit.server :as server]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.util.response :as resp]
    [ring.middleware.defaults :refer :all]
    [clojure.pprint :as pp]
    [clojure.string :as str]
    [clojure.data.json :as json])
  (:gen-class))



(defn scrambled? [s,w]
  (try
    (every? true? (vec (for [x (frequencies w)] (>= (get (frequencies s) (key x))(val x)))))  
    (catch Exception e false)
  )
)

  
; Simple Body Page
(defn simple-body-page [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello World"
  }
)

(defn scrambled [req]
  {:status  200
  :headers {"Content-Type" "text/html"}
  :body (->
      (str (scrambled? (:scrambledString (:params req)) (:word (:params req))))
    )
  }
)

(defroutes app-routes
  (GET "/scrambled" [] scrambled)
  (route/not-found "Error, page not found!"))

(defn -main
  "This is our main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))
