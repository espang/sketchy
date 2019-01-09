(ns sketchy.theta-test
  (:require [clojure.test :refer :all]
            [sketchy.theta :refer :all]))

(deftest test-union
  (is (= 4 (int (cardinality
                  (union
                    (make [1 2])
                    (make [3 4])))))))

(deftest test-union-2
  (let [s1 (make [1 2])
        _  (update! s1 5)]
    (is (= 5 (int (cardinality
                    (union
                      s1
                      (make [3 4]))))))))
