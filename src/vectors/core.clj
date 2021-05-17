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
  [mass]
  {:location     (make (/ 100 2) (/ 100 2))
   :velocity     (make 0 0)
   :acceleration (make 0 0)
   :mass         mass})

(defn apply-force
  [{:keys [location velocity acceleration mass]} force]
  (let [f (div force mass)]
    {:location location
     :velocity (add f velocity)
     :acceleration (add f acceleration)
     :mass mass}))

(defn update-mover
  [{:keys [location velocity acceleration mass]}]
  (let [new-velocity (add velocity acceleration)]
    {:location (add new-velocity location)
     :velocity new-velocity
     :acceleration (mult acceleration 0)
     :mass mass}))

(defn random-2d
  []
  (normalise (make (q/random -1 1) (q/random -1 1))))
