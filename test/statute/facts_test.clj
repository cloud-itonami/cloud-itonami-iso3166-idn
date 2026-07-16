(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest idn-has-spec-basis
  (let [sb (facts/spec-basis "IDN")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://jdih.kemenkeu.go.id/") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["IDN" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["idn.uu-27-2022-pelindungan-data-pribadi"]
         (mapv :statute/id (facts/by-topic "IDN" :privacy))))
  (is (empty? (facts/by-topic "IDN" :labor)))
  (is (empty? (facts/by-topic "ATL" :privacy))))
