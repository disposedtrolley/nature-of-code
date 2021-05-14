(ns vectors.core
  (:require [quil.core :as q]))

(defn make
  [x y]
  {:x x :y y})

(defn add
  [& vectors]
  (make
    (reduce + (map #(:x %) vectors))
    (reduce + (map #(:y %) vectors))))

(defn sub
  [& vectors]
  (make
    (reduce - (map #(:x %) vectors))
    (reduce - (map #(:y %) vectors))))

(defn mult
  [vector n]
  (make
    (* (:x vector) n)
    (* (:y vector) n)))

(defn div
  [vector n]
  (make
    (/ (:x vector) n)
    (/ (:y vector) n)))

(defn mag
  [vector]
  (Math/sqrt (+ (Math/pow (:x vector) 2)
                (Math/pow (:y vector) 2))))

(defn normalise
  [vector]
  (let [m (mag vector)]
    (cond
      (= 0 m) vector
      :else (div vector m))))

(defn make-mover
  []
  {:location     (make (/ 100 2) (/ 100 2))
   ;:location     (make (/ (q/width) 2) (/ (q/height) 2))
   :velocity     (make 0 0)
   :acceleration (make -0.001 0.01)})

(defn apply-force
  [mover force]
  {:location (mover :location)
   :velocity (mover :velocity)
   :acceleration (add force (mover :acceleration))})

(defn update-mover
  [{:keys [location velocity acceleration]}]
  (let [new-velocity (add velocity acceleration)]
    {:location (add new-velocity location)
     :velocity new-velocity
     :acceleration (mult acceleration 0)}))

(defn random-2d
  []
  (normalise (make (q/random -1 1) (q/random -1 1))))
