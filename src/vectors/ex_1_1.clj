(ns vectors.ex-1-1
  (:require [quil.core :as q]))

(def x (atom 100))
(def y (atom  100))
(def xspeed (atom 1))
(def yspeed (atom 3.3))

(defn setup []
  (q/background 255))

(defn draw []
  (q/background 255)

  (reset! x (+ @x @xspeed))
  (reset! y (+ @y @yspeed))

  (if (or
        (> @x (q/width))
        (< @x 0))
    (reset! xspeed (* @xspeed -1)))

  (if (or
        (> @y (q/height))
        (< @y 0))
    (reset! yspeed (* @yspeed -1)))

  (q/stroke 0)
  (q/fill 175)
  (q/ellipse @x @y 16 16))


(q/defsketch vectors
             :title "Vectors"
             :settings #(q/smooth 2)
             :setup setup
             :draw draw
             :size [640 360])