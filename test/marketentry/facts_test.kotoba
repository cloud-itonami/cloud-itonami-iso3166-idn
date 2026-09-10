(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest idn-has-spec-basis
  (let [sb (facts/spec-basis "IDN")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/rep-spec-basis "IDN")))
    (is (some? (facts/corporate-number-spec-basis "IDN")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "IDN")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "IDN" all)))
    (is (not (facts/required-evidence-satisfied? "IDN" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["IDN" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest comparative-jurisdictions-are-not-cross-contaminated
  ;; SGP and IND are legitimate comparative-jurisdiction entries in the
  ;; same catalog (same pattern as the sibling AGO/GHA actors' USA/ZAF/BRA
  ;; and NGA/KEN entries) -- each must carry its OWN jurisdiction's own
  ;; legal basis, not another jurisdiction's. A prior version of this
  ;; catalog had SGP's :legal-basis copy-pasted as "GFR" (India's General
  ;; Financial Rules, 2017) instead of Singapore's own Government
  ;; Procurement Act (GPA, Cap. 120, 1997; https://sso.agc.gov.sg/Act/GPA1997).
  (testing "Singapore cites its own Government Procurement Act, not India's GFR"
    (is (= "GPA" (:legal-basis (facts/spec-basis "SGP")))))
  (testing "India cites its own General Financial Rules"
    (is (= "GFR" (:legal-basis (facts/spec-basis "IND")))))
  (testing "Singapore and India don't share a legal-basis value"
    (is (not= (:legal-basis (facts/spec-basis "SGP"))
              (:legal-basis (facts/spec-basis "IND"))))))
