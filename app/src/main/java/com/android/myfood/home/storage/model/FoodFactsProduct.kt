// To parse the JSON, install jackson-module-kotlin and do:
//
//   val foodFactsProduct = FoodFactsProduct.fromJson(jsonString)

package com.android.myfood.home.storage.model

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.*
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fasterxml.jackson.module.kotlin.*


@Suppress("UNCHECKED_CAST")
@JsonIgnoreProperties(ignoreUnknown = true)

private fun <T> ObjectMapper.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonNode) -> T, toJson: (T) -> String, isUnion: Boolean = false) = registerModule(SimpleModule().apply {
        addSerializer(k.java as Class<T>, object : StdSerializer<T>(k.java as Class<T>) {
                override fun serialize(value: T, gen: JsonGenerator, provider: SerializerProvider) = gen.writeRawValue(toJson(value))
        })
        addDeserializer(k.java as Class<T>, object : StdDeserializer<T>(k.java as Class<T>) {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = fromJson(p.readValueAsTree())
        })
})

val mapper = jacksonObjectMapper().apply {
        propertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        convert(HasSubIngredients::class, { HasSubIngredients.fromValue(it.asText()) }, { "\"${it.value}\"" })
}
@JsonIgnoreProperties(ignoreUnknown = true)
data class FoodFactsProduct (
        @get:JsonProperty("status_verbose")@field:JsonProperty("status_verbose")
        val statusVerbose: String? = null,

        val status: Int? = null,
        val code: String? = null,
        val product: Product? = null
) {
        fun toJson() = mapper.writeValueAsString(this)

        companion object {
                fun fromJson(json: String) = mapper.readValue<FoodFactsProduct>(json)
        }
}
@JsonIgnoreProperties(ignoreUnknown = true)
data class Product (
        @get:JsonProperty("lang_debug_tags")@field:JsonProperty("lang_debug_tags")
        val langDebugTags: List<Any?>? = null,

        @get:JsonProperty("languages_tags")@field:JsonProperty("languages_tags")
        val languagesTags: List<String>? = null,

        @get:JsonProperty("nutrient_levels_tags")@field:JsonProperty("nutrient_levels_tags")
        val nutrientLevelsTags: List<String>? = null,

        @get:JsonProperty("update_key")@field:JsonProperty("update_key")
        val updateKey: String? = null,

        @get:JsonProperty("categories_properties")@field:JsonProperty("categories_properties")
        val categoriesProperties: CategoriesProperties? = null,

        @get:JsonProperty("pnns_groups_2")@field:JsonProperty("pnns_groups_2")
        val pnnsGroups2: String? = null,

        @get:JsonProperty("ingredients_from_or_that_may_be_from_palm_oil_n")@field:JsonProperty("ingredients_from_or_that_may_be_from_palm_oil_n")
        val ingredientsFromOrThatMayBeFromPalmOilN: Long? = null,

        @get:JsonProperty("additives_debug_tags")@field:JsonProperty("additives_debug_tags")
        val additivesDebugTags: List<Any?>? = null,

        val link: String? = null,

        @get:JsonProperty("correctors_tags")@field:JsonProperty("correctors_tags")
        val correctorsTags: List<String>? = null,

        @get:JsonProperty("amino_acids_prev_tags")@field:JsonProperty("amino_acids_prev_tags")
        val aminoAcidsPrevTags: List<Any?>? = null,

        @get:JsonProperty("allergens_from_user")@field:JsonProperty("allergens_from_user")
        val allergensFromUser: String? = null,

        @get:JsonProperty("last_editor")@field:JsonProperty("last_editor")
        val lastEditor: String? = null,

        @get:JsonProperty("minerals_tags")@field:JsonProperty("minerals_tags")
        val mineralsTags: List<Any?>? = null,

        @get:JsonProperty("languages_hierarchy")@field:JsonProperty("languages_hierarchy")
        val languagesHierarchy: List<String>? = null,

        @get:JsonProperty("data_quality_warnings_tags")@field:JsonProperty("data_quality_warnings_tags")
        val dataQualityWarningsTags: List<String>? = null,

        @get:JsonProperty("traces_hierarchy")@field:JsonProperty("traces_hierarchy")
        val tracesHierarchy: List<String>? = null,

        @get:JsonProperty("popularity_key")@field:JsonProperty("popularity_key")
        val popularityKey: Long? = null,

        @get:JsonProperty("additives_old_n")@field:JsonProperty("additives_old_n")
        val additivesOldN: Long? = null,

        val packagings: List<ProductPackaging>? = null,

        @get:JsonProperty("countries_lc")@field:JsonProperty("countries_lc")
        val countriesLc: String? = null,

        @get:JsonProperty("interface_version_created")@field:JsonProperty("interface_version_created")
        val interfaceVersionCreated: String? = null,

        @get:JsonProperty("nutriscore_grade")@field:JsonProperty("nutriscore_grade")
        val nutriscoreGrade: String? = null,

        @get:JsonProperty("unique_scans_n")@field:JsonProperty("unique_scans_n")
        val uniqueScansN: Long? = null,

        @get:JsonProperty("labels_hierarchy")@field:JsonProperty("labels_hierarchy")
        val labelsHierarchy: List<String>? = null,

        @get:JsonProperty("nutrition_grade_fr")@field:JsonProperty("nutrition_grade_fr")
        val nutritionGradeFr: String? = null,

        @get:JsonProperty("misc_tags")@field:JsonProperty("misc_tags")
        val miscTags: List<String>? = null,

        @get:JsonProperty("labels_prev_hierarchy")@field:JsonProperty("labels_prev_hierarchy")
        val labelsPrevHierarchy: List<String>? = null,

        val countries: String? = null,

        @get:JsonProperty("ingredients_text_en")@field:JsonProperty("ingredients_text_en")
        val ingredientsTextEn: String? = null,

        @get:JsonProperty("nutrition_grades")@field:JsonProperty("nutrition_grades")
        val nutritionGrades: String? = null,

        @get:JsonProperty("ingredients_hierarchy")@field:JsonProperty("ingredients_hierarchy")
        val ingredientsHierarchy: List<String>? = null,

        @get:JsonProperty("nutriscore_score_opposite")@field:JsonProperty("nutriscore_score_opposite")
        val nutriscoreScoreOpposite: Long? = null,

        @get:JsonProperty("ingredients_text_debug")@field:JsonProperty("ingredients_text_debug")
        val ingredientsTextDebug: String? = null,

        @get:JsonProperty("fruits-vegetables-nuts_100g_estimate")@field:JsonProperty("fruits-vegetables-nuts_100g_estimate")
        val fruitsVegetablesNuts100GEstimate: Long? = null,

        val brands: String? = null,

        @get:JsonProperty("generic_name")@field:JsonProperty("generic_name")
        val genericName: String? = null,

        @get:JsonProperty("allergens_hierarchy")@field:JsonProperty("allergens_hierarchy")
        val allergensHierarchy: List<Any?>? = null,

        @get:JsonProperty("ingredients_text_en_debug_tags")@field:JsonProperty("ingredients_text_en_debug_tags")
        val ingredientsTextEnDebugTags: List<Any?>? = null,

        @get:JsonProperty("other_nutritional_substances_tags")@field:JsonProperty("other_nutritional_substances_tags")
        val otherNutritionalSubstancesTags: List<Any?>? = null,

        val sortkey: Long? = null,
        val editors: List<String>? = null,
        val stores: String? = null,

        @get:JsonProperty("stores_tags")@field:JsonProperty("stores_tags")
        val storesTags: List<Any?>? = null,

        @get:JsonProperty("brands_debug_tags")@field:JsonProperty("brands_debug_tags")
        val brandsDebugTags: List<Any?>? = null,

        @get:JsonProperty("states_tags")@field:JsonProperty("states_tags")
        val statesTags: List<String>? = null,

        @get:JsonProperty("emb_codes_debug_tags")@field:JsonProperty("emb_codes_debug_tags")
        val embCodesDebugTags: List<Any?>? = null,

        val sources: List<Source>? = null,

        @get:JsonProperty("additives_tags_n")@field:JsonProperty("additives_tags_n")
        val additivesTagsN: Any? = null,

        @get:JsonProperty("emb_codes_orig")@field:JsonProperty("emb_codes_orig")
        val embCodesOrig: String? = null,

        @get:JsonProperty("pnns_groups_1_tags")@field:JsonProperty("pnns_groups_1_tags")
        val pnnsGroups1_Tags: List<String>? = null,

        @get:JsonProperty("origins_hierarchy")@field:JsonProperty("origins_hierarchy")
        val originsHierarchy: List<String>? = null,

        @get:JsonProperty("ecoscore_grade")@field:JsonProperty("ecoscore_grade")
        val ecoscoreGrade: String? = null,

        val allergens: String? = null,

        @get:JsonProperty("traces_from_ingredients")@field:JsonProperty("traces_from_ingredients")
        val tracesFromIngredients: String? = null,

        @get:JsonProperty("nutrition_data_prepared")@field:JsonProperty("nutrition_data_prepared")
        val nutritionDataPrepared: String? = null,

        @get:JsonProperty("codes_tags")@field:JsonProperty("codes_tags")
        val codesTags: List<String>? = null,

        @get:JsonProperty("image_front_url")@field:JsonProperty("image_front_url")
        val imageFrontURL: String? = null,

        @get:JsonProperty("nutrition_data_per")@field:JsonProperty("nutrition_data_per")
        val nutritionDataPer: String? = null,

        @get:JsonProperty("checkers_tags")@field:JsonProperty("checkers_tags")
        val checkersTags: List<Any?>? = null,

        @get:JsonProperty("nutrition_data")@field:JsonProperty("nutrition_data")
        val nutritionData: String? = null,

        @get:JsonProperty("product_name_en_debug_tags")@field:JsonProperty("product_name_en_debug_tags")
        val productNameEnDebugTags: List<Any?>? = null,

        val traces: String? = null,

        @get:JsonProperty("vitamins_prev_tags")@field:JsonProperty("vitamins_prev_tags")
        val vitaminsPrevTags: List<Any?>? = null,

        @get:JsonProperty("data_quality_info_tags")@field:JsonProperty("data_quality_info_tags")
        val dataQualityInfoTags: List<String>? = null,

        @get:JsonProperty("brand_owner")@field:JsonProperty("brand_owner")
        val brandOwner: String? = null,

        @get:JsonProperty("ingredients_ids_debug")@field:JsonProperty("ingredients_ids_debug")
        val ingredientsIDSDebug: List<String>? = null,

        @get:JsonProperty("states_hierarchy")@field:JsonProperty("states_hierarchy")
        val statesHierarchy: List<String>? = null,

        @get:JsonProperty("_keywords")@field:JsonProperty("_keywords")
        val keywords: List<String>? = null,

        @get:JsonProperty("last_image_dates_tags")@field:JsonProperty("last_image_dates_tags")
        val lastImageDatesTags: List<String>? = null,

        @get:JsonProperty("last_edit_dates_tags")@field:JsonProperty("last_edit_dates_tags")
        val lastEditDatesTags: List<String>? = null,

        @get:JsonProperty("entry_dates_tags")@field:JsonProperty("entry_dates_tags")
        val entryDatesTags: List<String>? = null,

        @get:JsonProperty("allergens_from_ingredients")@field:JsonProperty("allergens_from_ingredients")
        val allergensFromIngredients: String? = null,

        @get:JsonProperty("data_sources_imported")@field:JsonProperty("data_sources_imported")
        val dataSourcesImported: String? = null,

        @get:JsonProperty("traces_debug_tags")@field:JsonProperty("traces_debug_tags")
        val tracesDebugTags: List<Any?>? = null,

        @get:JsonProperty("serving_size_imported")@field:JsonProperty("serving_size_imported")
        val servingSizeImported: String? = null,

        val languages: Languages? = null,

        @get:JsonProperty("product_name_en")@field:JsonProperty("product_name_en")
        val productNameEn: String? = null,

        @get:JsonProperty("image_thumb_url")@field:JsonProperty("image_thumb_url")
        val imageThumbURL: String? = null,

        @get:JsonProperty("minerals_prev_tags")@field:JsonProperty("minerals_prev_tags")
        val mineralsPrevTags: List<Any?>? = null,

        val checkers: List<Any?>? = null,

        @get:JsonProperty("manufacturing_places_debug_tags")@field:JsonProperty("manufacturing_places_debug_tags")
        val manufacturingPlacesDebugTags: List<Any?>? = null,

        @get:JsonProperty("image_ingredients_small_url")@field:JsonProperty("image_ingredients_small_url")
        val imageIngredientsSmallURL: String? = null,

        @get:JsonProperty("nutrition_score_beverage")@field:JsonProperty("nutrition_score_beverage")
        val nutritionScoreBeverage: Long? = null,

        @get:JsonProperty("nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients_value")@field:JsonProperty("nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients_value")
        val nutritionScoreWarningFruitsVegetablesNutsEstimateFromIngredientsValue: Long? = null,

        @get:JsonProperty("nutrition_data_per_imported")@field:JsonProperty("nutrition_data_per_imported")
        val nutritionDataPerImported: String? = null,

        @get:JsonProperty("photographers_tags")@field:JsonProperty("photographers_tags")
        val photographersTags: List<String>? = null,

        @get:JsonProperty("cities_tags")@field:JsonProperty("cities_tags")
        val citiesTags: List<Any?>? = null,

        @get:JsonProperty("generic_name_en")@field:JsonProperty("generic_name_en")
        val genericNameEn: String? = null,

        val code: String? = null,

        @get:JsonProperty("nutrition_grades_tags")@field:JsonProperty("nutrition_grades_tags")
        val nutritionGradesTags: List<String>? = null,

        @get:JsonProperty("nutrition_data_prepared_per_imported")@field:JsonProperty("nutrition_data_prepared_per_imported")
        val nutritionDataPreparedPerImported: String? = null,

        @get:JsonProperty("no_nutrition_data")@field:JsonProperty("no_nutrition_data")
        val noNutritionData: String? = null,

        @get:JsonProperty("origins_lc")@field:JsonProperty("origins_lc")
        val originsLc: String? = null,

        @get:JsonProperty("nova_group_debug")@field:JsonProperty("nova_group_debug")
        val novaGroupDebug: String? = null,

        @get:JsonProperty("serving_size")@field:JsonProperty("serving_size")
        val servingSize: String? = null,

        @get:JsonProperty("informers_tags")@field:JsonProperty("informers_tags")
        val informersTags: List<String>? = null,

        val correctors: List<String>? = null,

        @get:JsonProperty("ingredients_analysis_tags")@field:JsonProperty("ingredients_analysis_tags")
        val ingredientsAnalysisTags: List<String>? = null,

        val ingredients: List<Ingredient>? = null,

        @get:JsonProperty("ingredients_debug")@field:JsonProperty("ingredients_debug")
        val ingredientsDebug: List<String?>? = null,

        val states: String? = null,

        @get:JsonProperty("data_quality_tags")@field:JsonProperty("data_quality_tags")
        val dataQualityTags: List<String>? = null,

        val lc: String? = null,

        @get:JsonProperty("image_nutrition_url")@field:JsonProperty("image_nutrition_url")
        val imageNutritionURL: String? = null,

        @get:JsonProperty("categories_lc")@field:JsonProperty("categories_lc")
        val categoriesLc: String? = null,

        @get:JsonProperty("nucleotides_prev_tags")@field:JsonProperty("nucleotides_prev_tags")
        val nucleotidesPrevTags: List<Any?>? = null,

        @get:JsonProperty("countries_debug_tags")@field:JsonProperty("countries_debug_tags")
        val countriesDebugTags: List<Any?>? = null,

        @get:JsonProperty("last_modified_t")@field:JsonProperty("last_modified_t")
        val lastModifiedT: Long? = null,

        @get:JsonProperty("image_ingredients_thumb_url")@field:JsonProperty("image_ingredients_thumb_url")
        val imageIngredientsThumbURL: String? = null,

        @get:JsonProperty("ingredients_n")@field:JsonProperty("ingredients_n")
        val ingredientsN: Long? = null,

        @get:JsonProperty("debug_param_sorted_langs")@field:JsonProperty("debug_param_sorted_langs")
        val debugParamSortedLangs: List<String>? = null,

        @get:JsonProperty("ingredients_text")@field:JsonProperty("ingredients_text")
        val ingredientsText: String? = null,

        @get:JsonProperty("nutriscore_score")@field:JsonProperty("nutriscore_score")
        val nutriscoreScore: Long? = null,

        @get:JsonProperty("expiration_date")@field:JsonProperty("expiration_date")
        val expirationDate: String? = null,

        @get:JsonProperty("last_image_t")@field:JsonProperty("last_image_t")
        val lastImageT: Long? = null,

        @get:JsonProperty("ingredients_original_tags")@field:JsonProperty("ingredients_original_tags")
        val ingredientsOriginalTags: List<String>? = null,

        @get:JsonProperty("image_front_thumb_url")@field:JsonProperty("image_front_thumb_url")
        val imageFrontThumbURL: String? = null,

        @get:JsonProperty("countries_imported")@field:JsonProperty("countries_imported")
        val countriesImported: String? = null,

        @get:JsonProperty("additives_n")@field:JsonProperty("additives_n")
        val additivesN: Long? = null,

        val rev: Long? = null,

        @get:JsonProperty("traces_from_user")@field:JsonProperty("traces_from_user")
        val tracesFromUser: String? = null,

        @get:JsonProperty("amino_acids_tags")@field:JsonProperty("amino_acids_tags")
        val aminoAcidsTags: List<Any?>? = null,

        @get:JsonProperty("ingredients_text_with_allergens")@field:JsonProperty("ingredients_text_with_allergens")
        val ingredientsTextWithAllergens: String? = null,

        @get:JsonProperty("additives_old_tags")@field:JsonProperty("additives_old_tags")
        val additivesOldTags: List<String>? = null,

        val packaging: String? = null,

        @get:JsonProperty("ingredients_percent_analysis")@field:JsonProperty("ingredients_percent_analysis")
        val ingredientsPercentAnalysis: Long? = null,

        @get:JsonProperty("nutriscore_data")@field:JsonProperty("nutriscore_data")
        val nutriscoreData: NutriscoreData? = null,

        @get:JsonProperty("nucleotides_tags")@field:JsonProperty("nucleotides_tags")
        val nucleotidesTags: List<Any?>? = null,

        @get:JsonProperty("countries_hierarchy")@field:JsonProperty("countries_hierarchy")
        val countriesHierarchy: List<String>? = null,

        @get:JsonProperty("id")@field:JsonProperty("id")
        val productID: String? = null,

        @get:JsonProperty("labels_prev_tags")@field:JsonProperty("labels_prev_tags")
        val labelsPrevTags: List<String>? = null,

        @get:JsonProperty("nutrient_levels")@field:JsonProperty("nutrient_levels")
        val nutrientLevels: NutrientLevels? = null,

        @get:JsonProperty("max_imgid")@field:JsonProperty("max_imgid")
        val maxImgid: String? = null,

        @get:JsonProperty("product_name_en_imported")@field:JsonProperty("product_name_en_imported")
        val productNameEnImported: String? = null,

        @get:JsonProperty("packaging_tags")@field:JsonProperty("packaging_tags")
        val packagingTags: List<String>? = null,

        @get:JsonProperty("manufacturing_places")@field:JsonProperty("manufacturing_places")
        val manufacturingPlaces: String? = null,

        @get:JsonProperty("image_url")@field:JsonProperty("image_url")
        val imageURL: String? = null,

        @get:JsonProperty("ecoscore_data")@field:JsonProperty("ecoscore_data")
        val ecoscoreData: EcoscoreData? = null,

        @get:JsonProperty("emb_codes")@field:JsonProperty("emb_codes")
        val embCodes: String? = null,

        @get:JsonProperty("additives_original_tags")@field:JsonProperty("additives_original_tags")
        val additivesOriginalTags: List<String>? = null,

        @get:JsonProperty("serving_size_debug_tags")@field:JsonProperty("serving_size_debug_tags")
        val servingSizeDebugTags: List<Any?>? = null,

        @get:JsonProperty("ingredients_n_tags")@field:JsonProperty("ingredients_n_tags")
        val ingredientsNTags: List<String>? = null,

        @get:JsonProperty("ciqual_food_name_tags")@field:JsonProperty("ciqual_food_name_tags")
        val ciqualFoodNameTags: List<String>? = null,

        @get:JsonProperty("categories_properties_tags")@field:JsonProperty("categories_properties_tags")
        val categoriesPropertiesTags: List<String>? = null,

        @get:JsonProperty("created_t")@field:JsonProperty("created_t")
        val createdT: Long? = null,

        @get:JsonProperty("ingredients_text_with_allergens_en")@field:JsonProperty("ingredients_text_with_allergens_en")
        val ingredientsTextWithAllergensEn: String? = null,

        @get:JsonProperty("pnns_groups_2_tags")@field:JsonProperty("pnns_groups_2_tags")
        val pnnsGroups2_Tags: List<String>? = null,

        @get:JsonProperty("unknown_ingredients_n")@field:JsonProperty("unknown_ingredients_n")
        val unknownIngredientsN: Long? = null,

        val labels: String? = null,

        @get:JsonProperty("categories_imported")@field:JsonProperty("categories_imported")
        val categoriesImported: String? = null,

        @get:JsonProperty("nova_groups_tags")@field:JsonProperty("nova_groups_tags")
        val novaGroupsTags: List<String>? = null,

        @get:JsonProperty("ingredients_that_may_be_from_palm_oil_tags")@field:JsonProperty("ingredients_that_may_be_from_palm_oil_tags")
        val ingredientsThatMayBeFromPalmOilTags: List<Any?>? = null,

        val creator: String? = null,

        @get:JsonProperty("purchase_places_debug_tags")@field:JsonProperty("purchase_places_debug_tags")
        val purchasePlacesDebugTags: List<Any?>? = null,

        @get:JsonProperty("languages_codes")@field:JsonProperty("languages_codes")
        val languagesCodes: LanguagesCodes? = null,

        @get:JsonProperty("traces_tags")@field:JsonProperty("traces_tags")
        val tracesTags: List<String>? = null,

        @get:JsonProperty("additives_prev_original_tags")@field:JsonProperty("additives_prev_original_tags")
        val additivesPrevOriginalTags: List<String>? = null,

        @get:JsonProperty("interface_version_modified")@field:JsonProperty("interface_version_modified")
        val interfaceVersionModified: String? = null,

        @get:JsonProperty("unknown_nutrients_tags")@field:JsonProperty("unknown_nutrients_tags")
        val unknownNutrientsTags: List<Any?>? = null,

        @get:JsonProperty("categories_tags")@field:JsonProperty("categories_tags")
        val categoriesTags: List<String>? = null,

        @get:JsonProperty("packaging_debug_tags")@field:JsonProperty("packaging_debug_tags")
        val packagingDebugTags: List<Any?>? = null,

        @get:JsonProperty("category_properties")@field:JsonProperty("category_properties")
        val categoryProperties: CategoriesProperties? = null,

        @get:JsonProperty("ingredients_text_en_imported")@field:JsonProperty("ingredients_text_en_imported")
        val ingredientsTextEnImported: String? = null,

        @get:JsonProperty("new_additives_n")@field:JsonProperty("new_additives_n")
        val newAdditivesN: Long? = null,

        @get:JsonProperty("emb_codes_20141016")@field:JsonProperty("emb_codes_20141016")
        val embCodes20141016: String? = null,

        @get:JsonProperty("ingredients_that_may_be_from_palm_oil_n")@field:JsonProperty("ingredients_that_may_be_from_palm_oil_n")
        val ingredientsThatMayBeFromPalmOilN: Long? = null,

        @get:JsonProperty("nutrition_data_prepared_per")@field:JsonProperty("nutrition_data_prepared_per")
        val nutritionDataPreparedPer: String? = null,

        val informers: List<String>? = null,

        @get:JsonProperty("ingredients_tags")@field:JsonProperty("ingredients_tags")
        val ingredientsTags: List<String>? = null,

        @get:JsonProperty("image_front_small_url")@field:JsonProperty("image_front_small_url")
        val imageFrontSmallURL: String? = null,

        @get:JsonProperty("product_quantity")@field:JsonProperty("product_quantity")
        val productQuantity: Long? = null,

        val images: Images? = null,

        @get:JsonProperty("_id")@field:JsonProperty("_id")
        val id: String? = null,

        @get:JsonProperty("image_ingredients_url")@field:JsonProperty("image_ingredients_url")
        val imageIngredientsURL: String? = null,

        @get:JsonProperty("nutrition_data_prepared_per_debug_tags")@field:JsonProperty("nutrition_data_prepared_per_debug_tags")
        val nutritionDataPreparedPerDebugTags: List<Any?>? = null,

        val categories: String? = null,

        @get:JsonProperty("generic_name_en_debug_tags")@field:JsonProperty("generic_name_en_debug_tags")
        val genericNameEnDebugTags: List<Any?>? = null,

        val complete: Long? = null,

        @get:JsonProperty("labels_tags")@field:JsonProperty("labels_tags")
        val labelsTags: List<String>? = null,

        @get:JsonProperty("origins_tags")@field:JsonProperty("origins_tags")
        val originsTags: List<String>? = null,

        @get:JsonProperty("data_quality_errors_tags")@field:JsonProperty("data_quality_errors_tags")
        val dataQualityErrorsTags: List<Any?>? = null,

        @get:JsonProperty("popularity_tags")@field:JsonProperty("popularity_tags")
        val popularityTags: List<String>? = null,

        @get:JsonProperty("countries_tags")@field:JsonProperty("countries_tags")
        val countriesTags: List<String>? = null,

        @get:JsonProperty("image_nutrition_thumb_url")@field:JsonProperty("image_nutrition_thumb_url")
        val imageNutritionThumbURL: String? = null,

        @get:JsonProperty("expiration_date_debug_tags")@field:JsonProperty("expiration_date_debug_tags")
        val expirationDateDebugTags: List<Any?>? = null,

        @get:JsonProperty("ingredients_from_palm_oil_n")@field:JsonProperty("ingredients_from_palm_oil_n")
        val ingredientsFromPalmOilN: Long? = null,

        val nutriments: Nutriments? = null,

        @get:JsonProperty("ingredients_from_palm_oil_tags")@field:JsonProperty("ingredients_from_palm_oil_tags")
        val ingredientsFromPalmOilTags: List<Any?>? = null,

        @get:JsonProperty("editors_tags")@field:JsonProperty("editors_tags")
        val editorsTags: List<String>? = null,

        val photographers: List<String>? = null,

        @get:JsonProperty("pnns_groups_1")@field:JsonProperty("pnns_groups_1")
        val pnnsGroups1: String? = null,

        @get:JsonProperty("nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients")@field:JsonProperty("nutrition_score_warning_fruits_vegetables_nuts_estimate_from_ingredients")
        val nutritionScoreWarningFruitsVegetablesNutsEstimateFromIngredients: Long? = null,

        @get:JsonProperty("known_ingredients_n")@field:JsonProperty("known_ingredients_n")
        val knownIngredientsN: Long? = null,

        @get:JsonProperty("categories_hierarchy")@field:JsonProperty("categories_hierarchy")
        val categoriesHierarchy: List<String>? = null,

        @get:JsonProperty("completed_t")@field:JsonProperty("completed_t")
        val completedT: Long? = null,

        @get:JsonProperty("brand_owner_imported")@field:JsonProperty("brand_owner_imported")
        val brandOwnerImported: String? = null,

        @get:JsonProperty("nova_groups")@field:JsonProperty("nova_groups")
        val novaGroups: String? = null,

        @get:JsonProperty("data_sources")@field:JsonProperty("data_sources")
        val dataSources: String? = null,

        @get:JsonProperty("scans_n")@field:JsonProperty("scans_n")
        val scansN: Long? = null,

        @get:JsonProperty("brands_tags")@field:JsonProperty("brands_tags")
        val brandsTags: List<String>? = null,

        @get:JsonProperty("ecoscore_tags")@field:JsonProperty("ecoscore_tags")
        val ecoscoreTags: List<String>? = null,

        @get:JsonProperty("serving_quantity")@field:JsonProperty("serving_quantity")
        val servingQuantity: Long? = null,

        @get:JsonProperty("categories_old")@field:JsonProperty("categories_old")
        val categoriesOld: String? = null,

        @get:JsonProperty("labels_lc")@field:JsonProperty("labels_lc")
        val labelsLc: String? = null,

        @get:JsonProperty("nutrition_data_per_debug_tags")@field:JsonProperty("nutrition_data_per_debug_tags")
        val nutritionDataPerDebugTags: List<Any?>? = null,

        @get:JsonProperty("origins_old")@field:JsonProperty("origins_old")
        val originsOld: String? = null,

        @get:JsonProperty("selected_images")@field:JsonProperty("selected_images")
        val selectedImages: SelectedImages? = null,

        @get:JsonProperty("purchase_places")@field:JsonProperty("purchase_places")
        val purchasePlaces: String? = null,

        @get:JsonProperty("last_modified_by")@field:JsonProperty("last_modified_by")
        val lastModifiedBy: String? = null,

        @get:JsonProperty("link_debug_tags")@field:JsonProperty("link_debug_tags")
        val linkDebugTags: List<Any?>? = null,

        @get:JsonProperty("nova_group")@field:JsonProperty("nova_group")
        val novaGroup: Long? = null,

        @get:JsonProperty("vitamins_tags")@field:JsonProperty("vitamins_tags")
        val vitaminsTags: List<Any?>? = null,

        @get:JsonProperty("manufacturing_places_tags")@field:JsonProperty("manufacturing_places_tags")
        val manufacturingPlacesTags: List<Any?>? = null,

        @get:JsonProperty("lc_imported")@field:JsonProperty("lc_imported")
        val lcImported: String? = null,

        val lang: String? = null,

        @get:JsonProperty("compared_to_category")@field:JsonProperty("compared_to_category")
        val comparedToCategory: String? = null,

        @get:JsonProperty("emb_codes_tags")@field:JsonProperty("emb_codes_tags")
        val embCodesTags: List<Any?>? = null,

        val quantity: String? = null,

        @get:JsonProperty("data_sources_tags")@field:JsonProperty("data_sources_tags")
        val dataSourcesTags: List<String>? = null,

        @get:JsonProperty("quantity_debug_tags")@field:JsonProperty("quantity_debug_tags")
        val quantityDebugTags: List<Any?>? = null,

        val completeness: Double? = null,

        @get:JsonProperty("product_name")@field:JsonProperty("product_name")
        val productName: String? = null,

        @get:JsonProperty("sources_fields")@field:JsonProperty("sources_fields")
        val sourcesFields: SourcesFields? = null,

        @get:JsonProperty("stores_debug_tags")@field:JsonProperty("stores_debug_tags")
        val storesDebugTags: List<Any?>? = null,

        @get:JsonProperty("allergens_tags")@field:JsonProperty("allergens_tags")
        val allergensTags: List<Any?>? = null,

        val origins: String? = null,

        @get:JsonProperty("purchase_places_tags")@field:JsonProperty("purchase_places_tags")
        val purchasePlacesTags: List<Any?>? = null,

        @get:JsonProperty("image_nutrition_small_url")@field:JsonProperty("image_nutrition_small_url")
        val imageNutritionSmallURL: String? = null,

        @get:JsonProperty("data_quality_bugs_tags")@field:JsonProperty("data_quality_bugs_tags")
        val dataQualityBugsTags: List<Any?>? = null,

        @get:JsonProperty("additives_tags")@field:JsonProperty("additives_tags")
        val additivesTags: List<String>? = null,

        @get:JsonProperty("image_small_url")@field:JsonProperty("image_small_url")
        val imageSmallURL: String? = null
)

typealias CategoriesProperties = JsonNode

@JsonIgnoreProperties(ignoreUnknown = true)
data class EcoscoreData (
        val missing: Missing? = null,
        val adjustments: Adjustments? = null,

        @get:JsonProperty("missing_agribalyse_match_warning")@field:JsonProperty("missing_agribalyse_match_warning")
        val missingAgribalyseMatchWarning: Long? = null,

        val status: String? = null,
        val agribalyse: Agribalyse? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Adjustments (
        @get:JsonProperty("production_system")@field:JsonProperty("production_system")
        val productionSystem: CategoriesProperties? = null,

        @get:JsonProperty("origins_of_ingredients")@field:JsonProperty("origins_of_ingredients")
        val originsOfIngredients: OriginsOfIngredients? = null,

        @get:JsonProperty("threatened_species")@field:JsonProperty("threatened_species")
        val threatenedSpecies: CategoriesProperties? = null,

        val packaging: AdjustmentsPackaging? = null
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class OriginsOfIngredients (
        @get:JsonProperty("origins_from_origins_field")@field:JsonProperty("origins_from_origins_field")
        val originsFromOriginsField: List<String>? = null,

        @get:JsonProperty("epi_value")@field:JsonProperty("epi_value")
        val epiValue: Long? = null,

        @get:JsonProperty("transportation_score")@field:JsonProperty("transportation_score")
        val transportationScore: Long? = null,

        @get:JsonProperty("epi_score")@field:JsonProperty("epi_score")
        val epiScore: Long? = null,

        @get:JsonProperty("transportation_value")@field:JsonProperty("transportation_value")
        val transportationValue: Long? = null,

        val value: Long? = null,

        @get:JsonProperty("aggregated_origins")@field:JsonProperty("aggregated_origins")
        val aggregatedOrigins: List<AggregatedOrigin>? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AggregatedOrigin (
        val percent: Long? = null,
        val origin: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AdjustmentsPackaging (
        val value: Long? = null,
        val score: Long? = null,
        val warning: String? = null,
        val packagings: List<PackagingPackaging>? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class PackagingPackaging (
        val material: String? = null,

        @get:JsonProperty("ecoscore_material_score")@field:JsonProperty("ecoscore_material_score")
        val ecoscoreMaterialScore: Long? = null,

        val shape: String? = null,

        @get:JsonProperty("ecoscore_shape_ratio")@field:JsonProperty("ecoscore_shape_ratio")
        val ecoscoreShapeRatio: Long? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Agribalyse (
        val warning: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Missing (
        val packagings: Long? = null,

        @get:JsonProperty("agb_category")@field:JsonProperty("agb_category")
        val agbCategory: Long? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Images (
        @get:JsonProperty("1")@field:JsonProperty("1")
        val the1: The1? = null,

        @get:JsonProperty("2")@field:JsonProperty("2")
        val the2: The1? = null,

        @get:JsonProperty("3")@field:JsonProperty("3")
        val the3: The1? = null,

        @get:JsonProperty("4")@field:JsonProperty("4")
        val the4: The1? = null,

        @get:JsonProperty("5")@field:JsonProperty("5")
        val the5: The1? = null,

        @get:JsonProperty("ingredients_en")@field:JsonProperty("ingredients_en")
        val ingredientsEn: Ingredients? = null,

        @get:JsonProperty("front_en")@field:JsonProperty("front_en")
        val frontEn: FrontEnClass? = null,

        @get:JsonProperty("nutrition_en")@field:JsonProperty("nutrition_en")
        val nutritionEn: Ingredients? = null,

        val ingredients: Ingredients? = null,
        val front: FrontEnClass? = null,
        val nutrition: Ingredients? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class FrontEnClass (
        @get:JsonProperty("white_magic")@field:JsonProperty("white_magic")
        val whiteMagic: Any? = null,

        val sizes: FrontSizes? = null,
        val normalize: Any? = null,
        val imgid: String? = null,
        val geometry: String? = null,
        val rev: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class FrontSizes (
        @get:JsonProperty("100")@field:JsonProperty("100")
        val the100: The100? = null,

        @get:JsonProperty("200")@field:JsonProperty("200")
        val the200: The100? = null,

        @get:JsonProperty("400")@field:JsonProperty("400")
        val the400: The100? = null,

        val full: The100? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class The100 (
        val h: Long? = null,
        val w: Long? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Ingredients (
        @get:JsonProperty("white_magic")@field:JsonProperty("white_magic")
        val whiteMagic: Any? = null,

        val sizes: FrontSizes? = null,
        val normalize: String? = null,
        val imgid: String? = null,
        val geometry: String? = null,
        val rev: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class The1 (
        val sizes: The1_Sizes? = null,

        @get:JsonProperty("uploaded_t")@field:JsonProperty("uploaded_t")
        val uploadedT: Long? = null,

        val uploader: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class The1_Sizes (
        @get:JsonProperty("100")@field:JsonProperty("100")
        val the100: The100? = null,

        @get:JsonProperty("400")@field:JsonProperty("400")
        val the400: The100? = null,

        val full: The100? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Ingredient (
        @get:JsonProperty("percent_max")@field:JsonProperty("percent_max")
        val percentMax: Double? = null,

        @get:JsonProperty("has_sub_ingredients")@field:JsonProperty("has_sub_ingredients")
        val hasSubIngredients: HasSubIngredients? = null,

        @get:JsonProperty("percent_min")@field:JsonProperty("percent_min")
        val percentMin: Double? = null,

        @get:JsonProperty("percent_estimate")@field:JsonProperty("percent_estimate")
        val percentEstimate: Double? = null,

        val rank: Long? = null,
        val id: String? = null,
        val text: String? = null,
        val vegan: HasSubIngredients? = null,
        val vegetarian: HasSubIngredients? = null,
        val processing: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
enum class HasSubIngredients(val value: String) {
        Yes("yes");

        companion object {
                fun fromValue(value: String): HasSubIngredients = when (value) {
                        "yes" -> Yes
                        else  -> throw IllegalArgumentException()
                }
        }
}
@JsonIgnoreProperties(ignoreUnknown = true)
data class Languages (
        @get:JsonProperty("en:english")@field:JsonProperty("en:english")
        val enEnglish: Long? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class LanguagesCodes (
        val en: Long? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class NutrientLevels (
        @get:JsonProperty("saturated-fat")@field:JsonProperty("saturated-fat")
        val saturatedFat: String? = null,

        val sugars: String? = null,
        val salt: String? = null,
        val fat: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Nutriments (
        @get:JsonProperty("fiber_100g")@field:JsonProperty("fiber_100g")
        val fiber100G: Double? = null,

        @get:JsonProperty("vitamin-a_value")@field:JsonProperty("vitamin-a_value")
        val vitaminAValue: Long? = null,

        @get:JsonProperty("energy_100g")@field:JsonProperty("energy_100g")
        val energy100G: Long? = null,

        @get:JsonProperty("cholesterol_value")@field:JsonProperty("cholesterol_value")
        val cholesterolValue: Long? = null,

        @get:JsonProperty("salt_100g")@field:JsonProperty("salt_100g")
        val salt100G: Double? = null,

        @get:JsonProperty("calcium_100g")@field:JsonProperty("calcium_100g")
        val calcium100G: Double? = null,

        val cholesterol: Long? = null,

        @get:JsonProperty("energy_unit")@field:JsonProperty("energy_unit")
        val energyUnit: String? = null,

        @get:JsonProperty("saturated-fat_serving")@field:JsonProperty("saturated-fat_serving")
        val saturatedFatServing: Double? = null,

        @get:JsonProperty("proteins_serving")@field:JsonProperty("proteins_serving")
        val proteinsServing: Long? = null,

        @get:JsonProperty("carbohydrates_serving")@field:JsonProperty("carbohydrates_serving")
        val carbohydratesServing: Long? = null,

        @get:JsonProperty("iron_serving")@field:JsonProperty("iron_serving")
        val ironServing: Double? = null,

        @get:JsonProperty("iron_value")@field:JsonProperty("iron_value")
        val ironValue: Double? = null,

        @get:JsonProperty("cholesterol_100g")@field:JsonProperty("cholesterol_100g")
        val cholesterol100G: Long? = null,

        @get:JsonProperty("fat_100g")@field:JsonProperty("fat_100g")
        val fat100G: Double? = null,

        @get:JsonProperty("fiber_unit")@field:JsonProperty("fiber_unit")
        val fiberUnit: String? = null,

        @get:JsonProperty("fruits-vegetables-nuts-estimate-from-ingredients_100g")@field:JsonProperty("fruits-vegetables-nuts-estimate-from-ingredients_100g")
        val fruitsVegetablesNutsEstimateFromIngredients100G: Long? = null,

        @get:JsonProperty("sugars_100g")@field:JsonProperty("sugars_100g")
        val sugars100G: Double? = null,

        @get:JsonProperty("sugars_serving")@field:JsonProperty("sugars_serving")
        val sugarsServing: Long? = null,

        @get:JsonProperty("vitamin-a")@field:JsonProperty("vitamin-a")
        val vitaminA: Double? = null,

        @get:JsonProperty("sodium_unit")@field:JsonProperty("sodium_unit")
        val sodiumUnit: String? = null,

        @get:JsonProperty("sodium_serving")@field:JsonProperty("sodium_serving")
        val sodiumServing: Double? = null,

        @get:JsonProperty("vitamin-a_100g")@field:JsonProperty("vitamin-a_100g")
        val vitaminA100G: Double? = null,

        @get:JsonProperty("sugars_unit")@field:JsonProperty("sugars_unit")
        val sugarsUnit: String? = null,

        @get:JsonProperty("vitamin-c_serving")@field:JsonProperty("vitamin-c_serving")
        val vitaminCServing: Long? = null,

        @get:JsonProperty("salt_unit")@field:JsonProperty("salt_unit")
        val saltUnit: String? = null,

        @get:JsonProperty("vitamin-a_unit")@field:JsonProperty("vitamin-a_unit")
        val vitaminAUnit: String? = null,

        @get:JsonProperty("carbohydrates_value")@field:JsonProperty("carbohydrates_value")
        val carbohydratesValue: Double? = null,

        @get:JsonProperty("nutrition-score-fr_100g")@field:JsonProperty("nutrition-score-fr_100g")
        val nutritionScoreFr100G: Long? = null,

        @get:JsonProperty("energy-kcal_value")@field:JsonProperty("energy-kcal_value")
        val energyKcalValue: Long? = null,

        @get:JsonProperty("cholesterol_unit")@field:JsonProperty("cholesterol_unit")
        val cholesterolUnit: String? = null,

        @get:JsonProperty("energy_serving")@field:JsonProperty("energy_serving")
        val energyServing: Long? = null,

        @get:JsonProperty("nova-group_serving")@field:JsonProperty("nova-group_serving")
        val novaGroupServing: Long? = null,

        @get:JsonProperty("salt_value")@field:JsonProperty("salt_value")
        val saltValue: Long? = null,

        @get:JsonProperty("vitamin-c")@field:JsonProperty("vitamin-c")
        val vitaminC: Long? = null,

        @get:JsonProperty("energy-kcal_serving")@field:JsonProperty("energy-kcal_serving")
        val energyKcalServing: Long? = null,

        @get:JsonProperty("salt_serving")@field:JsonProperty("salt_serving")
        val saltServing: Double? = null,

        @get:JsonProperty("trans-fat_value")@field:JsonProperty("trans-fat_value")
        val transFatValue: Long? = null,

        val fat: Double? = null,

        @get:JsonProperty("vitamin-c_unit")@field:JsonProperty("vitamin-c_unit")
        val vitaminCUnit: String? = null,

        @get:JsonProperty("calcium_value")@field:JsonProperty("calcium_value")
        val calciumValue: Long? = null,

        @get:JsonProperty("cholesterol_serving")@field:JsonProperty("cholesterol_serving")
        val cholesterolServing: Long? = null,

        @get:JsonProperty("fat_value")@field:JsonProperty("fat_value")
        val fatValue: Double? = null,

        @get:JsonProperty("energy-kcal_100g")@field:JsonProperty("energy-kcal_100g")
        val energyKcal100G: Long? = null,

        val salt: Double? = null,

        @get:JsonProperty("sodium_100g")@field:JsonProperty("sodium_100g")
        val sodium100G: Double? = null,

        @get:JsonProperty("trans-fat_100g")@field:JsonProperty("trans-fat_100g")
        val transFat100G: Long? = null,

        @get:JsonProperty("proteins_value")@field:JsonProperty("proteins_value")
        val proteinsValue: Double? = null,

        val carbohydrates: Double? = null,

        @get:JsonProperty("iron_100g")@field:JsonProperty("iron_100g")
        val iron100G: Double? = null,

        @get:JsonProperty("energy-kcal_unit")@field:JsonProperty("energy-kcal_unit")
        val energyKcalUnit: String? = null,

        @get:JsonProperty("nova-group")@field:JsonProperty("nova-group")
        val novaGroup: Long? = null,

        @get:JsonProperty("trans-fat")@field:JsonProperty("trans-fat")
        val transFat: Long? = null,

        @get:JsonProperty("carbohydrates_unit")@field:JsonProperty("carbohydrates_unit")
        val carbohydratesUnit: String? = null,

        @get:JsonProperty("vitamin-a_serving")@field:JsonProperty("vitamin-a_serving")
        val vitaminAServing: Double? = null,

        @get:JsonProperty("sugars_value")@field:JsonProperty("sugars_value")
        val sugarsValue: Double? = null,

        val sodium: Double? = null,

        @get:JsonProperty("nutrition-score-fr")@field:JsonProperty("nutrition-score-fr")
        val nutritionScoreFr: Long? = null,

        val calcium: Double? = null,

        @get:JsonProperty("proteins_unit")@field:JsonProperty("proteins_unit")
        val proteinsUnit: String? = null,

        @get:JsonProperty("energy_value")@field:JsonProperty("energy_value")
        val energyValue: Long? = null,

        @get:JsonProperty("saturated-fat_unit")@field:JsonProperty("saturated-fat_unit")
        val saturatedFatUnit: String? = null,

        @get:JsonProperty("fat_serving")@field:JsonProperty("fat_serving")
        val fatServing: Long? = null,

        @get:JsonProperty("fat_unit")@field:JsonProperty("fat_unit")
        val fatUnit: String? = null,

        @get:JsonProperty("vitamin-c_100g")@field:JsonProperty("vitamin-c_100g")
        val vitaminC100G: Long? = null,

        @get:JsonProperty("carbohydrates_100g")@field:JsonProperty("carbohydrates_100g")
        val carbohydrates100G: Double? = null,

        @get:JsonProperty("saturated-fat_value")@field:JsonProperty("saturated-fat_value")
        val saturatedFatValue: Double? = null,

        @get:JsonProperty("iron_unit")@field:JsonProperty("iron_unit")
        val ironUnit: String? = null,

        val iron: Double? = null,

        @get:JsonProperty("energy-kcal")@field:JsonProperty("energy-kcal")
        val energyKcal: Long? = null,

        @get:JsonProperty("calcium_unit")@field:JsonProperty("calcium_unit")
        val calciumUnit: String? = null,

        val fiber: Double? = null,

        @get:JsonProperty("nova-group_100g")@field:JsonProperty("nova-group_100g")
        val novaGroup100G: Long? = null,

        @get:JsonProperty("calcium_serving")@field:JsonProperty("calcium_serving")
        val calciumServing: Double? = null,

        @get:JsonProperty("fiber_value")@field:JsonProperty("fiber_value")
        val fiberValue: Double? = null,

        @get:JsonProperty("sodium_value")@field:JsonProperty("sodium_value")
        val sodiumValue: Long? = null,

        val sugars: Double? = null,

        @get:JsonProperty("fiber_serving")@field:JsonProperty("fiber_serving")
        val fiberServing: Double? = null,

        @get:JsonProperty("saturated-fat_100g")@field:JsonProperty("saturated-fat_100g")
        val saturatedFat100G: Double? = null,

        @get:JsonProperty("trans-fat_unit")@field:JsonProperty("trans-fat_unit")
        val transFatUnit: String? = null,

        val proteins: Double? = null,

        @get:JsonProperty("proteins_100g")@field:JsonProperty("proteins_100g")
        val proteins100G: Double? = null,

        @get:JsonProperty("trans-fat_serving")@field:JsonProperty("trans-fat_serving")
        val transFatServing: Long? = null,

        @get:JsonProperty("saturated-fat")@field:JsonProperty("saturated-fat")
        val saturatedFat: Double? = null,

        @get:JsonProperty("vitamin-c_value")@field:JsonProperty("vitamin-c_value")
        val vitaminCValue: Long? = null,

        val energy: Long? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class NutriscoreData (
        @get:JsonProperty("sodium_points")@field:JsonProperty("sodium_points")
        val sodiumPoints: Long? = null,

        @get:JsonProperty("is_fat")@field:JsonProperty("is_fat")
        val isFat: Long? = null,

        @get:JsonProperty("is_cheese")@field:JsonProperty("is_cheese")
        val isCheese: Long? = null,

        @get:JsonProperty("saturated_fat_ratio_value")@field:JsonProperty("saturated_fat_ratio_value")
        val saturatedFatRatioValue: Long? = null,

        @get:JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils_points")@field:JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils_points")
        val fruitsVegetablesNutsColzaWalnutOliveOilsPoints: Long? = null,

        @get:JsonProperty("energy_value")@field:JsonProperty("energy_value")
        val energyValue: Long? = null,

        val grade: String? = null,
        val score: Long? = null,

        @get:JsonProperty("saturated_fat")@field:JsonProperty("saturated_fat")
        val saturatedFat: Double? = null,

        @get:JsonProperty("saturated_fat_value")@field:JsonProperty("saturated_fat_value")
        val saturatedFatValue: Double? = null,

        val sodium: Long? = null,

        @get:JsonProperty("sugars_value")@field:JsonProperty("sugars_value")
        val sugarsValue: Double? = null,

        @get:JsonProperty("saturated_fat_ratio")@field:JsonProperty("saturated_fat_ratio")
        val saturatedFatRatio: Double? = null,

        @get:JsonProperty("is_water")@field:JsonProperty("is_water")
        val isWater: Long? = null,

        @get:JsonProperty("proteins_value")@field:JsonProperty("proteins_value")
        val proteinsValue: Double? = null,

        @get:JsonProperty("saturated_fat_points")@field:JsonProperty("saturated_fat_points")
        val saturatedFatPoints: Long? = null,

        @get:JsonProperty("proteins_points")@field:JsonProperty("proteins_points")
        val proteinsPoints: Long? = null,

        @get:JsonProperty("energy_points")@field:JsonProperty("energy_points")
        val energyPoints: Long? = null,

        @get:JsonProperty("is_beverage")@field:JsonProperty("is_beverage")
        val isBeverage: Long? = null,

        @get:JsonProperty("saturated_fat_ratio_points")@field:JsonProperty("saturated_fat_ratio_points")
        val saturatedFatRatioPoints: Long? = null,

        val energy: Long? = null,

        @get:JsonProperty("positive_points")@field:JsonProperty("positive_points")
        val positivePoints: Long? = null,

        val proteins: Double? = null,

        @get:JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils")@field:JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils")
        val fruitsVegetablesNutsColzaWalnutOliveOils: Long? = null,

        @get:JsonProperty("sodium_value")@field:JsonProperty("sodium_value")
        val sodiumValue: Long? = null,

        val sugars: Double? = null,

        @get:JsonProperty("fiber_value")@field:JsonProperty("fiber_value")
        val fiberValue: Double? = null,

        @get:JsonProperty("sugars_points")@field:JsonProperty("sugars_points")
        val sugarsPoints: Long? = null,

        val fiber: Double? = null,

        @get:JsonProperty("fiber_points")@field:JsonProperty("fiber_points")
        val fiberPoints: Long? = null,

        @get:JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils_value")@field:JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils_value")
        val fruitsVegetablesNutsColzaWalnutOliveOilsValue: Long? = null,

        @get:JsonProperty("negative_points")@field:JsonProperty("negative_points")
        val negativePoints: Long? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductPackaging (
        val material: String? = null,
        val shape: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class SelectedImages (
        val ingredients: IngredientsClass? = null,
        val front: IngredientsClass? = null,
        val nutrition: IngredientsClass? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class IngredientsClass (
        val display: Display? = null,
        val small: Display? = null,
        val thumb: Display? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Display (
        val en: String? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Source (
        val images: List<Any?>? = null,
        val url: String? = null,
        val fields: List<String>? = null,

        @get:JsonProperty("import_t")@field:JsonProperty("import_t")
        val importT: Long? = null,

        val id: String? = null,
        val name: String? = null,
        val manufacturer: Any? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class SourcesFields (
        @get:JsonProperty("org-database-usda")@field:JsonProperty("org-database-usda")
        val orgDatabaseUsda: OrgDatabaseUsda? = null
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class OrgDatabaseUsda (
        @get:JsonProperty("publication_date")@field:JsonProperty("publication_date")
        val publicationDate: String? = null,

        @get:JsonProperty("fdc_data_source")@field:JsonProperty("fdc_data_source")
        val fdcDataSource: String? = null,

        @get:JsonProperty("available_date")@field:JsonProperty("available_date")
        val availableDate: String? = null,

        @get:JsonProperty("fdc_id")@field:JsonProperty("fdc_id")
        val fdcID: String? = null,

        @get:JsonProperty("modified_date")@field:JsonProperty("modified_date")
        val modifiedDate: String? = null,

        @get:JsonProperty("fdc_category")@field:JsonProperty("fdc_category")
        val fdcCategory: String? = null
)
