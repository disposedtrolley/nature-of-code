(ns vectors.main
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup [])

(defn draw [])

(q/defsketch vectors
             :title "Vectors"
             :settings #(q/smooth 2)
             :setup setup
             :draw draw
             :size [640 480])