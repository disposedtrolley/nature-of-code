(ns vectors.ex-1-1
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn next-speed
  [n bounds curr-speed]
  (cond
    (or(> n bounds) (< n 0)) (* curr-speed -1)
    :else curr-speed))

(defn setup-state
  []
  (q/background 255)
  {:x 100
   :y 100
   :xspeed 1
   :yspeed 3.3})

(defn draw-state
  [{:keys [x y]}]

  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  (q/ellipse x y 16 16))

(defn update-state
  [{:keys [x y xspeed yspeed]}]
  (let [xspeed (next-speed x (q/width) xspeed)
        yspeed (next-speed y (q/height) yspeed)]
    {:x (+ x xspeed)
     :y (+ y yspeed)
     :xspeed xspeed
     :yspeed yspeed}))

(q/defsketch ex-1-1
             :title "Vectors - Ex 1.1"
             :settings #(q/smooth 2)
             :setup setup-state
             :draw draw-state
             :update update-state
             :size [640 360]
             :middleware [m/fun-mode])