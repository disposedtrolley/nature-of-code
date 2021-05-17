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
  [{:keys [location velocity acceleration mass topspeed]} force]
  (let [f (vec/div force mass)]
    (make location (vec/add f velocity) (vec/add f acceleration) mass topspeed)))

(defn tick
  [{:keys [location velocity acceleration mass topspeed]}]
  (let [new-velocity
        (vec/limit (vec/add velocity acceleration) topspeed)]
    (make
      (vec/add new-velocity location)
      new-velocity
      (vec/mult acceleration 0)
      mass
      topspeed)))

(defn check-edges
  [{:keys [location velocity acceleration mass topspeed]} bounds-x bounds-y]
  (let [new-location
        (vec/make
          (cond
            (> (location :x) bounds-x) 0
            (< (location :x) 0) bounds-x
            :else (location :x))
          (cond
            (> (location :y) bounds-y) 0
            (< (location :y) 0) bounds-y
            :else (location :y)))]
    (make new-location velocity acceleration mass topspeed)))