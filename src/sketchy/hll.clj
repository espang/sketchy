(ns sketchy.hll
  (:import [com.yahoo.sketches.hll HllSketch
                                   Union]))

(defn update! [s val]
  "Update takes a theta sketch s and a value val
   and updates s by adding val."
  (.update s val))

(defn make [coll]
  "Makes a hll from the collection."
  (reduce (fn [acc val]
            (do
              (.update acc val)
              acc))
          (new HllSketch 10)
          coll))

(defn union [s1 s2]
  (let [union (new Union 10)]
    (.update union s1)
    (.update union s2)
    (-> union .getResult)))

(defn cardinality [sketch]
  (.getEstimate sketch))
