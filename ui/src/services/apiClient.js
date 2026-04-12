const BASE_URL = process.env.REACT_APP_API_PRODUCT_SERVICE_BASE_URL || "http://localhost:8088/product-service/api/v1";

export const apiClient = async (endpoint, options = {}) => {
  const url = `${BASE_URL}${endpoint}`;

  const defaultHeaders = {
    "Content-Type": "application/json",
    Accept: "application/json",
    Authorization: "Bearer xxx"
  };

  const config = {
    ...options,
    headers: {
      ...defaultHeaders,
      ...options.headers,
    },
  };

  try {
    const response = await fetch(url, config);
    if (!response.ok) {
      const errorData = await response.json().catch(() => ({}));
      throw new Error(errorData.message || `API request failed with status ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error(`Error in apiClient for ${endpoint}:`, error);
    throw error;
  }
};
