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
  }
};
