(ns marketentry.facts "Indonesia market-entry catalog.")
(def catalog
  {"IDN" {:name "Republic of Indonesia"
          :owner-authority "LKPP / SPSE e-procurement"
          :legal-basis "Perpres pengadaan barang/jasa pemerintah"
          :national-spec "SPSE supplier registration + NIB/NPWP"
          :provenance "https://lpse.lkpp.go.id/"
          :required-evidence ["NIB/NPWP record"
                              "SPSE registration record"
                              "AHU company extract"
                              "Authorized-representative record"]
          :rep-owner-authority "LKPP / contracting authorities"
          :rep-legal-basis "Indonesian legal entity (NIB) typically required for SPSE participation"
          :rep-provenance "https://lpse.lkpp.go.id/"
          :corporate-number-owner-authority "OSS / DJP"
          :corporate-number-legal-basis "NIB / NPWP"
          :corporate-number-provenance "https://oss.go.id/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "SGP" {:name "Singapore" :owner-authority "GeBIZ" :legal-basis "GPA"
          :national-spec "GeBIZ" :provenance "https://www.gebiz.gov.sg/"
          :required-evidence ["UEN record" "GeBIZ registration" "GST record" "Authorized-representative record"]}
   "IND" {:name "India" :owner-authority "GeM" :legal-basis "GFR"
          :national-spec "GeM" :provenance "https://gem.gov.in/"
          :required-evidence ["GSTIN/PAN record" "GeM seller registration" "MCA extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
