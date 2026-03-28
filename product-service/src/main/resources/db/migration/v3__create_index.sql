CREATE INDEX idx_product_rates_lookup ON product_rates(product_id, age, gender);
CREATE INDEX idx_product_rates_active ON product_rates(is_active) WHERE is_active = true;
CREATE INDEX idx_products_type ON products(product_type_id);
CREATE INDEX idx_products_active ON products(is_active);
