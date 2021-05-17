(ns vectors.mover
  (:require [vectors.core :as vec]))

(defn make
  ([]
   (make 20))
  ([mass]
   (make (vec/make 50 50) (vec/make 0 0) (vec/make 0 0) mass))
  ([location velocity acceleration mass]
   (make location velocity acceleration mass 10))
  ([location velocity acceleration mass topspeed]
   {:location     location
    :velocity     velocity
    :acceleration acceleration
    :mass         mass
    :topspeed     topspeed}))

(defn apply-force
  [{:keys [velocity acceleration mass] :as mover} force]
  (let [f (vec/div force mass)]
    (assoc mover
      :velocity (vec/add f velocity)
      :acceleration (vec/add f acceleration))))

(defn tick
  [{:keys [location velocity acceleration topspeed] :as mover}]
  (let [v (vec/limit (vec/add velocity acceleration) topspeed)]
    (assoc mover
      :location (vec/add v location)
      :velocity v
      :acceleration (vec/mult acceleration 0))))

(defn check-edges
  [{:keys [location] :as mover} bounds-x bounds-y]
  (let [l
        (vec/make
          (cond
            (> (location :x) bounds-x) 0
            (< (location :x) 0) bounds-x
            :else (location :x))
          (cond
            (> (location :y) bounds-y) 0
            (< (location :y) 0) bounds-y
            :else (location :y)))]
    (assoc mover
      :location l)))