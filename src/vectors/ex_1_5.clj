(ns vectors.ex-1-5
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [vectors.core :as vec]))

(defn setup-state
  []
  {:mouse (vec/make (q/mouse-x)
                    (q/mouse-y))
   :centre (vec/make (/ (q/width) 2)
                     (/ (q/height) 2))})

(defn draw-state
  [{:keys [mouse centre]}]
  (q/background 255)

  (q/fill 0)
  (q/rect 0 0 (vec/mag mouse) 10)

  (q/translate (:x centre)
               (:y centre))

  (q/line 0 0 (:x mouse) (:y mouse)))

(defn update-state
  [{:keys [centre]}]
  (let [mouse (vec/make (q/mouse-x)
                        (q/mouse-y))]
    {:mouse (vec/sub mouse centre)
     :centre centre}))

(q/defsketch ex-1-5
             :title "Vectors - Ex 1.5"
             :settings #(q/smooth 2)
             :setup setup-state
             :draw draw-state
             :update update-state
             :size [640 360]
             :middleware [m/fun-mode])