import React from "react";

// Admin Imports
import ProductView from "views/admin/product";

// Auth Imports
import SignIn from "views/auth/SignIn";

// Icon Imports
import {
  MdHome, MdAdminPanelSettings, MdPeople, MdRequestQuote,
  MdOutlinePolicy, MdPayment, MdGavel, MdListAlt, MdLock,
  MdShield, MdTrendingUp, MdRule, MdSecurity, MdDashboard,
  MdPieChart, MdOutlineAccountBalance
} from "react-icons/md";

// Note: Unimplemented views return simple placeholders.
const MockView = ({ title }) => (
  <div className="flex h-full w-full items-center justify-center p-10">
    <div className="text-center">
      <h2 className="text-3xl font-extrabold text-navy-700 dark:text-white uppercase tracking-widest opacity-20">{title}</h2>
      <p className="text-gray-400 mt-2 font-bold tracking-widest">MODULE COMING SOON</p>
    </div>
  </div>
);

const routes = [
  {
    name: "Dashboard",
    layout: "/console",
    path: "dashboard",
    icon: <MdHome className="h-5 w-5" />,
    component: <MockView title="Dashboard" />,
  },
  {
    name: "Master Data",
    isCategory: true,
  },
  {
    name: "Canvas",
    layout: "/console",
    path: "default", /* Canvas remains default path */
    icon: <MdDashboard className="h-5 w-5" />,
    component: <ProductView />,
  },
  {
    name: "Products",
    layout: "/console",
    path: "products",
    icon: <MdShield className="h-5 w-5" />,
    component: <MockView title="Products" />,
  },
  {
    name: "Riders",
    layout: "/console",
    path: "riders",
    icon: <MdAdminPanelSettings className="h-5 w-5" />,
    component: <MockView title="Riders" />,
  },
  {
    name: "Rates",
    layout: "/console",
    path: "rates",
    icon: <MdTrendingUp className="h-5 w-5" />,
    component: <MockView title="Rates" />,
  },
  {
    name: "Rules",
    layout: "/console",
    path: "rules",
    icon: <MdRule className="h-5 w-5" />,
    component: <MockView title="Rules" />,
  },
  {
    name: "Customers",
    isCategory: true,
  },
  {
    name: "Customers",
    layout: "/console",
    path: "customers",
    icon: <MdPeople className="h-5 w-5" />,
    component: <MockView title="Customers" />,
  },
  {
    name: "Agents",
    layout: "/console",
    path: "agents",
    icon: <MdPeople className="h-5 w-5" />,
    component: <MockView title="Agents" />,
  },
  {
    name: "New Business",
    isCategory: true,
  },
  {
    name: "Quotations",
    layout: "/console",
    path: "quotations",
    icon: <MdRequestQuote className="h-5 w-5" />,
    component: <MockView title="Quotations" />,
  },
  {
    name: "Proposals",
    layout: "/console",
    path: "proposals",
    icon: <MdListAlt className="h-5 w-5" />,
    component: <MockView title="Proposals" />,
  },
  {
    name: "Underwriting",
    layout: "/console",
    path: "underwriting",
    icon: <MdGavel className="h-5 w-5" />,
    component: <MockView title="Underwriting" />,
  },
  {
    name: "Policies",
    isCategory: true,
  },
  {
    name: "Policies",
    layout: "/console",
    path: "policies",
    icon: <MdOutlinePolicy className="h-5 w-5" />,
    component: <MockView title="Policies" />,
  },
  {
    name: "Endorsements",
    layout: "/console",
    path: "endorsements",
    icon: <MdOutlinePolicy className="h-5 w-5" />,
    component: <MockView title="Endorsements" />,
  },
  {
    name: "Loans",
    layout: "/console",
    path: "loans",
    icon: <MdPayment className="h-5 w-5" />,
    component: <MockView title="Loans" />,
  },
  {
    name: "Surrenders",
    layout: "/console",
    path: "surrenders",
    icon: <MdPayment className="h-5 w-5" />,
    component: <MockView title="Surrenders" />,
  },
  {
    name: "Investment (Unit Link)",
    isCategory: true,
  },
  {
    name: "Funds",
    layout: "/console",
    path: "funds",
    icon: <MdOutlineAccountBalance className="h-5 w-5" />,
    component: <MockView title="Funds" />,
  },
  {
    name: "NAV / NAB",
    layout: "/console",
    path: "nav",
    icon: <MdTrendingUp className="h-5 w-5" />,
    component: <MockView title="NAV / NAB" />,
  },
  {
    name: "Unit Holdings",
    layout: "/console",
    path: "unit-holdings",
    icon: <MdPieChart className="h-5 w-5" />,
    component: <MockView title="Unit Holdings" />,
  },
  {
    name: "Transactions",
    layout: "/console",
    path: "unit-transactions",
    icon: <MdListAlt className="h-5 w-5" />,
    component: <MockView title="Transactions" />,
  },
  {
    name: "Cash Value",
    layout: "/console",
    path: "cash-value",
    icon: <MdPayment className="h-5 w-5" />,
    component: <MockView title="Cash Value" />,
  },
  {
    name: "Billing",
    isCategory: true,
  },
  {
    name: "Premium Payments",
    layout: "/console",
    path: "premium-payments",
    icon: <MdPayment className="h-5 w-5" />,
    component: <MockView title="Premium Payments" />,
  },
  {
    name: "Claims",
    isCategory: true,
  },
  {
    name: "Claims",
    layout: "/console",
    path: "claims",
    icon: <MdListAlt className="h-5 w-5" />,
    component: <MockView title="Claims" />,
  },
  {
    name: "Audit",
    isCategory: true,
  },
  {
    name: "Logs",
    layout: "/console",
    path: "logs",
    icon: <MdListAlt className="h-5 w-5" />,
    component: <MockView title="Logs" />,
  },
  {
    name: "System",
    isCategory: true,
  },
  {
    name: "Users",
    layout: "/console",
    path: "users",
    icon: <MdPeople className="h-5 w-5" />,
    component: <MockView title="Users" />,
  },
  {
    name: "Roles",
    layout: "/console",
    path: "roles",
    icon: <MdSecurity className="h-5 w-5" />,
    component: <MockView title="Roles" />,
  },
  {
    name: "Sign In",
    layout: "/auth",
    path: "sign-in",
    icon: <MdLock className="h-6 w-6" />,
    component: <SignIn />,
    hideInSidebar: true
  }
];

export default routes;
