(ns culture.facts
  "Country-level regional-culture catalog for Indonesia (IDN) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"IDN"
   [{:culture/id "idn.dish.nasi-goreng"
     :culture/name "Nasi goreng"
     :culture/country "IDN"
     :culture/kind :dish
     :culture/summary "Southeast Asian fried rice dish seasoned with sweet soy sauce, shallots, garlic and chilli; officially recognized as a national dish of Indonesia in 2018 and also eaten in Malaysia, Singapore, Brunei and southern Thailand."
     :culture/url "https://en.wikipedia.org/wiki/Nasi_goreng"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.dish.rendang"
     :culture/name "Rendang"
     :culture/country "IDN"
     :culture/kind :dish
     :culture/summary "Meat dish stewed in coconut milk and spices rooted in Minangkabau and Malay cuisines of the Malacca Strait region; Indonesia declared it a national dish in 2018 while Malaysia recognized it as heritage food in 2009 — origin is shared and contested."
     :culture/url "https://en.wikipedia.org/wiki/Rendang"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.dish.satay"
     :culture/name "Satay"
     :culture/country "IDN"
     :culture/kind :dish
     :culture/summary "Southeast Asian dish of seasoned skewered meat grilled over charcoal, typically served with peanut sauce; it evolved in Java, Sumatra and the Malay Peninsula."
     :culture/url "https://en.wikipedia.org/wiki/Satay"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.dish.gado-gado"
     :culture/name "Gado-gado"
     :culture/country "IDN"
     :culture/kind :dish
     :culture/summary "Indonesian salad of raw, boiled, blanched or steamed vegetables and hard-boiled eggs, served with a peanut sauce dressing."
     :culture/url "https://en.wikipedia.org/wiki/Gado-gado"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.product.tempeh"
     :culture/name "Tempeh"
     :culture/country "IDN"
     :culture/kind :product
     :culture/summary "Traditional Indonesian fermented soybean food cultured with Rhizopus molds into cake form, originating on Java where it is a staple protein source."
     :culture/url "https://en.wikipedia.org/wiki/Tempeh"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.beverage.jamu"
     :culture/name "Jamu"
     :culture/country "IDN"
     :culture/kind :beverage
     :culture/summary "Traditional Indonesian herbal medicine made from roots, bark, flowers, seeds, leaves and fruits, often sold as a beverage; jamu wellness culture was recognized as UNESCO intangible cultural heritage in 2023."
     :culture/url "https://en.wikipedia.org/wiki/Jamu"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.beverage.kopi-luwak"
     :culture/name "Kopi luwak"
     :culture/country "IDN"
     :culture/kind :beverage
     :culture/summary "Indonesian coffee made from beans that have passed through the digestive tract of Asian palm civets."
     :culture/url "https://en.wikipedia.org/wiki/Kopi_luwak"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.craft.batik"
     :culture/name "Batik"
     :culture/country "IDN"
     :culture/kind :craft
     :culture/summary "Wax-resist dyeing technique for patterned textiles, traditionally developed and refined in Java; Indonesian batik was recognized by UNESCO as intangible heritage of humanity in 2009."
     :culture/url "https://en.wikipedia.org/wiki/Batik"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.festival.nyepi"
     :culture/name "Nyepi"
     :culture/country "IDN"
     :culture/kind :festival
     :culture/summary "Balinese 'day of silence' held every new year of the Balinese calendar, observed in Bali, Indonesia, with silence, fasting and meditation."
     :culture/url "https://en.wikipedia.org/wiki/Nyepi"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "idn.heritage.borobudur"
     :culture/name "Borobudur"
     :culture/country "IDN"
     :culture/kind :heritage
     :culture/summary "9th-century Mahayana Buddhist temple in Central Java, Indonesia, the largest Buddhist temple in the world; a UNESCO World Heritage Site designated in 1991."
     :culture/url "https://en.wikipedia.org/wiki/Borobudur"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

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
      :note (str "cloud-itonami-iso3166-idn culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "IDN"))
                 " IDN entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
