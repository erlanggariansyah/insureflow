CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) CHECK (role IN ('AGENT', 'UNDERWRITER', 'ADMIN', 'MANAGER')),
    full_name VARCHAR(100) NOT NULL,
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE agents (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID UNIQUE REFERENCES users(id),
    agent_code VARCHAR(20) UNIQUE NOT NULL,
    license_number VARCHAR(50),
    hierarchy_level VARCHAR(20) DEFAULT 'AGENT',
    joined_date DATE DEFAULT CURRENT_DATE
);

CREATE TABLE customers (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) CHECK (gender IN ('MALE', 'FEMALE')),
    id_number VARCHAR(50) UNIQUE,
    address TEXT,
    city VARCHAR(50),
    postal_code VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_code VARCHAR(20) UNIQUE NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    type VARCHAR(20) CHECK (type IN ('TERM_LIFE', 'WHOLE_LIFE', 'UNIT_LINK', 'ENDOWMENT')),
    description TEXT,
    min_sum_assured DECIMAL(15,2) DEFAULT 10000000,
    max_sum_assured DECIMAL(15,2) DEFAULT 10000000000,
    min_age INT DEFAULT 18,
    max_age INT DEFAULT 65,
    is_active BOOLEAN DEFAULT true
);

CREATE TABLE product_rates (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id UUID REFERENCES products(id),
    age_from INT NOT NULL,
    age_to INT NOT NULL,
    gender VARCHAR(10),
    rate_per_1000 DECIMAL(10,6) NOT NULL,
    UNIQUE(product_id, age_from, age_to, gender)
);

CREATE TABLE quotations (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    quotation_number VARCHAR(30) UNIQUE NOT NULL,
    customer_id UUID REFERENCES customers(id),
    product_id UUID REFERENCES products(id),
    agent_id UUID REFERENCES users(id),
    sum_assured DECIMAL(15,2) NOT NULL,
    premium_amount DECIMAL(15,2) NOT NULL,
    policy_term INT NOT NULL,
    premium_mode VARCHAR(20) CHECK (premium_mode IN ('MONTHLY', 'QUARTERLY', 'SEMI_ANNUAL', 'ANNUAL')),
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'CONVERTED', 'EXPIRED')),
    valid_until DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE proposals (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    proposal_number VARCHAR(30) UNIQUE NOT NULL,
    quotation_id UUID UNIQUE REFERENCES quotations(id),
    customer_id UUID REFERENCES customers(id),
    product_id UUID REFERENCES products(id),
    agent_id UUID REFERENCES users(id),
    sum_assured DECIMAL(15,2) NOT NULL,
    premium_amount DECIMAL(15,2) NOT NULL,
    status VARCHAR(30) DEFAULT 'SUBMITTED' CHECK (status IN ('SUBMITTED', 'MEDICAL_REQUIRED', 'UNDERWRITING', 'APPROVED', 'REJECTED')),
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE medical_exams (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    proposal_id UUID REFERENCES proposals(id),
    exam_type VARCHAR(50) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'SCHEDULED', 'COMPLETED', 'WAIVED')),
    result TEXT,
    scheduled_date DATE,
    completed_date DATE
);

CREATE TABLE risk_assessments (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    proposal_id UUID UNIQUE REFERENCES proposals(id),
    risk_level VARCHAR(20) CHECK (risk_level IN ('LOW', 'MEDIUM', 'HIGH')),
    risk_score DECIMAL(3,2),
    assessed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE underwriting_decisions (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    proposal_id UUID UNIQUE REFERENCES proposals(id),
    decided_by UUID REFERENCES users(id),
    decision VARCHAR(20) CHECK (decision IN ('APPROVED', 'REJECTED', 'POSTPONED', 'REFERRED')),
    reason TEXT,
    decided_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE policies (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    policy_number VARCHAR(30) UNIQUE NOT NULL,
    proposal_id UUID UNIQUE REFERENCES proposals(id),
    customer_id UUID REFERENCES customers(id),
    product_id UUID REFERENCES products(id),
    agent_id UUID REFERENCES users(id),
    sum_assured DECIMAL(15,2) NOT NULL,
    premium_amount DECIMAL(15,2) NOT NULL,
    policy_term INT NOT NULL,
    premium_mode VARCHAR(20),
    status VARCHAR(20) DEFAULT 'INFORCE' CHECK (status IN ('INFORCE', 'LAPSED', 'PAID_UP', 'MATURED', 'SURRENDERED', 'CLAIMED')),
    issue_date DATE NOT NULL,
    start_date DATE NOT NULL,
    maturity_date DATE NOT NULL,
    next_premium_due DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE endorsements (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    policy_id UUID REFERENCES policies(id),
    type VARCHAR(50) NOT NULL CHECK (type IN ('ADDRESS_CHANGE', 'BENEFICIARY_CHANGE', 'SUM_ASSURED_INCREASE', 'PREMIUM_MODE_CHANGE', 'NAME_CHANGE')),
    old_value JSONB,
    new_value JSONB,
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED')),
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    approved_at TIMESTAMP
);

CREATE TABLE premium_payments (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    policy_id UUID REFERENCES policies(id),
    amount DECIMAL(15,2) NOT NULL,
    payment_mode VARCHAR(20),
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'PAID', 'OVERDUE', 'WAIVED')),
    due_date DATE NOT NULL,
    payment_date DATE
);

CREATE TABLE loans (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    policy_id UUID UNIQUE REFERENCES policies(id),
    loan_amount DECIMAL(15,2) NOT NULL,
    interest_rate DECIMAL(5,2) DEFAULT 6.00,
    loan_date DATE DEFAULT CURRENT_DATE,
    repayment_date DATE,
    status VARCHAR(20) DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'REPAID', 'OUTSTANDING'))
);

CREATE TABLE surrenders (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    policy_id UUID UNIQUE REFERENCES policies(id),
    surrender_date DATE DEFAULT CURRENT_DATE,
    surrender_value DECIMAL(15,2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING' CHECK (status IN ('PENDING', 'APPROVED', 'REJECTED', 'COMPLETED'))
);

CREATE INDEX idx_quotations_customer ON quotations(customer_id);
CREATE INDEX idx_quotations_agent ON quotations(agent_id);
CREATE INDEX idx_proposals_status ON proposals(status);
CREATE INDEX idx_policies_customer ON policies(customer_id);
CREATE INDEX idx_policies_status ON policies(status);
CREATE INDEX idx_premium_payments_due ON premium_payments(due_date);