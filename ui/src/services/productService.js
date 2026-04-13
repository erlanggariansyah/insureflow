import { apiClient } from "./apiClient";

export const productService = {
  getProductTypes: async () => {
    return apiClient("/products/types", { method: "GET" });
  },

  getProductsByType: async (productTypeId) => {
    return apiClient(`/products?productTypeId=${productTypeId}`, { method: "GET" });
  },

  getRidersByProduct: async (productId) => {
    return apiClient(`/products/riders?productId=${productId}`, { method: "GET" });
  },

  syncProductConfig: async (payload) => {
    return apiClient("/products/sync", { method: "POST", body: JSON.stringify(payload) });
  },

  getAllProducts: async () => {
    return apiClient("/products/all", { method: "GET" });
  },

  getAllRiders: async () => {
    return apiClient("/products/riders/all", { method: "GET" });
  },

  getAllRates: async () => {
    return apiClient("/products/rates/all", { method: "GET" });
  },

  getAllRules: async () => {
    return apiClient("/products/rules/all", { method: "GET" });
  },

  getAllRiderTypes: async () => {
    return apiClient("/products/rider-types", { method: "GET" });
  },

  getAllGenders: async () => {
    return apiClient("/products/genders", { method: "GET" });
  },

  getAllPaymentFrequencies: async () => {
    return apiClient("/products/payment-frequencies", { method: "GET" });
  },

  getAllRuleTypes: async () => {
    return apiClient("/products/rule-types", { method: "GET" });
  },

  lookupRate: async (productId, age, gender) => {
    return apiClient(`/products/rates/lookup?productId=${productId}&age=${age}&gender=${gender}`, { method: "GET" });
  }
};
