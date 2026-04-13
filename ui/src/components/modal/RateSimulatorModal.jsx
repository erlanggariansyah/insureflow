import React, { useState } from "react";
import { createPortal } from "react-dom";
import { productService } from "services/productService";
import { MdPlayCircleOutline, MdQueryStats, MdClose } from "react-icons/md";

export default function RateSimulatorModal({ isOpen, onClose, products, genders }) {
  const [formData, setFormData] = useState({
    productId: "",
    gender: "",
    age: ""
  });
  const [loading, setLoading] = useState(false);
  const [result, setResult] = useState(null);

  if (!isOpen) return null;

  const handleLookup = async () => {
    if (!formData.productId || !formData.gender || !formData.age) {
      alert("Please fill all fields to simulate.");
      return;
    }

    setLoading(true);
    try {
      const res = await productService.lookupRate(
        formData.productId,
        formData.age,
        formData.gender
      );
      if (res.data === null || res.data === undefined) {
        alert("No matching rate found in the database.");
      }
      setResult(res.data);
    } catch (err) {
      console.error(err);
      alert("Lookup failed: Could not reach the actuarial engine.");
      setResult(null);
    } finally {
      setLoading(false);
    }
  };

  return createPortal(
    <div className="fixed inset-0 z-[100] flex items-center justify-center p-4">
      {/* Backdrop */}
      <div
        className="absolute inset-0 bg-navy-900/60 backdrop-blur-[4px]"
        onClick={onClose}
      />

      {/* Modal Content */}
      <div className="relative w-full max-w-md animate-fadeIn rounded-3xl bg-white shadow-2xl dark:bg-navy-800">

        {/* Header */}
        <div className="flex items-center justify-between border-b border-gray-100 p-6 dark:border-navy-700">
          <div className="flex items-center gap-3">
            <div className="flex h-10 w-10 items-center justify-center rounded-xl bg-emerald-50 text-emerald-500 dark:bg-emerald-900/30">
              <MdPlayCircleOutline className="h-6 w-6" />
            </div>
            <div>
              <h3 className="text-lg font-bold text-navy-700 dark:text-white">Rate Simulator</h3>
            </div>
          </div>
          <button
            onClick={onClose}
            className="rounded-full p-2 text-gray-400 hover:bg-gray-50 hover:text-navy-700 dark:hover:bg-navy-700 dark:hover:text-white"
          >
            <MdClose className="h-5 w-5" />
          </button>
        </div>

        {/* Body */}
        <div className="p-6 space-y-6">
          <div className="space-y-4">
            {/* Product Select */}
            <div>
              <label className="mb-2 block text-xs font-bold uppercase tracking-widest text-gray-400">Product</label>
              <select
                className="w-full rounded-xl border border-gray-200 bg-white p-3 text-sm text-navy-700 focus:border-blue-500 focus:outline-none dark:border-navy-600 dark:bg-navy-900 dark:text-white"
                value={formData.productId}
                onChange={e => setFormData({ ...formData, productId: e.target.value })}
              >
                <option value="">Select base product</option>
                {products.map(p => <option key={p.id} value={p.id}>{p.name}</option>)}
              </select>
            </div>

            <div className="grid grid-cols-2 gap-4">
              {/* Gender Select */}
              <div>
                <label className="mb-2 block text-xs font-bold uppercase tracking-widest text-gray-400">Gender</label>
                <select
                  className="w-full rounded-xl border border-gray-200 bg-white p-3 text-sm text-navy-700 focus:border-blue-500 focus:outline-none dark:border-navy-600 dark:bg-navy-900 dark:text-white"
                  value={formData.gender}
                  onChange={e => setFormData({ ...formData, gender: e.target.value })}
                >
                  <option value="">Gender</option>
                  {genders.map(g => <option key={g.id} value={g.id}>{g.name}</option>)}
                </select>
              </div>

              {/* Age Input */}
              <div>
                <label className="mb-2 block text-xs font-bold uppercase tracking-widest text-gray-400">Age</label>
                <input
                  type="number"
                  placeholder="e.g. 35"
                  className="w-full rounded-xl border border-gray-200 bg-white p-3 text-sm text-navy-700 focus:border-blue-500 focus:outline-none dark:border-navy-600 dark:bg-navy-900 dark:text-white"
                  value={formData.age}
                  onChange={e => setFormData({ ...formData, age: e.target.value })}
                />
              </div>
            </div>
          </div>

          {/* Result Area */}
          {result !== null && (
            <div className="overflow-hidden rounded-2xl bg-gradient-to-br from-blue-600 to-blue-400 p-6 text-white shadow-xl shadow-blue-500/30">
              <div className="flex items-center justify-between">
                <div>
                  <p className="text-[10px] font-black uppercase tracking-[0.2em] opacity-80">Calculated Rate</p>
                  <p className="text-4xl font-black">{Number(result).toFixed(6)}</p>
                </div>
                <MdQueryStats className="h-12 w-12 opacity-30" />
              </div>
              <div className="mt-4 border-t border-white/20 pt-3">
                <p className="text-[9px] italic opacity-80">
                  *This rate is retrieved via Real-time API.
                </p>
              </div>
            </div>
          )}
        </div>

        {/* Footer */}
        <div className="flex items-center justify-end gap-3 rounded-b-3xl bg-gray-50/50 p-6 dark:bg-white/5">
          <button
            onClick={onClose}
            className="rounded-xl px-5 py-2.5 text-sm font-bold text-gray-400 transition hover:text-navy-700 dark:hover:text-white"
          >
            Cancel
          </button>
          <button
            onClick={handleLookup}
            disabled={loading}
            className="flex items-center gap-2 rounded-xl bg-gradient-to-r from-blue-600 to-blue-400 px-8 py-2.5 text-sm font-bold text-white shadow-lg shadow-blue-500/30 transition-all hover:scale-[1.02] hover:shadow-blue-500/50 active:scale-95 disabled:opacity-50"
          >
            {loading ? "Calculating..." : "Run"}
          </button>
        </div>
      </div>
    </div>,
    document.body
  );
}

