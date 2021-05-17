(ns vectors.ex-1-10
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [vectors.core :as vec]
            [vectors.mover :as mv]))

(defn setup-state
  []
  (mv/make
    (vec/make (q/random (q/width)) (q/random (q/height)))
    (vec/make 0 0)
    (vec/make -0.001 0.01)
    20
    10))

(defn draw-state
  [{:keys [location mass]}]
  (q/background 255)
  (q/stroke 0)
  (q/fill 175)
  (q/ellipse (location :x) (location :y) mass mass))

(defn update-state
  [{:keys [location] :as mover}]
  (let [mouse (vec/make (q/mouse-x) (q/mouse-y))
        dir (-> (vec/sub mouse location)
                (vec/normalise)
                (vec/mult 0.5))]
    (-> mover
      (mv/apply-force dir)
      (mv/tick)
      (mv/check-edges (q/width) (q/height)))))

(q/defsketch ex-1-10
             :title "Vectors - Ex 1.10"
             :settings #(q/smooth 2)
             :setup setup-state
             :draw draw-state
             :update update-state
             :size [640 360]
             :middleware [m/fun-mode])