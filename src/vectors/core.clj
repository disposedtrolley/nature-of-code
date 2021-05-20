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

(defn limit
  [vector max]
  (let [m (mag vector)]
    (cond
      (> m max) (mult (normalise vector) max)
      :else vector)))

(defn random-2d
  []
  (normalise (make (q/random -1 1) (q/random -1 1))))
