(ns statute.facts
  "General-law compliance catalog for Indonesia (IDN) -- extends this
  repo's existing `marketentry.facts` (narrow public-procurement
  scope) with a second, orthogonal catalog of statutes a company
  generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt/-bel/-bra/-mex/-chl/-arg/-zaf/-col/-ury/-cri/-pan/-ecu/-pry/-gtm/-hnd/-ind/-ken/-tha/-are/-vnm's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Reuses this tick-window's already-verified capital/organization data
  from cloud-itonami-municipality-idn-jakarta (Indonesia Q252, Jakarta
  Q3630, capital status independently verified against the ongoing
  Nusantara relocation process).

  peraturan.go.id (Indonesia's primary national legal-instrument
  portal) returned connect ECONNREFUSED; peraturan.bpk.go.id (the
  Supreme Audit Institution's mirror) returned HTTP 403; an OJK
  (Financial Services Authority) PDF mirror rendered entirely
  illegible via font-subsetting. Both entries here instead directly
  confirmed via jdih.kemenkeu.go.id (the Ministry of Finance's own
  legal-documentation portal, a different official government domain
  that rendered cleanly): Law No. 40 of 2007 on Limited Liability
  Companies (Perseroan Terbatas) -- established 16 August 2007; Law
  No. 27 of 2022 on Personal Data Protection (Pelindungan Data
  Pribadi) -- established and promulgated 17 October 2022.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries."
  {"IDN"
   [{:statute/id "idn.uu-40-2007-perseroan-terbatas"
     :statute/title "Undang-Undang tentang Perseroan Terbatas (Limited Liability Companies)"
     :statute/jurisdiction "IDN"
     :statute/kind :law
     :statute/law-number "UU No. 40 Tahun 2007"
     :statute/url "https://jdih.kemenkeu.go.id/dok/uu-40-tahun-2007"
     :statute/url-provenance :official-jdih-kemenkeu-go-id
     :statute/enacted-date "2007-08-16"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "idn.uu-27-2022-pelindungan-data-pribadi"
     :statute/title "Undang-Undang tentang Pelindungan Data Pribadi (Personal Data Protection)"
     :statute/jurisdiction "IDN"
     :statute/kind :law
     :statute/law-number "UU No. 27 Tahun 2022"
     :statute/url "https://jdih.kemenkeu.go.id/dok/uu-27-tahun-2022"
     :statute/url-provenance :official-jdih-kemenkeu-go-id
     :statute/enacted-date "2022-10-17"
     :statute/retrieved-at "2026-07-16"
     :statute/topic #{:data-protection :privacy}}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-idn statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "IDN")) " IDN statutes seeded with "
                 "official jdih.kemenkeu.go.id citations. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
