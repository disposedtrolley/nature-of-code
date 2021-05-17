(ns vectors.mover
  (:require [vectors.core :as vec]))

(defn make
  ([]
   (make 20))
  ([mass]
   (make (vec/make 50 50) (vec/make 0 0) (vec/make 0 0) mass))
  ([location velocity acceleration mass]
   {:location     location
    :velocity     velocity
    :acceleration acceleration
    :mass         mass}))

(defn apply-force
  [{:keys [location velocity acceleration mass]} force]
  (let [f (vec/div force mass)]
    (make location (vec/add f velocity) (vec/add f acceleration) mass)))

(defn tick
  [{:keys [location velocity acceleration mass]}]
  (let [new-velocity (vec/add velocity acceleration)]
    (make (vec/add new-velocity location) new-velocity (vec/mult acceleration 0) mass)))