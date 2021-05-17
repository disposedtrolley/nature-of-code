(ns vectors.ex-2-5
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [vectors.core :as vec]))

(defn setup-state
  []
  (q/background 255)
  (vec/make-mover 20))

(defn draw-state
  [{:keys [location mass]}]
  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  (q/ellipse (location :x) (location :y) mass mass))

(defn update-state
  [mover]
  ;(let [wind (vec/make 0.01 0) gravity (vec/make 0 0.1)]
  (let [wind (vec/make 0.01 0) gravity (vec/make 0 0.1)]
    (let [mover (vec/apply-force mover wind)]
      (let [mover2 (vec/apply-force mover gravity)]
        (vec/update-mover mover2)))))

(q/defsketch ex-2-5
             :title "Vectors - Ex 2.5"
             :settings #(q/smooth 2)
             :setup setup-state
             :draw draw-state
             :update update-state
             :size [640 360]
             :middleware [m/fun-mode])