(ns vectors.core)

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