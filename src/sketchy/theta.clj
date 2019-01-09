(ns sketchy.theta
  (:import [com.yahoo.sketches.theta UpdateSketch
                                     SetOperation
                                     Union
                                     Intersection
                                     CompactSketch
                                     Sketches]))

(defn update!
  "Update takes a theta sketch s and a value val
   and updates s by adding val."
  [s val]
  (.update s val))

(defn make
  "Makes a theta sketch from the collection."
  [coll]
  (reduce (fn [acc val]
            (do
              (.update acc val)
              acc))
          (-> (UpdateSketch/builder) .build)
          coll))

(defn cardinality
  "Returns the cardinality of the set."
  [sketch]
  (.getEstimate sketch))

(defn union [s1 s2]
  (let [union (-> (SetOperation/builder) .buildUnion)]
    (.update union s1)
    (.update union s2)
    (-> union .getResult)))

(defn intersection [s1 s2]
  (let [intersection (-> (SetOperation/builder) .buildIntersection)]
    (.update intersection s1)
    (.update intersection s2)
    (-> intersection .getResult)))
