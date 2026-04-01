import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import AdminLayout from "layouts/admin";
import AuthLayout from "layouts/auth";

const App = () => {
  return (
    <Routes>
      <Route path="auth/*" element={<AuthLayout />} />
      <Route path="console/*" element={<AdminLayout />} />
      <Route path="/" element={<Navigate to="/console" replace />} />
    </Routes>
  );
};

export default App;
