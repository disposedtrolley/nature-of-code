(ns vectors.ex-1-2
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [vectors.core :as vec]))

(defn next-speed
  [n bounds curr-speed]
  (cond
    (or(> n bounds) (< n 0)) (* curr-speed -1)
    :else curr-speed))

(defn setup-state
  []
  (q/background 255)
  {:location (vec/make 100 100)
   :velocity (vec/make 1 3.3)})

(defn draw-state
  [{:keys [location]}]

  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  (q/ellipse (location :x) (location :y) 16 16))

(defn update-state
  [{:keys [location velocity]}]
  (let [new-velocity
        (vec/make
          (next-speed (location :x) (q/width) (velocity :x))
          (next-speed (location :y) (q/height) (velocity :y)))]
    {:location (vec/add location new-velocity)
     :velocity new-velocity}))


(q/defsketch ex-1-2
             :title "Vectors - Ex 1.2"
             :settings #(q/smooth 2)
             :setup setup-state
             :draw draw-state
             :update update-state
             :size [640 360]
             :middleware [m/fun-mode])