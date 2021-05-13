(ns vectors.ex-1-6
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

  (q/translate (:x centre)
               (:y centre))

  (q/line 0 0 (:x mouse) (:y mouse)))

(defn update-state
  [{:keys [centre]}]
  (let [mouse (vec/make (q/mouse-x)
                        (q/mouse-y))]
    {:mouse (vec/mult
              (vec/normalise (vec/sub mouse centre))
              50)
     :centre centre}))

(q/defsketch ex-1-6
             :title "Vectors - Ex 1.6"
             :settings #(q/smooth 2)
             :setup setup-state
             :draw draw-state
             :update update-state
             :size [640 360]
             :middleware [m/fun-mode])